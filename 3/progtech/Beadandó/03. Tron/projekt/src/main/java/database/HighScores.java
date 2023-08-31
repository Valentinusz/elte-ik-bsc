package main.java.database;

import java.sql.*;
import java.util.ArrayList;

/** Singleton. Class responsible for connecting to the database and making queries. */
public class HighScores {
    /** Singleton instance of the class. */
    private static HighScores instance;

    /** Database connection. */
    private final Connection connection;

    /**
     * Constructor.
     * @throws SQLException if a database access error occurs
     */
    private HighScores() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tron", "root", "admin");
    }

    /**
     * @return singleton instance of the class
     * @throws SQLException if a database access error occurs
     */
    public static HighScores instance() throws SQLException {
        if (HighScores.instance == null) {
            HighScores.instance = new HighScores();
        }
        return instance;
    }

    /**
     * Inserts the score of a player into the database. If the player already has a record increments their score by one.
     * @param playerName name of the player
     * @throws SQLException if a database access error occurs
     */
    public void insertScore(String playerName) throws SQLException {
        // check if the database contains a record that belongs to the given player
        PreparedStatement selectPlayerID = this.connection.prepareStatement("SELECT id FROM highscores WHERE Name = ?");
        selectPlayerID.setString(1, playerName);

        ResultSet resultSet = selectPlayerID.executeQuery();

        // already existing records are updated so resultSet either contains 1 or 0 elements
        if (resultSet.next()) {
            // player record already exists
            int id = resultSet.getInt(1);
            PreparedStatement updateRecord = this.connection.prepareStatement("UPDATE highscores SET score = score + 1 WHERE id = ?");
            updateRecord.setInt(1, id);
            updateRecord.executeUpdate();
        } else {
            // new player record has to be created
            // primary key is auto-incremental, so inserting name and score of one 1 is enough
            PreparedStatement insertRecord = this.connection.prepareStatement("INSERT INTO highscores (name, score) VALUES (?, 1)");
            insertRecord.setString(1, playerName);
            insertRecord.executeUpdate();
        }
    }

    /**
     * @return ArrayList created from records that have a score value that is in the best 10 scores in the table.
     * @throws SQLException if a database access error occurs
     */
    public ArrayList<HighScore> getTopScores() throws SQLException {
        ArrayList<HighScore> topScores = new ArrayList<>();

        // select records that a score value that is in the top 10 of scores
        PreparedStatement selectTop = this.connection.prepareStatement("SELECT name, score FROM highscores ORDER BY score DESC LIMIT 10 ");
        ResultSet resultSet = selectTop.executeQuery();

        while (resultSet.next()) {
            topScores.add(new HighScore(resultSet.getString(1), resultSet.getInt(2)));
        }

        return topScores;
    }
}