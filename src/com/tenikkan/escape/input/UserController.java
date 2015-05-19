/**
 * 
 */
package com.tenikkan.escape.input;

import com.tenikkan.escape.graphics.Renderer;
import com.tenikkan.math.Vector2f;

/**
 * @author Nicholas Hamilton
 *
 */
public class UserController implements IController
{   
    private Keyboard keyboard;
    private Mouse mouse;
    private Renderer render;
    
    private Vector2f pos;
    
    public UserController(Keyboard keyboard, Mouse mouse, Renderer render) 
    {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.render = render;
        
        pos = new Vector2f(0, 0);
    }
    
    public void update() 
    {
        pos.set(render.getWorldX(mouse.getX()), render.getWorldY(mouse.getY()));
    }
    
    public Vector2f getAimPosition() { return pos; }
    
    public boolean get(Input in) 
    {
        switch(in) 
        {
        case MOVE_UP: return keyboard.isKeyDown(Keyboard.UP);
        case MOVE_DOWN: return keyboard.isKeyDown(Keyboard.DOWN);
        case MOVE_LEFT: return keyboard.isKeyDown(Keyboard.LEFT);
        case MOVE_RIGHT: return keyboard.isKeyDown(Keyboard.RIGHT);
        case JUMP: return keyboard.isKeyDown(Keyboard.JUMP);
        case FIRE_PRIMARY_WEAPON: return mouse.isButtonDown(Mouse.MAIN_BUTTON);
        case FIRE_SECONDARY_WEAPON: return mouse.isButtonDown(Mouse.SECONDARY_BUTTON);
        default: return false;
        }
    }
}
