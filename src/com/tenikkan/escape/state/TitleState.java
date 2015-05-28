package com.tenikkan.escape.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.tenikkan.escape.Resource;
import com.tenikkan.escape.graphics.Display;

public class TitleState extends GameState
{
    private Font font = new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 100);
    private Font font2 = new Font(Font.MONOSPACED, Font.BOLD, 30);
    
    public TitleState(int id, StateBasedGame game)
    {
        super("title_state", id, game);
    }

    @Override 
    public void reset() 
    {
        getDisplay().setCursor(Resource.CURSOR);
    }
    
    @Override
    public void update()
    {
        if(getKeyboard().isKeyDown(KeyEvent.VK_ENTER)) getGame().setState("play_state");
        if(getKeyboard().isKeyDown(KeyEvent.VK_DELETE)) getGame().setState("tutorial_state");
    }

    @Override
    public void render()
    {
        Display display = getDisplay();
        Graphics g = display.getGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, display.getWidth(), display.getHeight());
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("ESCAPE", 10, 70);
        g.setFont(font2);
        g.drawString("Press [Enter] to play...", 10, display.getHeight() - 40);
        g.drawString("Press [Delete] for instructions...", 10, display.getHeight() - 10);
        
        display.swapBuffers();
    }
}
