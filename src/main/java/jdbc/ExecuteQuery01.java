package jdbc;

import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tubA.123");
        Statement st = con.createStatement();

        //Example 1: region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "select country_name from countries where region_id=1";
        //Note: Recordları görmek için executeQuery() methodunu kullanmalıyız.
        ResultSet rst1 = st.executeQuery(sql1);
        while (rst1.next()){
            System.out.println(rst1.getString("country_name"));
        }
        System.out.println();

        //Example 2: region_id'nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "select country_id, country_name from countries where region_id>2";
        ResultSet rst2 = st.executeQuery(sql2);
        while (rst2.next()){
            System.out.println(rst2.getString(2) + "/" + rst2.getString(1));
        }
        System.out.println();

        //Example 3: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "select * from companies where number_of_employees=(select min(number_of_employees) from companies)";
        ResultSet rst3 = st.executeQuery(sql3);
        while (rst3.next()){
            System.out.println(rst3.getInt(1) + "/" + rst3.getString(2) + "/" + rst3.getInt(3));
        }

        con.close();
        st.close();
        rst1.close();
        rst2.close();
        rst3.close();
    }

}
