package com.tenikkan.arcana.state;

import java.awt.Graphics;

import com.tenikkan.arcana.Camera;
import com.tenikkan.arcana.Resource;
import com.tenikkan.arcana.entity.Entity;
import com.tenikkan.arcana.entity.Player;
import com.tenikkan.arcana.graphics.Display;
import com.tenikkan.arcana.graphics.Renderer;
import com.tenikkan.arcana.input.IController;
import com.tenikkan.arcana.input.KeyboardController;
import com.tenikkan.arcana.level.BasicTile;
import com.tenikkan.arcana.level.Level;
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
        camera = new Camera(0, 0, 16);
        
        level = new Level(300, 300);
        
        playerController = new KeyboardController(getKeyboard());
        
        player = new Player("Thinic", level.getEntities().getAvailableID(),
                 new Vector2f(0, level.getHeight() - 5), playerController);
        level.getEntities().add(player);
        
        render = new Renderer(camera, getDisplay().getWidth(), getDisplay().getHeight());
        
        positionCamera();
        initManagers();
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
        
        display.swapBuffers();
    }
}
