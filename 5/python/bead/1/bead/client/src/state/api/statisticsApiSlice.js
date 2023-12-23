import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey data. */
export const statisticsApi = createApi({
    reducerPath: 'statistics',
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:8000/statistics'
    }),
    endpoints: (builder) => ({
        getAverageTemperature: builder.query({
            query: () => 'avg_temp'
        }),
    }),
});

export const {
    useGetAverageTemperatureQuery
} = statisticsApi;
