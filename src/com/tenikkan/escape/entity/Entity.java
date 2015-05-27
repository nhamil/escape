/**
 * 
 */
package com.tenikkan.escape.entity;

import static com.tenikkan.escape.input.IController.Input.JUMP;
import static com.tenikkan.escape.input.IController.Input.MOVE_DOWN;
import static com.tenikkan.escape.input.IController.Input.MOVE_LEFT;
import static com.tenikkan.escape.input.IController.Input.MOVE_RIGHT;
import static com.tenikkan.escape.input.IController.Input.MOVE_UP;

import java.awt.image.BufferedImage;

import com.tenikkan.escape.Physics;
import com.tenikkan.escape.Resource;
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
    public static final int SPRITE_PLAYER = 0;
    public static final int SPRITE_ENEMY = 1;
    public static final int SPRITE_FIRE = 2;
    public static final int SPRITE_ICE = 3;
    public static final int SPRITE_ENEMY_STRONG = 4;
    public static final int SPRITE_ICE_2 = 3;
    
    private static BufferedImage player = Resource.loadImage("res/player.png");
    private static BufferedImage enemy = Resource.loadImage("res/enemy.png");
    private static BufferedImage enemyStrong = Resource.loadImage("res/enemy2.png");
    private static BufferedImage fire = Resource.loadImage("res/fire.png");
    private static BufferedImage ice = Resource.loadImage("res/ice.png");
    
    private int color;
    private String name;
    private Vector2f position;
    private Vector2f velocity;
    private float maxMovement;
    
    private int id;
    
    private IController c;
    
    private boolean colliding = false;
    private boolean onGround = false;
    private boolean touchingEndTile = false;
    
    private boolean delete = false;
    
    private float width, height;
    
    private int maxHealth;
    private int health;
    private int damage;
    private boolean showHealth;
    
    private int energy;
    private int maxEnergy;
    private boolean showEnergy;
    
    private float knockback;
    
    private float gravity;
    
    private float jump = 0.6f;
    
    private int imgID;
    
    public Entity(String name, int id, int imgID, float gravity, int hp, int dmg, int energy, float knock, boolean showHp, boolean showEnergy, int color, float width, float height, float maxMovement, Vector2f pos, Vector2f vel, IController c) 
    {
        this.name = name;
        this.color = color;
        position = pos;
        velocity = vel;
        this.maxMovement = maxMovement;
        this.width = width; 
        this.height = height;
        setController(c);
        
        this.imgID = imgID;
        
        this.gravity = gravity;
        
        maxHealth = health = hp;
        damage = dmg;
        maxEnergy = this.energy = energy;
        
        this.showEnergy = showEnergy;
        
        showHealth = showHp;
        
        knockback = knock;
        
        this.id = id;
    }
    
    public BufferedImage getImage() 
    {
        switch(imgID) 
        {
        case 0: return player;
        case 1: return enemy;
        case 2: return fire;
        case 3: return ice;
        case 4: return enemyStrong;
        default: return null;
        }
    }
    
    public float gravityAmount() { return gravity; }
    
    public void applyKnockback(Entity e) 
    {
        getVelocity().setX(getVelocity().getX() + e.getVelocity().normalized().getX() * e.getKnockback());
    }
    
    public float getKnockback() { return knockback; }
    
    public boolean showHealth() { return showHealth; }
    public boolean showEnergy() { return showEnergy; }
    
    public int getEnergy() { return energy; }
    public void setEnergy(int e) { energy = e; }
    public void changeEnergy(int de) { energy += de; }
    
    public int getMaxEnergy() { return maxEnergy; }
    public void setMaxEnergy(int amt) { maxEnergy = amt; }
    
    public int getDamage() { return damage; }
    public void setDamage(int dmg) { damage = dmg; }
    
    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int max) { maxHealth = max; }
    
    public int getHealth() { return health; }
    public void setHealth(int hp) { health = hp; }
    public void changeHealth(int dhp) 
    { 
        health += dhp; 
        if(health < 0) health = 0;
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
    
    public void setJumpAmount(float amt) { jump = amt; }
    
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
            dy += 0.014;
        }
        
        if(c.get(MOVE_DOWN)) 
        {
            dy -= 0.011;
        }
        
        if(c.get(JUMP) && onGround) 
        {
            dy += jump;
        }
        
        float friction = 0.02f;
        if(!moveSide || !((dx < 0 && velocity.getX() < 0) || (dx > 0 && velocity.getX() > 0)) || Math.abs(velocity.getX()) > maxMovement) 
        {
            if(velocity.getX() < -friction) velocity.setX(velocity.getX() + friction); 
            else if(velocity.getX() > friction) velocity.setX(velocity.getX() - friction);
            else velocity.setX(0);
        }
        
        if(Math.abs(velocity.getX()) < maxMovement)
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
        colliding = false;
        
        Physics.handleCollision(this, level);
        
        position = position.add(velocity);
    }
    
    public boolean onGround() { return onGround; }
    public void setOnGround(boolean og) { onGround = og; }
    
    public boolean colliding() { return colliding; }
    public void setColliding(boolean c) { colliding = c; }
    
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
