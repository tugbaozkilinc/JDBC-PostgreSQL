import java.sql.*;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1: Driver a kaydol
        Class.forName("org.postgresql.Driver");

        // 2: Database e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tubA.123");

        // 3: Statement olustur
        Statement st = con.createStatement();

        // 4: Query calistir
        // Note: execute() methodu DDL(create,drop,alter table) ve DQL(select) icin kullanilabilir
        // 1) Eger execute() methodu DDL icin kullanilirsa 'false' return eder
        // 2) Eger execute() methodu DQL icin kullanilirsa ResultSet alindiginda 'true' aksi halde 'false' return eder

        // 1. Ornek: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("create table workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println(sql1); // false, cunku data cagirmiyoruz

        // 2. Ornek: Table a worker_adress sutunu ekleyerek alter yapın
        String sql2 = "alter table workers add worker_adress varchar(80)";
        boolean sql4 = st.execute(sql2);
        System.out.println(sql4); // false

        // 3. Ornek: workers table ini silin.
        String sql3 = "drop table workers";
        boolean sql5 = st.execute(sql3);
        System.out.println(sql5); // false

        // 5: Baglanti ve statement i kapat
        con.close();
        st.close();

    }
}
