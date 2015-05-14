package com.tenikkan.arcana.level;

public class AirTile extends Tile
{
    public AirTile(int id) 
    {
        super("grass", id);
    }
    
    public int getColorCode(int data) 
    {
        return 0x66bbff;
    }
    
    public boolean isSolid(int data) 
    {
        return false;
    }
}
