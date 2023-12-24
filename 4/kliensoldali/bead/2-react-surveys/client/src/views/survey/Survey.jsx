import {Button, Grid, Stack, Step, StepLabel, Stepper, TextField, Typography} from '@mui/material';
import {useState} from 'react';
import {useCreateResultMutation} from '../../state/api/resultApiSlice.js';
import {useNavigate} from 'react-router-dom';
import {LoadingButton} from '@mui/lab';
import {KeyboardArrowLeft, KeyboardArrowRight} from '@mui/icons-material';

export function Survey({survey}) {
    const isPageFilled = (page) => formData[page].every(value => value !== '');
    const navigate = useNavigate();

    const [addAnswers, {isLoading}] = useCreateResultMutation();

    /** @type {[{pageName: string, questions: [string]}]} */
    const surveyPages = [...JSON.parse(survey.content)];

    const [formData, setFormData] = useState(surveyPages.map(page => (page.questions.map(() => ''))));
    const [currentPageIndex, setCurrentPageIndex] = useState(0);

    //computed values
    const isFirstPage = currentPageIndex === 0;
    const isLastPage = currentPageIndex + 1 === surveyPages.length;
    const canAdvanceToNextPage = isPageFilled(currentPageIndex);
    const canSubmit = formData.every((_, index) => isPageFilled(index));

    //handlers
    const handlePageJump = (pageIndex) => {
        if (pageIndex < currentPageIndex || canAdvanceToNextPage) {
            setCurrentPageIndex(pageIndex);
        }
    };

    const handleInput = (event, questionIndex) => {
        const newFormData = [...formData];
        newFormData[currentPageIndex][questionIndex] = event.target.value;
        setFormData(newFormData);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            await addAnswers({surveyId: survey.id, answer: JSON.stringify(formData)}).unwrap();
            navigate('/surveys');
        } catch (e) {
            console.error(e.status);
        }
    };

    return (
        <Stack spacing={4} component='form' method='POST' onSubmit={handleSubmit}>
            <Typography variant='h2' component='h1'>{survey.name}</Typography>

            <Stepper alternativeLabel activeStep={currentPageIndex}>
                {surveyPages.map((page, pageIndex) => (
                    <Step key={pageIndex}>
                        <StepLabel onClick={() => handlePageJump(pageIndex)}>{page.pageName}</StepLabel>
                    </Step>
                ))}
            </Stepper>

            {surveyPages[currentPageIndex].questions.map((question, index) => (
                <TextField key={`${currentPageIndex}.${index}`} label={question} name={question} required
                           value={formData[currentPageIndex][index]} onInput={event => handleInput(event, index)}
                />
            ))}

            <Grid container justifyContent='space-between'>
                <Grid item xs={2}>
                    <Button variant='contained' type='button' startIcon={<KeyboardArrowLeft/>} fullWidth
                            disabled={isFirstPage}
                            onClick={() => setCurrentPageIndex(currentPageIndex - 1)}
                    >
                        Vissza
                    </Button>
                </Grid>

                <Grid item xs={2}>
                    <Button variant='contained' type='button' endIcon={<KeyboardArrowRight/>} fullWidth
                            disabled={isLastPage || !canAdvanceToNextPage}
                            onClick={() => setCurrentPageIndex(currentPageIndex + 1)}
                    >
                        Következő
                    </Button>
                </Grid>
            </Grid>

            {isLastPage &&
                <LoadingButton variant='contained' type='submit' loading={isLoading}
                               disabled={!canAdvanceToNextPage || !canSubmit}
                >
                    Küldés
                </LoadingButton>
            }
        </Stack>
    );
}
