/**
 * 
 */
package com.tenikkan.arcana.entity;

import com.tenikkan.arcana.Resource;
import com.tenikkan.math.Vector2f;

/**
 * @author Nicholas Hamilton
 *
 */
public class Player extends Entity
{   
    public Player(String name, Vector2f position) 
    {
        super(name, Resource.getControllerManager().getID("keyboard"), 0xff00ff, 1.9f, 2.9f, 0.4f, position, new Vector2f(0, 0));
    }
}
