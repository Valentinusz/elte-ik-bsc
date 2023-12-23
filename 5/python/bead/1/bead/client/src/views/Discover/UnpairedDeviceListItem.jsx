import { ListItem, ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import { usePairDeviceMutation } from '../../state/api/deviceApiSlice';
import { DeviceIcon } from '../Device/DeviceIcon';

export function UnpairedDeviceListItem({device}) {
    const [pairDevice, {isLoading}] = usePairDeviceMutation();

    return (
        <ListItem>
            <ListItemButton onClick={() => pairDevice(device.device_id)}>
                <ListItemIcon><DeviceIcon type={device.type}/></ListItemIcon>
                <ListItemText primary={device.name} secondary={isLoading ? 'pairing...' : device.device_id}></ListItemText>
            </ListItemButton>
        </ListItem>
    );
}