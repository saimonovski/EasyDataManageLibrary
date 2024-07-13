package io.github.saimonovski.data.manage.dataManager;


/**
 * connector making a connections to various option for savings file types
 */
public interface Connector {
    /**
     * Method  which can manage data saved in the yaml file (.yml)
     * @param filePath path to .yml file which can be created or read(for example "config.yml" or "messages/message1.yml")
     * @return returns a class which can manage data saved in the yaml file (.yml)
     */
    static YAMLHandler connectYaml(String filePath) {
        return new YAMLHandler(filePath);
    }

    /**
     * Method  which can  manage data saved in the database (mysql)
     *
     * @return Method returns a class which can  manage data saved in the database (mysql)
     */
    static void connectMysql() {

    }
    //TODO makes a method that can connect to a database.
    //TODO makes a method that can connect to a

}
