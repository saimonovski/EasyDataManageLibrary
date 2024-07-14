package io.github.saimonovski.data.manage.dataManager.mysql;

public class MysqlHandler {
    String database_password, database_username, database_hostname, database_name, database_port;

    protected MysqlHandler(String database_password, String database_username, String database_hostname, String database_name, String database_port) {
        if (!(database_password != null && database_username != null && database_name != null && database_port != null))
        throw new NullPointerException();
        else {
            this.database_password = database_password;
            this.database_username = database_username;
            this.database_hostname = database_hostname;
            this.database_name = database_name;
            this.database_port = database_port;
        }
    }
    //todo connect method
    //todo insert & delete & get data method
    //todo create & delete table methods


}
