/**
 * 
 */
package com.tenikkan.arcana;

import com.tenikkan.arcana.entity.Entity;
import com.tenikkan.arcana.level.Level;

/**
 * @author Nicholas Hamilton
 *
 */
public class Physics
{   
    public static boolean isCollision(Entity e, Level level) 
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
                if(!level.getTileData(x, y).isSolid()) continue;
                tile.x = x;
                tile.y = y;
                if(collideAABBtoAABB(eBox, tile)) return true;
            }
        }
        
        return false;
    }
    
    public static void handleCollision(Entity e, Level level) 
    {
        
    }
    
    private static boolean collideAABBtoAABB(AABB b1, AABB b2) 
    {
        return b1.x < b2.x + b2.w &&
               b1.x + b1.w > b2.x &&
               b1.y < b2.y + b2.h && 
               b1.y + b1.h > b2.y;
    }
}
