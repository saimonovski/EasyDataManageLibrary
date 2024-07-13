package io.github.saimonovski.data.manage;

import io.github.saimonovski.data.manage.dataManager.Connector;
import io.github.saimonovski.data.manage.dataManager.YAMLHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * example of using this library
 *
 * @hidden this is only example
 */
public class Main {
    /**
     * this is only example
     * @throws IOException nothing
     * @hidden example
     */
    public static void main(String[]args) throws IOException {
        //example of creating manager for yaml files

        YAMLHandler yamlManager = Connector.connectYaml("config.yml");

            //below you can see how you can save data to a yaml file
            Map<String, Object> yamlDatas = new HashMap<>();
            yamlDatas.put("number1", 123);
            yamlDatas.put("number2", 1234);
            yamlDatas.put("number3", 12345);
            yamlManager.saveData(yamlDatas);

            //here you can see how you can get data from a yaml file

            System.out.println(yamlManager.loadData().toString());


    }
    }
