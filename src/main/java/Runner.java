import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws SQLException {

        Connection con = JdbcUtils.connectToDatabase("localhost", "postgres", "postgres", "tubA.123");
        Statement st = JdbcUtils.createStatement();

        JdbcUtils.createTable("students", "name varchar(20)", "id int", "address varchar(80)");
        JdbcUtils.execute("drop table students");

        JdbcUtils.createTable("school","classes varchar(20)", "teacher_name varchar(20)", "id int");
        JdbcUtils.execute("drop table school");

        ResultSet rst = JdbcUtils.selectFieldFromTable("countries", "region_id", "country_id");
        while (rst.next()){
            System.out.println(rst.getObject(1) + "/" + rst.getObject(2));
        }

        ResultSet rst1 = JdbcUtils.executeQuery("select * from countries where country_name='China'");
        while (rst1.next()){
            System.out.println(rst1.getString(1) + "/" + rst1.getString(2) + "/" + rst1.getInt(3));
        }

        JdbcUtils.executeUpdate("update users set age=30 where address='Izmir' and name='Ece'");
        JdbcUtils.enterValuesToTable("users", "'Ece'", 24, "'Izmir'");
        List<Object> list = JdbcUtils.addElementsToList("users", "age");
        System.out.println(list);

        JdbcUtils.closeConnectionAndStatement();

    }
}
