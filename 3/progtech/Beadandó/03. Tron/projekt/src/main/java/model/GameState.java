package main.java.model;

/** Enum representing the state of the game. */
public enum GameState {
    /** game is still in progress */
    IN_PROGRESS,

    /** game ended and player 1 won */
    PLAYER1WON,

    /** game ended and player 2 won */
    PLAYER2WON,

    /** players collided with each other, resulting in a draw */
    DRAW,
}
