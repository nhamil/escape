package com.tenikkan.arcana.level;

public class DirtTile extends Tile
{
    public DirtTile(int id)
    {
        super("dirt", id);
    }
    
    public int getColorCode(int data) 
    {
        return 0x797900;
    }
    
    public boolean isSolid(int data) 
    {
        return true;
    }
}
