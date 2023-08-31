package main.java.model;

import main.java.database.HighScores;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/** Model of the game. */
public class GameModel {
    /** Matrix of tiles representing the map of the game. */
    private final Tile[][] map;

    /** Array of players containing the two players of the game. */
    private final Player[] players;

    /**
     * @param min start of the interval
     * @param max end of the interval
     * @return a random integer from the interval [min,max[
     * */
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Generates a random obstacle at a given point and mirrors it to the other side of the map.
     * @param centerX x coordinate of the center of the obstacle
     * @param centerY y coordinate of the center of the obstacle
     */
    private void generateObstacleCluster(int centerX, int centerY) {
        ArrayList<int[]> obstacleCluster = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                obstacleCluster.add(new int[]{centerX + i, centerY + j});
            }
        }
        Collections.shuffle(obstacleCluster); // shuffle list

        for (int[] coordinate : obstacleCluster.stream().limit(getRandomNumber(3, 10)).toList()) {
            this.map[coordinate[0]][coordinate[1]].setColor(Color.BLACK);
            this.map[this.getVerticalSize() - 1 - coordinate[0]][this.getHorizontalSize() - 1 - coordinate[1]].setColor(Color.BLACK);
        }

        for (int i = 0; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                this.map[players[0].getVerticalLocation() + j][players[0].getHorizontalLocation() + i].setColor(Tile.SAFE_COLOR);
            }
        }
    }

    /**
     * Constructor.
     * @param player1Name    name of player1
     * @param player1Color   color of player1
     * @param player2Name    name of player2
     * @param player2Color   color of player2
     * @param horizontalSize horizontal size of the map
     * @param verticalSize   vertical size of the map
     * @throws IllegalArgumentException
     *     if a player has an empty name,
     *     if players have the same name,
     *     if players have the same color,
     *     if a player picks the color representing safe tiles,
     *     if map size is smaller than 7 on either axis or
     *     if map size is even on either axis
     */
    public GameModel(String player1Name, Color player1Color, String player2Name, Color player2Color,  int horizontalSize, int verticalSize) throws IllegalArgumentException {
        StringBuilder errorMessage = new StringBuilder();

        // check if a player has an empty name
        if (player1Name.trim().length() == 0 || player2Name.trim().length() == 0) {
            errorMessage.append("A játékosok neve nem lehet üres!\n");
        }

        // check if players have the same name
        if (Objects.equals(player1Name.trim(), player2Name.trim())) {
            errorMessage.append("A játékosok neveinek egyedinek kell lennie!\n");
        }

        // check if players have the same color
        if (Objects.equals(player1Color, player2Color)) {
            errorMessage.append("A játékosok színeinek egyedinek kell lennie!\n");
        }

        // check if the color reserved for safe tiles is picked
        if (player1Color.equals(Tile.SAFE_COLOR) || player2Color.equals(Tile.SAFE_COLOR)) {
            errorMessage.append("Az RGB(41, 53, 66) szín a játék számára van fenntartva.");
        }

        // check if game has a size of at least 7 in both axis
        if (horizontalSize < 7 || verticalSize < 7) {
            errorMessage.append("A pálya mérete nem lehet kisebb 7-nél!\n");
        }

        // check if game has a size of at least 7 in both axis
        if (horizontalSize % 2 == 0 || verticalSize % 2 == 0) {
            errorMessage.append("A pálya mérete nem lehet páros.\n");
        }

        // check if any error has occurred
        if (errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

        // take edge of the map into account
        verticalSize   += 2;
        horizontalSize += 2;

        // initialize players
        this.players    = new Player[2];

        int verticalStart = verticalSize / 2;

        int player1HorizontalStart = 1;
        this.players[0] = new Player(player1Name, player1Color, player1HorizontalStart, verticalStart, Direction.RIGHT);

        int player2HorizontalStart = horizontalSize-2;
        this.players[1] = new Player(player2Name, player2Color, player2HorizontalStart , verticalStart, Direction.LEFT);

        // initialize game map
        this.map = new Tile[verticalSize][horizontalSize];

        for (int row = 0; row < verticalSize; row++) {
            for (int column = 0; column < horizontalSize; column++) {
                // edge of the map is unsafe
                if (row == 0 || column == 0 || column == horizontalSize - 1 || row == verticalSize - 1 ) {
                    this.map[row][column] = new Tile(Color.BLACK);
                } else {
                    this.map[row][column] = new Tile();
                }
            }
        }

        // generate additional obstacles
        // generate 2 random points in the upper part of the map, including the middle, excluding the edge of the map
        int x1 = getRandomNumber(1, horizontalSize / 2 + 1);
        int y1 = getRandomNumber(1, verticalSize / 2 + 1);

        generateObstacleCluster(x1,y1);

        int x2 = getRandomNumber(horizontalSize / 2 + 1, horizontalSize - 1);
        int y2 = getRandomNumber(1, verticalSize / 2 + 1);

        generateObstacleCluster(x2,y2);

        // clear area near the players to ensure they are never trapped
        for (int i = -1; i <= 0; i++) {
            for (int j = -1; j <= 1; j++) {
                this.map[players[1].getVerticalLocation() + j][players[1].getHorizontalLocation() + i].setColor(Tile.SAFE_COLOR);
            }
        }

        // place players
        this.map[verticalStart][player1HorizontalStart].setColor(this.players[0].getColor());
        this.map[verticalStart][player2HorizontalStart].setColor(this.players[1].getColor());
    }

    /**
     * Simulates one round of the game (moves players, checks if a game ending condition is met).
     * @return
     *      {@code GameState.IN_PROGRESS} if the turns makes the game end with a draw (player on player collision happened),
     *      {@code GameState.PLAYER2WIN} if the game continues (no collision happened),
     *      {@code GameState.PLAYER1WIN} if the turn makes player1 the loser (player1 collides with an obstacle),
     *      {@code GameState.DRAW} if the turn makes player2 the loser (player2 collides with an obstacle)
     * @throws SQLException if a database access error occurs
     */
    public GameState doRound() throws SQLException {
        for (int i = 0; i < this.players.length; ++i) {
            Player player = this.players[i];

            player.move();

            Tile playerLocation = this.map[player.getVerticalLocation()][player.getHorizontalLocation()];

            // check for player on player collision
            if (players[0].getHorizontalLocation() == players[1].getHorizontalLocation() && players[0].getVerticalLocation() == players[1].getVerticalLocation()) {
                return GameState.DRAW;
            }

            // check for unsafe tile, perimeter of the map is unsafe
            if (!playerLocation.isSafe()) {
                if (i == 0) {
                    HighScores.instance().insertScore(players[1].getName());
                    return GameState.PLAYER2WON;
                } else {
                    HighScores.instance().insertScore(players[0].getName());
                    return GameState.PLAYER1WON;
                }
            } else {
                playerLocation.setColor(player.getColor()); // make safe tile unsafe
            }
        }
        return GameState.IN_PROGRESS;
    }

    /**
     * @param playerIndex index of player
     * @return Name of the player at {@code players[playerIndex]}
     */
    public String getPlayerName(int playerIndex) {
        return this.players[playerIndex].getName();
    }

    /**
     * @param playerIndex index of player
     * @return Color of the player at {@code players[playerIndex]}
     */
    public Color getPlayerColor(int playerIndex) {
        return this.players[playerIndex].getColor();
    }

    /**
     * @param playerIndex index of player
     * @return horizontal location of player
     */
    public int getHorizontalPlayerLocation(int playerIndex) {
        return this.players[playerIndex].getHorizontalLocation();
    }

    /**
     * @param playerIndex index of player
     * @return vertical location of player
     */
    public int getVerticalPlayerLocation(int playerIndex) {
        return this.players[playerIndex].getVerticalLocation();
    }

    /**
     * Changes direction of player.
     * @param direction   new direction of the player
     * @param playerIndex player to change direction of
     */
    public void setPlayerDirection(Direction direction, int playerIndex) {
        this.players[playerIndex].setDirection(direction);
    }

    /**
     * @return horizontal size of the map
     */
    public int getHorizontalSize() {
        // map size is at least 7 so map[0] always exists
        return this.map[0].length;
    }

    /**
     * @return vertical size of the map.
     */
    public int getVerticalSize() {
        return this.map.length;
    }

    /**
     * @param row    row index
     * @param column column index
     * @return Color of the Tile at {@code map[row][column]}.
     */
    public Color getColor(int row, int column) {
        return this.map[row][column].getColor();
    }
}