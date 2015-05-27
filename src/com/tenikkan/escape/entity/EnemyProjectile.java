package com.tenikkan.escape.entity;

import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;

public class EnemyProjectile extends Entity
{
    public EnemyProjectile(int id, int imgID, float grav, int dmg, float knock, int color, float width, float height, Vector2f pos, Vector2f vel)
    {
        super("enemy_arrow", id, imgID, grav, 1, dmg, 0, knock, false, false, color, width, height, 999, pos, vel, null);
    }
    
    public void update(Level level) 
    {
        super.update(level);
        
        if(onGround()) flagForDelete();
    }
}
