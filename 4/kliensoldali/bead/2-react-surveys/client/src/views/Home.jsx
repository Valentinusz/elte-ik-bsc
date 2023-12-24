import {Container, Typography} from '@mui/material';

/**
 * Home view of the app.
 * @returns {JSX.Element}
 * @constructor
 */
export function Home() {
    return (
        <Container height={800} style={{textAlign: 'center', alignContent: 'center', alignItems: 'center'}}>
            <Typography variant='h1'>Kérdőívek</Typography>
            <Typography variant='h2'>Kérdőív szerkesztő és kitöltő</Typography>
        </Container>
    );
}