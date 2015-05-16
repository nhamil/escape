package com.tenikkan.arcana;

import com.tenikkan.arcana.input.Controller;
import com.tenikkan.arcana.level.Tile;
import com.tenikkan.util.Manager;

public class Resource
{
    private static int size = 256;
    private static Manager<Tile> tileManager = new Manager<Tile>(size);
    private static Manager<Controller> controllerManager = new Manager<Controller>(size);
    
    public static Manager<Tile> getTileManager() { return tileManager; }

    public static Manager<Controller> getControllerManager() { return controllerManager; }
}
