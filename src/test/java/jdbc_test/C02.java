package jdbc_test;

import jdbc.JdbcUtils;
import org.junit.Assert;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C02 {

    @Test
    public void isPassed() throws SQLException {
        //Example: Yazili not ortalamasının 90 dan kucuk oldugunu test edın
        JdbcUtils.connectToDatabase("localhost", "Databasetesting", "postgres", "tubA.123");
        Statement st = JdbcUtils.createStatement();
        String sql = "select round(avg(yazili_notu), 2) from ogrenciler";
        ResultSet rst = st.executeQuery(sql);
        rst.next();
        double average = rst.getDouble(1);
        System.out.println("Average is: " + average);
        Assert.assertTrue(average<90);
        JdbcUtils.closeConnectionAndStatement();
    }

}
