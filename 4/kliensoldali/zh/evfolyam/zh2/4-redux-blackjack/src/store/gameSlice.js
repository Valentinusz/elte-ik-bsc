import {createSlice} from "@reduxjs/toolkit";
import _ from "lodash";
import {getValueOfCards} from "../utils/blackJackUtils.js";

const initialState = {
    deck: [],
    hands: {
        player: [],
        dealer: []
    },
    isGameStarted: false,
    isPlayerRoundDone: false,
    roundWinner: null,
    winnings: {
        player: 0,
        dealer: 0
    }
}

export const gameSlice = createSlice({
    name: "game",
    initialState,
    reducers: {
        newGame(state) {
            state.deck = _.shuffle(Array.from({length: 51}, (value, index) => index));
            state.hands.player = [];
            state.hands.dealer = [];
            state.isGameStarted = true;
            state.roundWinner = null;
            state.isPlayerRoundDone = false;
        },
        nextCard(state) {
            state.hands.player.push(state.deck.pop());
        },
        playerFinished(state) {
            state.isPlayerRoundDone = true;
        },
        dealerRound(state) {
            while (getValueOfCards(state.hands.dealer) <= 16) {
                state.hands.dealer.push(state.deck.pop());
            }

            const playerScore = (21 - getValueOfCards(state.hands.player));
            const dealerScore = (21 - getValueOfCards(state.hands.dealer));

            if (playerScore < 0 || dealerScore >= 0 && Math.abs(dealerScore) < Math.abs(playerScore)) {
                state.roundWinner = "dealer";
                state.winnings.dealer++;
            } else {
                state.roundWinner = "player";
                state.winnings.player++;
            }
        }

    }
})

export const gameReducer = gameSlice.reducer;
export const {newGame, nextCard, playerFinished, dealerRound} = gameSlice.actions;

export const selectDeck = (state) => state.game.deck;
export const selectDealerHand = (state) => state.game.hands.dealer;
export const selectPlayerHand = (state) => state.game.hands.player;
export const selectWinner = (state) => state.game.roundWinner;
export const selectIsPlayerRoundDone = (state) => state.game.isPlayerRoundDone;
export const selectIsGameStarted = (state) => state.game.isGameStarted;
export const selectWins = (state) => state.game.winnings;