import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/skincare_booking";
    private static final String USER = "root";
    private static final String PASSWORD = "150605";
    
    // Table and column names
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");

            // Thực hiện các thao tác với cơ sở dữ liệu
            createTable(connection);
            insertData(connection);
            selectData(connection);
            updateData(connection);
            deleteData(connection);
            
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ("
                + COLUMN_ID + " INT AUTO_INCREMENT PRIMARY KEY, "
                + COLUMN_USERNAME + " VARCHAR(50) NOT NULL, "
                + COLUMN_PASSWORD + " VARCHAR(50) NOT NULL, "
                + COLUMN_EMAIL + " VARCHAR(100) NOT NULL)";

        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
            System.out.println("Bảng '" + TABLE_USERS + "' đã được tạo thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi khi tạo bảng: " + e.getMessage());
        }
    }

    private static void insertData(Connection connection) {
        String insertSQL = "INSERT INTO " + TABLE_USERS + " (" 
                + COLUMN_USERNAME + ", " + COLUMN_PASSWORD + ", " + COLUMN_EMAIL 
                + ") VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, "user1");
            statement.setString(2, "password1");
            statement.setString(3, "user1@example.com");
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Dữ liệu đã được chèn thành công.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
        }
    }

    private static void selectData(Connection connection) {
        String selectSQL = "SELECT * FROM " + TABLE_USERS;

        try (PreparedStatement statement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Danh sách người dùng:");
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String username = resultSet.getString(COLUMN_USERNAME);
                String email = resultSet.getString(COLUMN_EMAIL);
                System.out.println("ID: " + id + ", Username: " + username + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
    }

    private static void updateData(Connection connection) {
        String updateSQL = "UPDATE " + TABLE_USERS + " SET " + COLUMN_EMAIL 
                + " = ? WHERE " + COLUMN_USERNAME + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setString(1, "new_email@example.com");
            statement.setString(2, "user1");
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Dữ liệu đã được cập nhật thành công.");
            } else {
                System.out.println("Không tìm thấy người dùng để cập nhật.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
    }

    private static void deleteData(Connection connection) {
        String deleteSQL = "DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setString(1, "user1");
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Dữ liệu đã được xóa thành công.");
            } else {
                System.out.println("Không tìm thấy người dùng để xóa.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
    }
}
