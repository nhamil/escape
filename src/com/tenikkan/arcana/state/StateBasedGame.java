package com.tenikkan.arcana.state;

import com.tenikkan.util.GameLoop;
import com.tenikkan.util.Manager;

public class StateBasedGame extends GameLoop
{
    private Manager<GameState> states;
    private int curID;
    
    public StateBasedGame(double fps, double ticks)
    {
        super(fps, ticks);
        states = new Manager<GameState>(256);
    }
    
    public void addState(GameState state) 
    {
        states.add(state);
    } 
    
    public void setState(String name) 
    {
        curID = states.getID(name); 
    }
    
    @Override
    public void update()
    {
        GameState state = states.get(curID);
        if(state != null) state.update();
    }

    @Override
    public void render()
    {
        GameState state = states.get(curID);
        if(state != null) state.render();
    }

    @Override
    public void init()
    {
        
    }
    
}
