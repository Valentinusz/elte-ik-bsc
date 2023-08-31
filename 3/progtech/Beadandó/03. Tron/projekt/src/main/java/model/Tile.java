package main.java.model;

import java.awt.*;

/**
 * Class representing a Tile of the games map.
 * A tile is safe to enter if its color is {@code Tile.SAFE_COLOR}, otherwise it is unsafe.
 */
public class Tile {
    /** Color identifying safe tiles. */
    public static final Color SAFE_COLOR = new Color(41, 53, 66);

    /** Color of the tile. */
    private Color color;

    /** Constructor. */
    public Tile() {
        this.color = Tile.SAFE_COLOR;
    }

    /**
     * Constructor.
     * @param color color of the tile
     */
    public Tile(Color color) {
        this.color = color;
    }

    /** @return color of the tile */
    public Color getColor() {
        return color;
    }

    /**
     * Changes color of the tile.
     * @param color new value for color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if tile is considered safe to move to.
     * @return true if the color of the tile is {@code SAFE_COLOR}
     */
    public boolean isSafe() {
        return this.color.equals(SAFE_COLOR);
    }
}