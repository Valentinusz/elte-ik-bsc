import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey results data. */
export const resultApi = createApi({
    reducerPath: 'results',
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:3030/results',
        prepareHeaders: (headers, {getState}) => {
            const token = getState().auth.token;

            if (token) {
                headers.set('Authorization', `Bearer ${token}`);
            }

            return headers;
        }
    }),
    endpoints: (builder) => ({
        // Returns all survey results of a given survey
        getResultsForSurvey: builder.query({
            query: surveyId => `?surveyId=${surveyId}`,
            transformResponse: (response) => response.data
        }),

        // Adds results to the given survey.
        createResult: builder.mutation({
            query: ({surveyId, answer}) => ({method: 'POST', body: {surveyId, content: answer}}),
        })
    }),
});

export const {useGetResultsForSurveyQuery, useCreateResultMutation} = resultApi;