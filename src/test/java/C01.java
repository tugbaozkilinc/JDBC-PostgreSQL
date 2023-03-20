import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class C01 {

    @Test
    public void isExist() throws SQLException {
        //Example: Ogrenciler tablosunda Merve Gul isimli ogrencinin oldugunu test edın
        JdbcUtils.connectToDatabase("localhost", "Databasetesting", "postgres", "tubA.123");
        Statement st = JdbcUtils.createStatement();
        String sql = "select isim from ogrenciler";
        ResultSet rst = st.executeQuery(sql);
        List<String> namesList = new ArrayList<>();
        while (rst.next()){
            namesList.add(rst.getString(1));
        }
        System.out.println("List" + namesList);
        assertTrue(namesList.contains("Merve Gul"));
        JdbcUtils.closeConnectionAndStatement();
    }

}
