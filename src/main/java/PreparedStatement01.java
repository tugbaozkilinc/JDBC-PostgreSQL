import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException {

        Connection con = JdbcUtils.connectToDatabase("localhost", "postgres", "postgres", "tubA.123");
        Statement st = JdbcUtils.createStatement();
        //PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
        //Parametrelendirilmis(Parameterised) SQL query(sorgu)'leri ile calisir. Bir sorguyu 0 veya daha fazla parametre ile kullanabiliriz.

        //Example 1: PreparedStatement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1. Adım: PreparedStatement query(parameterised sql query) olustur
        String sql1 = "update companies set number_of_employees= ? where company= ?"; //nereyi dinamik yapmak istiyorsak oraya ? yaziyoruz.

        //2. Adim: PreparedStatement objesini olustur
        PreparedStatement pst = con.prepareStatement(sql1);

        //3. Adim: setInt(), setString() method larini kullanarak ? yerlerine deger ata
        pst.setInt(1,9999); //parametre sayisi
        pst.setString(2, "IBM");

        //4. Adim: Query i calistir
        int updatedLineNumber = pst.executeUpdate();
        System.out.println(updatedLineNumber);

        String sql2 = "select * from companies";
        ResultSet rst1 = st.executeQuery(sql2);
        while (rst1.next()){
            System.out.println(rst1.getInt(1) + "/" + rst1.getString(2) + "/" + rst1.getObject(3)); //Sadece yazdiracaksan getObject() ile de alabilirsin
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst.setInt(1,5555);
        pst.setString(2, "GOOGLE");
        int updatedLineNumber2 = pst.executeUpdate();
        System.out.println(updatedLineNumber2);

        con.close();
        st.close();
        rst1.close();
        pst.close();
    }

}
