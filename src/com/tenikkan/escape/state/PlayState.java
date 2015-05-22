package com.tenikkan.escape.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.tenikkan.escape.Camera;
import com.tenikkan.escape.Physics;
import com.tenikkan.escape.Resource;
import com.tenikkan.escape.entity.Entity;
import com.tenikkan.escape.entity.Player;
import com.tenikkan.escape.entity.SimpleEnemyEntity;
import com.tenikkan.escape.graphics.Display;
import com.tenikkan.escape.graphics.Renderer;
import com.tenikkan.escape.input.IController;
import com.tenikkan.escape.input.UserController;
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
    
    public PlayState(int id, StateBasedGame game) 
    {
        super("play_state", id, game);
        
        init();
    }
    
    public void reset() 
    {
        numE = 5;
        width = 500;
        height = 800;
        enemyHealth = 50;
        levelNum = 1;
        init();
    }
    
    private void init() 
    {
        initManagers();
        
        camera = new Camera(0, 0, 16);
        
        render = new Renderer(camera, getDisplay().getWidth(), getDisplay().getHeight());
        
        level = new Level(width, height);
        
        playerController = new UserController(getKeyboard(), getMouse(), render, false);
        
        player = new Player("player", level.getEntities().getAvailableID(), 10000,
                 new Vector2f(0, level.getTopY(0)), playerController);
        
        setupLevel();
        
        positionCamera();
    }
    
    private void positionCamera() 
    {
        Display display = getDisplay();
        
        Vector2f camPos = player.getPosition().add(player.getWidth()/2, player.getHeight()/2);
        camPos.setY(height / 2f);
        camera.setPosition(camPos);
        
        if(camera.getPosition().getX() < display.getWidth() / 2 / camera.getPixelsPerUnit()) 
        {
            camera.getPosition().setX(display.getWidth() / 2 / camera.getPixelsPerUnit());
        }
        
        if(camera.getPosition().getX() > level.getWidth() - display.getWidth() / 2 / camera.getPixelsPerUnit()) 
        {
            camera.getPosition().setX(level.getWidth() - display.getWidth() / 2 / camera.getPixelsPerUnit());
        }
        
//        if(camera.getPosition().getY() < display.getHeight() / 2 / camera.getPixelsPerUnit()) 
//        {
//            camera.getPosition().setY(display.getHeight() / 2 / camera.getPixelsPerUnit());
//        }
//        
//        if(camera.getPosition().getY() > level.getHeight() -  display.getHeight() / 2 / camera.getPixelsPerUnit()) 
//        {
//            camera.getPosition().setY(level.getHeight() -  display.getHeight() / 2 / camera.getPixelsPerUnit());
//        }
    }
    
    private void initManagers() 
    {
        Resource.getTileManager().add(new BasicTile("air", 0, false, 0x66bbff));
        Resource.getTileManager().add(new BasicTile("grass", 1, true, 0x11aa33));
        Resource.getTileManager().add(new BasicTile("dirt", 2, true, 0x7f7f00));
        Resource.getTileManager().add(new BasicTile("stone", 3, true, 0x7f7f7f));
        Resource.getTileManager().add(new EndTile  ("end_tile", 4, 0xffd700));
        Resource.getTileManager().add(new BasicTile("boundry", 255, true, 0x3399cc));
    }
    
    private int numE = 5;
    private int width = 500;
    private int height = 800;
    private int enemyHealth = 50;
    private int levelNum = 1;
    
    @Override
    public void update()
    {
        render.setWidth(getDisplay().getWidth());
        render.setHeight(getDisplay().getHeight());
        
        if(getKeyboard().isKeyDown(KeyEvent.VK_ESCAPE)) 
        {
            reset();
            getGame().setState("title_state");
        }
        
        level.update(); 
        
        positionCamera();
        
        if(player.isTouchingEndTile()) 
        {
            setupLevel();
        }
        
        Object[] enemies = level.getEntities().getAll("simple_enemy");
        for(Object o : enemies) 
        {
            Entity e = (Entity)o;
            if(e != null) 
            {
                if(Physics.collideEntities(player, e)) 
                {
                    int oldHealth = player.getHealth();
                    
                    player.changeHealth(-e.getDamage()); 
                    
                    if(oldHealth != player.getHealth()) player.applyKnockback(e);
                    
                    if(player.getHealth() <= 0) 
                    {
                        reset();
                        getGame().setState("title_state");
                    }
                }
            }
        }
    }

    @Override
    public void render()
    {
        Display display = getDisplay();
        display.clear();
        
        camera.setPixelsPerUnit(display.getWidth() / 100);
        
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
    
    private void setupLevel() 
    {
        level = new Level(width, level.getHeight());
        level.getEntities().setSize(1000);
        
        player.changeEnergy(player.getMaxEnergy() / 2);
        player.changeHealth(player.getMaxHealth() / 2);
        player.getVelocity().set(0, 0);
        player.getPosition().set(1, level.getTopY(1));
        player.setRechargeAmount((int)Math.ceil(levelNum/2f));
        
        level.getEntities().add(player);
        
        player = (Player) level.getEntities().get(player.getID());
        
        for(int i = 0; i < numE; i++) 
        {
            int x = (int)(Math.random() * (level.getWidth() - 100)) + 100;
            int y = level.getTopY(x) + 2;
            Vector2f pos = new Vector2f(x, y);
            Entity e = new SimpleEnemyEntity(level.getEntities().getAvailableID(), enemyHealth, 1000, 0.8f, pos, level);
            level.getEntities().add(e);
        }
        
//        width = (int)(width * 1.25);
        numE =  (int)(numE + 3);
        enemyHealth = (int)(enemyHealth + 50);
        levelNum++;
    }
}
