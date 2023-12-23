import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey data. */
export const deviceApi = createApi({
    reducerPath: 'devices',
    tagTypes: ['Device'],
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:8000/devices'
    }),
    endpoints: (builder) => ({
        getPairedDevices: builder.query({
            query: () => '',
            providesTags: ['Device']
        }),

        getUnpairedDevices: builder.query({
            query: () => 'unpaired',
            providesTags: ['Device']
        }),

        discoverDevices: builder.mutation({
            query: () => ({
                url: 'discover',
                method: 'POST'
            }),
            invalidatesTags: ['Device']
        }),

        pairDevice: builder.mutation({
            query: (device_id) => ({
                method: 'POST',
                body: {device_id}
            }),
            invalidatesTags: ['Device']
        }),

        toggleDevice: builder.mutation({
            query: (device_id) => ({
                url: `${device_id}/toggle`,
                method: 'PATCH'
            })
        }),

        getDeviceById: builder.query({
            query: (device_id) => device_id
        })

        

        // // Creates a new survey. Invalidates the result of the getAllSurveys query;
        // createSurvey: builder.mutation({
        //     query: (body) => ({url: 'surveys', method: 'POST', body}),
        //     invalidatesTags: ['Survey'],
        // }),
        //
        // // Returns the survey of the given id and user.
        // getSurveyById: builder.query({
        //     query: ({surveyId, userId}) => `${surveyId}?userId=${userId}`
        // }),
        //
        // // Returns the survey of the given hash.
        // getSurveyByHash: builder.query({
        //     query: ({hash}) => `?hash=${hash}`,
        //     transformResponse: (response) => response.data
        // }),
        //
        // // Deletes the survey of the given id. Invalidates data queried by the getAllSurveys query.
        // deleteSurvey: builder.mutation({
        //     query: (surveyId) => ({url: `${surveyId}`, method: 'DELETE'}),
        //     invalidatesTags: (result, error, id) => [{type: 'Survey', id}, {type: 'Survey', id: 'LIST'}],
        // }),
    }),
});

export const {
    useGetPairedDevicesQuery,
    useGetUnpairedDevicesQuery,
    useDiscoverDevicesMutation,
    usePairDeviceMutation,
    useToggleDeviceMutation,
    useGetDeviceByIdQuery
} = deviceApi;
