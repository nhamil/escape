package com.tenikkan.arcana.level;

public class AirTile extends Tile
{
    private boolean isAir;
    
    public AirTile(int id, boolean wall) 
    {
        super("grass", id);
        isAir = wall;
    }
    
    public int getColorCode(int data) 
    {
        return 0x66bbff;
    }
    
    public boolean isSolid(int data) 
    {
        return isAir;
    }
}
