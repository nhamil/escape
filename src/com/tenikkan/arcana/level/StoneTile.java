package com.tenikkan.arcana.level;

public class StoneTile extends Tile
{
    public StoneTile(int id)
    {
        super("stone", id);
    }
    
    public int getColorCode(int data) 
    {
        return 0x7f7f7f;
    }
    
    public boolean isSolid(int data) 
    {
        return true;
    }
}
