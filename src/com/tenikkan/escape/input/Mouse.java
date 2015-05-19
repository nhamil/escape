/**
 * 
 */
package com.tenikkan.escape.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tenikkan.escape.graphics.Display;

/**
 * @author Nicholas Hamilton
 *
 */
public class Mouse extends MouseAdapter
{   
    public static final int MAIN_BUTTON = MouseEvent.BUTTON1;
    public static final int SECONDARY_BUTTON = MouseEvent.BUTTON3;
    
    private Display display;
    
    private boolean buttons[];
    
    private int dx, dy;
    
    private int oldGX, oldGY;
    private int gX, gY;
    private int x, y;
    
    public Mouse(Display display) 
    {
        buttons = new boolean[5];
        gX = oldGX = dx = x = 0;
        gY = oldGY = dy = y = 0;
        this.display = display;
    }
    
    public void move(int dx, int dy) 
    {
        this.dx = dx;
        this.dy = dy;
        gX += dx;
        gY += dy;
        display.setMousePosition(gX, gY);
    }
    
    public void setGlobalPosition(int gx, int gy) 
    {
        display.setMousePosition(gx, gy);
        oldGX = gX;
        oldGY = gY;
        gX = gx;
        gY = gy;
        dx = gX - oldGX;
        dy = gY - oldGY;
        x += dx;
        y += dy;
    }
    
    public void update() 
    {
        dx = gX - oldGX;
        dy = gY - oldGY;
        oldGX = gX;
        oldGY = gY;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public int getDX() { return dx; }
    public int getDY() { return dy; }
    
    public int getGlobalX() { return gX; }
    public int getGlobalY() { return gY; }
    
    public boolean isButtonDown(int code) 
    {
        return code < 0 ? false : code >= 5 ? false : buttons[code];
    }
    
    public void mouseMoved(MouseEvent e) 
    {
        gX = e.getXOnScreen();
        gY = e.getYOnScreen();
        x = e.getX();
        y = e.getY();
    }
    
    public void mouseDragged(MouseEvent e) 
    {
        mouseMoved(e);
    }
    
    public void mousePressed(MouseEvent e) 
    {
        int code = e.getButton();
        if(code < 0 || code >= 5) return;
        buttons[code] = true;
    }
    
    public void mouseReleased(MouseEvent e) 
    {
        int code = e.getButton();
        if(code < 0 || code >= 5) return;
        buttons[code] = false;
    }
}
