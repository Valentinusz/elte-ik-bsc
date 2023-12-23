import {useGetPairedDevicesQuery} from '../state/api/deviceApiSlice';
import {Button, Container, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Skeleton, Stack, Typography} from '@mui/material';
import { NavLink } from 'react-router-dom';
import { DeviceIcon } from './Device/DeviceIcon';
import { StastisticsPane } from './Statistics/StatisticsPane';
import { DeviceSwitch } from './Device/DeviceSwitch';

export function Devices() {
    const {data: devices, isLoading} = useGetPairedDevicesQuery(null, {pollingInterval: 5000});

    console.log(devices);

    return (
        <>
            <Typography variant='h2'>Dashboard</Typography>
            <Container>
                <Stack direction='row' justifyContent='space-between'>
                    <Stack>
                        <Typography variant='h3'>Alerts</Typography>
                    </Stack>
                    <StastisticsPane/>
                </Stack>

                <Stack>
                    <Typography variant='h3'>My devices</Typography>
                    {isLoading ? <Skeleton/> : <DeviceList devices={devices}/>}
                </Stack>
            </Container>
        </>
    );
}


function DeviceList({devices}) {
    if (devices.length === 0) {
        return (
            <Stack>
                <Typography variant='h3' align='center'>No devices found</Typography>
                <Button variant='text' component={NavLink} to='discover' align='center'>
                    <Typography variant='h6'>Unpaired devices</Typography>
                </Button> 
            </Stack>
        );
    } else {
        return (
            <List>
                {devices.map((device, index) => (
                    <ListItem key={index} disablePadding
                        secondaryAction={<DeviceSwitch device_id={device.device_id} on={device.on}></DeviceSwitch>}
                    >
                        <ListItemButton component={NavLink} to={`device/${device.device_id}`}>
                            <ListItemIcon><DeviceIcon type={device.type}/></ListItemIcon>
                            <ListItemText primary={`${device.name} / ${device.ip}`} secondary={deviceDetails(device)}/>
                        </ListItemButton>
                    </ListItem>
                ))}
            </List>
        );
    }
}

function deviceDetails(device) {
    switch(device.type) {
        case 'thermostat':
            return `Observed: ${device.observed_temperature.toFixed(2)}°C`;
        case 'camera':
            return `Zoom: ${device.zoom_amount.toFixed(2)}x`;
        case 'light':
            return device.automatic_switching ? 'Automatic mode' : 'Manual mode';
        default:
            return '';
    }
}