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
            System.out.println("Da them khach hang: " + kh.getTenKH());
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
            System.out.println("Da xoa khach hang co ma: " + maKH);
            ghiFile(TEN_FILE);
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
                ghiFile(TEN_FILE);
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
                        else System.out.println("Khong tim thay hoa don voi ma: " + maHD);
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
            System.out.println("\n===== QUAN LI KHACH HANG =====");
            System.out.println("1. Them khach hang");
            System.out.println("2. Sua khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Tim kiem khach hang");
            System.out.println("5. Hien thi danh sach");
            System.out.println("6.Gán hóa đơn cho khách hàng");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            choice = sc.nextInt();
            sc.nextLine();

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
                case 6 -> {
                    System.out.print("Nhap ma KH can gan hoa don: ");
                    String maKH = sc.nextLine().trim();
                    KhachHang kh = timKiem(maKH);
                    if (kh == null) {
                        System.out.println("Khong tim thay khach hang!");
                        break;
                    }
                    System.out.print("Nhap ma hoa don: ");
                    String maHD = sc.nextLine().trim();
                    HoaDon hd = (HoaDon) qlhd.timKiem(maHD);
                    if (hd != null) {
                        kh.setHoadon(hd);
                        System.out.println("Da gan hoa don " + maHD + " cho khach hang " + maKH);
                        ghiFile(TEN_FILE);
                    } else {
                        System.out.println("Khong tim thay hoa don voi ma: " + maHD);
                    }
                }
            }

        } while (choice != 0);
    }
}
