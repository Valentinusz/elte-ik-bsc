from abc import ABC, abstractmethod
from random import randint, randrange
from uuid import uuid4
from typing import Annotated, final

from pydantic import BaseModel, Field, UUID4, computed_field

from utils import generate_ip


class Device(ABC, BaseModel):
    """ Pydantic model representing an IOT device. """

    name: Annotated[str, Field(default='Smart device')]
    """ Name of the device. """

    on: Annotated[bool, Field(default=False)]
    """ True if the given device is on. Otherwise False. """

    ip: Annotated[str, Field(default_factory=lambda: next(generate_ip()))]
    """ Current IPv4 address of the device. """

    device_id: Annotated[UUID4, Field(default_factory=lambda: uuid4())]
    """ Unique identifier of the device. """

    @computed_field(alias='type')
    @final
    def device_type(self) -> str:
        """
        Computed field containing the type of object as a string, allowing discrimination of device types. The type is
        calculated by converting the name of the class to lowercase.

        Returns
        -------
        str
            a string used in the json serialization of the object.
        """
        return self.__class__.__name__.lower()

    def toggle(self) -> bool:
        """
        Switches the device on/off.

        Returns
        -------
        bool
            new status of the device
        """
        self.on = not self.on
        return self.on

    def __eq__(self, other) -> bool:
        """
        Defines equality between devices.

        Returns
        -------
        bool
            true if both devices have the same UUID.
        bool
            false otherwise
        """
        return self.device_id == other.device_id

    @abstractmethod
    def update(self):
        """ Updates the status of the device. """
        pass


class Camera(Device):
    """ Pydantic model representing an IOT camera device. """

    zoom_amount: Annotated[float, Field(ge=0.0, le=1.0, default=0.0)]
    """ Zoom level of the camera. """

    motion_detected: Annotated[bool, Field(default=False)]
    """ True if motion is detected by the camera. """

    def update(self):
        if self.on:
            if randint(1, 50) == 1:
                self.motion_detected = True
            else:
                self.motion_detected = False


class Thermostat(Device):
    """ Pydantic model representing an IOT thermostat device. """

    target_temperature: Annotated[float, Field(ge=5, le=30, default=22)]
    """ Target temperature of the device. The device operates heating until the desired temperature is reached. """

    observed_temperature: Annotated[float, Field(default_factory=lambda: randint(10, 15))]
    """ Temperature recorded by the device. """

    def update(self):
        if self.on and self.target_temperature > self.observed_temperature:
            self.observed_temperature = min((self.observed_temperature + randrange(0, 2) / 10), 30)
        elif not self.on:
            self.observed_temperature = max((self.observed_temperature - randrange(0, 2) / 10), 5)


class Light(Device):
    """ Pydantic model representing an IOT light device. """

    automatic_switching: bool = True
    """ Represents whether the light should automatically turn on if the light level is sufficiently low. """

    observed_light_level: Annotated[int, Field(ge=0, le=100, default_factory=lambda: randint(0, 100))]
    """ Light level detected by the device. """

    def update(self):
        if self.automatic_switching:
            if self.observed_light_level < 50:
                self.on = True
            else:
                self.on = False
