package QuanLi;

import NhanSu.NhanVien;
import NhanSu.NhanVienBanHang;
import NhanSu.NhanVienDungBep;
import NhanSu.QuanLi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class QuanLiNhanVien extends QuanLiChung {
    private int tongSoNhanVien;
    private QuanLi quanLi;
    private List<NhanVien> dsNhanVien = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private final String TEN_FILE = "src/nhanvien.csv";

    public QuanLiNhanVien() {
        super();
        this.quanLi = new QuanLi();
        tongSoNhanVien = 0;
    }

    @Override
    public void them(Object obj) {
        if (obj instanceof NhanVien) {
            dsNhanVien.add((NhanVien) obj);
            tongSoNhanVien++;
        }
    }

    @Override
    public void xoa(String maNV) {
        dsNhanVien.removeIf(nv -> nv.getMaNV().equalsIgnoreCase(maNV));
    }

    @Override
    public void sua(String maNV, Object newInfo) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNV().equalsIgnoreCase(maNV) && newInfo instanceof NhanVien) {
                NhanVien nvMoi = (NhanVien) newInfo;
                nv.setHoTen(nvMoi.getHoTen());
                nv.setLoaiCongViec(nvMoi.getLoaiCongViec());
                nv.setCmnd(nvMoi.getCmnd());
                nv.setSoNgayNghi(nvMoi.getSoNgayNghi());
                nv.setNamVaoLam(nvMoi.getNamVaoLam());
                nv.setHeSoLuong(nvMoi.getHeSoLuong());
            }
        }
    }

    @Override
    public Object timKiem(String maNV) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    public void sapXepNVTheoMa() {
        dsNhanVien.sort(Comparator.comparing(NhanVien::getMaNV));
        System.out.println("Đã sắp xếp nhân viên theo mã nhân viên ");
    }

    public void sapXepNVTheoLuong() {
        dsNhanVien.sort(Comparator.comparing(NhanVien::tinhLuong));
        System.out.println("Đã Sắp Xếp Nhân Viên Theo Lương : ");
    }


    public void hienThi() {
        if (dsNhanVien.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }
        sapXepNVTheoMa();
        for (NhanVien nv : dsNhanVien) {
            nv.xuatNhanVien();
            tongSoNhanVien++;
        }
        System.out.println("Tổng số nhân viên: " + tongSoNhanVien);
    }

    public void hienThiLuong() {
        if(dsNhanVien.isEmpty()) {
            System.out.println("Danh sach rong! ");
            return;
        }
        for (NhanVien nv : dsNhanVien) {
            nv.xuatLuongNhanVien();
        }
    }
    public void hienthiTheoLoai() {
        if (dsNhanVien.isEmpty()) {
            System.out.println("Danh sach rong !");
            return;
        }
        for(NhanVien nv : dsNhanVien) {
            nv.xuatNhanVienTheoXepLoai();
        }
    }



    // =====================================
    // DOC FILE CSV
    // =====================================
    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String dong;
            dsNhanVien.clear();
            br.readLine();

            while ((dong = br.readLine()) != null) {
                String[] arr = dong.split(",");
                if (arr.length < 8) continue;

                String loai = arr[0];
                String maNV = arr[1];
                String hoTen = arr[2];
                String loaiCV = arr[3];
                String cmnd = arr[4];
                int ngayNghi = Integer.parseInt(arr[5]);
                int namVaoLam = Integer.parseInt(arr[6]);
                double heSoLuong = Double.parseDouble(arr[7]);

                NhanVien nv = null;
                // Dựa theo loaiCV để xác định đúng lớp con
                if (loaiCV.equalsIgnoreCase("Ban hang") || loaiCV.equalsIgnoreCase("NhanVienBanHang")) {
                    double doanhThu = Double.parseDouble(arr[8]);
                    double doanhThuMin = Double.parseDouble(arr[9]);
                    nv = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, doanhThu, doanhThuMin, heSoLuong);
                } else if (loaiCV.equalsIgnoreCase("Dung bep") || loaiCV.equalsIgnoreCase("NhanVienDungBep")) {
                    int soLuongOrder = Integer.parseInt(arr[8]);
                    String quayPhuTrach = arr[9];
                    nv = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSoLuong, soLuongOrder, quayPhuTrach);
                } else if (loaiCV.equalsIgnoreCase("Quan li") || loaiCV.equalsIgnoreCase("QuanLi")) {
                    String phongBan = arr[8];
                    String chiNhanhQL = arr[9];
                    double phuCapQL = Double.parseDouble(arr[10]);
                    nv = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSoLuong, phongBan, chiNhanhQL, phuCapQL);
                }

                if (nv != null) dsNhanVien.add(nv);
            }

            System.out.println("Da doc file thanh cong (" + dsNhanVien.size() + " nhân viên).");
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }

    // =====================================
    // GHI FILE CSV
    // =====================================
    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            bw.write("MaNV,HoTen,LoaiCongViec,CMND,SoNgayNghi,NamVaoLam,HeSoLuong,ThuocTinh1,ThuocTinh2,ThuocTinh3");
            bw.newLine();
            for (NhanVien nv : dsNhanVien) {
                bw.write(nv.toCSV());
                bw.newLine();
            }
            System.out.println("Da ghi danh sach nhan vien ra file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    // =====================================
    // MENU CHINH
    // =====================================
    public void menu() {
        int choice;
        docFile(TEN_FILE);
        do {
            System.out.println("\n===== MENU QUẢN LÍ NHÂN VIÊN =====");
            System.out.println("1. Thêm nhân viên ");
            System.out.println("2. Xóa nhân viên ");
            System.out.println("3. Sửa thông tin nhân viên ");
            System.out.println("4. Tìm kiếm nhân viên ");
            System.out.println("5. Sắp xếp nhân viên theo lương ");
            System.out.println("6. Hiển thị danh sách nhiên viên ");
            System.out.println("7. Hiện thị danh sách nhân viên theo xếp loại ");
            System.out.println("8. Đánh giá nhân viên ");
            System.out.println("0. Thoat");
            System.out.print("Nhập lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> themNhanVien();
                case 2 -> xoaNhanVien();
                case 3 -> suaNhanVien();
                case 4 -> timNhanVien();
                case 5 -> {
                    sapXepNVTheoLuong();
                    hienThiLuong();
                    ghiFile(TEN_FILE);
                }
                case 6 -> hienThi();
                case 7 -> hienthiTheoLoai();
                case 8 -> danhgiaNhanVien();
                case 0 -> System.out.println("Thoát khỏi menu quản lí nhân viên.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    // =====================================
    // CAC PHUONG THUC CON
    // =====================================
    private void themNhanVien() {
        System.out.println("\nChọn loại nhân viên muốn thêm: ");
        System.out.println("1. Nhân viên bán hàng ");
        System.out.println("2. Nhân viên đứng bếp ");
        System.out.println("3. Nhân viên quản lí ");
        System.out.print("Nhập lựa chọn: ");
        int loaiNV = sc.nextInt();
        sc.nextLine();
        String maNV;
        do{
            System.out.print("Nhập mã Nhân Viên : ");
            maNV = sc.nextLine();
            if (timKiem(maNV) != null)
                System.out.println("Mã nhân viên đã tồn tại, vui lòng nhập lại!");
        }while (timKiem(maNV) != null);
        System.out.print("Nhập họ và tên : ");
        String hoTen = sc.nextLine();
        System.out.print("Nhập loại công việc : ");
        String loaiCV = sc.nextLine();
        System.out.print("Nhập CMND : ");
        String cmnd = sc.nextLine();
        System.out.print("Nhập số ngày nghỉ : ");
        int ngayNghi = sc.nextInt();
        System.out.print("Nhập năm vào làm : ");
        int namVaoLam = sc.nextInt();
        System.out.print("Nhập hệ số lương : ");
        double heSo = sc.nextDouble();
        sc.nextLine();

        NhanVien nv = null;
        switch (loaiNV) {
            case 1 -> {
                System.out.print("Nhập doanh thu : ");
                double doanhThu = sc.nextDouble();
                System.out.print("Nhập doanh thu tối thiểu : ");
                double doanhThuMin = sc.nextDouble();
                nv = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, doanhThu, doanhThuMin, heSo);
            }
            case 2 -> {
                System.out.print("Nhập số order : ");
                int soLuongOrder = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập quầy phụ trách : ");
                String quayPhuTrach = sc.nextLine();
                nv = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, soLuongOrder, quayPhuTrach);
            }
            case 3 -> {
                System.out.print("Nhập phòng ban : ");
                String phongBan = sc.nextLine();
                System.out.print("Nhập chi nhánh quản lí : ");
                String chiNhanhQL = sc.nextLine();
                System.out.print("Nhập phụ cấp quản lí ");
                double phuCapQL = sc.nextDouble();
                nv = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, phongBan, chiNhanhQL, phuCapQL);
            }
            default -> System.out.println("Loai nhan vien khong hop le!");
        }

        if (nv != null) {
            them(nv);
            ghiFile(TEN_FILE);
            System.out.println("Đã thêm nhân viên !");
        }
    }

    private void xoaNhanVien() {
        System.out.print("Nhập mã nhân viên cần xóa : ");
        String ma = sc.nextLine();
        xoa(ma);
        ghiFile(TEN_FILE);
        System.out.println("Đã xóa nếu nhân viên tồn tại! ");
    }

    private void suaNhanVien() {
        System.out.println("\nChọn loại nhân viên muốn sửa thông tin: ");
        System.out.println("1. Nhân viên bán hàng ");
        System.out.println("2. Nhân viên đứng bếp ");
        System.out.println("3. Nhân viên quản lí ");
        System.out.print("Nhập lựa chọn: ");
        int loaiNV = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập mã nhân viên cần sửa: ");
        String maNV = sc.nextLine();
        
        System.out.print("Nhập họ và tên mới: ");
        String hoTen = sc.nextLine();
        System.out.print("Nhập loại công việc mới: ");
        String loaiCV = sc.nextLine();
        System.out.print("Nhập CMND mới: ");
        String cmnd = sc.nextLine();
        System.out.print("Nhập số ngày nghỉ mới: ");
        int ngayNghi = sc.nextInt();
        System.out.print("Nhập năm vào làm mới: ");
        int namVaoLam = sc.nextInt();
        System.out.print("Nhập hệ số lương mới: ");
        double heSo = sc.nextDouble();
        sc.nextLine();

        NhanVien nvMoi = null;
        switch (loaiNV) {
            case 1 -> {
                System.out.print("Nhập doanh thu mới: ");
                double doanhThu = sc.nextDouble();
                System.out.print("Nhập doanh thu tối thiểu: ");
                double doanhThuMin = sc.nextDouble();
                nvMoi = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, doanhThu, doanhThuMin, heSo);
            }
            case 2 -> {
                System.out.print("Nhập số lượng order mới: ");
                int soLuongOrder = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập quầy phụ trách mới: ");
                String quayPhuTrach = sc.nextLine();
                nvMoi = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, soLuongOrder, quayPhuTrach);
            }
            case 3 -> {
                System.out.print("Nhập phòng ban mới: ");
                String phongBan = sc.nextLine();
                System.out.print("Nhập chi nhánh quản lí mới: ");
                String chiNhanhQL = sc.nextLine();
                System.out.print("Nhập phụ cấp quản lí mới: ");
                double phuCapQL = sc.nextDouble();
                nvMoi = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, phongBan, chiNhanhQL, phuCapQL);
            }
            default -> System.out.println("Loai nhan vien khong hop le!");
        }

        if (nvMoi != null) {
            sua(maNV, nvMoi);
            ghiFile(TEN_FILE);
            System.out.println("Đã sửa thông tin nhân viên thành công !");
        }
    }

    private void timNhanVien() {
        System.out.print("Nhập mã nhân viên cần tìm: ");
        String ma = sc.nextLine();
        Object obj = timKiem(ma);
        if (obj instanceof NhanVien nv) {
            System.out.println("Tìm thấy nhân viên: ");
            nv.xuatNhanVien();
        } else {
            System.out.println("Không tìm thấy nhân viên có mã: " + ma);
        }
    }

    public void danhgiaNhanVien() {
            System.out.println("=== DANH SÁCH ĐÁNH GIÁ NHÂN VIÊN ===");
            System.out.println("\n--- Nhân viên bán hàng ---");
            for (NhanVien nhanVien : dsNhanVien) {
                if (nhanVien instanceof NhanVienBanHang) {
                    quanLi.danhGiaNhanVien(nhanVien);
                    System.out.println();
                }
            }
            System.out.println("\n--- Nhân viên đứng bếp ---");
            for (NhanVien nhanVien : dsNhanVien) {
                if (nhanVien instanceof NhanVienDungBep) {
                    quanLi.danhGiaNhanVien(nhanVien);
                    System.out.println();
                }
            }
            System.out.println("\n--- Nhân viên khác ---");
            for (NhanVien nhanVien : dsNhanVien) {
                if (!(nhanVien instanceof NhanVienBanHang) && !(nhanVien instanceof NhanVienDungBep)) {
                    quanLi.danhGiaNhanVien(nhanVien);
                    System.out.println();
                }
            }
        }

    }



