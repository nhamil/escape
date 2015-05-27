package com.tenikkan.escape.level;

import java.awt.image.BufferedImage;

import com.tenikkan.escape.Resource;
import com.tenikkan.util.Identifiable;

public abstract class Tile implements Identifiable
{
    private int id;
    private String name;
    private boolean endTile;
    private BufferedImage img;
    
    public Tile(String name, int id, boolean endTile, String imgLocation) 
    {
        this.name = name;
        this.id = id;
        this.endTile = endTile;
        img = Resource.loadImage(imgLocation);
    }
    
    public BufferedImage getImage() { return img; }
    
    public abstract int getColorCode(int data);
    
    public abstract boolean isSolid(int data);
    
    public boolean isEndTile() { return endTile; }
    
    public String getName() { return name; }
    
    public int getID() { return id; }
}
