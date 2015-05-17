/**
 * 
 */
package com.tenikkan.escape.graphics;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.tenikkan.escape.input.Keyboard;
import com.tenikkan.escape.input.Mouse;

/**
 * 
 * @author Nicholas Hamilton
 * 
 */
public class Display
{
    private boolean showing = false;
    
    private JFrame frame;
    private Canvas canvas;
    private Keyboard keyboard;
    private Mouse mouse;
    private Robot robot;
    
    private int clearColor = 0x000000;
    
    private Cursor normCur, blankCur;
    
    public Display(String title, int width, int height, int sWidth, int sHeight) 
    {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        keyboard = new Keyboard();
        mouse = new Mouse(this);
        
        canvas = new Canvas();
        canvas.setSize(width, height);
        canvas.addKeyListener(keyboard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        
        normCur = canvas.getCursor();
        blankCur = Toolkit.getDefaultToolkit().createCustomCursor(
                new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
        
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        try
        {
            robot = new Robot();
        } catch(AWTException e)
        {
            e.printStackTrace();
        }
    }
    
    public void setClearColor(int col) { clearColor = col; }
    public void clear() 
    { 
        Graphics g = getGraphics();
        g.setColor(new Color(clearColor));
        
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.dispose();
    }
    
    public void showCursor() { canvas.setCursor(normCur); }
    public void hideCursor() { canvas.setCursor(blankCur); }
    
    public String getTitle() { return frame.getTitle(); }
    public void setTitle(String title) { frame.setTitle(title); }
    
    public int getX() { return frame.getX(); }
    public int getY() { return frame.getY(); }
    
    public boolean isFocused() { return canvas.hasFocus(); }
    
    public void setMousePosition(int x, int y)  { robot.mouseMove(x, y); }
    
    public Keyboard getKeyboard() { return keyboard; }
    public Mouse getMouse() { return mouse; }
    
    public Graphics getGraphics() { return canvas.getBufferStrategy().getDrawGraphics(); }
    
    public int getWidth() { return canvas.getWidth(); }
    public int getHeight() { return canvas.getHeight(); }
    
    public void show() { show(true); }
    public void hide() { show(false); }
    private void show(boolean show) 
    {
        showing = show;
        
        if(show) 
        {
            frame.setVisible(true);
            canvas.setFocusable(true);
            canvas.requestFocus();
            canvas.createBufferStrategy(2);
        } else 
        {
            frame.setVisible(false);
        }
    }
    
    public void swapBuffers() 
    {
        if(!showing) return;
        
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) 
        {
            canvas.createBufferStrategy(3);
            return;
        }
        
        bs.show();
    }
    
    public float getRatio()
    {
        return (float)getWidth()/getHeight();
    }
}
