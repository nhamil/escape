package com.tenikkan.arcana.level;

public class Level
{
    private TileData NULL_TILEDATA = new TileData(0);
    private TileData tiles[];
    private int width, height;
    
    public Level(int width, int height) 
    {
        this.width = width;
        this.height = height;
        
        initTiles();
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public void setTile(int x, int y, int id) 
    {
        if(!inBounds(x, y)) return;
        tiles[x + y*width].setTileID(id);
        tiles[x + y*width].setData(0);
    }
    
    public TileData getTileData(int x, int y) 
    {
        if(!inBounds(x, y)) return NULL_TILEDATA;
        return tiles[x + y*width];
    }
    
    public Tile getTile(int x, int y) 
    {
        return TileManager.get(getTileData(x, y).getTileID());
    }
    
    public boolean inBounds(int x, int y) 
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    private void initTiles() 
    {
        tiles = new TileData[width*height];
        for(int i = 0; i < tiles.length; i++)
            tiles[i] = new TileData((int)(Math.random() * 2) + 1);
        for(int y = height - 20; y < height; y++) 
        {
            for(int x = 0; x < width; x++) 
            {
                if(height - y < 20) setTile(x, y, 0);
                else if(Math.random() > 0.5) setTile(x, y, 0);
            }
        }
    }
}
