/**
 * 
 */
package com.tenikkan.escape.input;

import com.tenikkan.math.Vector2f;

/**
 * @author Nicholas Hamilton
 *
 */
public interface IController
{
    public static enum Input 
    {
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT,
        JUMP,
        FIRE_PRIMARY_WEAPON,
        FIRE_SECONDARY_WEAPON
    }
    
    public void update();
    
    public Vector2f getAimPosition();
    
    public boolean get(Input in);
}
