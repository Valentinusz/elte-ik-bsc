from typing import Annotated
from uuid import UUID

from fastapi import FastAPI, HTTPException, Body
from fastapi.middleware.cors import CORSMiddleware

from automation_system import AutomationSystem, NoSuchDevice


# instantiate FastAPI
app = FastAPI()

# allow CORS for localhost
app.add_middleware(
    CORSMiddleware,
    allow_origin_regex=r'https?:\/\/localhost:\d{1,5}',
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

automation_system = AutomationSystem()


@app.get('/devices')
async def get_all_devices():
    """ Route that returns all paired devices. """
    return automation_system.get_devices()


@app.get('/devices/unpaired')
async def get_all_unpaired_devices():
    """ Route that returns all devices that have not been paired but are discovered. """
    return automation_system.get_unpaired_devices()


@app.get('/statistics/avg_temp')
async def get_avg_temp():
    """ Route for querying statistics from the system. """
    return automation_system.avg_temperature()


@app.get('/security/alerts')
async def get_avg_temp():
    """ Route for showing security alerts. """
    return automation_system.avg_temperature()


@app.post('/devices/discover', status_code=201)
async def discover_devices():
    """ Route for initiating search for new devices. """
    return automation_system.find_devices()


@app.get('/devices/{device_id}')
async def get_device_by_id(device_id: UUID):
    """
    Route for getting a device by its UUID.

    Parameters
    ----------
    device_id: UUID
        id of the device

    Raises
    ------
    HTTPException
        404 Not Found, if a device with the given id doesn't exist.
    HTTPException(422)
        if the request is missing the device_id field or its value cannot be resolved to a UUID.
    """
    try:
        return automation_system.get_device_by_id(device_id)
    except NoSuchDevice:
        raise HTTPException(status_code=404)


@app.patch("/devices/{device_id}/toggle")
async def toggle_device_by_id(device_id: UUID):
    """
    Patch route for switching a given device on/off.

    Parameters
    ----------
    device_id: UUID
        id of the device

    Raises
    ------
    HTTPException(404)
        if a device with the given id doesn't exist.
    HTTPException(422)
        if the request is missing the device_id field or its value cannot be resolved to a UUID.
    """
    try:
        return automation_system.get_device_by_id(device_id).toggle()
    except NoSuchDevice:
        raise HTTPException(status_code=404, detail=f"Device of id: {device_id} doesn't exist.")


@app.post('/devices')
async def pair_device_by_id(device_id: Annotated[UUID, Body(..., embed=True)]):
    """
    Post route for pairing a device by its UUID.

    Parameters
    ----------
    device_id: UUID
        id of the device

    Raises
    ------
    HTTPException(404)
        if a device with the given id doesn't exist.
    HTTPException(422)
        if the request is missing the device_id field or its value cannot be resolved to a UUID.
    """
    try:
        return automation_system.pair_device(device_id)
    except NoSuchDevice:
        raise HTTPException(status_code=404, detail=f"Device of id: {device_id} doesn't exist.")


@app.delete('/devices/{device_id}')
async def unpair_device_by_id(device_id: UUID):
    """
    Delete route for unpairing a device by its UUID.

    Parameters
    ----------
    device_id: UUID
        id of the device

    Raises
    ------
    HTTPException(404)
        if a device with the given id doesn't exist.
    HTTPException(422)
        if the request is missing the device_id field or its value cannot be resolved to a UUID.
    """
    try:
        return automation_system.unpair_device(device_id)
    except NoSuchDevice:
        raise HTTPException(status_code=404, detail=f"Device of id: {device_id} doesn't exist.")


# start automation thread
automation_system.start()
