package dez.tank.game.entity;

import dez.tank.game.BulletManager;
import dez.tank.game.GameEngine;
import dez.tank.graphics.Atlas;
import dez.tank.graphics.LayerManager;
import dez.tank.graphics.SpriteAnimated;
import dez.tank.graphics.SpriteSheet;
import dez.tank.helpers.Time;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

abstract public class EntityTank extends Entity {

    public static final int scale       = 1;
    public static final int SPRITE_SIZE = 16;

    protected BulletManager                  bulletManager;
    protected TankType                       tankType;
    protected Map<Direction, SpriteAnimated> spritesMap;
    protected Direction direction = Direction.getRandomDirection();

    protected int   speed;
    protected int   bulletSpeed;
    protected int   damage;
    protected int   armor;
    protected int   health;
    protected float recharge;

    protected boolean onStop;
    protected boolean onCollision;

    private long lastShot;

    public enum Direction {

        NORTH(0, EntityBullet.Direction.NORTH),
        SOUTH(SPRITE_SIZE * 4, EntityBullet.Direction.SOUTH),
        EAST(SPRITE_SIZE * 6, EntityBullet.Direction.EAST),
        WEST(SPRITE_SIZE * 2, EntityBullet.Direction.WEST);

        private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final Random          RANDOM = new Random();

        public  int                    offsetX;
        private EntityBullet.Direction bulletDirection;

        Direction(int offsetX, EntityBullet.Direction direction) {
            this.offsetX = offsetX;
            this.bulletDirection = direction;
        }

        public static Direction getRandomDirection() {
            return VALUES.get(RANDOM.nextInt(VALUES.size()));
        }

        public EntityBullet.Direction getBulletDirection() {
            return this.bulletDirection;
        }

        public Direction opposite(Direction direction) {
            Direction d = Direction.NORTH;

            if (direction == Direction.NORTH) {
                d = Direction.SOUTH;
            } else if (direction == Direction.SOUTH) {
                d = Direction.NORTH;
            } else if (direction == Direction.WEST) {
                d = Direction.EAST;
            } else if (direction == Direction.EAST) {
                d = Direction.WEST;
            }

            return d;
        }

    }

    public EntityTank(EntityType entityType, TankType tankType, int x, int y, Atlas atlas) {

        super(entityType, x, y, atlas);

        this.tankType = tankType;
        this.speed = tankType.speed;
        this.bulletSpeed = tankType.bulletSpeed;
        this.damage = tankType.damage;
        this.armor = tankType.armor;
        this.health = tankType.health;
        this.recharge = tankType.recharge;

        this.onStop = false;
        this.onCollision = false;

        this.bulletManager = GameEngine.bulletManager;

        spritesMap = new EnumMap<Direction, SpriteAnimated>(Direction.class);

        for (Direction direction : Direction.values()) {
            BufferedImage image = this.atlas.cut(direction.offsetX, tankType.spriteCoordinates[0] * SPRITE_SIZE, 2 * SPRITE_SIZE, SPRITE_SIZE);
            SpriteSheet   sheet = new SpriteSheet(image, SPRITE_SIZE);

            spritesMap.put(direction, new SpriteAnimated(sheet, 8, SCALE));
        }

        this.lastShot = Time.nano();
    }

    public void hit(int damage) {
        if (this.armor > 0) {
            this.armor -= (damage / 2);
        } else {
            this.health -= damage;
        }
    }

    public void fire() {
        long elapsedTime = Time.nano() - this.lastShot;

        if(elapsedTime / (Time.ONE_NANO_SECOND / this.recharge) >= 1) {

            int x = this.x + EntityBullet.SPRITE_SIZE;
            int y = this.y + EntityBullet.SPRITE_SIZE;

            EntityBullet entityBullet = new EntityBullet(x - 8, y - 8, this.atlas, this, this.direction.getBulletDirection());

            GameEngine.bulletManager.add(entityBullet);
            GameEngine.layerManager.addDrawable(LayerManager.LayerType.BULLET, entityBullet);

            elapsedTime = 0;
            this.lastShot = Time.nano();
        }

    }

    public Rectangle rectangle() {
        return new Rectangle(this.x, this.y, SPRITE_SIZE * SCALE, SPRITE_SIZE * SCALE);
    }

    public void changeDirection() {
        this.direction = Direction.getRandomDirection();
    }

    public void stop() {
        this.onStop = true;
    }

    public void run() {
        this.onStop = false;
    }

    public void moveUp() {
        int newY = this.y;

        newY -= this.speed;

        if (0 >= newY) {
            newY = 0;
            this.direction = Direction.getRandomDirection();
        }

        this.y = newY;
    }

    public void moveDown() {
        int newY = this.y;

        newY += this.speed;

        if (newY >= (GameEngine.HEIGHT - SPRITE_SIZE)) {
            newY = GameEngine.HEIGHT - SPRITE_SIZE;
            this.direction = Direction.getRandomDirection();
        }

        this.y = newY;
    }

    public void moveLeft() {
        int newX = this.x;

        newX -= this.speed;

        if (0 >= newX) {
            newX = 0;
            this.direction = Direction.getRandomDirection();
        }

        this.x = newX;
    }

    public void moveRight() {
        int newX = this.x;

        newX += this.speed;

        if (newX >= (GameEngine.WIDTH - SPRITE_SIZE)) {
            newX = GameEngine.WIDTH - SPRITE_SIZE;
            this.direction = Direction.getRandomDirection();
        }

        this.x = newX;
    }

    public boolean isDead() {
        return 0 >= this.getHealth();
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public static int getScale() {
        return scale;
    }

    abstract public void collision(Entity entity);

}
