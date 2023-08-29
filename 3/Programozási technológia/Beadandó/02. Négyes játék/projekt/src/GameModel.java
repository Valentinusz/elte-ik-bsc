/**
 * @author Valentinusz / KDPHNI / Boda Bálint
 */

import java.util.ArrayList;

/**
 * Class containing the Logic of the game.
 */
public class GameModel {
    /**
     * Attribute representing an n*n board of tiles.
     */
    private final Tile[][] board;

    /**
     * Attribute representing the players.
     */
    private final Player[] players;

    /**
     * Index of the player who currently has their turn active. 0: player1, 1:player2
     */
    private byte currentPlayer;

    /**
     * Sets up an empty board of the specified size.
     * @param boardSize positive integer
     * @throws IllegalArgumentException if {@code boardSize < 1}
     */
    public GameModel(int boardSize) {
        if (boardSize < 1) {
            throw new IllegalArgumentException();
        }

        this.board = new Tile[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = new Tile();
            }
        }

        this.players = new Player[2];
        this.players[0] = new Player();
        this.players[1] = new Player();
        this.currentPlayer = 0;
    }

    /**
     * @param row index of row
     * @param column index of column
     * @return {@code board[x][y]}
     * @throws IndexOutOfBoundsException if x or y is outside index range
     */
    public Tile getTile(int row, int column) throws IndexOutOfBoundsException {
        return this.board[row][column];
    }

    /**
     * @return Index of the currently active player in players.
     */
    public byte getCurrentPlayerIndex() {
        return this.currentPlayer;
    }

    /**
     * @return Reference to the currently active player.
     */
    public Player getCurrentPlayer() {
        return this.players[this.currentPlayer];
    }

    /**
     * Changes the value of {@code this.currentPlayer} to be the index of the inactive player.
     */
    public void endTurn() {
        if (this.currentPlayer == 0) {
            this.currentPlayer = 1;

        } else {
            this.currentPlayer = 0;
        }
    }

    /**
     * @return true if the game is over (sum of scores is equal to the square of the size of the board)
     */
    public boolean isOver() {
        return this.players[0].getScore() + this.players[1].getScore() == this.board.length * this.board.length;
    }

    /**
     * @return index within {@code this.players} of the player with the higher score, if score is equal 1 is returned
     */
    public byte getWinner() {
        return this.players[0].getScore() > this.players[1].getScore() ? (byte)0 : (byte)1;
    }

    /**
     * Tips a tile and its neighbours.
     * @return {@code int[3]} where {@code int[0]} is the row of the tile {@code int[1]} is the column and {@code int[2]} is 1 if the tile has been tipped and 0 of it has not.
     */
    public ArrayList<int[]> tipTile(int row, int column) {
        ArrayList<int[]> tileCoordinates = new ArrayList<>();

        //center tile
        tileCoordinates.add(new int[]{row, column, 0});

        //north tile
        if (row > 0) {
            tileCoordinates.add(new int[]{row-1, column, 0});
        }

        //south tile
        if (row < this.board.length-1) {
            tileCoordinates.add(new int[]{row+1, column, 0});
        }

        //west tile
        if (column > 0) {
            tileCoordinates.add(new int[]{row, column-1, 0});
        }

        //east tile
        if (column < this.board.length-1) {
            tileCoordinates.add(new int[]{row, column+1, 0});
        }

        //tip tiles and change coords[2] if a given tile was tipped
        for (int[] coords: tileCoordinates) {
            if (this.board[coords[0]][coords[1]].tipTile(getCurrentPlayer())) {
                coords[2] = 1;
            }
        }

        return tileCoordinates;
    }
}
