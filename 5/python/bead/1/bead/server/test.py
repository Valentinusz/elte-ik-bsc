import unittest
import uuid

from automation_system import AutomationSystem, NoSuchDevice
from device import Light, Thermostat, Camera


# Since the default devices use pydantic most of their methods are not tested (since unit testing is not about testing
# other peoples code)


class TestDevice(unittest.TestCase):
    def test_toggle(self):
        device = Light()

        previous_value = device.on

        device.toggle()

        self.assertEqual(device.on, not previous_value)

    def test_eq(self):
        device_id = uuid.uuid4()

        device1 = Light(device_id=device_id)
        device2 = Light(device_id=device_id)

        self.assertTrue(device1 == device2)

    def test_device_type(self):
        light = Light()
        thermostat = Thermostat()
        camera = Camera()

        self.assertEqual('light', light.device_type)
        self.assertEqual('thermostat', thermostat.device_type)
        self.assertEqual('camera', camera.device_type)

    def test_thermostat_update(self):
        thermostat = Thermostat(on=False, target_temperature=25, observed_temperature=20)

        # check if temperature decreases when the device is off
        previous_value = thermostat.observed_temperature

        thermostat.update()

        self.assertLessEqual(thermostat.observed_temperature, previous_value)

        # check if temperature increases when the device is on
        thermostat.on = True

        previous_value = thermostat.observed_temperature

        thermostat.update()

        self.assertGreaterEqual(thermostat.observed_temperature, previous_value)

    # camera is too random to be tested

    def test_light_update(self):
        light = Light(on=False, automatic_switching=True)

        # Test if light switches on automatically
        light.observed_light_level = 25
        light.update()

        self.assertTrue(light.on)

        # Test if light switches of automatically
        light.observed_light_level = 75
        light.update()

        self.assertFalse(light.on)

        # Test if automatic switch is disabled when automatic is false
        light.automatic_switching = False
        light.observed_light_level = 25


class TestAutomationSystem(unittest.TestCase):
    def test_find_devices(self):
        automation_system = AutomationSystem()

        unpaired_device_count = len(automation_system.unpaired_devices)

        automation_system.find_devices()

        self.assertGreater(len(automation_system.unpaired_devices), unpaired_device_count)

    def test_pair_device(self):
        a_sys = AutomationSystem()

        self.assertRaises(NoSuchDevice, lambda: a_sys.pair_device(uuid.uuid4()))

        a_sys.find_devices()

        previous_unpaired_length = len(a_sys.unpaired_devices)
        previous_paired_length = len(a_sys.devices)

        self.assertTrue(a_sys.pair_device(a_sys.unpaired_devices[0].device_id))
        self.assertEqual(previous_unpaired_length - 1, len(a_sys.unpaired_devices))
        self.assertEqual(previous_paired_length + 1, len(a_sys.devices))

    def test_unpair_device(self):
        a_sys = AutomationSystem()

        self.assertRaises(NoSuchDevice, lambda: a_sys.unpair_device(uuid.uuid4()))

        a_sys.find_devices()
        a_sys.pair_device(a_sys.unpaired_devices[0].device_id)

        previous_unpaired_length = len(a_sys.unpaired_devices)
        previous_paired_length = len(a_sys.devices)

        self.assertTrue(a_sys.unpair_device(a_sys.devices[0].device_id))

        self.assertEqual(previous_unpaired_length + 1, len(a_sys.unpaired_devices))
        self.assertEqual(previous_paired_length - 1, len(a_sys.devices))

    def test_get_devices(self):
        a_sys = AutomationSystem()
        a_sys.find_devices()

        count = 0
        for device in a_sys.unpaired_devices:
            a_sys.pair_device(device.device_id)
            count += 1

        self.assertEqual(len(a_sys.get_devices()), count)

    def test_avg_temp(self):
        a_sys = AutomationSystem()
        self.assertEqual(a_sys.avg_temperature(), 'N/A')

        a_sys.devices = [
            Thermostat(observed_temperature=15),
            Thermostat(observed_temperature=20),
            Thermostat(observed_temperature=25)
        ]

        self.assertEqual(a_sys.avg_temperature(), 20)

    def test_security_alerts(self):
        a_sys = AutomationSystem()

        a_sys.devices = [
            Camera(motion_detected=True),
            Camera(motion_detected=True),
            Camera(motion_detected=False)
        ]

        self.assertEqual(len(a_sys.security_alerts()), 2)

    def test_automation_start(self):
        a_sys = AutomationSystem()
        a_sys.start()

        self.assertTrue(a_sys.is_alive())



if __name__ == '__main__':
    unittest.main()
