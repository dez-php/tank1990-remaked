package dez.game.engine.graphics;

import dez.game.engine.helpers.ResourceImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Atlas {

    BufferedImage image;

    public Atlas() {
        try {
            image = new ResourceImageLoader("resource/data/atlas.png").loadImage();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public BufferedImage cut(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

}
