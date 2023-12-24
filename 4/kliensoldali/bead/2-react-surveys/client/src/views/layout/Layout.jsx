import {Navbar} from './Navbar.jsx';
import {Outlet} from 'react-router-dom';
import {Container} from '@mui/material';

export function Layout() {
    return (
        <>
            <Navbar/>
            <Container component='main'>
                <Outlet/>
            </Container>
        </>
    );
}
