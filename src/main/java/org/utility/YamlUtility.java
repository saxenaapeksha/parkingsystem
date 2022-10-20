package org.utility;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlUtility {
    private static final String filename = "src/main/resources/spotDetails.yaml";

    public static Map<String, Map<String, Integer>> loadYaml() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(filename));
        Yaml yaml = new Yaml();
        Map<String, Map<String, Integer>> data = (Map<String, Map<String, Integer>>) yaml.load(inputStream);
        return data;
    }
}
