package com.tenikkan.arcana.level;

import com.tenikkan.arcana.Resource;

public class TileData
{
    private int tileID;
    private int data;
    
    public TileData(int tileID) 
    {
        this.tileID = tileID;
        data = 0;
    }
    
    public Tile getTile() { return Resource.getTileManager().get(tileID); }
    public int getTileID() { return tileID; }
    public void setTileID(int id) { tileID = id; }
    
    public int getData() { return data; }
    public void setData(int d) { data = d; }
    
    public int getColorCode() { return Resource.getTileManager().get(tileID).getColorCode(data); }
    
    public boolean isSolid() { return Resource.getTileManager().get(tileID).isSolid(data); }
}
