/**
 * @author Valentinusz / KDPHNI / Boda Bálint
 */

/**
 * Class representing a single tile of the board.
 */
public class Tile {
    /**
     * Attribute representing how many times a tile has been touched.
     */
    private byte tipCount;

    /**
     * Constructor for class.
     */
    public Tile() {
        this.tipCount = 0;
    }

    /**
     * @param player tipper of the tile
     * @return true if {@code this.tipCount} reaches four
     */
    public boolean tipTile(Player player) {
        if (this.tipCount < 4) {
            this.tipCount++;
            if (this.tipCount == 4) {
                player.incrementScore();
                return true;
            }
        }
        return false;
    }

    /**
     * @return tipCount of the tile
     */
    public byte getTipCount() {
        return tipCount;
    }
}
