package com.tenikkan.arcana.level;

public class Level
{
    private int tiles[];
    private int width, height;
    
    public Level(int width, int height) 
    {
        this.width = width;
        this.height = height;
        
        initTiles();
    }
    
    public void setTileID(int x, int y, int id) 
    {
        if(!inBounds(x, y)) return;
        tiles[x + y*width] = id;
    }
    
    public int getTileID(int x, int y) 
    {
        if(!inBounds(x, y)) return -1;
        return tiles[x + y*width];
    }
    
    public Tile getTile(int x, int y) 
    {
        return TileManager.get(getTileID(x, y));
    }
    
    public boolean inBounds(int x, int y) 
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    private void initTiles() 
    {
        tiles = new int[width*height];
    }
}
