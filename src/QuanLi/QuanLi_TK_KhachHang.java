package QuanLi;

import DichVu.KhachHang;
import java.io.*;
import java.util.*;

public class QuanLi_TK_KhachHang extends QuanLiChung {
    private ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
    private final String TEN_FILE = "KhachHang.csv"; // tên file mặc định

    // ------------------ CÁC CHỨC NĂNG CƠ BẢN ------------------

    @Override
    public void them(Object obj) {
        if (obj instanceof KhachHang kh) {
            dsKhachHang.add(kh);
            System.out.println("Da them khach hang: " + kh.getTenKH());
            ghiFile(TEN_FILE); // ghi lại ngay sau khi thêm
        }
    }

    @Override
    public KhachHang timKiem(String maKH) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKH().equalsIgnoreCase(maKH)) {
                return kh;
            }
        }
        return null;
    }

    @Override
    public void xoa(String maKH) {
        boolean removed = dsKhachHang.removeIf(kh -> kh.getMaKH().equalsIgnoreCase(maKH));
        if (removed) {
            System.out.println("Da xoa khach hang co ma: " + maKH);
            ghiFile(TEN_FILE); // ghi lại ngay sau khi xóa
        } else {
            System.out.println("Khong tim thay khach hang can xoa!");
        }
    }

    @Override
    public void sua(String maKH, Object newInfo) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKH().equalsIgnoreCase(maKH) && newInfo instanceof KhachHang khMoi) {
                kh.setTenKH(khMoi.getTenKH());
                kh.setDiaChi(khMoi.getDiaChi());
                kh.setSoDienThoai(khMoi.getSoDienThoai());
                System.out.println("Da sua thong tin khach hang: " + maKH);
                ghiFile(TEN_FILE); // ghi lại ngay sau khi sửa
                return;
            }
        }
        System.out.println("Khong tim thay ma khach hang: " + maKH);
    }

    // ------------------ ĐỌC FILE CSV ------------------

    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            br.readLine();
            dsKhachHang.clear(); // xóa danh sách cũ trước khi đọc lại
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 4) {
                    String maKH = arr[0].trim();
                    String tenKH = arr[1].trim();
                    String diaChi = arr[2].trim();
                    int soDienThoai = Integer.parseInt(arr[3].trim());
                    dsKhachHang.add(new KhachHang(maKH, tenKH, diaChi, soDienThoai));
                }
            }
            System.out.println("Doc file thanh cong: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }

    // ------------------ GHI FILE CSV ------------------

    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (KhachHang kh : dsKhachHang) {
                String line = kh.getMaKH() + "," + kh.getTenKH() + "," +
                        kh.getDiaChi() + "," + kh.getSoDienThoai();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Da cap nhat file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    // ------------------ HIỂN THỊ DANH SÁCH ------------------

    public void hienThi() {
        if (dsKhachHang.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }
        for (KhachHang kh : dsKhachHang) {
            kh.xuat();
        }
    }

    // ------------------ MENU SWITCH CASE ------------------

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        // Đọc file ban đầu nếu có
        docFile(TEN_FILE);

        do {
            System.out.println("\n===== QUAN LI KHACH HANG =====");
            System.out.println("1. Them khach hang");
            System.out.println("2. Sua khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Tim kiem khach hang");
            System.out.println("5. Hien thi danh sach");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            choice = sc.nextInt();
            sc.nextLine(); // bỏ dòng trống

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhap ma KH: ");
                    String ma = sc.nextLine();
                    System.out.print("Nhap ten KH: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhap dia chi: ");
                    String diaChi = sc.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    int sdt = sc.nextInt();
                    sc.nextLine();
                    them(new KhachHang(ma, ten, diaChi, sdt));
                }
                case 2 -> {
                    System.out.print("Nhap ma KH can sua: ");
                    String ma = sc.nextLine();
                    System.out.print("Nhap ten moi: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhap dia chi moi: ");
                    String diaChi = sc.nextLine();
                    System.out.print("Nhap SDT moi: ");
                    int sdt = sc.nextInt();
                    sc.nextLine();
                    sua(ma, new KhachHang(ma, ten, diaChi, sdt));
                }
                case 3 -> {
                    System.out.print("Nhap ma KH can xoa: ");
                    String ma = sc.nextLine();
                    xoa(ma);
                }
                case 4 -> {
                    System.out.print("Nhap ma KH can tim: ");
                    String ma = sc.nextLine();
                    KhachHang kh = timKiem(ma);
                    if (kh != null) kh.xuat();
                    else System.out.println("Khong tim thay!");
                }
                case 5 -> hienThi();
                case 0 -> System.out.println("Thoat chuong trinh!");
                default -> System.out.println("Lua chon khong hop le!");
            }

        } while (choice != 0);
    }
}
