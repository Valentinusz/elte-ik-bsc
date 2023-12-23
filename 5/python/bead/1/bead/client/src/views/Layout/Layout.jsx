import {Outlet} from 'react-router-dom';
import {Container} from '@mui/material';
import { NavBar } from './NavBar';

export function Layout() {
    return (
        <>
            <NavBar/>
            <Container component='main'>
                <Outlet/>
            </Container>
        </>
    );
}
