package com.tenikkan.arcana.level;

public class BasicTile extends Tile
{
    private boolean solid;
    private int color;
    
    public BasicTile(String name, int id, boolean solid, int color) 
    {
        super(name, id);
        
        this.solid = solid;
        this.color = color;
    }
    
    public boolean isSolid(int data) { return solid; }
    public int getColorCode(int data) { return color; }
}
