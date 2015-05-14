package com.tenikkan.arcana;

import com.tenikkan.arcana.graphics.Display;
import com.tenikkan.util.GameLoop;

public class ArcanaGame extends GameLoop
{
    private static final String TITLE = "Arcana v0.0.0 beta";
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
        display.setClearColor(0x111111);
    }

    @Override
    public void update()
    {
        display.setTitle(TITLE + " - " + getData());
    }

    @Override
    public void render()
    {
        display.clear();
        
        display.render();
    }
    
    public static void main(String args[]) 
    {
        new ArcanaGame(-1).run();
    }
}
