package dez.tank.game.entity;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.Sprite;
import dez.tank.helpers.Time;

import java.awt.*;
import java.util.Random;

public class Enemy extends EntityTank {

    protected long lastTimeUpdate;
    protected int  nextTimeUpdate;

    public Enemy(TankType tankType, int x, int y, Atlas atlas) {
        super(EntityType.PLAYER, tankType, x, y, atlas);

        this.lastTimeUpdate = Time.nano();
        this.nextTimeUpdate = (new Random().nextInt(30) + 1) / 10;
    }

    protected void updateDirection() {
        long elapsedTime = Time.nano() - this.lastTimeUpdate;

        if (elapsedTime > Time.ONE_NANO_SECOND * this.nextTimeUpdate) {
            this.lastTimeUpdate = Time.nano();
            this.nextTimeUpdate = (new Random().nextInt(20) + 1) / 10;

            this.changeDirection();
        }
    }

    @Override
    public void collision(Entity entity) {

        if (entity.entityType == EntityType.PLAYER) {
            ((Player) entity).isDead();
            this.onCollision = true;
            this.direction = this.direction.opposite(((Player) entity).direction);
        } else if (entity.entityType == EntityType.BULLET) {
            if (((EntityBullet) entity).getOwner().entityType == EntityType.PLAYER) {
                this.hit(((EntityBullet) entity).getOwner().getDamage());
            }
        }

    }

    public void move() {
        if (this.direction == Direction.NORTH) {
            this.moveUp();
        } else if (this.direction == Direction.SOUTH) {
            this.moveDown();
        } else if (this.direction == Direction.WEST) {
            this.moveLeft();
        } else if (this.direction == Direction.EAST) {
            this.moveRight();
        }
    }

    @Override
    public void update() {

        this.move();
        this.fire();

        this.updateDirection();

        if (this.onStop) {
            this.changeDirection();
            this.run();
        }

        this.onCollision = false;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (!isDead()) {
            Sprite sprite = this.spritesMap.get(this.direction).getSprite();
            sprite.transparentColor(0xff000001, 0);
            sprite.draw(graphics2D, x, y);
        } else {

        }
    }

    @Override
    public void draw(Graphics graphics) {
        this.render((Graphics2D) graphics);
    }
}
