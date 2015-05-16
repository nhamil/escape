/**
 * 
 */
package com.tenikkan.arcana.input;

import com.tenikkan.util.Identifiable;

/**
 * @author Nicholas Hamilton
 *
 */
public abstract class Controller implements Identifiable
{   
    private String name;
    private int id;
    
    public static enum Input 
    {
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT,
        JUMP
    }
    
    public Controller(String name, int id) 
    {
        this.name = name;
        this.id = id;
    }
    
    public String getName() { return name; }
    public int getID() { return id; }
    
    public abstract boolean get(Input in);
}
