/**
 * 
 */
package com.tenikkan.escape.entity;

import com.tenikkan.escape.input.IController;
import com.tenikkan.math.Vector2f;

/**
 * @author Nicholas Hamilton
 *
 */
public class Player extends Entity
{   
    public Player(String name, int id, Vector2f position, IController c) 
    {
        super(name, id, 0xffffff, 1.9f, 2.9f, 0.4f, position, new Vector2f(0, 0), c);
    }
}
