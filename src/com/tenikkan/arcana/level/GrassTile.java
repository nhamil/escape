package com.tenikkan.arcana.level;

public class GrassTile extends Tile
{
    public GrassTile(int id)
    {
        super("grass", id);
    }
    
    public int getColorCode(int data) 
    {
        return 0x11aa33;
    }
    
    public boolean isSolid(int data) 
    {
        return true;
    }
}
