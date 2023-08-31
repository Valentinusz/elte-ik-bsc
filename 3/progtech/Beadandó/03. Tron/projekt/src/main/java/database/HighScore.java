package main.java.database;

/**
 * Record representing the score of a player.
 * @param name name of the player
 * @param score score of the player
 */
public record HighScore(String name, int score) {}
