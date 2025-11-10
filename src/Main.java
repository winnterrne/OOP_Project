import DichVu.*;
import NhanSu.*;
import QuanLi.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuanLiSanPham quanLiSanPham = new QuanLiSanPham();
        QuanLiNhanVien quanLiNhanVien = new QuanLiNhanVien();
        QuanLiHoaDon quanLiHoaDon = new QuanLiHoaDon();
        QuanLi_TK_KhachHang quanLiTkKhachHang = new QuanLi_TK_KhachHang();
        QuanLiCSVC quanLiCSVC = new QuanLiCSVC();

        int choice;
        do {
            System.out.println("=====MENU=====");
            System.out.println("1.Quan Li San Pham");
            System.out.println("2.Quan Li Nhan Vien");
            System.out.println("3.Quan Li Hoa Don");
            System.out.println("4.Quan Li TK KH");
            System.out.println("5. QUan Li CSVT");
            System.out.println("0. Thoat Chuong Trinh");
            System.out.print("Mời chọn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> quanLiSanPham.menu();
                case 2 -> quanLiNhanVien.menu();
                case 3 -> quanLiHoaDon.menu();
                case 4 -> quanLiTkKhachHang.menu();
                case 5 -> quanLiCSVC.menu();
                case 0 -> System.out.println("Da Thoat CHuong Trinh");
                default -> System.out.println("Lua Chon Khong Hop Le");
            }
        }while (choice != 0);

    }
}