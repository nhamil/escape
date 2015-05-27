package com.tenikkan.escape.level;

import com.tenikkan.escape.Physics;
import com.tenikkan.escape.Resource;
import com.tenikkan.escape.entity.EnemyProjectile;
import com.tenikkan.escape.entity.Entity;
import com.tenikkan.escape.entity.Projectile;
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
        
        Object[] arrows = getEntities().getAll("arrow");
        
        for(int i = 0; i < size; i++) 
        {
            Entity e = getEntities().get(i);
            if(e == null) continue;
            if(e.flaggedForDelete()) 
            {
                getEntities().remove(e.getID());
            } else 
            {
                e.accelerate(gravity.mul(e.gravityAmount())); 
                if(e instanceof Projectile && e.colliding()) e.flagForDelete();
                if(e instanceof EnemyProjectile && e.colliding()) e.flagForDelete();
                e.update(this); 
                e.setTouchingEndTile(Physics.collideEndTile(e, this)); 
                if(e.getName().equalsIgnoreCase("simple_enemy")) 
                {
                    for(Object arrow : arrows) 
                    {
                        if(!((Entity)arrow).flaggedForDelete() && Physics.collideEntities(e, (Entity)arrow))
                        {
                            e.changeHealth(-((Entity)arrow).getDamage());
                            e.applyKnockback((Entity)arrow);
                            ((Entity)arrow).flagForDelete();
                            if(e.getHealth() <= 0) 
                            {
                                e.flagForDelete();
                                continue;
                            } 
                        }
                    }
                }
            }
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
    
    public int getTopY(int x) 
    {
        if(!inBounds(x, 0)) return -1;
        for(int y = height - 1; y >= 0; y--) 
            if(getTileData(x, y).isSolid()) return y;
        return 0;
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
        int minY = height/2 - 10;
        int maxY = height/2 + 0;
        
        for(int i = 0; i < tiles.length; i++)
            tiles[i] = new TileData(0);
        
        {
            int y = (int)(Math.random() * height);
            for(int x = 0; x < width; x++) 
            {
                if(y < minY) y = minY;
                if(y >= maxY) y = maxY - 1;
                
                for(int yy = y; yy >= 0; yy--) 
                {
                    int id = (y - yy < 1) ? getTileType(1) : getTileType(2);
                    setTile(x, yy, id);
                }
                
                if(Math.random() < 0.5) y += (int)(Math.random() * 3) - 1;
            }
        }
        
        int amt = width*height / 160;
        for(int i = 0; i < amt; i++) 
        {
            int x = (int)(Math.random() * width);
            int y = (int)(Math.random() * height);
            for(int j = 0; j < 30; j++) 
            {
                if(getTileData(x, y).getTileID()%10 == 2) 
                {
                    setTile(x, y, getTileType(3));
                }
                x += (int)(Math.random() * 3) - 1;
                y += (int)(Math.random() * 3) - 1;;
            }
        }
        
        int endX = getWidth() - 4;
        int endY = getTopY(endX) + 4;
        int endID = Resource.getTileManager().get("end_tile").getID();
        
        setTile(endX + 0, endY + 0, endID);
        setTile(endX + 1, endY + 0, endID);
        setTile(endX + 0, endY + 1, endID);
        setTile(endX + 1, endY + 1, endID);
    }
    
    private int getTileType(int baseID) 
    {
        return baseID + 10*(int)(Math.random() * 3);
    }
}
