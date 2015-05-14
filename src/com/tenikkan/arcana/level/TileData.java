package com.tenikkan.arcana.level;

public class TileData
{
    private int tileID;
    private int data;
    
    public TileData(int tileID) 
    {
        this.tileID = tileID;
        data = 0;
    }
    
    public Tile getTile() { return TileManager.get(tileID); }
    public int getTileID() { return tileID; }
    public void setTileID(int id) { tileID = id; }
    
    public int getData() { return data; }
    public void setData(int d) { data = d; }
    
    public int getColorCode() { return TileManager.get(tileID).getColorCode(data); }
    
    public boolean isSolid() { return TileManager.get(tileID).isSolid(data); }
}
