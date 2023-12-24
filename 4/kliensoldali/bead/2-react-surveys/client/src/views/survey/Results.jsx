import {useGetResultsForSurveyQuery} from '../../state/api/resultApiSlice.js';
import {useParams} from 'react-router-dom';
import {CircularProgress, Container, List, ListItem, Stack, Typography} from '@mui/material';
import {useGetSurveyByIdQuery} from '../../state/api/surveyApiSlice.js';
import {useSelector} from 'react-redux';
import {selectUserId} from '../../state/authSlice.js';

export function Results() {
    const {surveyId} = useParams();
    const {data: results, isLoading: isResultsLoading} = useGetResultsForSurveyQuery(surveyId);
    const {data: survey, isLoading: isSurveyLoading} = useGetSurveyByIdQuery({
        surveyId,
        userId: useSelector(selectUserId)
    });

    const isLoading = isResultsLoading || isSurveyLoading;

    if (isLoading) {
        return (
            <Stack justifyContent={'center'} alignItems={'center'} height={800}>
                <CircularProgress/>
            </Stack>
        );
    }

    const surveyPages = JSON.parse(survey.content);
    // const answers = JSON.parse(results.content)

    console.log(surveyPages);
    console.log(results);

    const parsedResults = results.map(result => JSON.parse(result.content));
    console.log(parsedResults);

    return (
        <Stack spacing={4}>
            <Typography variant='h2' component='h1'>{survey.name}</Typography>
            {surveyPages.map((page, pageIndex) => (
                <Stack key={pageIndex} sx={{ p: 2, border: '1px solid lightgrey' }} spacing={2}>
                    <Typography variant='h3' component='h2'>{page.pageName}</Typography>
                    {page.questions.map((question, questionIndex) => (
                        <Container key={questionIndex} sx={{ p: 2, border: '1px solid lightgrey' }}>
                            <Typography variant='h4' component='h3'>{question}</Typography>
                            <List sx={{ p: 2, border: '1px solid lightgrey' }}>
                            {parsedResults.map((answer, answerIndex) => (
                                <ListItem key={answerIndex} divider>
                                    <Typography variant='h5' component='h4'>
                                        {parsedResults[answerIndex][pageIndex][questionIndex]}
                                    </Typography>
                                </ListItem>
                            ))}
                            </List>
                        </Container>
                    ))}
                </Stack>

            ))}

        </Stack>
    );
}