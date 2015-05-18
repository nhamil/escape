package com.tenikkan.escape.entity;

import com.tenikkan.math.Vector2f;

public class Projectile extends Entity
{
    public Projectile(int id, int color, Vector2f pos, Vector2f vel)
    {
        super("arrow", id, color, 0.1f, 0.1f, 999, pos, vel, null);
    }
}
