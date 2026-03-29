package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // 1. Biến tĩnh lưu trữ instance duy nhất
    private static DatabaseConnection instance;
    private Connection connection;

    // 2. Cấu hình thông tin Database
    // (Lưu ý: Thay đổi port 3306 và password cho khớp với máy của bạn)
    private static final String URL = "jdbc:mysql://localhost:3306/auction_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // 3. Constructor private để ngăn chặn khởi tạo từ bên ngoài bằng từ khóa 'new'
    private DatabaseConnection() {
        try {
            // Nạp MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Kết nối Database thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy JDBC Driver. Hãy kiểm tra lại file pom.xml.");
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối MySQL: " + e.getMessage());
        }
    }

    // 4. Cung cấp điểm truy cập toàn cục (Thread-safe)
    public static synchronized DatabaseConnection getInstance() {
        try {
            // Kiểm tra nếu instance chưa được tạo, hoặc kết nối đã bị đóng ngắt quãng
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kiểm tra trạng thái kết nối: " + e.getMessage());
        }
        return instance;
    }

    // 5. Hàm getter để các lớp DAO lấy Connection
    public Connection getConnection() {
        return connection;
    }
}