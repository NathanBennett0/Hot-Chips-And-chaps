package nz.ac.vuw.ecs.swen225.gp22.persistency;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class Level {
    public List<Tile> tiles;
    public List<Locked> lockedtiles;
    public List<Key> keytiles;
    public Chap player;
    public List<Key> inventory;
    // add inventory
    // seperate keys from tiles

    public Level(List<Tile> tiles, List<Locked> lockedtiles, List<Key> keytiles, Chap player) {
        this.tiles = tiles;
        this.lockedtiles = lockedtiles;
        this.keytiles = keytiles;
        this.player = player;
        this.inventory = new ArrayList<Key>();
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

    public Chap getChap() {
        return player;
    }

    public List<Key> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public void removeKeyTile(Key key) {
        keytiles.remove(key);
    }

    public void removeLockedTile(Locked locked) {
        lockedtiles.remove(locked);
    }

}
