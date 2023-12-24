import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey data. */
export const surveyApi = createApi({
    reducerPath: 'surveys',
    providesTags: ['Survey'],
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:3030/surveys',
        prepareHeaders: (headers, {getState}) => {
            const token = getState().auth.token;

            if (token) {
                headers.set('Authorization', `Bearer ${token}`);
            }

            return headers;
        }
    }),
    endpoints: (builder) => ({
        // Returns all surveys made by the authenticated user
        getAllSurveys: builder.query({
            query: userId => `?userId=${userId}`,
            transformResponse: (response) => response.data,
            providesTags:
                (result) => result ? [...result.map(({id}) => ({type: 'Survey', id})), 'Survey'] : ['Survey']
        }),

        // Returns the count of surveys made by the authenticated user
        getSurveyCount: builder.query({
            query: userId => `?userId=${userId}`,
            transformResponse: (response) => response.data.length,
            providesTags: [{type: 'Survey', id: 'LIST'}]
        }),

        // Creates a new survey. Invalidates the result of the getAllSurveys query;
        createSurvey: builder.mutation({
            query: (body) => ({url: 'surveys', method: 'POST', body}),
            invalidatesTags: ['Survey'],
        }),

        // Returns the survey of the given id and user.
        getSurveyById: builder.query({
            query: ({surveyId, userId}) => `${surveyId}?userId=${userId}`
        }),

        // Returns the survey of the given hash.
        getSurveyByHash: builder.query({
            query: ({hash}) => `?hash=${hash}`,
            transformResponse: (response) => response.data
        }),

        // Deletes the survey of the given id. Invalidates data queried by the getAllSurveys query.
        deleteSurvey: builder.mutation({
            query: (surveyId) => ({url: `${surveyId}`, method: 'DELETE'}),
            invalidatesTags: (result, error, id) => [{type: 'Survey', id}, {type: 'Survey', id: 'LIST'}],
        }),
    }),
});

export const {
    useGetAllSurveysQuery,
    useGetSurveyCountQuery,
    useCreateSurveyMutation,
    useGetSurveyByIdQuery,
    useGetSurveyByHashQuery,
    useDeleteSurveyMutation
} = surveyApi;