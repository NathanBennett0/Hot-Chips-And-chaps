package nz.ac.vuw.ecs.swen225.gp22.persistency;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import nz.ac.vuw.ecs.swen225.gp22.domain.Actor;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;

/*
 * @author Nathan Bennett
 * bennetnath1
 * 300580123 
 */

 /**
  * Level class, mainly uses as a data storage for all of the objects in the physical maze
  */
public class Level {
    private List<Tile> tiles;
    private List<Locked> lockedtiles;
    private List<Key> keytiles;
    private Chap player;
    private List<Tile> inventory;
    private InfoField info;
    private int time;
    private int levelnum;
    private Actor actor;

    /**
     * Level constructor
     * @param tiles
     * @param lockedtiles
     * @param keytiles
     * @param player
     * @param info
     * @param time
     * @param levelnum
     * @param actor
     */
    public Level(List<Tile> tiles, List<Locked> lockedtiles, List<Key> keytiles, Chap player, InfoField info, int time, int levelnum, Actor actor) {
        this.tiles = tiles;
        this.lockedtiles = lockedtiles;
        this.keytiles = keytiles;
        this.player = player;
        this.info = info;
        this.inventory = new ArrayList<Tile>();
        this.time = time;
        this.levelnum = levelnum;
        this.actor = actor;
    }

    /**
     * Returns the int corresponding to the level number
     * @return int
     */
    public int getLevel() {
        return levelnum;
    }

    /**
     * Returns all of the tiles exluding locked and keys
     * @return List
     */
    public List<Tile> getTiles() {
        return Collections.unmodifiableList(tiles);
    }

    /**
     * Returns all of the locked tiles
     * @return List
     */
    public List<Locked> getLockedTiles() {
        return Collections.unmodifiableList(lockedtiles);
    }

    /**
     * Returns all of the key tiles
     * @return List
     */
    public List<Key> getKeyTiles() {
        return Collections.unmodifiableList(keytiles);
    }

    /**
     * Returns a list of all the tiles that make up the board
     * @return List
     */
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

    /**
     * Returns the chap
     * @return Chap
     */
    public Chap getChap() {
        return player;
    }

    /**
     * Returns the actor
     * @return Actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Returns the infofield
     * @return InfoField
     */
    public InfoField getInfoField() {
        return info;
    }

    /**
     * Returns the time remaining
     * @return int
     */
    public int getTime() {
        return time;
    }
    
    /**
     * Returns the inventory of keys and treasures
     * @return
     */
    public List<Tile> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    /**
     * Removes the given key tile from the keytiles list
     * @param key
     */
    public void removeKeyTile(Key key) {
        keytiles.remove(key);
    }

    /**
     * Removes the given locked tile from the lockedtiles list
     * @param locked
     */
    public void removeLockedTile(Locked locked) {
        lockedtiles.remove(locked);
    }

    /**
     * Removes the given treasure tile from the tilelist
     * @param treasure
     */
    public void removeTreasureTile(Treasure treasure) {
        tiles.remove(treasure);
    }

    /**
     * Adds given tile to the inventory
     * @param T
     */
    public void addTileToInventory(Tile T) {
        inventory.add(T);
    }

}
