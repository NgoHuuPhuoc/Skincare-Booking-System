import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/skincare_booking"; // Thay đổi tên cơ sở dữ liệu nếu cần
    private static final String USER = "root"; // Thay đổi nếu cần
    private static final String PASSWORD = "150605"; // Thay đổi mật khẩu của bạn

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Kết nối thành công đến cơ sở dữ liệu!");

                // Thực hiện các thao tác với cơ sở dữ liệu
                createTable(connection);
                insertData(connection);
                selectData(connection);
                updateData(connection);
                deleteData(connection);
            }
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
    }

    private static void createTable(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(50) NOT NULL, "
                + "password VARCHAR(50) NOT NULL, "
                + "email VARCHAR(100) NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Bảng 'users' đã được tạo thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi tạo bảng: " + e.getMessage());
        }
    }

    private static void insertData(Connection connection) {
        String insertSQL = "INSERT INTO users (username, password, email) VALUES ('user1', 'password1', 'user1@example.com')";

        try (Statement statement = connection.createStatement()) {
            int rowsInserted = statement.executeUpdate(insertSQL);
            if (rowsInserted > 0) {
                System.out.println("Dữ liệu đã được chèn thành công.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
        }
    }

    private static void selectData(Connection connection) {
        String selectSQL = "SELECT * FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            System.out.println("Danh sách người dùng:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Username: " + username + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
    }

    private static void updateData(Connection connection) {
        String updateSQL = "UPDATE users SET email = 'new_email@example.com' WHERE username = 'user1'";

        try (Statement statement = connection.createStatement()) {
            int rowsUpdated = statement.executeUpdate(updateSQL);
            if (rowsUpdated > 0) {
                System.out.println("Dữ liệu đã được cập nhật thành công.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
    }

    private static void deleteData(Connection connection) {
        String deleteSQL = "DELETE FROM users WHERE username = 'user1'";

        try (Statement statement = connection.createStatement()) {
            int rowsDeleted = statement.executeUpdate(deleteSQL);
            if (rowsDeleted > 0) {
                System.out.println("Dữ liệu đã được xóa thành công.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
    }
}