package com.tenikkan.arcana.entity;

import com.tenikkan.arcana.input.SimpleMoveAI;
import com.tenikkan.arcana.level.Level;
import com.tenikkan.math.Vector2f;

public class WalkingEntity extends Entity
{
    public WalkingEntity(int id, Vector2f position, Level level) 
    {
        super("walking_entity", id, 0xffff00, 1.0f, 1.0f, 0.4f, position, new Vector2f(0, 0), null);
        setController(new SimpleMoveAI(this, level));
    }
}
