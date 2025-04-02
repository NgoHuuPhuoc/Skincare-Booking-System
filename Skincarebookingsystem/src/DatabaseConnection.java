import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/skincare_booking";
    private static final String USER = "root";
    private static final String PASSWORD = "150605";

    // Các phương thức kết nối cơ bản
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Tạo các bảng cần thiết cho hệ thống
    public static void createTables() {
        String[] createTablesSQL = {
            "CREATE TABLE IF NOT EXISTS customers (" +
            "customer_id INT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(100) NOT NULL," +
            "phone VARCHAR(20) NOT NULL," +
            "email VARCHAR(100)," +
            "registration_date DATE NOT NULL)",

            "CREATE TABLE IF NOT EXISTS services (" +
            "service_id INT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(100) NOT NULL," +
            "description TEXT," +
            "price DECIMAL(10,2) NOT NULL," +
            "duration INT NOT NULL)",

            "CREATE TABLE IF NOT EXISTS staff (" +
            "staff_id INT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(100) NOT NULL," +
            "specialization VARCHAR(100)," +
            "phone VARCHAR(20))",

            "CREATE TABLE IF NOT EXISTS appointments (" +
            "appointment_id INT AUTO_INCREMENT PRIMARY KEY," +
            "customer_id INT NOT NULL," +
            "service_id INT NOT NULL," +
            "staff_id INT NOT NULL," +
            "appointment_time DATETIME NOT NULL," +
            "status VARCHAR(20) DEFAULT 'BOOKED'," +
            "notes TEXT," +
            "FOREIGN KEY (customer_id) REFERENCES customers(customer_id)," +
            "FOREIGN KEY (service_id) REFERENCES services(service_id)," +
            "FOREIGN KEY (staff_id) REFERENCES staff(staff_id))"
        };

        try (Connection conn = getConnection(); 
             Statement stmt = conn.createStatement()) {
            
            for (String sql : createTablesSQL) {
                stmt.executeUpdate(sql);
            }
            System.out.println("Các bảng dữ liệu đã được tạo/xác nhận!");
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi tạo bảng: " + e.getMessage());
        }
    }

    // ========== CÁC PHƯƠNG THỨC CHO DỊCH VỤ ==========
    public static void addService(String name, String description, double price, int duration) {
        String sql = "INSERT INTO services (name, description, price, duration) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, duration);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm dịch vụ: " + e.getMessage());
        }
    }

    public static List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                services.add(new Service(
                    rs.getInt("service_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("duration")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách dịch vụ: " + e.getMessage());
        }
        return services;
    }

    // ========== CÁC PHƯƠNG THỨC CHO NHÂN VIÊN ==========
    public static void addStaff(String name, String specialization, String phone) {
        String sql = "INSERT INTO staff (name, specialization, phone) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, specialization);
            pstmt.setString(3, phone);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm nhân viên: " + e.getMessage());
        }
    }

    // ========== CÁC PHƯƠNG THỨC CHO LỊCH HẸN ==========
    public static boolean bookAppointment(int customerId, int serviceId, int staffId, 
                                         Timestamp appointmentTime, String notes) {
        String sql = "INSERT INTO appointments (customer_id, service_id, staff_id, appointment_time, notes) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, serviceId);
            pstmt.setInt(3, staffId);
            pstmt.setTimestamp(4, appointmentTime);
            pstmt.setString(5, notes);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi đặt lịch hẹn: " + e.getMessage());
            return false;
        }
    }

    public static List<Appointment> getAppointmentsByDate(Date date) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.*, c.name AS customer_name, s.name AS service_name, st.name AS staff_name " +
                     "FROM appointments a " +
                     "JOIN customers c ON a.customer_id = c.customer_id " +
                     "JOIN services s ON a.service_id = s.service_id " +
                     "JOIN staff st ON a.staff_id = st.staff_id " +
                     "WHERE DATE(a.appointment_time) = ? " +
                     "ORDER BY a.appointment_time";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("appointment_id"),
                    rs.getInt("customer_id"),
                    rs.getString("customer_name"),
                    rs.getInt("service_id"),
                    rs.getString("service_name"),
                    rs.getInt("staff_id"),
                    rs.getString("staff_name"),
                    rs.getTimestamp("appointment_time"),
                    rs.getString("status"),
                    rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy lịch hẹn: " + e.getMessage());
        }
        return appointments;
    }

    // ========== CÁC LỚP DỮ LIỆU ==========
    public static class Service {
        private int id;
        private String name;
        private String description;
        private double price;
        private int duration; // in minutes

        // Constructor, getters, toString...
    }

    public static class Appointment {
        private int id;
        private int customerId;
        private String customerName;
        private int serviceId;
        private String serviceName;
        private int staffId;
        private String staffName;
        private Timestamp appointmentTime;
        private String status;
        private String notes;

        // Constructor, getters, toString...
    }

    public static void main(String[] args) {
        // Test kết nối và khởi tạo database
        try (Connection conn = getConnection()) {
            System.out.println("Kết nối database thành công!");
            createTables();
            
            // Thêm dữ liệu mẫu
            addService("Chăm sóc da cơ bản", "Làm sạch và dưỡng ẩm da", 250000, 60);
            addStaff("Nguyễn Thị A", "Chuyên gia chăm sóc da", "0987654321");
            
            // Test lấy dữ liệu
            System.out.println("\nDanh sách dịch vụ:");
            getAllServices().forEach(System.out::println);
            
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối database: " + e.getMessage());
        }
    }
}
