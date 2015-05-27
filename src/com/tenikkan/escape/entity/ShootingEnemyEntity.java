package com.tenikkan.escape.entity;

import com.tenikkan.escape.input.IController;
import com.tenikkan.escape.input.MoveShootAI;
import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;

public class ShootingEnemyEntity extends Entity
{
    private int lastShot = 0, waitTime;
    
    public ShootingEnemyEntity(int id, int hp, int dmg, float knock, int waitTime, Vector2f position, Level level) 
    {
        super("simple_enemy", id, Entity.SPRITE_ENEMY_STRONG, 1f, hp, dmg, 0, knock, true, false, 0xff00ff, 1.9f, 2.9f, 0.1f, position, new Vector2f(0, 0), null);
        setController(new MoveShootAI(this, level));
        this.waitTime = waitTime;
        setJumpAmount(0.4f);
    }
    
    public void update(Level level) 
    {
        super.update(level);
        
        lastShot += 1;
        
        if(getController().get(IController.Input.FIRE_PRIMARY_WEAPON) && lastShot > waitTime) 
        {
            int id = level.getEntities().getAvailableID();
            
            float mX = getController().getAimPosition().getX();
            float mY = getController().getAimPosition().getY();
            
            Vector2f vel = getPosition().add(getWidth() / 2, getHeight() / 2).sub(mX, mY).normalized().mul(-1f);
            
            Vector2f offset = new Vector2f(0, (float)Math.random() - 1.0f).mul(0.1f);
            
            float scale = 0.3f;
            
            Entity arrow = new EnemyProjectile(id, Entity.SPRITE_ICE_2, 0f, 1000, 0.9f, 0xff7f7f, 1.6f, 0.4f, getPosition().add(getWidth() / 2 - 0.8f, 3 * getHeight() / 4 - 0.1f), vel.add(offset).mul(scale));
            
            level.getEntities().add(arrow);
            
            lastShot = 0;
        }
    }
}
