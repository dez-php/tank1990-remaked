package dez.tank.game;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.GameWindow;
import dez.tank.graphics.Sprite;
import dez.tank.graphics.SpriteSheet;
import dez.tank.helpers.Time;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends Machine {

    SpriteSheet sheet;
    int animationSpeed = 3;

    public Player(Point coordinates, Atlas atlas) {
        super(EntityMachineType.SIMPLE, (int) coordinates.getX(), (int) coordinates.getY(), atlas);
        this.sheet = new SpriteSheet(atlas.cut(0, 0, 32, 16), 16);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics2D) {
        Sprite sprite = new Sprite(sheet, 2, 1);
        sprite.transparentColor(0xff000001, 0.02f);
        sprite.draw(graphics2D, GameWindow.WIDTH / 2, GameWindow.HEIGHT / 2);
    }

    @Override
    public void draw(Graphics graphics) {
        this.render((Graphics2D) graphics);
    }
}
