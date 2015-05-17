/**
 * 
 */
package com.tenikkan.escape;

import com.tenikkan.escape.entity.Entity;
import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;

/**
 * @author Nicholas Hamilton
 *
 */
public class Physics
{   
    public static boolean collideEndTile(Entity e, Level level) 
    {
        int xStart = (int)Math.floor(e.getPosition().getX());
        int xEnd   = (int)Math.ceil (e.getPosition().getX() + e.getWidth());
        int yStart = (int)Math.floor(e.getPosition().getY());
        int yEnd   = (int)Math.ceil (e.getPosition().getY() + e.getHeight());
        
        AABB eBox = new AABB(e.getPosition().getX(), e.getPosition().getY(), e.getWidth(), e.getHeight());
        AABB tile = new AABB(0, 0, 1, 1);
        
        for(int y = yStart; y < yEnd; y++) 
        {
            for(int x = xStart; x < xEnd; x++) 
            {
                if(level.getTile(x, y).isEndTile()) 
                {
                    tile.x = x;
                    tile.y = y;
                    if(collideAABBtoAABB(eBox, tile)) return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean handleCollision(Entity e, Level level) 
    {
        Vector2f vel = e.getVelocity();
        
        float angle = (float)Math.toRadians(vel.angle());
        
        if(vel.getX() == 0 && vel.getY() == 0) 
        {
            return handleEntityCustomVelocity(e, level, vel);
        } else if(Math.abs(vel.getX()) > Math.abs(vel.getY())) 
        {
            float sinTheta = (float)Math.sin(angle);
            for(int x = 0; x < Math.abs(vel.getX()); x++) 
            {
                float vy = x * sinTheta;
                float vx = vel.getX() > 0 ? x : -x;
                if(handleEntityCustomVelocity(e, level, new Vector2f(vx, vy))) return true;
            }
            return handleEntityCustomVelocity(e, level, vel);
        } else 
        {
            float cosTheta = (float)Math.cos(angle);
            for(int y = 0; y < Math.abs(vel.getY()); y++) 
            {
                float vy = vel.getY() > 0 ? y : -y;
                float vx = y * cosTheta;
                if(handleEntityCustomVelocity(e, level, new Vector2f(vx, vy))) return true;
            }
            return handleEntityCustomVelocity(e, level, vel);
        }
    }
    
    private static boolean handleEntityCustomVelocity(Entity e, Level level, Vector2f vel)
    {
        boolean collide = false;
        
        int xStart = (int)Math.floor(e.getPosition().getX());
        int xEnd   = (int)Math.floor(e.getPosition().getX() + e.getWidth());
        int yStart = (int)Math.floor(e.getPosition().getY());
        int yEnd   = (int)Math.floor(e.getPosition().getY() + e.getHeight());
        
        AABB eBox = new AABB(e.getPosition().getX() + vel.getX(), e
                .getPosition().getY() + vel.getY(), e.getWidth(), e.getHeight());
        AABB tile = new AABB(0, 0, 1, 1);
        
        for (int y = yStart; y <= yEnd; y++)
        {
            int x;
            if (e.getVelocity().getX() < 0)
                x = (int) Math.floor(eBox.x);
            else if (e.getVelocity().getX() > 0)
                x = (int) Math.floor(eBox.x + eBox.w);
            else
                continue;
            if (level.getTileData(x, y).isSolid())
            {
                tile.x = x;
                tile.y = y;
                if (!collideAABBtoAABB(eBox, tile))
                    continue;
                if (e.getVelocity().getX() < 0)
                    e.getPosition().setX(x + 1);
                else
                    e.getPosition().setX(x - eBox.w);
                
                e.getVelocity().setX(0);
                
                collide = true;
            }
        }
        
        eBox = new AABB(e.getPosition().getX(), e.getPosition().getY()
                + vel.getY(), e.getWidth(), e.getHeight());
        
        for (int x = xStart; x <= xEnd; x++)
        {
            int y;
            if (e.getVelocity().getY() < 0)
                y = (int) Math.floor(eBox.y);
            else if (e.getVelocity().getY() > 0)
                y = (int) Math.floor(eBox.y + eBox.h);
            else
                continue;
            if (level.getTileData(x, y).isSolid())
            {
                tile.x = x;
                tile.y = y;
                if (!collideAABBtoAABB(eBox, tile))
                    continue;
                if (e.getVelocity().getY() < 0)
                {
                    e.getPosition().setY(y + 1);
                    e.setOnGround(true);
                } else
                    e.getPosition().setY(y - eBox.h);
                
                e.getVelocity().setY(0);
                
                collide = true;
            }
        }
        
        return collide;
    }

    private static boolean collideAABBtoAABB(AABB b1, AABB b2) 
    {
        return b1.x < b2.x + b2.w &&
               b1.x + b1.w > b2.x &&
               b1.y < b2.y + b2.h && 
               b1.y + b1.h > b2.y;
    }
}
