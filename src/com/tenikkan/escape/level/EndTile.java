package com.tenikkan.escape.level;

public class EndTile extends Tile
{
    private int color;
    
    public EndTile(String name, int id, String img, int color) 
    {
        super(name, id, true, img);
        
        this.color = color;
    }
    
    public boolean isSolid(int data) { return false; }
    public int getColorCode(int data) { return color; }
}
