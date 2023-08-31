import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void tipTileFalse() {
        Tile tile = new Tile();
        Player player = new Player();
        Assert.assertFalse(tile.tipTile(player));
    }

    @Test
    public void tipTileTrue() {
        Tile tile = new Tile();
        Player player = new Player();
        tile.tipTile(player);
        tile.tipTile(player);
        tile.tipTile(player);
        Assert.assertTrue(tile.tipTile(player));
    }

    @Test
    public void tipTileReturnsTrueOnlyFirstTime() {
        Tile tile = new Tile();
        Player player = new Player();
        tile.tipTile(player);
        tile.tipTile(player);
        tile.tipTile(player);
        tile.tipTile(player);
        Assert.assertFalse(tile.tipTile(player));
    }

    @Test
    public void tipTileDoesNotIncreaseTipCountAboveFour() {
        Tile tile = new Tile();
        Player player = new Player();
        tile.tipTile(player);
        tile.tipTile(player);
        tile.tipTile(player);
        tile.tipTile(player);
        tile.tipTile(player);
        Assert.assertEquals(tile.getTipCount(),(byte)4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalBoardSize() {
        GameModel model = new GameModel(-1);
    }

}
