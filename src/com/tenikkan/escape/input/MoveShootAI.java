package com.tenikkan.escape.input;

import com.tenikkan.escape.entity.Entity;
import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;

public class MoveShootAI implements IController
{
    private Vector2f goalPos;
    private Entity e;
    private Level level;
    
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean jump = false;
    
    private Vector2f aim = new Vector2f();
    private boolean shoot = false;
    
    public MoveShootAI(Entity e, Level level)
    {
        this.e = e;
        this.level = level;
        goalPos = e.getPosition().copy();
    }
    
    public void update() 
    {
        float x1 = goalPos.getX();
        float x2 = e.getPosition().getX();
        
        shoot = false;
        
        if(x1 == x2 || Math.abs(x1 - x2) < 1e-1) 
        {
            int nx = (int)(Math.random() * 20) - 10;
            goalPos = new Vector2f(x2 + nx, 0);
        }
        
        Entity player = level.getEntities().get("player");
        if(player != null) 
        {
            if(player.getPosition().sub(e.getPosition()).length() < 70) 
            {
                goalPos = player.getPosition().copy();
                aim = player.getPosition().add(player.getWidth()/2, player.getHeight()/2);
                shoot = true;
            }
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
        case FIRE_PRIMARY_WEAPON: return shoot;
        default: return false;
        }
    }

    public Vector2f getAimPosition()
    {
        return aim;
    }
}
