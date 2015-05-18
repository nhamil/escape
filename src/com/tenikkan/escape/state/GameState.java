package com.tenikkan.escape.state;

import com.tenikkan.escape.graphics.Display;
import com.tenikkan.escape.input.Keyboard;
import com.tenikkan.escape.input.Mouse;
import com.tenikkan.util.Identifiable;

public abstract class GameState implements Identifiable
{
    private String name;
    private int id;
    private boolean isPaused;
    private StateBasedGame game;
    
    public GameState(String name, int id, StateBasedGame game) 
    {
        this.name = name;
        this.id = id;
        this.game = game;
        
        reset();
    }
    
    public Display getDisplay() { return game.getDisplay(); }
    public Keyboard getKeyboard() { return game.getDisplay().getKeyboard(); }
    public Mouse getMouse() { return game.getDisplay().getMouse(); }
    
    public abstract void reset();
    public abstract void update();
    public abstract void render();
    
    public boolean isPaused() { return isPaused; }
    public void setPaused(boolean p) { isPaused = p; }
    
    public String getName() { return name; }
    public int getID() { return id; }
}
