/**
 * 
 */
package com.tenikkan.arcana.input;

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
        MOVE_RIGHT
    }
    
    public boolean get(Input in);
}
