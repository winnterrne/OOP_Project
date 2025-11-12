
import QuanLi.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Scanner sc = new Scanner(System.in);
        QuanLiSanPham quanLiSanPham = new QuanLiSanPham();
        QuanLiNhanVien quanLiNhanVien = new QuanLiNhanVien();
        QuanLiHoaDon quanLiHoaDon = new QuanLiHoaDon();
        QuanLi_TK_KhachHang quanLiTkKhachHang = new QuanLi_TK_KhachHang();
        QuanLiCSVC quanLiCSVC = new QuanLiCSVC();

        int choice;
        do {
            System.out.println("===============MENU===============");
            System.out.println("1.Quản Lí Sản Phẩm");
            System.out.println("2.Quản Lí Nhân Viên");
            System.out.println("3.Quản Lí Hóa Đơn");
            System.out.println("4.Quản Lí Tài Khoản Khách Hàng");
            System.out.println("5.Quản Lí Cơ Sở Vật Chất");
            System.out.println("0.Thoát Chương Trình");
            System.out.print("Mời chọn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> quanLiSanPham.menu();
                case 2 -> quanLiNhanVien.menu();
                case 3 -> quanLiHoaDon.menu();
                case 4 -> quanLiTkKhachHang.menu();
                case 5 -> quanLiCSVC.menu();
                case 0 -> System.out.println("Đã Thoát Chương Trình");
                default -> System.out.println("Lựa Chọn Không Hợp Lệ");
            }
        }while (choice != 0);
        
        sc.close();
    }
}