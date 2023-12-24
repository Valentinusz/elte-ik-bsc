import {Avatar, Button, Skeleton, Stack, Typography} from '@mui/material';
import {useDispatch, useSelector} from 'react-redux';
import {logout, selectUserEmail, selectUserId, selectUserName} from '../state/authSlice.js';
import {stringAvatar} from '../services/stringAvatar.js';
import {useGetSurveyCountQuery} from '../state/api/surveyApiSlice.js';
import {useNavigate} from 'react-router-dom';

/**
 * Profile component.
 * @returns {JSX.Element}
 * @constructor
 */
export function Profile() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const user = {email: useSelector(selectUserEmail), name: useSelector(selectUserName)};
    const {data: surveyCount, isLoading} = useGetSurveyCountQuery(useSelector(selectUserId));

    const handleLogout = () => {
        dispatch(logout());
        navigate('/');
    };

    return (
        <Stack spacing={2} alignItems='center' style={{marginTop: '4rem'}}>
            <Avatar {...stringAvatar(user.name)} />
            <Typography variant='h3' as='h1'>{user.name}</Typography>
            <Typography variant='h5' as='h2'>{user.email}</Typography>
            <Typography variant='h5' as='h2'>
                {isLoading ? <Skeleton></Skeleton> : <>{surveyCount} kérdőív</>}
            </Typography>
            <Button variant='contained' color='error' onClick={handleLogout}>Kijelentkezés</Button>
        </Stack>
    );
}