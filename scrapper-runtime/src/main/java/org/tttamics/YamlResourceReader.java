package org.tttamics;

import com.esotericsoftware.yamlbeans.YamlReader;

import javax.inject.Named;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Named
public class YamlResourceReader {
    public YamlResourceReader() {}

    public Object readRawYamlFile(String yamlFilePath) throws IOException, URISyntaxException {
        URL url = this.getClass().getClassLoader().getResource(yamlFilePath);
        Path path = Paths.get(url.toURI());
        File yamlFile = path.toFile();
        YamlReader reader = new YamlReader(new FileReader(yamlFile));
        Object rawData = reader.read();
        return rawData;
    }
}
