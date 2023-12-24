import {configureStore} from "@reduxjs/toolkit";
import {gameReducer} from "./gameSlice.js";

export const store = configureStore({
    reducer: {
        game: gameReducer
    },
});
