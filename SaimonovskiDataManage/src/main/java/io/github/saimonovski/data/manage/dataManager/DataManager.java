package io.github.saimonovski.data.manage.dataManager;


/**
 * class making a connections to various option for savings file types
 */
public class DataManager {
    private DataManager() {
        //this constructor makes nothing because you must to use the static methods
    }

    /**
     * Method  which can manage data saved in the yaml file (.yml)
     *
     * @param filePath path to .yml file which can be created or read(for example "config.yml" or "messages/message1.yml")
     * @return returns a class which can manage data saved in the yaml file (.yml)
     */
    public static YAMLHandler createInstance(String filePath) {
        return new YAMLHandler(filePath);
    }

    /**
     * Method  which can  manage data saved in the database (mysql)
     *
     * @param user         Username whose can access to a database
     * @param password     password for a database
     * @param hostname     hostname of database
     * @param databaseName name of database
     * @param port         port of database
     * @return Method returns a class which can  manage data saved in the database (mysql)
     */
    public static MYSQLHandler createInstance(String user, String password, String hostname, String databaseName, String port) {
        return new MYSQLHandler(user, password, hostname, databaseName, port);
    }

}
