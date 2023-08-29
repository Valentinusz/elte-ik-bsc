package main.java.model;

import java.awt.Color;

/** Class representing a player of the game. */
public class Player {
    /** name of the player */
    private final String name;

    /** color of the player */
    private final Color color;

    /** horizontal location of the player */
    private int horizontalLocation;

    /** vertical location of the player */
    private int verticalLocation;

    /** direction of the player */
    private Direction direction;

    /**
     * Constructor.
     * @param name               name of the player
     * @param color              color of the player
     * @param horizontalLocation horizontal location of the player
     * @param verticalLocation   vertical location of the player
     * @param direction          direction of the player
     */
    public Player(String name, Color color, int horizontalLocation, int verticalLocation, Direction direction) {
        this.name = name;
        this.color = color;
        this.horizontalLocation = horizontalLocation;
        this.verticalLocation = verticalLocation;
        this.direction = direction;
    }

    /** Changes location of player based on their current direction. */
    public void move() {
        switch (direction) {
            case UP -> verticalLocation--;
            case DOWN -> verticalLocation++;
            case LEFT -> horizontalLocation--;
            case RIGHT -> horizontalLocation++;
        }
    }

    /** @return name of the player */
    public String getName() {
        return name;
    }

    /** @return color of the player */
    public Color getColor() {
        return color;
    }

    /** @return horizontal location of the player */
    public int getHorizontalLocation() {
        return horizontalLocation;
    }

    /** @return vertical location of the player */
    public int getVerticalLocation() {
        return verticalLocation;
    }

    /**
     * Changes location of player.
     * @param direction new value for direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
