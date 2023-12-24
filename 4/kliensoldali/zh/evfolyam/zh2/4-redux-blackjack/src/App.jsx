import './App.css'
import Hand from './view/Hand';
import {useDispatch, useSelector} from "react-redux";
import {
    dealerRound,
    newGame, nextCard, playerFinished,
    selectDealerHand,
    selectDeck, selectIsGameStarted,
    selectIsPlayerRoundDone,
    selectPlayerHand,
    selectWinner, selectWins
} from "./store/gameSlice";
import {getValueOfCards} from "./utils/blackJackUtils.js";

function App() {
    const dispatch = useDispatch();

    const deck = useSelector(selectDeck);
    const handOfDealer = useSelector(selectDealerHand);
    const handOfPlayer = useSelector(selectPlayerHand);
    const isPlayerDone = useSelector(selectIsPlayerRoundDone);
    const winner = useSelector(selectWinner);
    const gameStarted = useSelector(selectIsGameStarted);

    const {player: playerWins, dealer: dealerWins} = useSelector(selectWins);

    console.log(deck)

    return (
        <div className='container'>
            <h1>Simple Blackjack</h1>
            <h2>Dealer ({dealerWins}):</h2>
            <Hand cards={handOfDealer}></Hand>
            <button disabled={!isPlayerDone} onClick={() => dispatch(dealerRound())}>Play Dealer</button>
            <h2>Player ({playerWins}):</h2>
            <Hand cards={handOfPlayer}></Hand>
            <button disabled={!gameStarted || isPlayerDone || getValueOfCards(handOfPlayer) > 21} onClick={() => dispatch(nextCard())}>Get New Card</button>
            <button disabled={!gameStarted || isPlayerDone} onClick={() => dispatch(playerFinished())}>Stop</button>
            <button onClick={() => dispatch(newGame())}>New Game</button>
            {winner && (<p className='result'>{winner} won!</p>)}
        </div>
    )
}

export default App
