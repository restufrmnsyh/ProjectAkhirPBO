package Model;

import java.sql.*;

public class Connector {
    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String nama_db = "db_topup";
    private static String url_db = "jdbc:mysql://localhost:3306/db_topup";
    private static String username_db = "root";
    private static String password_db = "";

    static Connection conn;
    
    // Mencoba menghubungkan program ke database MySQL.
    public static Connection Connect() {
        try {
            // 1. Register driver
            Class.forName(jdbc_driver);
            
            // 2. Buat koneksi
            conn = DriverManager.getConnection(url_db, username_db, password_db);
            
            // 3. Pesan sukses
            System.out.println("MySQL Connected Successfully!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Tidak Ditemukan! Detail: " + e.getMessage());
            e.printStackTrace(); // <--- Ini penting untuk melacak error
        } catch (SQLException e) {
            System.out.println("Koneksi ke Database Gagal! Detail: " + e.getMessage());
            e.printStackTrace(); // <--- Ini penting untuk melacak error
        }
        return conn;
    }
    }

