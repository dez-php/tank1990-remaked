package dez.tank.game;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.Drawable;

import java.awt.*;

abstract public class Entity implements Drawable {

    protected Point coordinate;
    protected Atlas atlas;
    protected Enum  entityType;

    abstract public void update();

    abstract public void render(Graphics2D graphics2D);

}
