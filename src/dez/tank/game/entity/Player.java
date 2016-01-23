package dez.tank.game.entity;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.Sprite;
import dez.tank.io.Input;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends EntityTank {

    private Input input;

    public Player(int x, int y, Atlas atlas, Input input) {
        super(EntityType.PLAYER, TankType.FLASH, x, y, atlas);
        this.input = input;
    }

    @Override
    public void collision(Entity entity) {
        if(entity.entityType == EntityType.ENEMY) {
            ((EntityTank) entity).isDead();
            this.stop();
        }
    }

    @Override
    public void update() {

        if(this.input.getKey(KeyEvent.VK_SPACE)) {
            this.fire();
        }

        if(! this.onStop) {
            if (this.input.getUp()) {
                this.moveUp();
                this.direction = Direction.NORTH;
            } else if (this.input.getDown()) {
                this.moveDown();
                this.direction = Direction.SOUTH;
            } else if (this.input.getLeft()) {
                this.moveLeft();
                this.direction = Direction.WEST;
            } else if (this.input.getRight()) {
                this.moveRight();
                this.direction = Direction.EAST;
            }
        } else {
            this.run();
        }

        if(this.isDead()) {
            System.out.println("You are dead!");
        }

    }

    @Override
    public void render(Graphics2D graphics2D) {
        Sprite sprite = this.spritesMap.get(this.direction).getSprite();
        sprite.transparentColor(0xff000001, 0);
        sprite.draw(graphics2D, x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        this.render((Graphics2D) graphics);
    }
}
