package io.github.saimonovski.data.manage.dataManager.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    //todo connect method
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

    /*   *//**
     * Method which will create tables in your database
     * @param tables this is a list of keymaps where key is a table name and a value is other keymap where key is a column name and value is a sql column value e.g. LONGTEXT or VARCHAR(255)
     * @return always return true unless were not got an exception
     * @throws SQLException throws an exception if you  couldn't connect to a database e.g. for invalid password or invalid data
     *//*
    public boolean createTables(List<Map<String,Map<String,String>>> tables) throws SQLException {
            StringBuilder builder = new StringBuilder();

      if (!connect()){
          throw new SQLException(); return false;
    }
      for(
              Map<String, Map<String,String>> table :
              tables
      ) {
          for (
                  String s :
                  table.keySet()
          ) {
              builder.append("CREATE TABLE IF NOT EXISTS ")
                      .append(s + "(");
              for (Map<String, String> columns : table.values()
              ) {

              }
          }
      }}*/
    //todo insert & delete & get data method
    //todo create & delete table methods


}
