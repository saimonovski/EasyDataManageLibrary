package io.github.saimonovski.data.manage.dataManager;

import java.sql.*;

/**
 * This class handles Mysql operations
 */

public class MYSQLHandler {
    private final String USER, PASSWORD, HOSTNAME, NAME, PORT;
    private Connection connection;

    /**
     * mysql data:
     *
     * @param user     database user
     * @param password database password
     * @param hostname database hostname
     * @param name     database name
     * @param port     database port
     */

    protected MYSQLHandler(String user, String password, String hostname, String name, String port) {
        USER = user;
        PASSWORD = password;
        HOSTNAME = hostname;
        NAME = name;
        PORT = port;
    }

    private void connect() throws SQLException {

        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + NAME
                , USER, PASSWORD);
    }

    /**
     * Method that closes the connection to the database.
     *
     * @throws SQLException SQL exception that can be thrown while closing the connection
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     * Method which gets data from a specified table and row.
     *
     * @param tableName  Name of the table
     * @param columnName Name of the column from which we want to fetch data
     * @param rowId      Identifier of the row
     * @return Object containing the fetched data
     * @throws SQLException SQL exception that can be thrown while executing the SQL query
     */
    public Object getData(String tableName, String columnName, int rowId) throws SQLException {
        connect();
        Object data = null;
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rowId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    data = resultSet.getObject(columnName);
                }
            }
        }
        return data;
    }

    /**
     * Method that modifies data in a specified row and column.
     *
     * @param tableName  Name of the table
     * @param columnName Name of the column to update
     * @param newValue   New value to set in the column
     * @param rowId      Identifier of the row to update
     * @throws SQLException SQL exception that can be thrown while executing the SQL query
     */
    public void updateData(String tableName, String columnName, Object newValue, int rowId) throws SQLException {

        String query = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connect();
            statement.setObject(1, newValue);
            statement.setInt(2, rowId);
            statement.executeUpdate();
        }
    }

    /**
     * Method that adds a new row to a table.
     *
     * @param tableName Name of the table
     * @param values    Array containing values to insert into the new row
     * @throws SQLException SQL exception that can be thrown while executing the SQL query
     */
    public void insertData(String tableName, Object[] values) throws SQLException {

        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
        for (int i = 0; i < values.length; i++) {
            queryBuilder.append("?,");
        }
        queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Remove the last comma
        queryBuilder.append(")");

        try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
            connect();
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        }
    }

    /**
     * Method that deletes a row from a table.
     *
     * @param tableName Name of the table
     * @param rowId     Identifier of the row to delete
     * @throws SQLException SQL exception that can be thrown while executing the SQL query
     */
    public void deleteRow(String tableName, int rowId) throws SQLException {
        connect();
        String query = "DELETE FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rowId);
            statement.executeUpdate();
        }
    }

    /**
     * Method that creates a new table in the database.
     *
     * @param tableName   Name of the new table
     * @param columnNames Array containing the names of the columns in the new table
     * @param columnTypes Array containing the data types for each column
     * @throws SQLException SQL exception that can be thrown while executing the SQL query
     */
    public void createTable(String tableName, String[] columnNames, String[] columnTypes) throws SQLException {
        connect();
        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        for (int i = 0; i < columnNames.length; i++) {
            queryBuilder.append(columnNames[i]).append(" ").append(columnTypes[i]).append(",");
        }
        queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Remove the last comma
        queryBuilder.append(")");

        try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
            statement.executeUpdate();
        }
    }
}
