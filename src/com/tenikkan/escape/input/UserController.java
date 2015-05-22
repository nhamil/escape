/**
 * 
 */
package com.tenikkan.escape.input;

import java.awt.event.KeyEvent;

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
    
    private boolean lefty;
    
    public UserController(Keyboard keyboard, Mouse mouse, Renderer render, boolean lefty) 
    {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.render = render;
        
        this.lefty = lefty;
        
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
//        case MOVE_UP: return keyboard.isKeyDown(Keyboard.UP) || keyboard.isKeyDown(KeyEvent.VK_W);
//        case MOVE_DOWN: return keyboard.isKeyDown(Keyboard.DOWN) || keyboard.isKeyDown(KeyEvent.VK_S);
        case MOVE_LEFT: return keyboard.isKeyDown(Keyboard.LEFT) || keyboard.isKeyDown(KeyEvent.VK_A);
        case MOVE_RIGHT: return keyboard.isKeyDown(Keyboard.RIGHT) || keyboard.isKeyDown(KeyEvent.VK_D);
        case JUMP: return keyboard.isKeyDown(Keyboard.JUMP);
        case FIRE_PRIMARY_WEAPON: return lefty ? mouse.isButtonDown(Mouse.MAIN_BUTTON) : mouse.isButtonDown(Mouse.SECONDARY_BUTTON);
        case FIRE_SECONDARY_WEAPON: return lefty ? mouse.isButtonDown(Mouse.SECONDARY_BUTTON) : mouse.isButtonDown(Mouse.MAIN_BUTTON);
        default: return false;
        }
    }
}
