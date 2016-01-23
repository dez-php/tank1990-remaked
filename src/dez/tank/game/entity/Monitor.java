package dez.tank.game.entity;

import dez.tank.graphics.Atlas;

import java.awt.*;

public class Monitor extends Entity {

    public Monitor(EntityType entityType, int x, int y, Atlas atlas) {
        super(entityType, x, y, atlas);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics2D) {

    }

    @Override
    public void draw(Graphics graphics) {
        this.render((Graphics2D) graphics);
    }
}
