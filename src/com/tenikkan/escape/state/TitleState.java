package com.tenikkan.escape.state;

public class TitleState extends GameState
{
    @SuppressWarnings( "unused" )
    private int ticksLeft;
    
    public TitleState(int id, StateBasedGame game)
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
