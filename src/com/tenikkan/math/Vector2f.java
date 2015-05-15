package com.tenikkan.math;

public class Vector2f
{
    private float x, y;
    
    public Vector2f() 
    {
        this(0, 0);
    }
    
    public Vector2f(final Vector2f r) 
    {
        x = r.x;
        y = r.y;
    }
    
    public Vector2f(float x, float y) 
    {
        set(x, y);
    }
    
    public void setX(float x) { this.x = (float)x; }
    public void setY(float y) { this.y = (float)y; }
    public void set(float x, float y) 
    {
        this.x = (float)x;
        this.y = (float)y;
    }
    
    public float getX() { return x; }
    public float getY() { return y; }
    
    public float lengthSquared() { return x * x + y * y; }
    public float length() { return (float)Math.sqrt(lengthSquared()); }
    
    public float dot(final Vector2f r) { return x * r.x + y * r.y; }
    
    public Vector2f normalized() 
    {
        float len = length();
        if(len == 0) return copy();
        return new Vector2f(x/len, y/len);
    }
    
    public Vector2f add(final Vector2f r) { return new Vector2f(x + r.x, y + r.y); }
    public Vector2f sub(final Vector2f r) { return new Vector2f(x - r.x, y - r.y); }
    public Vector2f mul(final Vector2f r) { return new Vector2f(x * r.x, y * r.y); }
    public Vector2f div(final Vector2f r) { return new Vector2f(x / r.x, y / r.y); }
    
    public Vector2f add(float r) { return new Vector2f(x + r, y + r); }
    public Vector2f sub(float r) { return new Vector2f(x - r, y - r); }
    public Vector2f mul(float r) { return new Vector2f(x * r, y * r); }
    public Vector2f div(float r) { return new Vector2f(x / r, y / r); }
    
    public Vector2f add(float rx, float ry) { return new Vector2f(x + rx, y + ry); }
    public Vector2f sub(float rx, float ry) { return new Vector2f(x - rx, y - ry); }
    public Vector2f mul(float rx, float ry) { return new Vector2f(x * rx, y * ry); }
    public Vector2f div(float rx, float ry) { return new Vector2f(x / rx, y / ry); }
    
    public Vector2f copy() { return new Vector2f(x, y); }
    
    public String toString() { return "(" + x + ", " + y + ")"; }
}
