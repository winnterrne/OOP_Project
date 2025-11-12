package QuanLi;

import DichVu.KhachHang;
import DichVu.HoaDon;
import java.io.*;
import java.util.*;

public class QuanLi_TK_KhachHang extends QuanLiChung {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
    private final String TEN_FILE = "src/khachhang.csv";
    private QuanLiHoaDon qlhd;

    public QuanLi_TK_KhachHang() {
        qlhd = new QuanLiHoaDon();
        qlhd.docFile(TEN_FILE.replace("khachhang", "hoadon"));
        qlhd.docFileDonHang(TEN_FILE.replace("khachhang", "hoadon"));
        docFile(TEN_FILE);
    }

    // ------------------ CÁC CHỨC NĂNG CƠ BẢN ------------------

    @Override
    public void them(Object obj) {
        if (obj instanceof KhachHang kh) {
            dsKhachHang.add(kh);
            System.out.println("Đã thêm khách hàng: " + kh.getTenKH());
            ghiFile(TEN_FILE);
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
            System.out.println("Đã xóa khách hàng có mã: " + maKH);
            ghiFile(TEN_FILE);
        } else {
            System.out.println("Không tìm thấy mã khách hàng cần xóa!");
        }
    }

    @Override
    public void sua(String maKH, Object newInfo) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKH().equalsIgnoreCase(maKH) && newInfo instanceof KhachHang khMoi) {
                kh.setTenKH(khMoi.getTenKH());
                kh.setDiaChi(khMoi.getDiaChi());
                kh.setSoDienThoai(khMoi.getSoDienThoai());
                System.out.println("Đã sửa thông tin khách hàng: " + maKH);
                ghiFile(TEN_FILE);
                return;
            }
        }
        System.out.println("Không tìm thấy mã khách hàng: " + maKH);
    }

    // ------------------ ĐỌC FILE CSV ------------------

    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            br.readLine();
            dsKhachHang.clear();
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length >= 4) {
                    String maKH = arr[0].trim();
                    String tenKH = arr[1].trim();
                    String diaChi = arr[2].trim();
                    int soDienThoai = Integer.parseInt(arr[3].trim());
                    KhachHang kh = new KhachHang(maKH, tenKH, diaChi, soDienThoai);
                    if (arr.length >= 5) {
                        String maHD = arr[4].trim();
                        HoaDon hd = (HoaDon) qlhd.timKiem(maHD);
                        if (hd != null) kh.setHoadon(hd);
                        else System.out.println("Không tìm thấy mã hóa đơn và hóa đơn: " + maHD);
                    }
                    dsKhachHang.add(kh);
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
            bw.write("MaKH,TenKH,DiaChi,SDT,MaHD");
            bw.newLine();
            for (KhachHang kh : dsKhachHang) {
                String maHD = (kh.getHoadon() != null) ? kh.getHoadon().getMaHoaDon() : "";
                String line = kh.getMaKH() + "," + kh.getTenKH() + "," +
                        kh.getDiaChi() + "," + kh.getSoDienThoai() + "," + maHD;
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
        int choice;

        // Đọc file ban đầu nếu có
        docFile(TEN_FILE);

        do {
            System.out.println("\n===== QUAN LI KHÁCH HÀNG =====");
            System.out.println("1. Thêm thông tin khách hàng ");
            System.out.println("2. Sửa thông tin khách hàng ");
            System.out.println("3. Xóa thông tin khách hàng ");
            System.out.println("4. Tìm kiếm thông tin khách hàng ");
            System.out.println("5. Hiển thị danh sách khách hàng ");
            System.out.println("6. Gán hóa đơn cho khách hàng ");
            System.out.println("0. Thoát ");
            System.out.print("Nhập lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập mã Khách Hàng: ");
                    String ma = sc.nextLine();
                    System.out.print("Nhập tên Khách Hàng: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhập địa chỉ: ");
                    String diaChi = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    int sdt = sc.nextInt();
                    sc.nextLine();
                    them(new KhachHang(ma, ten, diaChi, sdt));
                }
                case 2 -> {
                    System.out.print("Nhập mã khách hàng cần sửa: ");
                    String ma = sc.nextLine();
                    System.out.print("Nhập tên khách hàng mới: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhập địa chỉ khách hàng mới: ");
                    String diaChi = sc.nextLine();
                    System.out.print("Nhập SDT khách hàng mới: ");
                    int sdt = sc.nextInt();
                    sc.nextLine();
                    sua(ma, new KhachHang(ma, ten, diaChi, sdt));
                }
                case 3 -> {
                    System.out.print("Nhập mã khách hàng cần xóa: ");
                    String ma = sc.nextLine();
                    xoa(ma);
                }
                case 4 -> {
                    System.out.print("Nhập mã khách hàng cần tìm ");
                    String ma = sc.nextLine();
                    KhachHang kh = timKiem(ma);
                    if (kh != null) kh.xuat();
                    else System.out.println("Khong tim thay!");
                }
                case 5 -> hienThi();
                case 6 -> {
                    System.out.print("Nhập mã khách hàng có gán hóa đơn: ");
                    String maKH = sc.nextLine().trim();
                    KhachHang kh = timKiem(maKH);
                    if (kh == null) {
                        System.out.println("Khong tim thay khach hang!");
                        break;
                    }
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD = sc.nextLine().trim();
                    HoaDon hd = (HoaDon) qlhd.timKiem(maHD);
                    if (hd != null) {
                        kh.setHoadon(hd);
                        System.out.println("Đã gán mã hóa đơn: " + maHD + " cho khách hàng " + maKH);
                        ghiFile(TEN_FILE);
                    } else {
                        System.out.println("Khong tim thay hoa don voi ma: " + maHD);
                    }
                }
            }

        } while (choice != 0);
    }
}
