package jdbc;

import java.sql.*;

public class Execute {

    public static void main(String[] args) throws SQLException {

        //1. Step: Database e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tubA.123"); //creating a connection between DriverManager and DBMS
        //localhost -> kullanacagimiz host un adi.
        //5432 -> postgre nin kullandigi port ayari
        ///postgres -> baglandigin database
        //postgres -> username (dbName ve userName aynı olmak zorunda degil)

        //2. Step: Statement olustur
        Statement st = con.createStatement(); //executes an sql query

        //3. Step: Query calistir
        //Note: execute() methodu DDL(create,drop,alter) ve DQL(select) icin kullanilabilir
        //1)Eger execute() methodu DDL icin kullanilirsa 'false' return eder
        //2)Eger execute() methodu DQL icin kullanilirsa ResultSet alindiginda 'true' aksi halde 'false' return eder

        //Example 1: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        String sql1 = "create table workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)";
        boolean isTrueOrFalse1 = st.execute(sql1);
        System.out.println(isTrueOrFalse1); //false

        //Example 2: Table a worker_address sutunu ekleyerek alter yapın
        String sql2 = "alter table workers add column worker_address varchar(80)";
        boolean isTrueOrFalse2 = st.execute(sql2);
        System.out.println(isTrueOrFalse2); //false

        //Example 3: workers table ini silin.
        String sql3 = "drop table workers";
        boolean isTrueOrFalse3 = st.execute(sql3);
        System.out.println(isTrueOrFalse3); //false

        //5. Step: Baglanti ve statement i kapat
        con.close();
        st.close();

        //execute() -> create, alter, drop (DDL)
        //executeQuery() -> select
        //executeUpdate() -> insert, update, delete (DML)
    }

}
