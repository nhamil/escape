/**
 * 
 */
package com.tenikkan.escape.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas Hamilton
 *
 */
public class Keyboard extends KeyAdapter
{   
    public static final int LEFT = KeyEvent.VK_J;
    public static final int RIGHT = KeyEvent.VK_L;
    public static final int UP = KeyEvent.VK_I;
    public static final int DOWN = KeyEvent.VK_K;
    public static final int ESCAPE = KeyEvent.VK_ESCAPE;
    public static final int JUMP = KeyEvent.VK_SPACE;
    
    private boolean keys[]; 
    private List<Integer> keyList = new ArrayList<Integer>();
    
    private boolean lastKey = false;
    private boolean newKey = false;
    
    public Keyboard() 
    {
        keys = new boolean[256];
    }
    
    public void update() 
    {
        lastKey = newKey;
        newKey = false;
    }
    
    public boolean isKeyPressed() { return lastKey; }
    
    public boolean isKeyDown(int code) 
    {
        return code < 0 ? false : code >= 256 ? false : keys[code];
    }
    
    public List<Integer> getKeysTyped() 
    { 
        List<Integer> k = new ArrayList<Integer>(keyList);
        keyList.clear();
        return k; 
    }
    
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
        newKey = true;
        if(code < 0 || code >= 256) return;
        keys[code] = true;
        keyList.add(code);
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if(code < 0 || code >= 256) return;
        keys[code] = false;
    }
}
