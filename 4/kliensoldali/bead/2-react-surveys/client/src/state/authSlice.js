import {createSlice} from '@reduxjs/toolkit';

/** @type {{id: ?number, name: ?string, email: ?string, token: ?string}} */
const initialState = {id: null, name: null, email: null, token: null};

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login(state, {payload}) {
            state.id = payload.id;
            state.name = payload.name;
            state.email = payload.email;
            state.token = payload.token;
        },

        logout() {
            return initialState;
        }
    }
});

export const authReducer = authSlice.reducer;

export const selectUserId = (state) => state.auth.id;
export const selectUserName = (state) => state.auth.name;
export const selectUserToken = (state) => state.auth.token;
export const selectUserEmail = (state) => state.auth.email;
export const selectIsAuthenticated = (state) => state.auth.token != null;

export const {login, logout} = authSlice.actions;