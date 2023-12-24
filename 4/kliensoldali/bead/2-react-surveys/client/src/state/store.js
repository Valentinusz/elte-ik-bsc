import {configureStore} from '@reduxjs/toolkit';
import {authReducer} from './authSlice.js';
import {authApi} from './api/authApiSlice.js';
import {surveyApi} from './api/surveyApiSlice.js';
import {resultApi} from './api/resultApiSlice.js';

export const store = configureStore({
    reducer: {
        auth: authReducer,
        [authApi.reducerPath]: authApi.reducer,
        [surveyApi.reducerPath]: surveyApi.reducer,
        [resultApi.reducerPath]: resultApi.reducer
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware()
            .concat(authApi.middleware)
            .concat(surveyApi.middleware)
            .concat(resultApi.middleware)
});