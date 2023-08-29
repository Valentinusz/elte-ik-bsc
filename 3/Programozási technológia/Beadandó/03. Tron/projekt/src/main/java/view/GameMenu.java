package main.java.view;

import main.java.model.Direction;
import main.java.model.GameModel;
import main.java.model.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Objects;

/** Menu containing the GUI for the game. */
public class GameMenu extends JPanel {
    /** Size of a visual representation of a tile. */
    private final int TILE_SIZE = 40;

    /** Timer for game. */
    private final Timer timer;

    /** Time since the game started. */
    private int time;

    /** Label displaying elapsed time. */
    private final JLabel timeLabel;

    /** Matrix of {@code JLabel} representing the visual area of the game. */
    private JLabel[][] gameArea;

    /** Model of the game. */
    private GameModel game;

    /** Image used for the players. */
    private final ImageIcon motorSprite;

    /** Image used for dead players. */
    private final ImageIcon deathSprite;

    /** Parent of this component. */
    private final GameView parent;

    /**
     * Constructor. Sets parent, initializes layout, creates input-action map of game controls, and creates the timer.
     * @param parent of this component
     */
    public GameMenu(GameView parent) {
        this.parent = parent;

        // create icons
        ImageIcon motorIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resources/helmet.png")));
        this.motorSprite    = new ImageIcon(motorIcon.getImage().getScaledInstance(this.TILE_SIZE - 5, this.TILE_SIZE - 5,  java.awt.Image.SCALE_SMOOTH));

        ImageIcon deathIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resources/dead.png")));
        this.deathSprite    = new ImageIcon(deathIcon.getImage().getScaledInstance(this.TILE_SIZE - 5, this.TILE_SIZE - 5,  java.awt.Image.SCALE_SMOOTH));

        // add player1 controls
        // w - up
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "Player1 Up");
        this.getActionMap().put("Player1 Up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.UP,0);}
        });

        // d - right
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "Player1 Right");
        this.getActionMap().put("Player1 Right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.RIGHT,0);}
        });

        // s - down
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "Player1 Down");
        this.getActionMap().put("Player1 Down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.DOWN,0);}
        });

        // a - left
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "Player1 Left");
        this.getActionMap().put("Player1 Left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.LEFT,0);}
        });

        // add player2 controls
        // up arrow - up
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "Player2 Up");
        this.getActionMap().put("Player2 Up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.UP,1);}
        });

        // right arrow - right
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "Player2 Right");
        this.getActionMap().put("Player2 Right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.RIGHT,1);}
        });

        // down arrow - down
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "Player2 Down");
        this.getActionMap().put("Player2 Down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.DOWN,1);}
        });

        // left arrow - left
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "Player2 Left");
        this.getActionMap().put("Player2 Left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { game.setPlayerDirection(Direction.LEFT,1);}
        });

        // return to menu keybind
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "Return to menu");
        this.getActionMap().put("Return to menu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { exitGame(); }
        });

        // set gamePanel layout
        this.setLayout(new BorderLayout());

        // create timer
        this.time = 0;
        this.timer = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (game != null) {
                        updateLabel();
                        clearPlayerIcon();

                        GameState gameState = game.doRound();
                        paintPlayerLocation();

                        if (gameState != GameState.IN_PROGRESS) {
                            timer.stop();
                            String gameEndMessage = "";
                            switch (gameState) {
                                case DRAW -> {
                                    gameEndMessage = "Döntetlen.";
                                    paintDeadIcon(0);
                                }
                                case PLAYER1WON -> {
                                    gameEndMessage = game.getPlayerName(0) + " nyert.";
                                    paintDeadIcon(1); // change other player icon
                                }
                                case PLAYER2WON -> {
                                    gameEndMessage = game.getPlayerName(1) + " nyert.";
                                    paintDeadIcon(0); // change other player icon
                                }
                            }
                            JOptionPane.showMessageDialog(GameMenu.this, gameEndMessage, "Játék vége", JOptionPane.INFORMATION_MESSAGE);

                            exitGame();
                        }
                    }
                } catch (SQLException exception) {
                JOptionPane.showMessageDialog(GameMenu.this, "Adatbázis hiba történt! A keresett adatbázis nem elérhető vagy nem létezik.", "Hiba", JOptionPane.ERROR_MESSAGE);
                GameMenu.this.exitGame();
                }
            }
        });

        this.timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        this.timeLabel.setFont(this.timeLabel.getFont().deriveFont(25f));
        this.add(this.timeLabel, BorderLayout.NORTH);

        this.add(new JLabel("ESC - kilépés", SwingConstants.CENTER), BorderLayout.SOUTH);
    }

    /**
     * Validates data. Creates a new game if validation is successful.
     * @param player1Name    name of player 1
     * @param player1Color   color of player 1
     * @param player2Name    name of player 2
     * @param player2Color   color of player 2
     * @param horizontalSize horizontal size of the game
     * @param verticalSize   vertical size of the game
     * @throws IllegalArgumentException if validation in {@code GameModel} constructor is unsuccessful.
     */
    public void newGame(String player1Name, Color player1Color, String player2Name, Color player2Color, int horizontalSize, int verticalSize) throws IllegalArgumentException {
        this.game = new GameModel(player1Name, player1Color, player2Name, player2Color, horizontalSize, verticalSize);

        // switch to game tab
        CardLayout cardLayout = (CardLayout) (this.parent.getContentPane().getLayout());
        cardLayout.show(this.parent.getContentPane(), "Game");

        this.requestFocus(); // request focus for keyboard input

        this.drawMap();

        // start / restart timer
        this.timer.restart();

        // update timeLabel appearance based on game size
        this.timeLabel.setPreferredSize(new Dimension(horizontalSize * this.TILE_SIZE, 50));
    }

    /**
     * Discards current game, stops and resets the timer and switches GUI back to the main menu.
     */
    private void exitGame() {
        // discard current game
        this.game = null;

        // stop timer, reset time
        this.timer.stop();
        this.time = 0;

        //reset label text
        this.timeLabel.setText("00:00");

        //remove game map
        this.remove(((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER));

        this.parent.returnToMenu();
    }

    /**
     * Resizes the applications frame according to the maps size and creates a grid based on the state described in the {@code game} variable.
     */
    private void drawMap() {
        int horizontalSize = this.game.getHorizontalSize();
        int verticalSize = this.game.getVerticalSize();
        Dimension gamePanelDimension = new Dimension(horizontalSize * this.TILE_SIZE, verticalSize * this.TILE_SIZE + 100);

        // change panel and frame size
        this.setPreferredSize(gamePanelDimension);
        this.parent.setSize(gamePanelDimension);

        // create container for tiles
        JPanel gameAreaPanel = new JPanel();
        gameAreaPanel.setLayout(new GridLayout(verticalSize, horizontalSize));
        gameAreaPanel.setPreferredSize(new Dimension(horizontalSize * this.TILE_SIZE, verticalSize * this.TILE_SIZE));

        this.add(gameAreaPanel, BorderLayout.CENTER);

        // initialize gameArea
        this.gameArea = new JLabel[verticalSize][horizontalSize];

        for (int row = 0; row < verticalSize; row++) {
            for (int column = 0; column < horizontalSize; column++) {
                JLabel tile = new JLabel();
                tile.setOpaque(true);

                tile.setPreferredSize(new Dimension(this.TILE_SIZE, this.TILE_SIZE));

                tile.setBackground(this.game.getColor(row,column));
                tile.setBorder(BorderFactory.createLineBorder(new Color(144,221,231), 1));

                gameAreaPanel.add(tile);
                this.gameArea[row][column] = tile;
            }
        }
        paintPlayerLocation();
    }

    /**
     * Colors the {@code JLabel} at the location of the players to the corresponding color, and places icons at said tile.
     */
    private void paintPlayerLocation() {
        JLabel player1Location = gameArea[game.getVerticalPlayerLocation(0)][game.getHorizontalPlayerLocation(0)];
        JLabel player2Location = gameArea[game.getVerticalPlayerLocation(1)][game.getHorizontalPlayerLocation(1)];

        player1Location.setBackground(this.game.getPlayerColor(0));
        player2Location.setBackground(this.game.getPlayerColor(1));

        player1Location.setIcon(motorSprite);
        player2Location.setIcon(motorSprite);
    }

    /**
     * Removes player icons from the map.
     */
    private void clearPlayerIcon() {
        this.gameArea[game.getVerticalPlayerLocation(0)][game.getHorizontalPlayerLocation(0)].setIcon(null);
        this.gameArea[game.getVerticalPlayerLocation(1)][game.getHorizontalPlayerLocation(1)].setIcon(null);
    }

    /**
     * Paints the dead icon at the location of the given player.
     * @param player index of player
     */
    private void paintDeadIcon(int player) {
        this.gameArea[game.getVerticalPlayerLocation(player)][game.getHorizontalPlayerLocation(player)].setIcon(this.deathSprite);
    }

    /**
     * Updates the text of {@code this.TimeLabel}, in the format minutes:seconds.
     */
    private void updateLabel() {
        this.time++;

        int seconds = this.time % 60;
        int minutes = this.time / 60;

        StringBuilder stringBuilder = new StringBuilder();

        // add padding zero if needed
        if (minutes < 10) {
            stringBuilder.append(0);
        }

        stringBuilder.append(minutes);
        stringBuilder.append(':');

        // add padding zero if needed
        if (seconds < 10) {
            stringBuilder.append(0);
        }

        stringBuilder.append(seconds);

        this.timeLabel.setText(stringBuilder.toString());
    }
}