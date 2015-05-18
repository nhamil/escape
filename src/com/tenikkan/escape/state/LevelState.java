package com.tenikkan.escape.state;

import com.tenikkan.escape.graphics.Display;

public class LevelState extends GameState
{
    private int ticksLeft;
    
    public LevelState(int id, StateBasedGame game)
    {
        super("level_state", id, game);
    }

    @Override 
    public void reset() 
    {
        ticksLeft = 2 * 60;
    }
    
    @Override
    public void update()
    {
        ticksLeft--;
        
    }

    @Override
    public void render()
    {
    }
}
