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
    private Display display;
    
    public GameState(String name, int id, Display display) 
    {
        this.name = name;
        this.id = id;
        this.display = display;
    }
    
    public Display getDisplay() { return display; }
    public Keyboard getKeyboard() { return display.getKeyboard(); }
    public Mouse getMouse() { return display.getMouse(); }
    
    public abstract void update();
    public abstract void render();
    
    public boolean isPaused() { return isPaused; }
    public void setPaused(boolean p) { isPaused = p; }
    
    public String getName() { return name; }
    public int getID() { return id; }
}
