import {Alert, Stack, TextField, Typography} from '@mui/material';
import {useNavigate} from 'react-router-dom';
import {useRegisterMutation} from '../../state/api/authApiSlice.js';
import {useState} from 'react';
import {LoadingButton} from '@mui/lab';

/**
 * Registration form component.
 * @returns {JSX.Element}
 * @constructor
 */
export function Register() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({fullname: '', email: '', password: ''});
    const [register, {error, isError, isLoading}] = useRegisterMutation();

    const handleInput = event => {
        setFormData({...formData, [event.target.name]: event.target.value});
    };

    const handleSubmit = async event => {
        event.preventDefault();

        try {
            await register({...formData}).unwrap();
            navigate('/login');
        } catch (e) {
            console.error(e.status);
        }
    };

    return (
        <Stack component='form' spacing={4} onSubmit={handleSubmit}>
            <Typography variant='h2' component='h1' style={{marginTop: '4rem', textAlign: 'center'}}>
                Fiók létrehozása
            </Typography>

            {isError && <Alert severity='error'>Sikertelen Regisztráció! Hibakód: {error.status}.</Alert>}

            <TextField label='Név' name='fullname' required onInput={handleInput} value={formData.fullname}/>

            <TextField label='Email cím' name='email' type='email' required
                       onInput={handleInput} value={formData.email}
            />

            <TextField label='Jelszó' name='password' type='password' required
                       onInput={handleInput} value={formData.password}
            />

            <LoadingButton type='submit' variant='contained' loading={isLoading}
                           style={{marginLeft: 'auto', marginRight: 'auto', width: '40%'}}
            >
                Regisztáció
            </LoadingButton>
        </Stack>
    );
}