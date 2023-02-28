import java.sql.*;

public class ExecuteUpdate {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tubA.123");
        Statement st = con.createStatement();

        //1. Ornek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.
        String sql1 = "update companies set number_of_employees=16000 where number_of_employees<(select avg(number_of_employees) from companies)";
        st.executeUpdate(sql1); //eger bunu yazdırırsan update edilmiş satır sayısını gorursun System.out.println(st.executeUpdate(sql1)); 2
        ResultSet rst1 = st.executeQuery("select * from companies");
        while (rst1.next()){
            System.out.println(rst1.getString(1) + "/" + rst1.getString(2) + "/" + rst1.getString(3));
        }

        con.close();
        st.close();
        rst1.close();
    }

}
