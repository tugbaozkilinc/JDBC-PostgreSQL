import java.sql.*;

public class Execute {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Step: Driver a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Step: Database e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tubA.123");
        //localhost -> kullanacagimiz host un adi.
        //5432 -> bu fix postgre nin kullandigi port ayari
        //postgres -> baglandigin database(dabName ile userName ayni olmak zorunda DEGIL)
        //com.mysql.jdbc.Driver
        //oracle.jdbc.driver.OracleDriver
        //org.postgresql.Driver
        //com.microsoft.sqlserver.jdbc.SQLServerDrive
        //org.sqlite.JDBC
        //3. Step: Statement olustur
        Statement st = con.createStatement();

        //4. Step: Query calistir
        //Note: execute() methodu DDL(create,drop,alter table) ve DQL(select) icin kullanilabilir
        //1)Eger execute() methodu DDL icin kullanilirsa 'false' return eder
        //2)Eger execute() methodu DQL icin kullanilirsa ResultSet alindiginda 'true' aksi halde 'false' return eder

        //Example 1: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("create table workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println(sql1); //false, cunku data cagirmiyoruz, sadece data olusturduk

        //Example 2: Table a worker_adress sutunu ekleyerek alter yapın
        String sql2 = "alter table workers add column worker_adress varchar(80)";
        boolean sql4 = st.execute(sql2);
        System.out.println(sql4); //false

        //Example 3: workers table ini silin.
        String sql3 = "drop table workers";
        boolean sql5 = st.execute(sql3);
        System.out.println(sql5); //false

        //5. Step: Baglanti ve statement i kapat
        con.close();
        st.close();
    }

}
