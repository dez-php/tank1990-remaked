package dez.tank.game;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.Sprite;


import java.awt.*;
import java.util.Map;

abstract public class Machine extends Entity {

    Map<Direction, Sprite> spriteMap;
    Direction direction = Direction.NORTH;

    public static final int scale = 1;

    public Machine(EntityMachineType machineType, int x, int y, Atlas atlas) {
        this.coordinate = new Point(x, y);
        this.atlas = atlas;
        this.entityType = machineType;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

}
