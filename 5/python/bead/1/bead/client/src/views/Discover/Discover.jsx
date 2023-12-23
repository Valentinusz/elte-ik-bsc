import { List, Skeleton, Stack, Typography } from '@mui/material';
import { useDiscoverDevicesMutation, useGetUnpairedDevicesQuery } from '../../state/api/deviceApiSlice';
import { LoadingButton } from '@mui/lab';
import { Search } from '@mui/icons-material';
import { UnpairedDeviceListItem } from './UnpairedDeviceListItem';

export function Discover() {
    const {data: devices, isLoading: isQueryLoading} = useGetUnpairedDevicesQuery();
    const [discoverDevices, {isLoading: isDiscoverLoading}] = useDiscoverDevicesMutation();

    console.log(devices);
    console.log(isQueryLoading);

    return (
        <Stack>
            <Stack direction='row'>
                <LoadingButton variant='contained' startIcon={<Search/>} loading={isDiscoverLoading} onClick={discoverDevices}>
                    <Typography variant='h6'>Locate devices</Typography>
                </LoadingButton>
            </Stack>
            {isQueryLoading ? <Skeleton/> : <UnpairedDevices devices={devices}/>}
        </Stack>
    );
}

/**
 * 
 * @param {Object} props 
 * @param {{device_id: string, type: string}[]} props.devices
 * @returns 
 */
function UnpairedDevices({devices}) {
    if (devices.length === 0) {
        return (
            <Typography variant='h3' align='center'>No device detected</Typography>
        );
    } else {
        return (
            <List>
                {devices.map((device, index) => <UnpairedDeviceListItem key={index} device={device}/>)}
            </List>
        );
    }
}