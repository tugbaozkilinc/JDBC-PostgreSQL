import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class C03 {

    @Test
    public void isTheNameHasan() throws SQLException {
        // Example: Ogrenciler tablosundaki ismi Mustafa Bak olan ogrencilerin velilerinden herhangi birinin isminin Ali olup olmadigini test edin
        JdbcUtils.connectToDatabase("localhost", "Databasetesting", "postgres", "tubA.123");
        Statement st = JdbcUtils.createStatement();
        String sql = "select veli_isim from ogrenciler where isim='Mustafa Bak'";
        ResultSet rst = st.executeQuery(sql);
        List<String> parentNameList = new ArrayList<>();
        while (rst.next()){
            parentNameList.add(rst.getString(1));
        }
        System.out.println("Parent Name List: " + parentNameList);
        Assert.assertTrue(parentNameList.contains("Ali"));
        JdbcUtils.closeConnectionAndStatement();
    }

}
