package com.tenikkan.arcana.level;

public abstract class Tile
{
    private int id;
    private String name;
    private boolean solid;
    private int color;
    
    public Tile(String name, int id, boolean solid, int color) 
    {
        this.name = name;
        this.id = id;
        this.solid = solid;
        this.color = color;
        
        TileManager.add(this);
    }
    
    public int getColorCode() { return color; }
    
    public boolean isSolid() { return solid; }
    
    public String getName() { return name; }
    
    public int getID() { return id; }
}
