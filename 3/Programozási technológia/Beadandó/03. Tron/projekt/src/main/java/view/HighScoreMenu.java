package main.java.view;

import main.java.database.HighScore;
import main.java.database.HighScores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

/** Highscores menu of the application. */
public class HighScoreMenu extends JPanel {
    /** Table model for the table */
    private final DefaultTableModel tableModel;

    /** Parent of the component */
    private final GameView parent;

    /**
     * Constructor.
     * @param parent parent of the component
     */
    public HighScoreMenu(GameView parent)  {
        this.parent = parent;

        // set layout, size
        this.setLayout(new BorderLayout());
        this.setPreferredSize(this.parent.FRAME_DIMENSION);

        // add title
        JLabel header  = new JLabel("Legjobb eredmények", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(30f));
        this.add(header, BorderLayout.NORTH);

        // create new table model with the following columns
        this.tableModel = new DefaultTableModel(new String[]{"Név", "Pont"}, 0);

        // create table based on table model
        JTable highScoreTable = new JTable(this.tableModel);
        highScoreTable.setDefaultEditor(Object.class, null); // make table uneditable
        this.add(new JScrollPane(highScoreTable), BorderLayout.CENTER);

        // create return to main menu button
        JButton backButton = new JButton("Vissza");
        backButton.addActionListener(e -> this.parent.returnToMenu());
        this.add(backButton, BorderLayout.SOUTH);
    }

    /** Updates table model. Shows error dialog if a database access error occurs. */
    public void updateTable() {
        tableModel.setRowCount(0);
        try {
            // request database data
            ArrayList<HighScore> data = HighScores.instance().getTopScores();

            // add data to table model
            for (HighScore highScore : data) {
                Object[] row = {highScore.name(), highScore.score()};
                this.tableModel.addRow(row);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Adatbázis hiba történt! A keresett adatbázis nem elérhető vagy nem létezik.", "Hiba", JOptionPane.ERROR_MESSAGE);
            this.parent.returnToMenu();
        }
    }
}
