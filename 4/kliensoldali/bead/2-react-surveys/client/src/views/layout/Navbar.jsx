import {AppBar, Button, Menu, MenuItem, Toolbar, Typography} from '@mui/material';
import {NavLink} from 'react-router-dom';
import {useState} from 'react';
import {logout, selectIsAuthenticated, selectUserName} from '../../state/authSlice.js';
import {useDispatch, useSelector} from 'react-redux';

export function Navbar() {
    const dispatch = useDispatch();

    const isAuthenticated = useSelector(selectIsAuthenticated);
    const username = useSelector(selectUserName);

    const [anchorElement, setAnchorElement] = useState(null);
    const isOpen = anchorElement != null;

    const handleClose = () => setAnchorElement(null);

    return (
        <AppBar position='sticky'>
            <Toolbar>
                <Button variant='outline' component={NavLink} to='/'><Typography variant='h6'>Kérdőívek</Typography></Button>
                {!isAuthenticated &&
                    <>
                        <Button variant='outline' component={NavLink} to='/login' style={{marginLeft: 'auto'}}>Bejelentkezés</Button>
                        <Button variant='outline' component={NavLink} to='/register'>Regisztráció</Button>
                    </>
                }
                {isAuthenticated &&
                    <>
                        <Button variant='outline' component={NavLink} to='/surveys'>Kérdőíveim</Button>
                        <Button style={{marginLeft: 'auto'}} variant='outline'
                                aria-controls={open ? 'basic-menu' : undefined} aria-haspopup='true'
                                aria-expanded={open ? 'true' : undefined}
                                onClick={event => setAnchorElement(event.target)}
                        >
                            {username}
                        </Button>
                        <Menu open={isOpen} anchorEl={anchorElement} onClose={handleClose}>
                            <MenuItem component={NavLink} to='/profile'>Profil</MenuItem>
                            <MenuItem onClick={() => {dispatch(logout()); handleClose();}}>Kijelentkezés</MenuItem>
                        </Menu>
                    </>
                }
            </Toolbar>
        </AppBar>
    );
}
