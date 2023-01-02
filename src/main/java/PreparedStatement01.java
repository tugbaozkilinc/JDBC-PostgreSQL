import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection con = JdbcUtils.connectToDatabase("localhost", "postgres", "postgres", "tubA.123");
        Statement st = JdbcUtils.createStatement();

        /*
          PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
          Parametrelendirilmis(Parameterised) SQL query(sorgu)'leri ile calisir. Bir sorguyu 0 veya daha fazla parametre ile kullanabiliriz
        */

        // 1. Ornek: PreparedStatement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        // 1. Adım: PreparedStatement query sini olustur
        String sql1 = "update companies set number_of_employees= ? where company= ?"; // nereyi dinamik yapmak istiyorsak oraya ? yazacagiz

        // 2. Adim: PreparedStatement objesini olustur
        PreparedStatement pst1 = JdbcUtils.preparedStatement(sql1);

        // 3. Adim: setInt(), setString() method larini kullanarak ? yerlerine deger ata
        pst1.setInt(1,9999); // parametre sayisi
        pst1.setString(2, "IBM");

        // 4. Adim: Query i calistir
        int updatedLineNumber = pst1.executeUpdate();
        System.out.println(updatedLineNumber);

        String sql2 = "select * from companies";
        ResultSet rst1 = st.executeQuery(sql2);
        while (rst1.next()){
            System.out.println(rst1.getInt(1) + "/" + rst1.getString(2) + "/" + rst1.getObject(3)); // Sadece yazdiracaksan getObject() ile alabilirsin
        }

        // 2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1,5555); // sutun sayisi
        pst1.setString(2, "GOOGLE");
        int updatedLineNumber2 = pst1.executeUpdate();
        System.out.println(updatedLineNumber2);

        ResultSet rst2 = st.executeQuery(sql2);
        while (rst2.next()){
            System.out.println(rst2.getInt(1) + "/" + rst2.getString(2) + "/" + rst2.getObject(3)); // Sadece yazdiracaksan getObject() ile alabilirsin
        }

        con.close();
        st.close();
        rst1.close();
        rst2.close();
        pst1.close();

    }
}
