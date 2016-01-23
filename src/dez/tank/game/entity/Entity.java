package dez.tank.game.entity;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.Drawable;

import java.awt.*;

abstract public class Entity implements Drawable {

    public static final int SCALE = 1;

    protected int        x;
    protected int        y;
    protected Atlas      atlas;
    protected EntityType entityType;

    public Entity(EntityType entityType, int x, int y, Atlas atlas) {
        this.x = x;
        this.y = y;
        this.atlas = atlas;
        this.entityType = entityType;
    }

    abstract public void update();

    abstract public void render(Graphics2D graphics2D);

}
