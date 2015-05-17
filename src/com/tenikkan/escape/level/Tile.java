package com.tenikkan.escape.level;

import com.tenikkan.util.Identifiable;

public abstract class Tile implements Identifiable
{
    private int id;
    private String name;
    private boolean endTile;
    
    public Tile(String name, int id, boolean endTile) 
    {
        this.name = name;
        this.id = id;
        this.endTile = endTile;
    }
    
    public abstract int getColorCode(int data);
    
    public abstract boolean isSolid(int data);
    
    public boolean isEndTile() { return endTile; }
    
    public String getName() { return name; }
    
    public int getID() { return id; }
}
