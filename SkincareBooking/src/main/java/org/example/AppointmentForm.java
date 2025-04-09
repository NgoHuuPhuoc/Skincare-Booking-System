package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AppointmentForm extends JFrame {
    private final User user;

    private JTextField serviceField;
    private JTextArea notesArea;
    private JSpinner dateSpinner;
    private JLabel logoLabel;

    public AppointmentForm(User user) {
        this.user = user;
        setTitle("Đặt lịch chăm sóc da");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initUI();
    }

    private void initUI() {
        // Font và màu chữ chung
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 14));
        Color textColor = Color.WHITE;

        // Ảnh nền
        Image bgImage = null;
        try {
            bgImage = new ImageIcon(getClass().getResource("/images/spa.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy ảnh nền spa.jpg!");
        }

        BackgroundPanel backgroundPanel = new BackgroundPanel(bgImage);
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Logo
        java.net.URL logoURL = getClass().getClassLoader().getResource("images/logo.jpg");
        if (logoURL != null) {
            ImageIcon logoIcon = new ImageIcon(logoURL);
            Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(scaledLogo));
        } else {
            System.out.println("⚠️ Không tìm thấy ảnh logo!");
            logoLabel = new JLabel("Spa Care");
            logoLabel.setForeground(textColor);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(logoLabel, gbc);

        // Nút xem lịch hẹn
        JButton viewButton = new JButton("Xem lịch hẹn", loadIcon("calendar.png"));
        styleButton(viewButton, textColor);
        viewButton.addActionListener(this::handleViewAppointments);
        gbc.gridy++;
        backgroundPanel.add(viewButton, gbc);

        // Ngày giờ hẹn
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel dateLabel = new JLabel("Ngày giờ hẹn:");
        dateLabel.setForeground(textColor);
        backgroundPanel.add(dateLabel, gbc);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setValue(new Date());
        styleSpinner(dateSpinner, textColor);
        gbc.gridx = 1;
        backgroundPanel.add(dateSpinner, gbc);

        // Dịch vụ
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel serviceLabel = new JLabel("Dịch vụ:");
        serviceLabel.setForeground(textColor);
        backgroundPanel.add(serviceLabel, gbc);

        serviceField = new JTextField();
        styleTextField(serviceField, textColor);
        gbc.gridx = 1;
        backgroundPanel.add(serviceField, gbc);

        // Ghi chú
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel noteLabel = new JLabel("Ghi chú:");
        noteLabel.setForeground(textColor);
        backgroundPanel.add(noteLabel, gbc);

        notesArea = new JTextArea(3, 20);
        styleTextArea(notesArea, textColor);
        JScrollPane scrollPane = new JScrollPane(notesArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        gbc.gridx = 1;
        backgroundPanel.add(scrollPane, gbc);

        // Nút đặt lịch
        JButton bookButton = new JButton("Đặt lịch", loadIcon("check.png"));
        styleButton(bookButton, textColor);
        bookButton.addActionListener(this::handleBooking);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        backgroundPanel.add(bookButton, gbc);

        setContentPane(backgroundPanel);
    }

    private void styleButton(JButton button, Color textColor) {
        button.setForeground(textColor);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    private void styleTextField(JTextField field, Color textColor) {
        field.setForeground(textColor);
        field.setBackground(new Color(255, 255, 255, 50));
        field.setOpaque(false);
        field.setCaretColor(textColor);
        field.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    private void styleTextArea(JTextArea area, Color textColor) {
        area.setForeground(textColor);
        area.setBackground(new Color(255, 255, 255, 50));
        area.setOpaque(false);
        area.setCaretColor(textColor);
        area.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    private void styleSpinner(JSpinner spinner, Color textColor) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            tf.setForeground(textColor);
            tf.setBackground(new Color(255, 255, 255, 50));
            tf.setOpaque(false);
            tf.setCaretColor(textColor);
            tf.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }
    }

    private ImageIcon loadIcon(String fileName) {
        try {
            java.net.URL iconURL = getClass().getResource("/icons/" + fileName);
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                Image scaled = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Không tìm thấy icon: " + fileName);
        }
        return null;
    }

    private void handleBooking(ActionEvent e) {
        Date utilDate = (Date) dateSpinner.getValue();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());

        String service = serviceField.getText().trim();
        String notes = notesArea.getText().trim();

        if (service.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên dịch vụ.");
            return;
        }

        Appointment appt = new Appointment();
        appt.setUserID(user.getUserID());
        appt.setAppointmentDate(sqlDate);
        appt.setAppointmentTime(sqlTime);
        appt.setServiceName(service);
        appt.setNotes(notes);

        AppointmentDAO dao = new AppointmentDAO();
        if (dao.bookAppointment(appt)) {
            JOptionPane.showMessageDialog(this, "Đặt lịch thành công!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi đặt lịch.");
        }
    }

    private void handleViewAppointments(ActionEvent e) {
        AppointmentDAO dao = new AppointmentDAO();
        List<Appointment> appointments = dao.getAppointmentsByUser(user.getUserID());

        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có lịch hẹn nào.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        for (Appointment appt : appointments) {
            String dateStr = (appt.getAppointmentDate() != null) ? dateFormat.format(appt.getAppointmentDate()) : "N/A";
            String timeStr = (appt.getAppointmentTime() != null) ? timeFormat.format(appt.getAppointmentTime()) : "N/A";

            sb.append("Ngày: ").append(dateStr)
                    .append(" | Giờ: ").append(timeStr)
                    .append("\nDịch vụ: ").append(appt.getServiceName() != null ? appt.getServiceName() : "")
                    .append("\nGhi chú: ").append(appt.getNotes() != null ? appt.getNotes() : "")
                    .append("\n---------------------------\n");
        }

        JTextArea textArea = new JTextArea(sb.toString(), 15, 30);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Danh sách lịch hẹn", JOptionPane.INFORMATION_MESSAGE);
    }
}
