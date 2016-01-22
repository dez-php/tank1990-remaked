package dez.tank.helpers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceImageLoader extends ResourceLoader {

    public ResourceImageLoader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public BufferedImage loadImage() throws IOException {
        return ImageIO.read(this.getResourceFile());
    }

}
