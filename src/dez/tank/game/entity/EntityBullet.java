package dez.tank.game.entity;

import dez.tank.game.GameEngine;
import dez.tank.graphics.Atlas;
import dez.tank.graphics.Sprite;
import dez.tank.graphics.SpriteSheet;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EntityBullet extends Entity {

    public static final int SPRITE_SIZE = 8;

    private EntityTank             owner;
    private Map<Direction, Sprite> spriteMap;
    private Direction              direction;
    private boolean                isDead;

    public EntityBullet(int x, int y, Atlas atlas, EntityTank owner, Direction direction) {
        super(EntityType.BULLET, x, y, atlas);

        this.isDead = false;
        this.owner = owner;
        this.direction = direction;

        this.spriteMap = new HashMap<>();
        SpriteSheet spriteSheet = new SpriteSheet(atlas.cut(20 * 16, 6 * 16 + 4, 8 * 4, 8), 8);

        for (Direction d : Direction.values()) {
            this.spriteMap.put(d, new Sprite(spriteSheet, SCALE, d.position()));
        }
    }

    public enum Direction {
        NORTH(0), SOUTH(2), EAST(3), WEST(1);

        Direction(int position) {
            this.position = position;
        }

        public int position() {
            return position;
        }

        int position;
    }

    public boolean isDead() {
        return isDead;
    }

    public EntityTank getOwner() {
        return owner;
    }

    public Direction getDirection() {
        return direction;
    }

    public void moveUp() {
        int newY = this.y;

        newY -= this.getOwner().getBulletSpeed();

        if (0 >= newY) {
            newY = 0;
            this.isDead = true;
        }

        this.y = newY;
    }

    public void moveDown() {
        int newY = this.y;

        newY += this.owner.getBulletSpeed();

        if (newY >= (GameEngine.HEIGHT - SPRITE_SIZE)) {
            newY = GameEngine.HEIGHT - SPRITE_SIZE;
            this.isDead = true;
        }

        this.y = newY;
    }

    public void moveLeft() {
        int newX = this.x;

        newX -= this.owner.getBulletSpeed();

        if (0 >= newX) {
            newX = 0;
            this.isDead = true;
        }

        this.x = newX;
    }

    public void moveRight() {
        int newX = this.x;

        newX += this.owner.getBulletSpeed();

        if (newX >= (GameEngine.WIDTH - SPRITE_SIZE)) {
            newX = GameEngine.WIDTH - SPRITE_SIZE;
            this.isDead = true;
        }

        this.x = newX;
    }

    public void collision(Entity entity) {
        if(entity.entityType != this.owner.entityType) {
            ((EntityTank) entity).hit(this.owner.damage);
            this.isDead = true;
        }
    }

    public Rectangle rectangle() {
        return new Rectangle(this.x, this.y, SPRITE_SIZE, SPRITE_SIZE);
    }

    @Override
    public void update() {

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
    public void render(Graphics2D graphics2D) {
        if(! this.isDead) {
            Sprite sprite = this.spriteMap.get(this.direction);
            sprite.transparentColor(0xff000001, 0);
            sprite.draw(graphics2D, x, y);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        this.render((Graphics2D) graphics);
    }

}
