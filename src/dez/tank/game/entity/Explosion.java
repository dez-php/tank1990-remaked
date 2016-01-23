package dez.tank.game.entity;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.Sprite;
import dez.tank.graphics.SpriteAnimated;
import dez.tank.graphics.SpriteSheet;

import java.awt.*;

public class Explosion extends Entity {

    public static final int SPRITE_SIZE = 16;
    public static final int SCALE = 2;

    SpriteAnimated spriteAnimated;

    public Explosion(EntityType entityType, int x, int y, Atlas atlas) {
        super(entityType, x, y, atlas);

        SpriteSheet sheet = new SpriteSheet(atlas.cut(16 * SPRITE_SIZE, 8 * SPRITE_SIZE, 3 * SPRITE_SIZE, SPRITE_SIZE), SPRITE_SIZE);

        this.spriteAnimated = new SpriteAnimated(sheet, 2, SCALE);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics2D) {
        Sprite sprite = this.spriteAnimated.getSprite();
        sprite.transparentColor(0xff000001, 0);
        sprite.draw(graphics2D, this.x, this.y);
    }

    @Override
    public void draw(Graphics graphics) {
        this.render((Graphics2D) graphics);
    }
}
