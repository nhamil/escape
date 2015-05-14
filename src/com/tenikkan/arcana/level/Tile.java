package com.tenikkan.arcana.level;

public abstract class Tile
{
    private int id;
    private String name;
    
    public Tile(String name, int id) 
    {
        this.name = name;
        this.id = id;
    }
    
    public abstract int getColorCode(int data);
    
    public abstract boolean isSolid(int data);
    
    public String getName() { return name; }
    
    public int getID() { return id; }
}
