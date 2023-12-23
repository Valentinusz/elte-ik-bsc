import { Stack, Typography } from '@mui/material';
import { useGetAverageTemperatureQuery } from '../../state/api/statisticsApiSlice';

export function StastisticsPane() {
    const {data} = useGetAverageTemperatureQuery(null, {
        pollingInterval: 5000,
        refetchOnFocus: true
    });

    return (
        <Stack>
            <Typography variant='h3'>Statistics</Typography>
            <Typography variant='h4' align='center'>Average temperature: {data ?? 'N/A'}°C</Typography>
        </Stack>
    );
}