package dez.tank.helpers;

import java.io.File;
import java.io.FileNotFoundException;

abstract public class ResourceLoader {

    private File resourceFile;

    public ResourceLoader(String fileName) throws FileNotFoundException {
        resourceFile = new File(fileName);
    }

    public File getResourceFile() {
        return resourceFile;
    }
}
