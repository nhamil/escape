package com.tenikkan.escape.level;

public class EndTile extends Tile
{
    private int color;
    
    public EndTile(String name, int id, int color) 
    {
        super(name, id, true);
        
        this.color = color;
    }
    
    public boolean isSolid(int data) { return false; }
    public int getColorCode(int data) { return color; }
}
