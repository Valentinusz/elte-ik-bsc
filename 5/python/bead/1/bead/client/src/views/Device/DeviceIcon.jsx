import VideocamRoundedIcon from '@mui/icons-material/VideocamRounded';
import LightbulbRoundedIcon from '@mui/icons-material/LightbulbRounded';
import DeviceThermostatRoundedIcon from '@mui/icons-material/DeviceThermostatRounded';
import DeviceUnknownRoundedIcon from '@mui/icons-material/DeviceUnknownRounded';

export function DeviceIcon({type}) {
    switch(type) {
        case 'camera':
            return <VideocamRoundedIcon/>;
        case 'light':
            return <LightbulbRoundedIcon/>;
        case 'thermostat':
            return <DeviceThermostatRoundedIcon/>;
        default:
            return <DeviceUnknownRoundedIcon/>;
    }
}