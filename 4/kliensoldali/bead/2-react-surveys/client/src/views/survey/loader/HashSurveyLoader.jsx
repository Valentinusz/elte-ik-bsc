import {useGetSurveyByHashQuery} from '../../../state/api/surveyApiSlice.js';
import {CircularProgress, Stack} from '@mui/material';
import {useParams} from 'react-router-dom';
import {Survey} from '../Survey.jsx';

/**
 * Wrapper around Survey component. Used to make sure said component is only shown when data is successfully fetched.
 * @returns {JSX.Element}
 * @constructor
 */
export function HashSurveyLoader() {
    const {hash} = useParams();
    const {data: result, isLoading, isError, error} = useGetSurveyByHashQuery({hash});

    if (isLoading) {
        return (
            <Stack justifyContent={'center'} alignItems={'center'} height={800}>
                <CircularProgress/>
            </Stack>
        );
    }

    if (isError) {
        //TODO add error page
        console.error(error);
        return null;
    }

    if (result.length === 0) {
        //TODO add 404 handler
        console.error(404);
        return null;
    }

    const survey = result[0];

    return (
        <Survey survey={survey}></Survey>
    );
}