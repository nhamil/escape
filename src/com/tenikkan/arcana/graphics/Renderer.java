package com.tenikkan.arcana.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.tenikkan.arcana.Camera;
import com.tenikkan.arcana.level.Level;

public class Renderer
{
    private Graphics g;
    private Camera cam;
    private int width, height;
    
    public Renderer(Graphics g, Camera cam, int width, int height) 
    {
        this.g = g;
        this.width = width;
        this.height = height;
        this.cam = cam;
    }
    
    public void drawLevel(Level level) 
    {
        float cx = cam.getPosition().getX();
        float cy = cam.getPosition().getY();
        
        int xStart = getXCoord(-cx);
        int yStart = getYCoord(-cy);
        
        int xIStart = (int)(cx - width / cam.getPixelsPerUnit() / 2);
        int yIStart = (int)(cy - height / cam.getPixelsPerUnit() / 2);
        
        for(int y = -1; y < height / cam.getPixelsPerUnit(); y++) 
        {
            int yy = y + yIStart;
            int yDraw = getYDisplacement(yy) + yStart;
            for(int x = -1; x < width / cam.getPixelsPerUnit(); x++) 
            {
                int xx = x + xIStart;
                int xDraw = getXDisplacement(xx) + xStart;
                g.setColor(new Color(level.getTileData(xx, yy).getColorCode()));
                g.fillRect(xDraw, yDraw, cam.getIntPixelsPerUnit(), cam.getIntPixelsPerUnit());
            }
        }
    }
    
    private int getXDisplacement(float x) 
    {
        return (int)Math.floor(x * cam.getPixelsPerUnit());
    }
    
    private int getYDisplacement(float y) 
    {
        return (int)Math.floor(y * cam.getPixelsPerUnit());
    }
    
    private int getXCoord(float x) 
    {
        return (int)Math.floor(width / 2 + getXDisplacement(x));
    }
    
    private int getYCoord(float y) 
    {
        return (int)Math.floor(height / 2 - getYDisplacement(y));
    }
}
