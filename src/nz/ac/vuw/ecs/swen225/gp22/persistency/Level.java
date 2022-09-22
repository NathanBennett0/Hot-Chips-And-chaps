package nz.ac.vuw.ecs.swen225.gp22.persistency;
import java.util.ArrayList;

import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class Level {
    public ArrayList<Tile> tiles;
    public Chap player;

    public Level(ArrayList<Tile> tiles, Chap player) {
        this.tiles = tiles;
        this.player = player;
    }

    public void removeTile(Tile tile) {
        
    }

}
