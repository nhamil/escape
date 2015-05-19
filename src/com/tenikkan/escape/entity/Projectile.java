package com.tenikkan.escape.entity;

import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;

public class Projectile extends Entity
{
    public Projectile(int id, int color, float width, float height, Vector2f pos, Vector2f vel)
    {
        super("arrow", id, color, width, height, 999, pos, vel, null);
    }
    
    public void update(Level level) 
    {
        super.update(level);
        
        if(onGround()) flagForDelete();
    }
}
