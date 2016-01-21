package dez.game.tank.game;

import dez.game.engine.Engine;
import dez.game.engine.graphics.Drawable;
import dez.game.engine.graphics.GameWindow;
import dez.game.engine.graphics.LayerType;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class GameEngine extends Engine {

    public float i = 0;
    public float delta = 0;

    public GameEngine() throws IOException {
        super();
    }

    @Override
    protected void update() {
        this.i += 0.02;
    }

    @Override
    protected void render() {

        layers.clear();

        for(LayerType layerType : LayerType.values()) {
            layers.put(layerType, new LinkedList<Drawable>());
        }

        Drawable drawable = new Drawable() {

            public void draw(Graphics graphics, int value) {
                graphics.setColor(Color.RED);
                graphics.fillOval(
                    GameWindow.WIDTH / 2 + value - 75,
                    GameWindow.HEIGHT / 2 - 75, 150, 150
                );
            }

        };

        layers.get(LayerType.PLAYER).add(drawable);

        layers.get(LayerType.LEVEL).add(new Drawable() {

            public void draw(Graphics graphics, int value) {
                graphics.setColor(Color.GREEN);
                graphics.fillOval(
                    GameWindow.WIDTH / 2 + value - 75,
                    GameWindow.HEIGHT / 2 - 75, 150, 150
                );
            }

        });

        for (Drawable d : layers.get(LayerType.PLAYER)) {
            d.draw(getGraphics2D(), (int) (Math.sin(this.i) * 200));
        }

        for (Drawable d : layers.get(LayerType.LEVEL)) {
            d.draw(getGraphics2D(), - (int) (Math.sin(this.i) * 200));
        }

    }
}
