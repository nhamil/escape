/**
 * 
 */
package com.tenikkan.escape.input;

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
        JUMP
    }
    
    public void update();
    
    public boolean get(Input in);
}
