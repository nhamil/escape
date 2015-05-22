package com.tenikkan.escape;

import com.tenikkan.escape.graphics.Display;
import com.tenikkan.escape.state.PlayState;
import com.tenikkan.escape.state.StateBasedGame;
import com.tenikkan.escape.state.TitleState;
import com.tenikkan.escape.state.TutorialState;

public class EscapeGame extends StateBasedGame
{
    private static final String TITLE = "Escape v0.4.1 BETA";
    
    public EscapeGame(double frames, Display display)
    {
        super(frames, 60.0, display);
    }

    @Override
    public void init()
    {
        getDisplay().show();
        getDisplay().setClearColor(0x333333);
        
        this.addState(new PlayState(0, this));
        this.addState(new TitleState(1, this));
        this.addState(new TutorialState(2, this));
        
        setState("title_state");
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
        new EscapeGame(60, new Display(TITLE, 800, 600)).run();
    }
}
