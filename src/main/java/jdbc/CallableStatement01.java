package jdbc;

import java.sql.*;
import java.sql.CallableStatement;

public class CallableStatement01 {

    public static void main(String[] args) throws SQLException {

        //Java'da method'lar return type sahibi olsa da olmasa da method olarak adlandırılır.
        //SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandırilir
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tubA.123");
        Statement st = con.createStatement();

        //CallableStatement ile function çagirmayi parametrelendirecegiz
        //Example 1: Iki parametreli toplama islemi yapan bir function yaziniz.
        //1. Adim: Function kodunu yaz
        String sql1 = "CREATE OR REPLACE FUNCTION toplama(x numeric, y numeric) " +
                      "returns numeric " +
                      "language plpgsql " +
                      "as " +
                      "$$ " +
                      "begin " +
                      "return x+y; " +
                      "end " +
                      "$$;";
        //2. Adim: Function i calistir
        st.execute(sql1);

        //3. Adim: Function i cagir
        CallableStatement cst1 = con.prepareCall("{? = call toplama(?, ?)}"); //Bunun PreparedStatement tan farkı burda return type i da parametre olarak aliyoruz.

        //4. Adim: Return icin registerOutParameter() methodunu parametreler icinse set()... method larini uygula
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        //5. Adim: execute() methodu ile CallableStatement i calistir
        cst1.execute();

        //6. Adim: Sonucu cagir(return data type tipine gore)
        System.out.println("Iki sayının toplamı: " + cst1.getBigDecimal(1)); //1 sutun gelecegi icin 1 yazdik

        //2. Ornek: Koninin hacmini hesaplayan bir function yazın.
        String sql2 = "CREATE OR REPLACE FUNCTION konininHacmi(r int, h int) " +
                      "returns int " +
                      "language plpgsql " +
                      "as " +
                      "$$ " +
                      "begin " +
                      "return 3.14*r*r*h/3; " +
                      "end " +
                      "$$;";
        st.execute(sql2);
        CallableStatement cst2 = con.prepareCall("{? = call konininHacmi(?, ?)}");
        cst2.registerOutParameter(1, Types.INTEGER);
        cst2.setInt(2, 1);
        cst2.setInt(3, 6);
        cst2.execute();
        System.out.println("Koninin hacmi: " + cst2.getInt(1));
        System.out.printf("%.2f", 2345.6778977);

        con.close();
        st.close();
        cst1.close();
        cst2.close();
    }

}
