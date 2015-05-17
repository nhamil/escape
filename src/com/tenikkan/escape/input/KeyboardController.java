/**
 * 
 */
package com.tenikkan.escape.input;

/**
 * @author Nicholas Hamilton
 *
 */
public class KeyboardController implements IController
{   
    private Keyboard keyboard;
    
    public KeyboardController(Keyboard keyboard) 
    {
        this.keyboard = keyboard;
    }
    
    public void update() 
    {
        
    }
    
    public boolean get(Input in) 
    {
        switch(in) 
        {
        case MOVE_UP: return keyboard.isKeyDown(Keyboard.UP);
        case MOVE_DOWN: return keyboard.isKeyDown(Keyboard.DOWN);
        case MOVE_LEFT: return keyboard.isKeyDown(Keyboard.LEFT);
        case MOVE_RIGHT: return keyboard.isKeyDown(Keyboard.RIGHT);
        case JUMP: return keyboard.isKeyDown(Keyboard.JUMP);
        default: return false;
        }
    }
}
