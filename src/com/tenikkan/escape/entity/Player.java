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
    
    public Player(String name, int id, Vector2f position, IController c) 
    {
        super(name, id, 0xffffff, 1.9f, 2.9f, 0.4f, position, new Vector2f(0, 0), c);
    }
    
    public void update(Level level) 
    {
        super.update(level);
        
        lastShot++;
        
        if(getController().get(IController.Input.FIRE_PRIMARY_WEAPON) && lastShot > 15) 
        {
            int id = level.getEntities().getAvailableID();
            
            float mX = getController().getAimPosition().getX();
            float mY = getController().getAimPosition().getY();
            
            Vector2f vel = getPosition().add(getWidth() / 2, getHeight() / 2).sub(mX, mY).normalized().mul(-2f);
            
            Entity arrow = new Projectile(id, 0x0000ff, 1.6f, 0.2f, getPosition().add(getWidth() / 2 - 0.8f, getHeight() / 2 - 0.1f), vel);
            level.getEntities().add(arrow);
            
            lastShot = 0;
        }
        if(getController().get(IController.Input.FIRE_SECONDARY_WEAPON) && lastShot > 5) 
        {
            int id = level.getEntities().getAvailableID();
            
            float mX = getController().getAimPosition().getX();
            float mY = getController().getAimPosition().getY();
            
            Vector2f vel = getPosition().add(getWidth() / 2, getHeight() / 2).sub(mX, mY).normalized().mul(-1.5f);
            
            Entity arrow = new Projectile(id, 0xffff00, 0.4f, 0.4f, getPosition().add(getWidth() / 2 - 0.8f, getHeight() / 2 - 0.1f), vel);
            level.getEntities().add(arrow);
            
            lastShot = 0;
        }
    }
}
