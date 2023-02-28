import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tubA.123");
        Statement st = con.createStatement();

        //1. Ornek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        //1. yol:
        String sql1 = "select company, number_of_employees from companies order by number_of_employees desc offset(1) limit(1)";
        ResultSet rst1 = st.executeQuery(sql1);
        while (rst1.next()){
            System.out.println(rst1.getString(1) + "/" + rst1.getInt("number_of_employees"));
        }
        System.out.println();
        //2. yol:
        String sql2 = "SELECT company, number_of_employees FROM companies WHERE number_of_employees=(SELECT MAX(number_of_employees) FROM companies WHERE number_of_employees<(SELECT MAX(number_of_employees) FROM companies))";
        ResultSet rst2 = st.executeQuery(sql2);
        while (rst2.next()){
            System.out.println(rst2.getString("company") + "/" + rst2.getString("number_of_employees"));
        }

        con.close();
        st.close();
        rst1.close();
        rst2.close();
    }

}
