package com.tenikkan.arcana.level;

import com.tenikkan.arcana.Resource;
import com.tenikkan.arcana.entity.Entity;
import com.tenikkan.math.Vector2f;
import com.tenikkan.util.Manager;

public class Level
{
    private TileData NULL_TILEDATA = new TileData(255);
    private TileData tiles[];
    private int width, height;
    
    private Vector2f gravity = new Vector2f(0, -0.03f);
    
    private Manager<Entity> entities;
    
    public Level(int width, int height) 
    {
        this.width = width;
        this.height = height;
        
        initTiles();
        genSimpleLevel();
        
        entities = new Manager<Entity>(1000);
    }
    
    public void update() 
    {
        int size = getEntities().size(); 
        
        for(int i = 0; i < size; i++) 
        {
            Entity e = getEntities().get(i);
            if(e == null) continue;
            e.accelerate(gravity); 
            e.update(this); 
        }
    }
    
    public Manager<Entity> getEntities() { return entities; }
    
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
        return Resource.getTileManager().get(getTileData(x, y).getTileID());
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
    }
    
    private void genSimpleLevel() 
    {
        for(int i = 0; i < tiles.length; i++)
            tiles[i] = new TileData(0);
        
        {
            int y = (int)(Math.random() * height);
            for(int x = 0; x < width; x++) 
            {
                if(y < 0) y = 0;
                if(y >= height) y = height - 1;
                
                for(int yy = y; yy >= 0; yy--) 
                {
                    int id = (y - yy < 1) ? 1 : 2;
                    setTile(x, yy, id);
                }
                
                y += (int)(Math.random() * 3) - 1;
            }
        }
        
        int amt = width*height / 160;
        for(int i = 0; i < amt; i++) 
        {
            int x = (int)(Math.random() * width);
            int y = (int)(Math.random() * height);
            for(int j = 0; j < 30; j++) 
            {
                if(getTileData(x, y).getTileID() == 2) 
                {
                    setTile(x, y, 3);
                }
                x += (int)(Math.random() * 3) - 1;
                y += (int)(Math.random() * 3) - 1;;
            }
        }
    }
}
