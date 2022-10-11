package nz.ac.vuw.ecs.swen225.gp22.persistency;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;

public class Level {
    private List<Tile> tiles;
    private List<Locked> lockedtiles;
    private List<Key> keytiles;
    private Chap player;
    private List<Tile> inventory;
    private InfoField info;
    private int time;
    private int levelnum;

    public Level(List<Tile> tiles, List<Locked> lockedtiles, List<Key> keytiles, Chap player, InfoField info, int time, int levelnum) {
        this.tiles = tiles;
        this.lockedtiles = lockedtiles;
        this.keytiles = keytiles;
        this.player = player;
        this.info = info;
        this.inventory = new ArrayList<Tile>();
        this.time = time;
        this.levelnum = levelnum;
    }

    public int getLevel() {
        return levelnum;
    }

    public List<Tile> getTiles() {
        return Collections.unmodifiableList(tiles);
    }

    public List<Locked> getLockedTiles() {
        return Collections.unmodifiableList(lockedtiles);
    }

    public List<Key> getKeyTiles() {
        return Collections.unmodifiableList(keytiles);
    }

    // return a list of all tiles, used for drawing
    public List<Tile> getAllTiles() {
        List<Tile> allTiles = new ArrayList<Tile>();
        for(Tile T : tiles) {
            allTiles.add(T);
        }
        for(Tile T : lockedtiles) {
            allTiles.add(T);
        }
        for(Tile T : keytiles) {
            allTiles.add(T);
        }
        return allTiles;
    }

    public Chap getChap() {
        return player;
    }

    public InfoField getInfoField() {
        return info;
    }

    public int getTime() {
        return time;
    }
    
    public List<Tile> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public void removeKeyTile(Key key) {
        keytiles.remove(key);
    }

    public void removeLockedTile(Locked locked) {
        lockedtiles.remove(locked);
    }

    public void removeTreasureTile(Treasure treasure) {
        tiles.remove(treasure);
    }

    public void addTileToInventory(Tile T) {
        inventory.add(T);
    }

}
