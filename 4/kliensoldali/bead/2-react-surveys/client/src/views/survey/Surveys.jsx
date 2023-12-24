import {
    Alert, Button, Container, IconButton, Link, Snackbar, Stack, Table, TableBody, TableCell, TableHead, TableRow,
    Typography, Skeleton, Tooltip,
} from '@mui/material';

import {Add, Analytics, ContentCopy, Delete,} from '@mui/icons-material';
import {NavLink, useLocation} from 'react-router-dom';
import {useSuccessSnackbar} from '../hooks/useSuccessSnackbar.js';
import {useDeleteSurveyMutation, useGetAllSurveysQuery} from '../../state/api/surveyApiSlice.js';
import {useSelector} from 'react-redux';
import {selectUserId} from '../../state/authSlice.js';
import {useSnackbar} from '../hooks/useSnackbar.js';

/**
 * Component displaying an array of surveys.
 * @returns {JSX.Element}
 * @constructor
 */
export function Surveys() {
    const {state} = useLocation();
    const {isOpen: isSuccessSnackbarOpen, close: closeSuccessSnackbar} = useSnackbar(state?.creationSuccess);
    const {
        isOpen: isCopySnackbarOpen,
        isSuccessful: isCopySuccessful,
        open: openCopySnackbar,
        close: closeCopySnackbar,
        succeed: succeedCopy,
        fail: failCopy
    } = useSuccessSnackbar(false);

    const {data: surveys, isLoading} = useGetAllSurveysQuery(useSelector(selectUserId));
    const [deleteSurvey] = useDeleteSurveyMutation();

    const surveysPerPage = 10;
    const surveysToDisplay = surveys;

    const handleCopy = (hash) => {
        navigator.clipboard.writeText(`${window.location.href}/${hash}`)
            .then(succeedCopy)
            .catch(failCopy)
            .finally(openCopySnackbar);
    };

    return (
        <Container>
            <Stack direction='row' justifyContent='space-between' alignItems='center'>
                <Typography variant='h2' as='h1'>Kérdőíveim</Typography>
                <Button variant='contained' endIcon={<Add/>} component={NavLink} to='/surveys/create'>
                    Új kérdőív
                </Button>
            </Stack>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Cím</TableCell>
                        <TableCell>Létrehozva</TableCell>
                        <TableCell>Műveletek</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {!isLoading && surveysToDisplay.map((survey) => (
                        <TableRow key={survey.id}>
                            <TableCell>
                                <Link component={NavLink} to={`/survey/${survey.hash}`}>{survey.name}</Link>
                            </TableCell>
                            <TableCell>
                                {new Date(survey.createdAt).toLocaleDateString('hu-HU')}
                            </TableCell>
                            <TableCell>
                                <Tooltip title='Válaszok megtekintése'>
                                    <IconButton component={NavLink} to={`/surveys/${survey.id}/results`}>
                                        <Analytics/>
                                    </IconButton>
                                </Tooltip>

                                <Tooltip title='Kérdőív hivatkozásának másolása'>
                                    <IconButton onClick={() => handleCopy(survey.hash)}><ContentCopy/></IconButton>
                                </Tooltip>

                                <Tooltip title='Kérdőív törlése'>
                                    <IconButton onClick={() => deleteSurvey(survey.id)}><Delete/></IconButton>
                                </Tooltip>
                            </TableCell>
                        </TableRow>
                    ))}

                    {isLoading && [...Array(surveysPerPage)].map((_, index) => (
                        <TableRow key={index}>
                            <TableCell><Skeleton height={40}></Skeleton></TableCell>
                            <TableCell><Skeleton height={40}></Skeleton></TableCell>
                            <TableCell><Skeleton height={40}></Skeleton></TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>

            <Snackbar open={isSuccessSnackbarOpen} autoHideDuration={6000} onClose={closeSuccessSnackbar}>
                <Alert onClose={closeSuccessSnackbar} severity='success' sx={{width: '100%'}}>
                    Sikeres létrehozás!
                </Alert>
            </Snackbar>
            <Snackbar open={isCopySnackbarOpen} autoHideDuration={6000} onClose={closeCopySnackbar}>
                <Alert onClose={closeCopySnackbar} severity={isCopySuccessful ? 'success' : 'error'}
                       sx={{width: '100%'}}>
                    {isCopySuccessful ? 'A kérdőív linkje a vágólapra másolva!' : 'Nem sikerült misáolni a linket!'}
                </Alert>
            </Snackbar>
        </Container>
    );
}