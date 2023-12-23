import { AppBar, Button, Toolbar, Typography } from '@mui/material';
import { NavLink } from 'react-router-dom';

export function NavBar() {
    return (
        <AppBar position='sticky'>
            <Toolbar>
                <Typography variant='h6' sx={{ marginRight: '2rem' }}>IOT</Typography>
                <Button variant='outline' component={NavLink} to='/'>
                    <Typography variant='h6'>Dashboard</Typography>
                </Button>
                <Button variant='outline' component={NavLink} to='discover'>
                    <Typography variant='h6'>Discover devices</Typography>
                </Button>
            </Toolbar>
        </AppBar>
    );
}