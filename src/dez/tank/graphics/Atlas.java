package dez.tank.graphics;

import dez.tank.helpers.ResourceImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Atlas {

    BufferedImage image;

    public Atlas(BufferedImage image) {
        this.image  = image;
    }

    public BufferedImage cut(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

}
