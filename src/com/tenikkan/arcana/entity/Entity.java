/**
 * 
 */
package com.tenikkan.arcana.entity;

import static com.tenikkan.arcana.input.IController.Input.*;

import com.tenikkan.arcana.input.IController;
import com.tenikkan.math.Vector2f;

/**
 * @author Nicholas Hamilton
 *
 */
public abstract class Entity
{   
    private int color;
    private String name;
    private Vector2f position;
    private Vector2f velocity;
    private float maxMovement;
    
    private float width, height;
    
    public Entity(String name, int color, float width, float height, float maxMovement, Vector2f pos, Vector2f vel) 
    {
        this.name = name;
        this.color = color;
        position = pos;
        velocity = vel;
        this.maxMovement = maxMovement;
        this.width = width; 
        this.height = height;
    }
    
    public void handleInput(IController c) 
    {
        float dx = 0;
        float dy = 0;
        
        if(c.get(MOVE_LEFT)) 
        {
            dx -= 0.01;
        }
        
        if(c.get(MOVE_RIGHT)) 
        {
            dx += 0.01;
        }
        
        if(c.get(MOVE_UP)) 
        {
            dy += 0.01;
        }
        
        if(c.get(MOVE_DOWN)) 
        {
            dy -= 0.01;
        }
        
//        velocity.setX(dx);
//        velocity.setY(dy);
//        velocity = velocity.add(dx/6, dy/6);
        velocity.setX(Math.max(-maxMovement, Math.min(maxMovement, velocity.getX() + dx)));
        velocity.setY(Math.max(-maxMovement, Math.min(maxMovement, velocity.getY() + dy)));
    }
    
    public void update(IController controller) 
    {
        handleInput(controller);
        position = position.add(velocity);
        
//        velocity.setX(0);
    }
    
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    
    public float getMaxMovement() { return maxMovement; }
    
    public Vector2f getPosition() { return position; }
    public Vector2f getVelocity() { return velocity; }
    
    public void setPosition(Vector2f pos) { position = pos; }
    public void setVelocity(Vector2f vel) { velocity = vel; }
    
    public void move(Vector2f move) { position.add(move); }
    public void accelerate(Vector2f accel) { velocity.add(accel); }
    
    public int getColorCode() { return color; }
    
    public String getName() { return name; }
}
