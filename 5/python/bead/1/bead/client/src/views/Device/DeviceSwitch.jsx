import { Switch } from '@mui/material';
import { useToggleDeviceMutation } from '../../state/api/deviceApiSlice';

export function DeviceSwitch({device_id, on}) {
    const [toggleDevice, {isLoading}] = useToggleDeviceMutation();

    return <Switch disabled={isLoading} defaultChecked={on} title='Toggle device' onClick={() => toggleDevice(device_id)}/>;
}