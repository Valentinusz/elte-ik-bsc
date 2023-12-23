import logging

import statistics
import time
from threading import Thread
from uuid import UUID

from device import Device, Light, Camera, Thermostat
from random import randint


class NoSuchDevice(Exception):
    """ Exception thrown when a device of the supplied ID does not exist in the database. """
    pass


class AutomationSystem(Thread):
    """
    Class for coordinating smart devices.

    Updates the status every five seconds through the run() method, after the start() method is called on an instance.

    Essentially acts as a rudimentary database supporting CRUD operations.
    """

    def __init__(self):
        """
        Constructor.
        """

        Thread.__init__(self)

        self.devices: list[Device] = []
        """ Devices managed by the automation system. """

        self.unpaired_devices: list[Device] = []
        """ Devices discovered, but not managed by the automation system. """

    def run(self):
        """
        Calls the update_services() method every 5 seconds. Overrides threading.Thread.run().
        """
        logger = logging.getLogger('uvicorn.info')

        while True:
            self.update_devices()
            logger.info(f'Updated {len(self.devices)} devices [{time.strftime("%H:%M:%S", time.localtime())}]')
            time.sleep(5)

    def find_devices(self) -> list[Device]:
        """
        Discovers new devices, adding them to the unpaired_devices list.

        Returns
        -------
        list
            The updated list of unpaired devices.
        """

        for _ in range(randint(1, 2)):
            match (randint(0, 2)):
                case 0:
                    self.unpaired_devices.append(Light())
                case 1:
                    self.unpaired_devices.append(Camera())
                case 2:
                    self.unpaired_devices.append(Thermostat())

        return self.unpaired_devices

    def pair_device(self, device_id: UUID) -> bool:
        """
        Attempts to pair a discovered device (from unpaired_Devices), by id.

        Parameters
        ----------
        device_id: UUID
            Unique identifier of the device.

        Returns
        -------
        bool
            True if the device was successfully paired.

        Raises
        ------
        NoSuchDevice
            if a device with the supplied id doesn't exist.
        """

        try:
            device_to_pair = next(item for item in self.unpaired_devices if item.device_id == device_id)
            self.unpaired_devices.remove(device_to_pair)
            self.devices.append(device_to_pair)

            return True
        except StopIteration:
            raise NoSuchDevice()

    def unpair_device(self, device_id: UUID) -> bool:
        """
        Attempts to unpair a device by id.

        Parameters
        ----------
        device_id: UUID
            Unique identifier of the device.

        Returns
        -------
        bool
            True if the device was successfully unpaired.

        Raises
        ------
        NoSuchDevice
            if a device with the supplied id doesn't exist.
        """

        try:
            device_to_unpair = next(item for item in self.devices if item.device_id == device_id)
            self.devices.remove(device_to_unpair)
            self.unpaired_devices.append(device_to_unpair)

            return True
        except StopIteration:
            raise NoSuchDevice()

    def get_devices(self) -> list[Device]:
        """ Getter. Returns the list of paired devices. """

        return self.devices

    def get_unpaired_devices(self) -> list[Device]:
        """ Getter. Returns a list of unpaired devices. """
        return self.unpaired_devices

    def get_device_by_id(self, device_id: UUID) -> Device:
        """
        Locates a device by its id.

        Parameters
        ----------
        device_id: UUID
            Unique identifier of the device.

        Returns
        -------
        Device

        Raises
        ------
        NoSuchDevice
            if no device with the supplied id is paired.
        """
        try:
            device_to_find = next(item for item in self.devices if item.device_id == device_id)

            return device_to_find
        except StopIteration:
            raise NoSuchDevice()

    def update_devices(self):
        """ Updates all paired devices based on automation rules. """
        for device in self.devices:
            device.update()

    def avg_temperature(self) -> float|str:
        """
        Calculates the average temperature based on the thermometers inside the system.

        Returns
        -------
        float
            average temperature
        """

        return round(statistics.mean(
            map(lambda device: device.observed_temperature,
                filter(lambda device: isinstance(device, Thermostat), self.devices))
        ), 2) if len(self.devices) > 0 else 'N/A'

    def security_alerts(self) -> list[Device]:
        """
        Returns a list of security logs read from security devices.

        Returns
        -------
        list[Device]
            security logs
        """

        return [device for device in self.devices if isinstance(device, Camera) and device.motion_detected]
