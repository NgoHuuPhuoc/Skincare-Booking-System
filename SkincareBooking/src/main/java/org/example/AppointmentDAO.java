package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Hàm đặt lịch hẹn
    public static boolean bookAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointment (UserID, AppointmentDate, AppointmentTime, ServiceName, Notes) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getUserID());
            stmt.setDate(2, appointment.getAppointmentDate());
            stmt.setTime(3, appointment.getAppointmentTime());
            stmt.setString(4, appointment.getServiceName());
            stmt.setString(5, appointment.getNotes() != null ? appointment.getNotes() : "");

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm lấy danh sách lịch hẹn theo user
    public static List<Appointment> getAppointmentsByUser(int userId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment WHERE UserID = ? ORDER BY AppointmentDate, AppointmentTime";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentID(rs.getInt("AppointmentID"));
                appointment.setUserID(rs.getInt("UserID"));
                appointment.setAppointmentDate(rs.getDate("AppointmentDate"));
                appointment.setAppointmentTime(rs.getTime("AppointmentTime"));
                appointment.setServiceName(rs.getString("ServiceName"));
                appointment.setNotes(rs.getString("Notes"));
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
