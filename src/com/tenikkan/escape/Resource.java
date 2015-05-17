package com.tenikkan.escape;

import com.tenikkan.escape.level.Tile;
import com.tenikkan.util.Manager;

public class Resource
{
    private static int size = 256;
    private static Manager<Tile> tileManager = new Manager<Tile>(size);
    
    public static Manager<Tile> getTileManager() { return tileManager; }
}
