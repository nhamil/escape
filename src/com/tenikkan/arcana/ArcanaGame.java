package com.tenikkan.arcana;

import com.tenikkan.arcana.entity.*;
import com.tenikkan.arcana.graphics.*;
import com.tenikkan.arcana.input.*;
import com.tenikkan.arcana.level.*;
import com.tenikkan.math.*;
import com.tenikkan.util.*;

public class ArcanaGame extends GameLoop
{
    private static final String TITLE = "Arcana v0.0.0 beta";
    private Display display;
    private Keyboard keys;
    private Renderer render;
    private Camera camera;
    private IController controller;
    
    private Player player;
    
    private Level level;
    
    public ArcanaGame(double frames)
    {
        super(frames, 60.0);
    }

    @Override
    public void init()
    {
        camera = new Camera(0, 3, 16);
        
        display = new Display(TITLE, 800, 600, 800, 600);
        display.show();
        display.setClearColor(0x333333);
        
        keys = display.getKeyboard();
        
        controller = new KeyboardController(keys);
        
        render = new Renderer(display.getGraphics(), camera, display.getWidth(), display.getHeight());
        
        level = new Level(800, 600);
        level.setTile(0, 0, 0);
        
        player = new Player("Thinic", new Vector2f(0, 600 - 19));
        
        TileManager.add(new AirTile(0));
        TileManager.add(new GrassTile(1));
        TileManager.add(new StoneTile(2));
    }

    @Override
    public void update()
    {
        player.update(controller);
        
        camera.setPosition(player.getPosition().add(player.getWidth()/2, player.getHeight()/2));
        
        if(Physics.isCollision(player, level)) Physics.handleCollision(player, level);
        
        display.setTitle(TITLE + " - " + getData());
    }

    @Override
    public void render()
    {
        display.clear();
        
        render.drawLevel(level);
        render.drawEntity(player);
        
        display.render();
    }
    
    public static void main(String args[]) 
    {
        new ArcanaGame(-1).run();
    }
}
