package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    public static Connection connectToDatabase(String hostName, String dbName, String username, String password){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("Connection Succeeded");
        }else {
            System.out.println("Connection Failed");
        }
        return connection;
    }

    public static void closeConnectionAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed() && statement.isClosed()){
                System.out.println("Connection and statement is closed");
            } else {
                System.out.println("Connection and statement is not closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Statement createStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public static PreparedStatement preparedStatement(String sql){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return preparedStatement;
    }

    public static boolean execute(String sql){
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    public static ResultSet executeQuery(String sql){
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static int executeUpdate(String sql){
        int updatedLineNumber;
        try {
            updatedLineNumber = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedLineNumber;
    }

    public static void createTable(String tableName, String... fieldName_dataType){
        StringBuilder fieldName_dataValue = new StringBuilder("");
        for(String w : fieldName_dataType){
            fieldName_dataValue.append(w).append(",");
        }
        fieldName_dataValue.deleteCharAt(fieldName_dataValue.length()-1);
        try {
            statement.execute( "CREATE TABLE " + tableName + "(" + fieldName_dataValue + ")");
            System.out.println("Table " + tableName + " successfully has been created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet selectFieldFromTable(String tableName, String... fieldName){
        StringBuilder fieldValue = new StringBuilder("");
        for (String w : fieldName){
            fieldValue.append(w).append(",");
        }
        fieldValue.deleteCharAt(fieldValue.length()-1);
        try {
            resultSet = statement.executeQuery("select " + fieldValue + " from " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void enterValuesToTable(String tableName, Object... values){
        StringBuilder fieldValues = new StringBuilder("");
        for (Object w : values){
            fieldValues.append(w).append(",");
        }
        fieldValues.deleteCharAt(fieldValues.length()-1);
        try {
            statement.execute("insert into " + tableName + " values(" + fieldValues + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Object> addElementsToList(String tableName, String fieldName){
        List<Object> list = new ArrayList<>();
        try {
            String sql = "select " + fieldName + " from " + tableName;
            executeQuery(sql);
            while (resultSet.next()){
                list.add(resultSet.getObject(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
