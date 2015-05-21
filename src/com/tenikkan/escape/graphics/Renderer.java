package com.tenikkan.escape.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.tenikkan.escape.Camera;
import com.tenikkan.escape.entity.Entity;
import com.tenikkan.escape.level.Level;

public class Renderer
{
    private Camera cam;
    private int width, height;
    
    public Renderer(Camera cam, int width, int height) 
    {
        this.width = width;
        this.height = height;
        this.cam = cam;
    }
    
    public void setWidth(int width) 
    {
        this.width = width;
    }
    
    public void setHeight(int height) 
    {
        this.height = height;
    }
    
    public void drawLevel(Graphics g, Level level) 
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
                drawRect(g, xIndex, yIndex, 1, 1);
            }
        }
    }
    
    public void drawEntity(Graphics g, Entity e) 
    {
        float x = e.getPosition().getX();
        float y = e.getPosition().getY();
        float w = e.getWidth();
        float h = e.getHeight();
        
        g.setColor(new Color(e.getColorCode()));
        drawRect(g, x, y, w, h);
        
        if(e.showHealth())
        {
            int hx = getScreenX(x - 0.2f);
            int hw = getScreenX(x + w + 0.25f) - hx;
            int hy = getScreenY(y + h + 0.6f);
            int hh = getScreenY(y + h + 0.2f) - hy;
            
            float hpRatio = (float)e.getHealth()/e.getMaxHealth();
            
            g.setColor(Color.RED);
            g.fillRect(hx, hy, hw, hh);
            g.setColor(Color.GREEN);
            g.fillRect(hx, hy, (int)(hw * hpRatio), hh);
        }
    }
    
    private void drawRect(Graphics g, float x, float y, float w, float h) 
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
    
    public int getScreenX(float worldX) 
    {
        return (int)(width/2f + (worldX - cam.getPosition().getX())*cam.getPixelsPerUnit());
    }
    
    public int getScreenY(float worldY) 
    {
        return (int)(height/2f + (cam.getPosition().getY() - worldY)*cam.getPixelsPerUnit());
    }
    
    public float getWorldX(float screenX) 
    {
        return (screenX - 0.5f * width) / cam.getPixelsPerUnit() + cam.getPosition().getX();
    }
    
    public float getWorldY(float screenY) 
    {
        return -( (screenY - 0.5f * height) / cam.getPixelsPerUnit() - cam.getPosition().getY());
    }
}
