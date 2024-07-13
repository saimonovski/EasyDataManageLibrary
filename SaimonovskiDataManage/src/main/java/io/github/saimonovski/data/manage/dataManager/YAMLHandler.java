package io.github.saimonovski.data.manage.dataManager;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * The YAMLHandler class is responsible for saving, retrieving, and updating data in YAML files.
 */
public class YAMLHandler {
    private final Yaml yaml;
    private final String filePath;

    /**
     * Constructor for the YAMLHandler class.
     * Initializes the Yaml object with appropriate formatting settings.
     *
     * @param filePath the path to the YAML file
     */
    protected YAMLHandler(String filePath) {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yaml = new Yaml(options);
        this.filePath = filePath;
    }

    /**
     * Saves data to a YAML file.
     *
     * @param data the data to be saved, represented as a map
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveData(Map<String, Object> data) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            yaml.dump(data, writer);
        }
    }

    /**
     * Loads data from a YAML file.
     *
     * @return the data loaded from the file, represented as a map
     * @throws IOException if an I/O error occurs while reading from the file
     */
    public Map<String, Object> loadData() throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            return yaml.load(inputStream);
        }
    }

    /**
     * Updates data in a YAML file.
     *
     * @param updates the data to be updated, represented as a map
     * @throws IOException if an I/O error occurs while updating the file
     */
    public void updateData(Map<String, Object> updates) throws IOException {
        Map<String, Object> data = loadData();
        data.putAll(updates);
        saveData(data);
    }

    /**
     * Main method demonstrating the usage of the YAMLHandler class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String filePath = "data.yml";
        YAMLHandler yamlHandler = new YAMLHandler(filePath);

        try {
            Map<String, Object> data = Map.of(
                    "name", "John",
                    "age", 30,
                    "city", "New York"
            );

            yamlHandler.saveData(data);

            Map<String, Object> loadedData = yamlHandler.loadData();
            System.out.println("Loaded data: " + loadedData);

            Map<String, Object> updates = Map.of("age", 31);
            yamlHandler.updateData(updates);

            Map<String, Object> updatedData = yamlHandler.loadData();
            System.out.println("Updated data: " + updatedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
