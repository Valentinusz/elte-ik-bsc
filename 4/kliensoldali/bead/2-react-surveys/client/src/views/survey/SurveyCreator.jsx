import {Stack, TextField, Typography} from '@mui/material';
import {useState} from 'react';
import {useCreateSurveyMutation} from '../../state/api/surveyApiSlice.js';
import {LoadingButton} from '@mui/lab';
import {useNavigate} from 'react-router-dom';

export function SurveyCreator() {
    const [data, setData] = useState('');
    const [errors, setErrors] = useState([]);
    const [parsedData, setParsedData] = useState({});
    const [createSurvey, {isLoading}] = useCreateSurveyMutation();
    const navigate = useNavigate();

    const handleInput = event => {
        const newErrors = [];

        const splitData = event.target.value.split(/\n$/m); // treat first line as title
        const title = splitData.shift();

        if (!title) {
            newErrors.push('Nem lett megadva a kérdőív neve!');
        }

        /** @type {{pageName: string, questions: string[]}[]} */
        const pages = splitData.map(rawPage => {
            const splitPage = rawPage.split('\n');
            splitPage.shift();
            const pageName = splitPage.shift();
            return {pageName, questions: splitPage};
        }).filter(page => page.pageName);

        if (pages.length < 1) {
            newErrors.push('Nem lett egy lap sem a kérdőívhez rendelve!');
        } else {
            for (const page of pages) {
                if (page.questions.length < 1) {
                    newErrors.push(`A(z) ${page.pageName} laphoz nem lett kérdés rendelve.`);
                }
            }
        }

        setData(event.target.value);
        setErrors(newErrors);
        setParsedData({title, pages});
    };

    const handleSubmit = async event => {
        event.preventDefault();
        console.log(parsedData);

        await createSurvey({
            name: parsedData.title,
            content: JSON.stringify(parsedData.pages)
        }).unwrap();

        navigate('/surveys', {state: {creationSuccess: true}});
    };

    return (
        <Stack as='form' spacing={4} onSubmit={handleSubmit}>
            <Typography variant='h2' as='h1'>Új kérdőív</Typography>
            <TextField
                multiline minRows={12} fullWidth
                value={data} onInput={handleInput}
                error={errors.length > 0} helperText={errors.join(' ')}
            />
            <LoadingButton loading={isLoading} type='submit' variant='contained' disabled={data === '' || errors.length > 0}>Létrehozás</LoadingButton>
        </Stack>
    );
}