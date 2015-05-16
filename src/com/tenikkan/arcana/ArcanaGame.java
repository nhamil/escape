package com.tenikkan.arcana;

import java.awt.Graphics;

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
    private Controller controller;
    
    private Player player;
    
    private Level level;
    
    private Vector2f gravity;
    
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
        
        initManagers();
        
        render = new Renderer(camera, display.getWidth(), display.getHeight());
        
        level = new Level(1600, 800);
        
        player = new Player("Thinic", new Vector2f(level.getWidth()/2, level.getHeight() - 3));
        
        gravity = new Vector2f(0, -0.03f);
        
        time = System.currentTimeMillis();
    }
    
    private void initManagers() 
    {
        controller = new KeyboardController(keys, 0);
        Resource.getControllerManager().add(controller); 
        
        Resource.getTileManager().add(new AirTile(0, false));
        Resource.getTileManager().add(new GrassTile(1));
        Resource.getTileManager().add(new DirtTile(2));
        Resource.getTileManager().add(new StoneTile(3));
        Resource.getTileManager().add(new AirTile(255, true));
    }

    long time = System.currentTimeMillis();
    
    @Override
    public void update()
    {
        render.setWidth(display.getWidth());
        render.setHeight(display.getHeight());
        
        player.accelerate(gravity);
        player.update(level);
        
        positionCamera();
        
        display.setTitle(TITLE + " - " + getData());
    }
    
    private void positionCamera() 
    {
        camera.setPosition(player.getPosition().add(player.getWidth()/2, player.getHeight()/2));
        
        if(camera.getPosition().getX() < display.getWidth() / 2 / camera.getPixelsPerUnit()) 
        {
            camera.getPosition().setX(display.getWidth() / 2 / camera.getPixelsPerUnit());
        }
        
        if(camera.getPosition().getX() > level.getWidth() - display.getWidth() / 2 / camera.getPixelsPerUnit()) 
        {
            camera.getPosition().setX(level.getWidth() - display.getWidth() / 2 / camera.getPixelsPerUnit());
        }
        
        if(camera.getPosition().getY() < display.getHeight() / 2 / camera.getPixelsPerUnit()) 
        {
            camera.getPosition().setY(display.getHeight() / 2 / camera.getPixelsPerUnit());
        }
        
        if(camera.getPosition().getY() > level.getHeight() -  display.getHeight() / 2 / camera.getPixelsPerUnit()) 
        {
            camera.getPosition().setY(level.getHeight() -  display.getHeight() / 2 / camera.getPixelsPerUnit());
        }
    }

    @Override
    public void render()
    {
        display.clear();
        
        Graphics g = display.getGraphics();
        
        render.drawLevel(g, level);
        render.drawEntity(g, player);
        
        display.swapBuffers();
    }
    
    public static void main(String args[]) 
    {
        new ArcanaGame(-1).run();
    }
}
