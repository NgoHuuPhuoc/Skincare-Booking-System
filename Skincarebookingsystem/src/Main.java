import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG ĐẶT LỊCH CHĂM SÓC DA ===");
        
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    viewServices();
                    break;
                case 3:
                    manageStaff();
                    break;
                case 4:
                    viewAppointments();
                    break;
                case 5:
                    System.out.println("Cảm ơn đã sử dụng hệ thống. Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n==== MENU CHÍNH ====");
        System.out.println("1. Đặt lịch hẹn mới");
        System.out.println("2. Xem danh sách dịch vụ");
        System.out.println("3. Quản lý nhân viên");
        System.out.println("4. Xem lịch hẹn");
        System.out.println("5. Thoát chương trình");
        System.out.print("Vui lòng chọn: ");
    }
    
    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Vui lòng nhập số!");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static void bookAppointment() {
        System.out.println("\n=== ĐẶT LỊCH HẸN ===");
        System.out.print("Nhập tên khách hàng: ");
        scanner.nextLine(); // Clear buffer
        String customerName = scanner.nextLine();
        
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();
        
        System.out.print("Chọn dịch vụ (1 - Chăm sóc da cơ bản, 2 - Trị mụn, 3 - Dưỡng trắng): ");
        int serviceChoice = scanner.nextInt();
        
        System.out.print("Chọn nhân viên (1 - Nguyễn Văn A, 2 - Trần Thị B): ");
        int staffChoice = scanner.nextInt();
        
        System.out.print("Nhập ngày hẹn (dd/mm/yyyy): ");
        String date = scanner.next();
        
        System.out.print("Nhập giờ hẹn (hh:mm): ");
        String time = scanner.next();
        
        // Lưu thông tin vào database (phần này sẽ thêm sau)
        System.out.println("\nĐã đặt lịch thành công cho " + customerName);
        System.out.println("Thông tin lịch hẹn:");
        System.out.println("- Dịch vụ: " + getServiceName(serviceChoice));
        System.out.println("- Nhân viên: " + getStaffName(staffChoice));
        System.out.println("- Thời gian: " + date + " lúc " + time);
    }
    
    private static void viewServices() {
        System.out.println("\n=== DANH SÁCH DỊCH VỤ ===");
        System.out.println("1. Chăm sóc da cơ bản - 250.000đ - 60 phút");
        System.out.println("2. Trị mụn chuyên sâu - 500.000đ - 90 phút");
        System.out.println("3. Dưỡng trắng da - 400.000đ - 75 phút");
        System.out.println("4. Phục hồi da sau mụn - 350.000đ - 60 phút");
    }
    
    private static void manageStaff() {
        System.out.println("\n=== QUẢN LÝ NHÂN VIÊN ===");
        System.out.println("1. Nguyễn Văn A - Chuyên viên chăm sóc da");
        System.out.println("2. Trần Thị B - Chuyên viên trị liệu");
        System.out.println("3. Lê Thị C - Chuyên viên dưỡng da");
    }
    
    private static void viewAppointments() {
        System.out.println("\n=== DANH SÁCH LỊCH HẸN ===");
        System.out.println("1. Nguyễn Thị D - 15/12/2023 14:00 - Chăm sóc da cơ bản");
        System.out.println("2. Trần Văn E - 16/12/2023 10:30 - Trị mụn chuyên sâu");
    }
    
    private static String getServiceName(int choice) {
        switch (choice) {
            case 1: return "Chăm sóc da cơ bản";
            case 2: return "Trị mụn chuyên sâu";
            case 3: return "Dưỡng trắng da";
            default: return "Dịch vụ khác";
        }
    }
    
    private static String getStaffName(int choice) {
        switch (choice) {
            case 1: return "Nguyễn Văn A";
            case 2: return "Trần Thị B";
            default: return "Nhân viên khác";
        }
    }
}
