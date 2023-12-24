import {useGetSurveyByIdQuery} from '../../../state/api/surveyApiSlice.js';
import {useSelector} from 'react-redux';
import {selectUserId} from '../../../state/authSlice.js';
import {CircularProgress, Stack} from '@mui/material';
import {useParams} from 'react-router-dom';
import {Survey} from '../Survey.jsx';

/**
 * Wrapper around Survey component. Used to make sure said component is only shown when data is successfully fetched.
 * @returns {JSX.Element}
 * @constructor
 */
export function IdSurveyLoader() {
    const {surveyId} = useParams();
    const {data: survey, isLoading, isError} = useGetSurveyByIdQuery({surveyId, userId: useSelector(selectUserId)});

    if (isLoading) {
        return (
            <Stack justifyContent={'center'} alignItems={'center'} height={800}>
                <CircularProgress/>
            </Stack>
        );
    }

    if (isError) {
        //TODO add error page
        return null;
    }

    return (
        <Survey survey={survey}></Survey>
    );
}