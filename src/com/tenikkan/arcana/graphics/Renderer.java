package com.tenikkan.arcana.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.tenikkan.arcana.Camera;
import com.tenikkan.arcana.entity.Entity;
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
        
        int ppuInt = cam.getIntPixelsPerUnit();
        
        int xCenter = (int)Math.floor(cx);
        int yCenter = (int)Math.floor(cy);
        
        int w2 = width  / (2 *ppuInt);
        int h2 = height / (2 * ppuInt);
        
        for(int y = -h2 - 2; y < h2 + 3; y++) 
        {
            int yIndex = yCenter + y;
            for(int x = -w2 - 2; x < w2 + 3; x++) 
            {
                int xIndex = xCenter + x;
                g.setColor(new Color(level
                        .getTileData(xIndex, yIndex)
                        .getColorCode()));
                drawRect(xIndex, yIndex, 1, 1);
            }
        }
    }
    
    public void drawEntity(Entity e) 
    {
        float x = e.getPosition().getX();
        float y = e.getPosition().getY();
        float w = e.getWidth();
        float h = e.getHeight();
        
        g.setColor(new Color(e.getColorCode()));
        drawRect(x, y, w, h);
    }
    
    private void drawRect(float x, float y, float w, float h) 
    {
        float cx = cam.getPosition().getX();
        float cy = cam.getPosition().getY();
        
        float ppu = cam.getPixelsPerUnit();
        
        x = x - cx;
        y = cy - y;
        
        float yDraw = height/2f + y*ppu - h * ppu;
        float xDraw = width/2f + x*ppu;
        
        g.fillRect((int)Math.ceil(xDraw), (int)Math.ceil(yDraw), (int)Math.ceil(w * ppu), (int)Math.ceil(h * ppu));
    }
}
