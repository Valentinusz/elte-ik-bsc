import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";

export const countryApi = createApi({
    reducerPath: "countryApi",
    baseQuery: fetchBaseQuery({baseUrl: "https://date.nager.at/api/v3"}),
    endpoints: (builder) => ({
        getCountries: builder.query({
            query: () => `AvailableCountries`, // transformResponse: (response) => response.data,
        }),
        getHolidays: builder.query({
            query: ({year, country}) => `PublicHolidays/${year}/${country}`
        }),
    }),
});

export const {useGetCountriesQuery, useGetHolidaysQuery} = countryApi;
