package com.tenikkan.escape;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tenikkan.escape.level.Tile;
import com.tenikkan.util.Manager;

public class Resource
{
    private static int size = 256;
    private static Manager<Tile> tileManager = new Manager<Tile>(size);
    
    public static Manager<Tile> getTileManager() { return tileManager; }
    
    public static Cursor CURSOR;
    public static Cursor CROSSHAIR;
    
    static 
    {
        BufferedImage cursor = loadImage("res/cursor2.png");
        if(cursor != null) CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "cursor");
        cursor = loadImage("res/cursor.png");
        if(cursor != null) CROSSHAIR = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(16, 16), "crosshair");
    }
    
    public static BufferedImage loadImage(String file) 
    {
        if(file == null) return null;
        try
        {
            BufferedImage img = ImageIO.read(new File(file));
            return img;
        } catch(IOException e)
        {
            System.err.println("could not load image: " + file);
            return null;
        }
    }
}
