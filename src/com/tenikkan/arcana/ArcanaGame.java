package com.tenikkan.arcana;

import com.tenikkan.arcana.graphics.Display;
import com.tenikkan.arcana.state.PlayState;
import com.tenikkan.arcana.state.StateBasedGame;

public class ArcanaGame extends StateBasedGame
{
    private static final String TITLE = "Arcana v0.1.0 beta";
    private Display display;
    
    public ArcanaGame(double frames)
    {
        super(frames, 60.0);
    }

    @Override
    public void init()
    {
        display = new Display(TITLE, 800, 600, 800, 600);
        display.show();
        display.setClearColor(0x333333);
        
        this.addState(new PlayState("play_state", 0, display));
    }
    
    @Override
    public void update()
    {
        super.update();
        
        display.setTitle(TITLE + " - " + getData());
    }
    
    @Override
    public void render()
    {
        super.render();
    }
    
    public static void main(String args[]) 
    {
        new ArcanaGame(-1).run();
    }
}
