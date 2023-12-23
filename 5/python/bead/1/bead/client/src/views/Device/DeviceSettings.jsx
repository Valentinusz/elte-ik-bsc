import { Skeleton, Stack, IconButton, FormGroup, FormControlLabel, Switch, TextField, ButtonGroup, Typography } from '@mui/material';
import { useGetDeviceByIdQuery } from '../../state/api/deviceApiSlice';
import Thermostat from './Thermostat';
import Camera from './Camera';
import Light from './Light';
import LinkOffRoundedIcon from '@mui/icons-material/LinkOffRounded';
import { useParams } from 'react-router-dom';
import { RefreshRounded } from '@mui/icons-material';

export function DeviceSettings() {
    const { id } = useParams();

    const {data: device, isLoading} = useGetDeviceByIdQuery(id);

    console.log(device);

    let details;

    if (isLoading) {
        details = <Skeleton/>;
    } else {
        switch(device.type) {
            case 'thermostat':
                details = <Thermostat/>;
                break;
            case 'camera':
                details = <Camera/>;
                break;
            case 'light':
                details = <Light/>;
                break;
            default:
                details = <Skeleton/>;
        }
    }

    return (
        <Stack component={FormGroup}>
            
            <Stack direction='row' justifyContent='space-between' flexGrow='0'>
                {isLoading ? <Skeleton/> : <Typography variant='h2'>Device Settings</Typography>}
                <ButtonGroup>
                    <IconButton edge='end' title='Refresh'><RefreshRounded/></IconButton>
                    <IconButton edge='end' title='Unpair device'><LinkOffRoundedIcon/></IconButton>
                </ButtonGroup>
            </Stack>
            <TextField label="Name" variant="standard"/>
            
            <FormControlLabel control={<Switch defaultChecked />} label="ON/OFF" />
            {details}
        </Stack>
    );
}