/**
 * 
 */
package com.tenikkan.escape.entity;

import static com.tenikkan.escape.input.IController.Input.*;

import com.tenikkan.escape.Physics;
import com.tenikkan.escape.input.IController;
import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;
import com.tenikkan.util.Identifiable;

/**
 * @author Nicholas Hamilton
 *
 */
public abstract class Entity implements Identifiable
{   
    private int color;
    private String name;
    private Vector2f position;
    private Vector2f velocity;
    private float maxMovement;
    
    private int id;
    
    private IController c;
    
    private boolean onGround = false;
    private boolean touchingEndTile = false;
    
    private boolean delete = false;
    
    private float width, height;
    
    public Entity(String name, int id, int color, float width, float height, float maxMovement, Vector2f pos, Vector2f vel, IController c) 
    {
        this.name = name;
        this.color = color;
        position = pos;
        velocity = vel;
        this.maxMovement = maxMovement;
        this.width = width; 
        this.height = height;
        setController(c);
        
        this.id = id;
    }
    
    public boolean flaggedForDelete() { return delete; }
    public void flagForDelete() { delete = true; }
    
    public boolean isTouchingEndTile() { return touchingEndTile; }
    public void setTouchingEndTile(boolean touch) { touchingEndTile = touch; }
    
    public void setController(IController c) 
    {
        this.c = c;
    }
    public IController getController() { return c; }
    
    public void handleInput() 
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
        if(c != null) 
        { 
            c.update();
            handleInput(); 
        }
        
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
    public int getID() { return id; }
}
