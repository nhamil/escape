package com.tenikkan.arcana.input;

import com.tenikkan.arcana.entity.Entity;
import com.tenikkan.arcana.level.Level;
import com.tenikkan.math.Vector2f;

public class SimpleMoveAI implements IController
{
    private Vector2f goalPos;
    private Entity e;
    private Level level;
    
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean jump = false;
    
    public SimpleMoveAI(Entity e, Level level)
    {
        this.e = e;
        this.level = level;
        goalPos = e.getPosition().copy();
    }
    
    public void update() 
    {
        float x1 = goalPos.getX();
        float x2 = e.getPosition().getX();
        
        if(x1 == x2 || Math.abs(x1 - x2) < 1e-1) 
        {
            int nx = (int)(Math.random() * level.getWidth());
            int ny = (int)(Math.random() * level.getHeight());
            goalPos = new Vector2f(nx, ny);
        }
        
        if(e.getPosition().getX() < goalPos.getX()) 
        {
            moveLeft = false;
            moveRight = true;
        } else 
        {
            moveLeft = true;
            moveRight = false;
        }

        int dx = x1 < x2 ? -1 : 1;
        int x = (int)x2;
        
        if(level.getTileData(x + dx * 1, (int)e.getPosition().getY()).isSolid() || 
           level.getTileData(x + dx * 2, (int)e.getPosition().getY()).isSolid() || 
           level.getTileData(x + dx * 3, (int)e.getPosition().getY()).isSolid() || 
           level.getTileData(x + dx * 4, (int)e.getPosition().getY()).isSolid()) 
        {
            jump = true;
        } else 
        {
            jump = false;
        }
    }
    
    @Override
    public boolean get(Input in)
    {
        switch(in) 
        {
        case MOVE_LEFT: return moveLeft;
        case MOVE_RIGHT: return moveRight;
        case JUMP: return jump;
        default: return false;
        }
    }
    
}
