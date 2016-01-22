package dez.tank.game;

import dez.tank.graphics.Atlas;
import dez.tank.graphics.Drawable;
import dez.tank.graphics.LayerManager;
import dez.tank.helpers.ResourceImageLoader;

import java.awt.*;
import java.io.IOException;

public class GameEngine extends Engine {

    private Atlas  atlas;
    private Player player;

    public GameEngine() throws IOException {
        super();

        this.layerManager.getAllLayers().clear();
        this.atlas = new Atlas(new ResourceImageLoader("resource/data/atlas.png").loadImage());
        this.player = new Player(new Point(150, 150), this.atlas);
        this.layerManager.addDrawable(LayerManager.LayerType.PLAYER, this.player);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void render() {
        for (LayerManager.LayerType layerType : LayerManager.LayerType.values()) {
            for (Drawable entity : layerManager.getLayers(layerType)) {
                entity.draw(graphics2D);
            }
        }
    }
}
