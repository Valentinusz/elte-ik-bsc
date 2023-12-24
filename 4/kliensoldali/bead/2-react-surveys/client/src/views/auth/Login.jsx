import {Alert, Stack, TextField, Typography} from '@mui/material';
import {useLoginMutation} from '../../state/api/authApiSlice.js';
import {useLocation, useNavigate} from 'react-router-dom';
import {useDispatch} from 'react-redux';
import {useState} from 'react';
import {login} from '../../state/authSlice.js';
import {LoadingButton} from '@mui/lab';

/**
 * Login view component.
 * @returns {JSX.Element}
 * @constructor
 */
export function Login() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    /** @type {{state: {redirected: boolean}}} */
    const location = useLocation();

    const [formData, setFormData] = useState({email: '', password: ''});
    const [apiLogin, {error, isLoading, isError}] = useLoginMutation();

    const handleInput = event => {
        setFormData({...formData, [event.target.name]: event.target.value});
    };

    const handleLogin = async(event) => {
        event.preventDefault();

        try {
            /** @type {{user: {id: number, name: string, email: string}, accessToken: string}} */
            const response = await apiLogin({
                strategy: 'local',
                email: formData.email,
                password: formData.password
            }).unwrap();

            dispatch(
                login({
                    id: response.user.id,
                    name: response.user.fullname,
                    email: response.user.email,
                    token: response.accessToken
                })
            );

            return navigate('/');
        } catch (e) {
            console.error(e.status);
        }
    };

    return (
        <Stack component='form' spacing={4} onSubmit={handleLogin}>
            <Typography variant='h2' component='h1' style={{marginTop: '4rem', textAlign: 'center'}}>
                Bejelentkezés
            </Typography>

            {location?.state?.redirected && <Alert severity='error'>A funkció eléréséhez be kell jelentkezned!</Alert>}

            {isError && <Alert severity='error'>Sikertelen bejelentkezés! Hibakód: {error.status}.</Alert>}

            <TextField label='E-mail cím' name='email' type='email' required
                       value={formData.email ?? ''} onInput={handleInput}
            />

            <TextField label='Jelszó' name='password' required
                       value={formData.password ?? ''} onInput={handleInput}
            />

            <LoadingButton type='submit'
                           variant='contained' style={{marginLeft: 'auto', marginRight: 'auto', width: '40%'}}
                           loading={isLoading}
            >
                Bejelentkezés
            </LoadingButton>
        </Stack>
    );
}