package com.tenikkan.arcana;

import com.tenikkan.math.Vector2f;

public class Camera
{
    private Vector2f position;
    
    private float ppu;
    
    public Camera(float x, float y, float ppu) 
    {
        position = new Vector2f(x, y);
        this.ppu = ppu;
    }
    
    public Vector2f getPosition() { return position; }
    public void setPosition(Vector2f pos) { position = pos; }
    public void move(Vector2f move) { position = position.add(move); }
    
    public int getIntPixelsPerUnit() { return (int)Math.ceil(ppu); }
    public float getPixelsPerUnit() { return ppu; }
    public void setPixelsPerUnit(float amt) { ppu = amt; }
    public void zoom(float dAmt) { ppu += dAmt; }
}
