package io.github.saimonovski.data.manage.dataManager.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class MysqlHandler {
    String database_password, database_username, database_hostname, database_name, database_port;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public final String DB_URL;
    private Connection connection;
    private Statement statement;

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
        DB_URL = "jdbc:mysql://" + database_hostname + "/" + database_name;
    }


    private boolean connect() throws SQLException {
        try {
            Class.forName(MysqlHandler.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot find JDBC driver. This is an error from the code");
            System.out.println(e.getMessage());
            return false;
        }

        connection = DriverManager.getConnection(DB_URL, database_username, database_password);
        statement = connection.createStatement();

        return true;
    }

    /**
     * Method which will create tables in your database
     * @param tables  keymap where key is a table name and a value is other keymap where key is a column name and value is a sql column value e.g. LONGTEXT or VARCHAR(255)
     * @return always return true unless were not got an exception
     * @throws SQLException throws an exception if you  couldn't connect to a database e.g. for invalid password or invalid data
     */
    public boolean createTables(Map<String, Map<String, String>> tables) throws SQLException {
        for (String s : tables.keySet()
        ) {
            if (!createTable(s, tables.get(s))) return false;
            break;
        }
        return true;
    }

    /**
     * Method which will create table in your database
     *
     * @param tableName Name of mysql table
     * @param columns   Map<String,String> where first string is a column name and second string is a sql column value e.g. longtext or varchar(255)
     * @return unless get an exception return boolean value true
     * @throws SQLException throws an exception when cannot execute the sql or sql column value is invalid
     */
    public boolean createTable(String tableName, Map<String, String> columns) throws SQLException {
        if (!connect()) return false;
        StringBuilder builder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + "(");
        for (
                String columnName : columns.keySet()
        ) {
            builder.append(columnName)
                    .append(" ")
                    .append(columns.get(columnName)).append(", ");

        }
        builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length());
        builder.append(");");
        statement.execute(builder.toString());
        return true;
    }
    //todo insert & delete & get data method
    public boolean deleteTable(String tableName) throws SQLException {
        if(!connect()) return false;
        statement.execute("DROP TABLE "+tableName);
        return true;
    }

    /**
     * Method which will deleted some tables
     * @param tableNames names of tables that have to be deleted
     * @return return a true if all go positive
     * @throws SQLException when get an exception or invalid table name
     */
    public boolean deletetables(String...tableNames) throws SQLException {
        for (String tableName : tableNames) {
            deleteTable(tableName);
        }
        return true;
    }

}
