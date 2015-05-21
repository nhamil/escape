/**
 * 
 */
package com.tenikkan.escape.entity;

import com.tenikkan.escape.input.IController;
import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;

/**
 * @author Nicholas Hamilton
 *
 */
public class Player extends Entity
{   
    private int lastShot = 0;
    
    private int invincibilityTicks = 60;
    private int dmgTicksSince = 0;
    
    public Player(String name, int id, int hp, Vector2f position, IController c) 
    {
        super(name, id, hp, 0, 3000, 0f, true, true, 0xffffff, 1.9f, 2.9f, 0.4f, position, new Vector2f(0, 0), c);
    }
    
    public void changeHealth(int dhp) 
    {
        if(dhp >= 0) 
        {
            super.changeHealth(dhp);
            return;
        }
        if(dmgTicksSince >= invincibilityTicks) 
        {
            super.changeHealth(dhp);
            dmgTicksSince = 0;
            return;
        }
    }
    
    public void update(Level level) 
    {
        super.update(level);
        
        dmgTicksSince++;
        
        lastShot++;
        
        changeHealth(1);
        if(getHealth() > getMaxHealth()) setHealth(getMaxHealth());
        
        changeEnergy(2);
        if(getEnergy() > getMaxEnergy()) setEnergy(getMaxEnergy());
        if(getEnergy() < 0) setEnergy(0);
        
        if(getController().get(IController.Input.FIRE_PRIMARY_WEAPON) && lastShot > 15 && getEnergy() > 150) 
        {
            int id = level.getEntities().getAvailableID();
            
            float mX = getController().getAimPosition().getX();
            float mY = getController().getAimPosition().getY();
            
            Vector2f vel = getPosition().add(getWidth() / 2, getHeight() / 2).sub(mX, mY).normalized().mul(-2f);
            
            Entity arrow = new Projectile(id, 20, 3, 0x00ffff, 1.6f, 0.2f, getPosition().add(getWidth() / 2 - 0.8f, getHeight() / 2 - 0.1f), vel);
            
            level.getEntities().add(arrow);
            
            lastShot = 0;
            
            changeEnergy(-150);
        }
        if(getController().get(IController.Input.FIRE_SECONDARY_WEAPON) && lastShot > 5 && getEnergy() > 60) 
        {
            int id = level.getEntities().getAvailableID();
            
            float mX = getController().getAimPosition().getX();
            float mY = getController().getAimPosition().getY();
            
            Vector2f vel = getPosition().add(getWidth() / 2, getHeight() / 2).sub(mX, mY).normalized().mul(-1.5f);
            
            Entity arrow = new Projectile(id, 10, 0.1f, 0xffff00, 0.4f, 0.4f, getPosition().add(getWidth() / 2 - 0.8f, getHeight() / 2 - 0.1f), vel);
            level.getEntities().add(arrow);
            
            lastShot = 0;
            
            changeEnergy(-60);
        }
    }
}
