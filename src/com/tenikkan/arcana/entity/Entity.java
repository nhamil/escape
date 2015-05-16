/**
 * 
 */
package com.tenikkan.arcana.entity;

import static com.tenikkan.arcana.input.Controller.Input.*;

import com.tenikkan.arcana.Resource;
import com.tenikkan.arcana.Physics;
import com.tenikkan.arcana.input.Controller;
import com.tenikkan.arcana.level.Level;
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
    
    private int aiID;
    
    private boolean onGround = false;
    
    private float width, height;
    
    public Entity(String name, int aiID, int color, float width, float height, float maxMovement, Vector2f pos, Vector2f vel) 
    {
        this.name = name;
        this.color = color;
        position = pos;
        velocity = vel;
        this.maxMovement = maxMovement;
        this.width = width; 
        this.height = height;
        this.aiID = aiID;
    }
    
    public void handleInput(Controller c) 
    {
        float dx = 0;
        float dy = 0;
        
        boolean moveSide = false;
        float sideAmt = 0.01f;
        
        if(c.get(MOVE_LEFT)) 
        {
            moveSide = true;
            dx -= sideAmt;
        }
        
        if(c.get(MOVE_RIGHT)) 
        {
            moveSide = true;
            dx += sideAmt;
        }
        
        if(c.get(MOVE_UP)) 
        {
            dy += 0.01;
        }
        
        if(c.get(MOVE_DOWN)) 
        {
            dy -= 0.01;
        }
        
        if(c.get(JUMP) && onGround) 
        {
            dy += 0.6f;
        }
        
        float friction = 0.02f;
        if(!moveSide || !((dx < 0 && velocity.getX() < 0) || (dx > 0 && velocity.getX() > 0))) 
        {
            if(velocity.getX() < -friction) velocity.setX(velocity.getX() + friction);
            else if(velocity.getX() > friction) velocity.setX(velocity.getX() - friction);
            else velocity.setX(0);
        }
        
        velocity.setX(Math.max(-maxMovement, Math.min(maxMovement, velocity.getX() + dx)));
        velocity.setY(velocity.getY() + dy);
    }
    
    public void update(Level level) 
    {
        Controller c = Resource.getControllerManager().get(aiID);
        if(c != null) handleInput(c);
        
        onGround = false;
        
        Physics.handleCollision(this, level);
        
        position = position.add(velocity);
    }
    
    public boolean onGround() { return onGround; }
    public void setOnGround(boolean og) { onGround = og; }
    
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    
    public float getMaxMovement() { return maxMovement; }
    
    public Vector2f getPosition() { return position; }
    public Vector2f getVelocity() { return velocity; }
    
    public void setPosition(Vector2f pos) { position = pos; }
    public void setVelocity(Vector2f vel) { velocity = vel; }
    
    public void move(Vector2f move) { position = position.add(move); }
    public void accelerate(Vector2f accel) { velocity = velocity.add(accel); }
    
    public int getColorCode() { return color; }
    
    public String getName() { return name; }
}
