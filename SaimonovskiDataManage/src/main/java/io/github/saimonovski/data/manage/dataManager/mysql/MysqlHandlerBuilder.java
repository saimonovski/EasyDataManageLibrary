package io.github.saimonovski.data.manage.dataManager.mysql;

public final class MysqlHandlerBuilder {
    private static String database_password, database_username, database_hostname, database_name, database_port;

    public static String getDatabase_password() {
        return database_password;
    }

    public static void setDatabase_password(String database_password) {
        MysqlHandlerBuilder.database_password = database_password;
    }

    public static String getDatabase_username() {
        return database_username;
    }

    public static void setDatabase_username(String database_username) {
        MysqlHandlerBuilder.database_username = database_username;
    }

    public static String getDatabase_hostname() {
        return database_hostname;
    }

    public static void setDatabase_hostname(String database_hostname) {
        MysqlHandlerBuilder.database_hostname = database_hostname;
    }

    public static String getDatabase_name() {
        return database_name;
    }

    public static void setDatabase_name(String database_name) {
        MysqlHandlerBuilder.database_name = database_name;
    }

    public static String getDatabase_port() {
        return database_port;
    }

    public static void setDatabase_port(String database_port) {
        MysqlHandlerBuilder.database_port = database_port;
    }

    public static MysqlHandler build() {

        return new MysqlHandler(database_password, database_username, database_hostname, database_name, database_port);
    }
}
