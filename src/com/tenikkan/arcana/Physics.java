/**
 * 
 */
package com.tenikkan.arcana;

import com.tenikkan.arcana.entity.Entity;
import com.tenikkan.arcana.level.Level;
import com.tenikkan.math.Vector2f;

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
    
    public static boolean handleCollision(Entity e, Level level) 
    {
        System.out.println(e.getVelocity().angle());
        boolean collide = false;
        
        int xStart = (int)Math.floor(e.getPosition().getX());
        int xEnd   = (int)Math.floor(e.getPosition().getX() + e.getWidth());
        int yStart = (int)Math.floor(e.getPosition().getY());
        int yEnd   = (int)Math.floor(e.getPosition().getY() + e.getHeight());
        
        AABB eBox = new AABB(e.getPosition().getX(), e.getPosition().getY(), e.getWidth(), e.getHeight());
        AABB tile = new AABB(0, 0, 1, 1);
        
        for(int y = yStart; y < yEnd + 1; y++) 
        {
            int x = e.getVelocity().getX() < 0 ? xStart : xEnd;
            if(!level.getTileData(x, y).isSolid()) continue;
            tile.x = x;
            tile.y = y;
            if(collideAABBtoAABB(eBox, tile)) 
            {
                collide = true;
                if(e.getVelocity().getX() < 0) e.getPosition().setX(x + 1);
                else                           e.getPosition().setX(x - e.getWidth());
                e.getVelocity().setX(0);
            }
        }
        
//        for(int x = xStart; x < xEnd + 1; x++) 
//        {
//            int y = e.getVelocity().getY() < 0 ? yStart : yEnd;
//            if(!level.getTileData(x, y).isSolid()) continue;
//            tile.x = x;
//            tile.y = y;
//            if(collideAABBtoAABB(eBox, tile)) 
//            {
//                collide = true;
//                if(e.getVelocity().getY() < 0) e.getPosition().setY(y + 1);
//                else                           e.getPosition().setY(y - 1);
//                e.getVelocity().setY(0);
//            }
//        }
//        
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
