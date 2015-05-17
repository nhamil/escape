package com.tenikkan.escape.state;

import java.awt.Graphics;

import com.tenikkan.escape.Camera;
import com.tenikkan.escape.Resource;
import com.tenikkan.escape.entity.Entity;
import com.tenikkan.escape.entity.Player;
import com.tenikkan.escape.entity.WalkingEntity;
import com.tenikkan.escape.graphics.Display;
import com.tenikkan.escape.graphics.Renderer;
import com.tenikkan.escape.input.IController;
import com.tenikkan.escape.input.KeyboardController;
import com.tenikkan.escape.level.BasicTile;
import com.tenikkan.escape.level.EndTile;
import com.tenikkan.escape.level.Level;
import com.tenikkan.math.Vector2f;

public class PlayState extends GameState
{
    private Camera camera; 
    private Player player;
    private Level level;
    private Renderer render;
    private IController playerController;
    
    public PlayState(String name, int id, Display display) 
    {
        super("play_state", id, display);
        
        init();
    }
    
    private void init() 
    {
        initManagers();
        
        camera = new Camera(0, 0, 8);
        
        level = new Level(300, 300);
        level.getEntities().setSize(100000);
        
        playerController = new KeyboardController(getKeyboard());
        
        player = new Player("player", level.getEntities().getAvailableID(),
                 new Vector2f(0, level.getTopY(0)), playerController);
        level.getEntities().add(player);
        
        for(int i = 0; i < 10; i++) 
        {
            int x = (int)(Math.random() * level.getWidth());
            int y = level.getTopY(x) + 2;
            Vector2f pos = new Vector2f(x, y);
            Entity e = new WalkingEntity(level.getEntities().getAvailableID(), pos, level);
            System.out.println(e.getID());
            level.getEntities().add(e);
        }
        
        render = new Renderer(camera, getDisplay().getWidth(), getDisplay().getHeight());
        
        positionCamera();
    }
    
    private void positionCamera() 
    {
        Display display = getDisplay();
        
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
    
    private void initManagers() 
    {
        Resource.getTileManager().add(new BasicTile("air", 0, false, 0x66bbff));
        Resource.getTileManager().add(new BasicTile("grass", 1, true, 0x11aa33));
        Resource.getTileManager().add(new BasicTile("dirt", 2, true, 0x7f7f00));
        Resource.getTileManager().add(new BasicTile("stone", 3, true, 0x7f7f7f));
        Resource.getTileManager().add(new EndTile  ("end_tile", 4, 0xffd700));
        Resource.getTileManager().add(new BasicTile("boundry", 255, true, 0x66bbff));
    }

    @Override
    public void update()
    {
        render.setWidth(getDisplay().getWidth());
        render.setHeight(getDisplay().getHeight());
        
        level.update(); 
        
        positionCamera();
    }

    @Override
    public void render()
    {
        Display display = getDisplay();
        display.clear();
        
        Graphics g = display.getGraphics();
        
        render.drawLevel(g, level);
        render.drawEntity(g, player);
        for(Object o : level.getEntities().toArray()) 
        {
            Entity e = (Entity)o;
            if(!(e == null))
                render.drawEntity(g, e); 
        }
        
        display.swapBuffers();
    }
}
