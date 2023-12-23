import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey data. */
export const securityApi = createApi({
    reducerPath: 'security',
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://localhost:8000/security'
    }),
    endpoints: (builder) => ({
        getSecurityAlerts: builder.query({
            query: () => 'alerts'
        }),
    }),
});

export const {
    useGetSecurityAlertsQuery
} = securityApi;
