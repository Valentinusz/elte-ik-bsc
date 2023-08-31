/**
 * @author Valentinusz / KDPHNI / Boda Bálint
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI for the game.
 */
public class GameGUI {
    /**
     * Frame of the game.
     */
    private final JFrame frame;

    /**
     * n*n matrix of buttons with event listeners.
     */
    private JButton[][] board;

    /**
     * label displaying which player has their turn at the moment.
     */
    private JLabel turnLabel;

    /**
     * array of labels which display the score of the players.
     */
    private JLabel[] scoreLabels;

    /**
     * inner logic of the game.
     */
    private GameModel game;

    /**
     * Constructor for class. Only initializes components that are not reset upon creation of a new game.
     */
    public GameGUI() {
        //create frame
        this.frame = new JFrame("4x4-es játék");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setPreferredSize(new Dimension(350,200));
        this.frame.setLayout(new BorderLayout());

        //create menu
        JMenuBar menuBar = new JMenuBar();
        this.frame.setJMenuBar(menuBar);

        //create menu entry
        JMenu gameMenu = new JMenu("Új játék");
        menuBar.add(gameMenu);

        for (int boardSize : new int[]{3, 5, 7}) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            gameMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(e -> {
                this.frame.getContentPane().removeAll();
                setupGame(boardSize);
                this.frame.pack();
            });
        }

        this.frame.pack();
        this.frame.setVisible(true);

    }

    /**
     * Removes all existing GUI components and creates a new GUI with a board of the same size.
     */
    private void resetGame() {
        this.frame.getContentPane().removeAll();
        setupGame(board.length);
        this.frame.pack();
    }

    /**
     * Sets up a game and for it a GUI of given {@code boardSize}.
     * @param boardSize size of the board
     */
    private void setupGame(int boardSize) {
        int tileSize = 75;

        // initialize game model
        this.game = new GameModel(boardSize);

        // set up turn display
        this.turnLabel = new JLabel("Egyes játékos köre");
        JPanel turnPanel = new JPanel();
        turnPanel.add(this.turnLabel);
        this.frame.add(turnPanel, BorderLayout.NORTH);

        // set up score display
        JPanel[] scorePanels = new JPanel[2];
        this.scoreLabels = new JLabel[2];

        scorePanels[0] = new JPanel();
        scorePanels[0].setPreferredSize(new Dimension(160, boardSize * tileSize));
        this.scoreLabels[0] = new JLabel("0");
        scorePanels[0].add(new JLabel("Egyes játékos pontszáma:"));
        scorePanels[0].add(this.scoreLabels[0]);
        this.frame.add(scorePanels[0], BorderLayout.WEST);

        scorePanels[1] = new JPanel();
        scorePanels[1].setPreferredSize(new Dimension(160, boardSize * tileSize));
        this.scoreLabels[1] = new JLabel("0");
        scorePanels[1].add(new JLabel("Kettes játékos pontszáma:"));
        scorePanels[1].add(this.scoreLabels[1]);
        this.frame.add(scorePanels[1], BorderLayout.EAST);

        // set up board
        JPanel boardPanel = new JPanel();
        boardPanel.setSize(boardSize * tileSize, boardSize * tileSize);
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        this.board = new JButton[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // set up button
                JButton button = new JButton();
                button.setBackground(Color.lightGray);
                button.addActionListener(new ButtonListener(i,j));
                button.setPreferredSize(new Dimension(tileSize, tileSize));
                button.setText(String.valueOf(this.game.getTile(i, j).getTipCount()));

                // add button to button matrix
                this.board[i][j] = button;

                // add button to panel
                boardPanel.add(button);
            }
        }
        // change frame size to match board size
        this.frame.setPreferredSize(new Dimension(boardSize * tileSize + 300, boardSize * tileSize + 50));
        this.frame.add(boardPanel, BorderLayout.CENTER);
    }

    /**
     * ActionListener implementation for the buttons on the board.
     */
    class ButtonListener implements ActionListener {
        /**
         * Row index of the button.
         */
        private final int row;
        /**
         * Column index of the button.
         */
        private final int column;

        /**
         * Constructor.
         * @param row row index
         * @param column column index
         */
        public ButtonListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int currentPlayerIndex = game.getCurrentPlayerIndex();
            Player player = game.getCurrentPlayer();

            // update affected buttons
            ArrayList<int[]> tipped = game.tipTile(row, column);

            for (int[] tile : tipped) {
                int x = tile[0];
                int y = tile[1];

                // disable button if fully tipped
                if (tile[2] == 1) {
                    board[x][y].setBackground(game.getCurrentPlayerIndex() == 0 ? Color.BLUE : Color.RED);
                    board[x][y].removeActionListener(board[x][y].getActionListeners()[0]);
                    scoreLabels[currentPlayerIndex].setText(String.valueOf(player.getScore()));
                    board[x][y].setEnabled(false);
                }

                // update button text
                board[x][y].setText(String.valueOf(game.getTile(x,y).getTipCount()));
            }

            //change turn label
            if (currentPlayerIndex == 0) {
                turnLabel.setText("Kettes játékos köre");
            } else {
                turnLabel.setText("Egyes játékos köre");
            }

            //chane turn
            game.endTurn();

            // check if game is over
            if (game.isOver()) {
                JOptionPane.showMessageDialog(frame,
                        game.getWinner() == 0 ? "A játékot az egyes játékos nyerte." : "A játékot a kettes játékos nyerte.",
                        "Játék vége.", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            }

        }
    }
}
