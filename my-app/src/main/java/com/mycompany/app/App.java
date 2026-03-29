package com.mycompany.app;

import dao.DatabaseConnection; // Import lớp DatabaseConnection của bạn
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        System.out.println("Đang tiến hành kết nối đến cơ sở dữ liệu...");

        // Gọi đến class DatabaseConnection để lấy kết nối
        Connection conn = DatabaseConnection.getInstance().getConnection();

        if (conn != null) {
            System.out.println("🎉 Xin chúc mừng! Kết nối MySQL thành công!");
        } else {
            System.out.println("❌ Kết nối thất bại. Hãy kiểm tra lại URL, Username hoặc Password.");
        }
    }
}