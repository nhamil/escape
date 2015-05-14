package com.tenikkan.arcana.level;

public class TileManager
{
    private static Tile[] tiles = new Tile[256];
    
    public static void setSize(int amt) { tiles = new Tile[amt]; }
    
    public static void add(Tile tile) 
    { 
        if(tile.getID() < 0 || tile.getID() >= tiles.length) return;
        tiles[tile.getID()] = tile; 
    }
    
    public static Tile get(int id) 
    { 
        if(id < 0 || id >= tiles.length) return tiles[0];
        return tiles[id]; 
    };
}
