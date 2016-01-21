package dez.game.engine.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

abstract public class ResourceLoader {

    private File resourceFile;

    public ResourceLoader(String fileName) throws FileNotFoundException {
        resourceFile = new File(fileName);
    }

    public File getResourceFile() {
        return resourceFile;
    }
}
