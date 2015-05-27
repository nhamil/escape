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
    
    private int recharge = 1;
    
    public Player(String name, int id, int hp, Vector2f position, IController c) 
    {
        super(name, id, Entity.SPRITE_PLAYER, 1f, hp, 0, 5000, 0f, true, true, 0xffffff, 1.9f, 2.9f, 0.4f, position, new Vector2f(0, 0), c);
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
    
    public void setRechargeAmount(int amt) 
    {
        recharge = amt;
    }
    
    public void update(Level level) 
    {
        super.update(level);
        
        dmgTicksSince++;
        
        lastShot++;
        
        changeHealth(1);
        if(getHealth() > getMaxHealth()) setHealth(getMaxHealth());
        
        changeEnergy(recharge);
        if(getEnergy() > getMaxEnergy()) setEnergy(getMaxEnergy());
        if(getEnergy() < 0) setEnergy(0);
        
        if(getController().get(IController.Input.FIRE_PRIMARY_WEAPON) && lastShot > 15 && getEnergy() > 450) 
        {
            int id = level.getEntities().getAvailableID();
            
            float mX = getController().getAimPosition().getX();
            float mY = getController().getAimPosition().getY();
            
            Vector2f vel = getPosition().add(getWidth() / 2, getHeight() / 2).sub(mX, mY).normalized().mul(-0.5f);
            
            Vector2f offset = new Vector2f(0, (float)Math.random() - 1f).mul(0.03f);
            
            Entity arrow = new Projectile(id, Entity.SPRITE_ICE, 0f, 200, 0.7f, 0x007f7f, 1.6f, 0.4f, getPosition().add(getWidth() / 2 - 0.8f, 3 * getHeight() / 4 - 0.1f), vel.add(offset));
            
            level.getEntities().add(arrow);
            
            lastShot = 0;
            
            changeEnergy(-450);
        }
        if(getController().get(IController.Input.FIRE_SECONDARY_WEAPON) && lastShot > 1 && getEnergy() > 15) 
        {
            int id = level.getEntities().getAvailableID();
            
            float mX = getController().getAimPosition().getX();
            float mY = getController().getAimPosition().getY();
            
            Vector2f vel = getPosition().add(getWidth() / 2, getHeight() / 2).sub(mX, mY).normalized().mul(-0.7f);
            
            Vector2f offset = new Vector2f(0, (float)Math.random() - 1.0f).mul(0.1f);
            
            Entity arrow = new Projectile(id, Entity.SPRITE_FIRE, 0.0f, 6, 0.001f, 0xff7f00, 0.4f, 0.4f, getPosition().add(getWidth() / 2 - 0.8f, 3 * getHeight() / 4 - 0.1f), vel.add(offset));
            level.getEntities().add(arrow);
            
//            lastShot = 0;
            
            changeEnergy(-15);
        }
    }
}
