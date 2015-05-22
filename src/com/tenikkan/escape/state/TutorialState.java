package com.tenikkan.escape.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.tenikkan.escape.graphics.Display;

public class TutorialState extends GameState
{
    private Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
    private Font font2 = new Font(Font.MONOSPACED, Font.BOLD, 30);
    
    public TutorialState(int id, StateBasedGame game)
    {
        super("tutorial_state", id, game);
    }

    @Override 
    public void reset() 
    {
    }
    
    @Override
    public void update()
    {
        if(getKeyboard().isKeyDown(KeyEvent.VK_ENTER)) getGame().setState("play_state");
        if(getKeyboard().isKeyDown(KeyEvent.VK_ESCAPE)) getGame().setState("title_state");
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
        
        int height = 20;
        
        g.drawString("Welcome to Escape.", 10, height * 1);
        g.drawString("You are stuck in a hostile land and must find a way out.", 10, height * 2);
        g.drawString("In order to leave a level, you must get to the end and touch", 10, height * 3);
        g.drawString("the yellow tiles. Enemies become increasingly tougher as the", 10, height * 4);
        g.drawString("game progresses.", 10, height * 5);
        
        g.drawString("Move left        - [A] or [J]", 10, height * 7);
        g.drawString("Move right       - [D] or [L]", 10, height * 8);
        g.drawString("Jump             - [Space]", 10, height * 9);
        g.drawString("Primary Attack   - [Left_Mouse_Button]", 10, height * 10);
        g.drawString("Secondary Attack - [Right_Mouse_Button]", 10, height * 11);
        g.drawString("Title Screen     - [Escape]", 10, height * 11);
        
        g.drawString("You can only attack with ranged weapons. As you shoot, you", 10, height * 14);
        g.drawString("slowly run out of energy, which is displayed on the screen as", 10, height * 15);
        g.drawString("the blue bar above you character. When you get hit by enemies,", 10, height * 16);
        g.drawString("you loose health, which is shown with the greeen bar. As you", 10, height * 17);
        g.drawString("progress through the game, you will regain energy faster. When", 10, height * 18);
        g.drawString("you start a new level, you gain back up to half of your full", 10, height * 19);
        g.drawString("energy and health.", 10, height * 20);
        
        g.drawString("When you die, you will be brought back to the title screen.", 10, height * 22);
        
        g.drawString("Good luck...", 10, height * 24);
        
        g.setFont(font2);
        g.drawString("Press [Enter] to play...", 10, display.getHeight() - 40);
        g.drawString("Press [Escape] for title screen...", 10, display.getHeight() - 10);
        
        display.swapBuffers();
    }
}
