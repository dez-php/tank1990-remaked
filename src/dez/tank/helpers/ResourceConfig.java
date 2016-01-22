package dez.tank.helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ResourceConfig extends ResourceLoader{

    private Properties config;

    public ResourceConfig(String fileName) throws IOException {
        super(fileName);
        config = new Properties();
    }

    public Properties loadProperties() throws IOException {
        config.load(new FileReader(this.getResourceFile()));
        return config;
    }
}
