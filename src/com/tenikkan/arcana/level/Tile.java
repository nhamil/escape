package com.tenikkan.arcana.level;

public abstract class Tile
{
    private int id;
    private String name;
    private boolean solid;
    
    public Tile(String name, int id, boolean solid, int color) 
    {
        this.name = name;
        this.id = id;
        
        TileManager.add(this);
    }
    
    public abstract void update(Level level, int x, int y, int data);
    public abstract void render();
    
    public boolean isSolid() { return solid; }
    
    public String getName() { return name; }
    
    public int getID() { return id; }
}
