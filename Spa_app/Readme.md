# Ứng Dụng Đặt Lịch Spa - Skincare Booking App

## 🔧 Công nghệ sử dụng
- Backend: Java Spring Boot
- Database: MySQL
- Frontend: HTML, CSS, JavaScript
- Công cụ test API: Postman

## 🚀 Hướng dẫn chạy
### 1. Backend
- Cài đặt MySQL và tạo schema `skincareapp`
- Import file `database/skincareapp.sql`
- Mở project `backend/` bằng IntelliJ hoặc VS Code
- Cấu hình `application.properties` với thông tin DB của bạn
- Chạy `SkincareappApplication.java`

### 2. Frontend
- Mở `frontend/datlich.html` bằng Live Server hoặc trình duyệt
- Đảm bảo backend đang chạy ở `http://localhost:8080`

## 🧪 Test API
- Tạo lịch hẹn: `POST /api/datlich`
- Kiểm tra giờ đã đặt: `GET /api/datlich/check?branchId=1&date=2025-04-16`

## 📂 Cấu trúc thư mục
- backend/: mã nguồn Java Spring Boot
- frontend/: giao diện HTML
- database/: file SQL tạo bảng và dữ liệu mẫu

## 👤 Thành viên thực hiện
Ngô Hữu Phước 
Nguyễn Khánh Phúc
