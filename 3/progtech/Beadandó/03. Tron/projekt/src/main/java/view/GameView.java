package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/** Frame of the application. */
public class GameView extends JFrame {
    /** Size of the applications frame. */
    public final Dimension FRAME_DIMENSION  = new Dimension(500, 400);

    /** Component containing the GUI for the main menu. */
    private final MainMenu mainMenu;

    /** Component containing the GUI of the game. */
    private final GameMenu gameMenu;

    /** Component containing the GUI for the highscore database. */
    private final HighScoreMenu highScoreMenu;

    /**
     * Constructor.
     */
    public GameView() {
        // style frame
        this.setTitle("Tron");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.FRAME_DIMENSION);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resources/helmet.png"))).getImage());

        // create card layout
        this.getContentPane().setLayout(new CardLayout());

        this.mainMenu      = new MainMenu(this);
        this.gameMenu      = new GameMenu(this);
        this.highScoreMenu = new HighScoreMenu(this);

        this.getContentPane().add(mainMenu,           "Main menu" );
        this.getContentPane().add(this.gameMenu,      "Game"      );
        this.getContentPane().add(this.highScoreMenu, "Score menu");

        this.setVisible(true);
    }

    /**
     * Switches card shown to the main menu. Changes frame size back to the one used in the main menu.
     */
    public void returnToMenu() {
        ((CardLayout) (this.getContentPane().getLayout())).show(this.getContentPane(), "Main menu");
        this.setSize(this.FRAME_DIMENSION);
    }

    /**
     * Tries creating a new game based on parameters. Shows an error dialog to user if unsuccessful.
     * @param player1Name       name of player1
     * @param player1Color      color of player1
     * @param player2Name       name of player2
     * @param player2Color      color of player2
     * @param horizontalSizeRaw horizontal map size as a string
     * @param verticalSizeRaw   vertical map size as a string
     */
    public void newGame(String player1Name, Color player1Color, String player2Name, Color player2Color, String horizontalSizeRaw, String verticalSizeRaw) {
        try {
            // remove extra whitespace from player names
            player1Name = player1Name.trim();
            player2Name = player2Name.trim();

            // try parsing map size input, NumberFormatException is thrown if not successful
            int horizontalSize = Integer.parseInt(horizontalSizeRaw);
            int verticalSize   = Integer.parseInt(verticalSizeRaw);

            this.gameMenu.newGame(player1Name, player1Color, player2Name, player2Color, horizontalSize, verticalSize);

            // show error message if an error occurs
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Érvénytelen pályaméret!", "Hiba", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Request table data update. */
    public void requestScores() {
        this.highScoreMenu.updateTable();
    }
}
