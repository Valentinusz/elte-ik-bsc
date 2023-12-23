import { configureStore } from '@reduxjs/toolkit';
import { deviceApi } from './api/deviceApiSlice';
import { securityApi } from './api/securityApiSlice';
import { statisticsApi } from './api/statisticsApiSlice';

export const store = configureStore({
    reducer: {
        [deviceApi.reducerPath]: deviceApi.reducer,
        [securityApi.reducerPath]: securityApi.reducer,
        [statisticsApi.reducerPath]: statisticsApi.reducer

    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware()
            .concat(deviceApi.middleware)
            .concat(securityApi.middleware)
            .concat(statisticsApi.middleware)
});
