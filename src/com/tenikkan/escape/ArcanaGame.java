package com.tenikkan.escape;

import com.tenikkan.escape.graphics.Display;
import com.tenikkan.escape.state.PlayState;
import com.tenikkan.escape.state.StateBasedGame;

public class ArcanaGame extends StateBasedGame
{
    private static final String TITLE = "Escape v0.2.0 beta";
    
    public ArcanaGame(double frames, Display display)
    {
        super(frames, 60.0, display);
    }

    @Override
    public void init()
    {
        getDisplay().show();
        getDisplay().setClearColor(0x333333);
        
        this.addState(new PlayState("play_state", 0, this));
    }
    
    @Override
    public void update()
    {
        super.update();
        
        getDisplay().setTitle(TITLE + " - " + getData());
    }
    
    @Override
    public void render()
    {
        super.render();
    }
    
    public static void main(String args[]) 
    {
        new ArcanaGame(-1, new Display(TITLE, 800, 600)).run();
    }
}
