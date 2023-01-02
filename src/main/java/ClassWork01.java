import java.sql.*;
import java.util.Scanner;

public class ClassWork01 {

    // Kullanıcıdan int öğelerini girmesini ve listeye öğe eklemesini istemek için kod yazın.
    // Kullanıcıdan kaldırılacak öğeleri girmesini isteyin, ardından bu öğeyi listeden kaldırın.
    // Kullanıcıdan güncellemek için öğeyi girmesini isteyin, ardından güncelleyin.

    private static Scanner scan = new Scanner(System.in);
    private static Connection connection;
    private static Statement createStatement;

    public static void main(String[] args) {
        connectToDatabase("localhost", "postgres", "postgres", "tubA.123");
        createStatement();
        menu();
    }

    public static void menu(){
        System.out.println("Press 1 to see the table\n" +
                           "Press 2 to enter values to the table\n" +
                           "Press 3 to update the values\n" +
                           "Press 4 to remove the values\n" +
                           "Press 5 to exit");
        int option = scan.nextInt();
        if (option==1){
            showTheTable();
        } else if(option==2){
            System.out.println("Enter the table name you want to enter the values");
            String tableName = scan.next();
            scan.nextLine();
            System.out.println("Enter the values");
            Object values = scan.nextLine();
            JdbcUtils.enterValuesToTable(tableName, values);
        } else if(option==3){
            updateTheTable();
        } else if(option==4){
            removeValuesFromTable();
        } else if(option==5){
            exit();
        } else {
            try{
                throw new Exception();
            }catch (Exception e){
                System.out.println("You did the wrong keying");
            }
        }
    }

    public static void showTheTable(){
        System.out.println("Enter the table name you want to see");
        String tableName = scan.next();
        System.out.println("Enter field numbers you want to see");
        int fieldNumber = scan.nextInt();
        String sql = "select * from "+tableName;
        ResultSet rst = JdbcUtils.executeQuery(sql);
        try{
            while (rst.next()){
                System.out.println(rst.getObject(fieldNumber));
            }
        }catch (SQLException e){
            throw new  RuntimeException(e);
        }
        while (true) {
            System.out.println("To continue to see the table fields press 'c' to return to the menu press 'm'");
            String option = scan.next();
            if (option.equalsIgnoreCase("c")) {
                showTheTable();
                break;
            } else if (option.equalsIgnoreCase("m")) {
                menu();
                break;
            } else {
                try{
                    throw new Exception();
                }catch (Exception e){
                    System.out.println("You did the wrong keying");
                }
            }
        }
    }

    public static void updateTheTable(){
        System.out.println("Enter the table name you want to update");
        String tableName = scan.nextLine();
        scan.nextLine();
        System.out.println("Enter the field name you want to update");
        String fieldName = scan.nextLine();
        System.out.println("Enter the value you want to update");
        String oldValue = scan.nextLine();
        System.out.println("Enter the new value for being updated one");
        String newValue = scan.nextLine();
        String sql = "update "+tableName+" set "+fieldName+"="+newValue+" where "+fieldName+"="+oldValue;
        try {
            createStatement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            System.out.println("To continue to update the values press 'c' to return to the menu press 'm'");
            String option = scan.next();
            if (option.equalsIgnoreCase("c")) {
                updateTheTable();
                break;
            } else if (option.equalsIgnoreCase("m")) {
                menu();
                break;
            } else {
                try{
                    throw new Exception();
                }catch (Exception e){
                    System.out.println("You did the wrong keying");
                }
            }
        }
    }

    public static void removeValuesFromTable(){
        System.out.println("Enter the table name from which you want to remove");
        String tableName = scan.nextLine();
        scan.nextLine();
        System.out.println("Enter the field name in which you want to remove from the table");
        String fieldName = scan.nextLine();
        System.out.println("Enter the value you want to remove");
        String value = scan.nextLine();
        String sql = "delete from "+tableName+" where "+fieldName+"="+value;
        while (true) {
            System.out.println("To continue to remove the values press 'c' to return to the menu press 'm'");
            String option = scan.next();
            if (option.equalsIgnoreCase("c")) {
                removeValuesFromTable();
                break;
            } else if (option.equalsIgnoreCase("m")) {
                menu();
                break;
            } else {
                try{
                    throw new Exception();
                }catch (Exception e){
                    System.out.println("You did the wrong keying");
                }
            }
        }
    }

    public static void exit(){
        System.out.println("Have a nice day...");
    }

    public static Connection connectToDatabase(String hostName, String dbName, String username, String password){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("Connection Success");
        }else {
            System.out.println("Connection Fail");
        }
        return connection;
    }

    public static Statement createStatement(){
        try {
            createStatement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return createStatement;
    }

}
