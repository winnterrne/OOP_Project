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
    private List<NhanVien> dsNhanVien = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private final String TEN_FILE = "nhanvien.csv";

    public QuanLiNhanVien() {
        super();
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
        System.out.println("Đã sắp xếp nhân viên theo mã nhân viên");
        ghiFile(TEN_FILE);
    }

    public void hienThi() {
        if (dsNhanVien.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }
        sapXepNVTheoMa();
        for (NhanVien nv : dsNhanVien) {
            nv.xuatNhanVien();
        }
        System.out.println("Tổng số nhân viên: " + tongSoNhanVien);
    }

    // =====================================
    // DOC FILE CSV
    // =====================================
    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String dong;
            dsNhanVien.clear();

            while ((dong = br.readLine()) != null) {
                String[] arr = dong.split(",");
                if (arr.length < 7) continue;

                String maNV = arr[0];
                String hoTen = arr[1];
                String loaiCV = arr[2];
                String cmnd = arr[3];
                int ngayNghi = Integer.parseInt(arr[4]);
                int namVaoLam = Integer.parseInt(arr[5]);
                double heSoLuong = Double.parseDouble(arr[6]);

                NhanVien nv = null;
                // Dựa theo loaiCV để xác định đúng lớp con
                if (loaiCV.equalsIgnoreCase("Ban hang") || loaiCV.equalsIgnoreCase("NhanVienBanHang")) {
                    double doanhThu = Double.parseDouble(arr[7]);
                    double doanhThuMin = Double.parseDouble(arr[8]);
                    nv = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, doanhThu, doanhThuMin, heSoLuong);
                } else if (loaiCV.equalsIgnoreCase("Dung bep") || loaiCV.equalsIgnoreCase("NhanVienDungBep")) {
                    int soLuongOrder = Integer.parseInt(arr[7]);
                    String quayPhuTrach = arr[8];
                    nv = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSoLuong, soLuongOrder, quayPhuTrach);
                } else if (loaiCV.equalsIgnoreCase("Quan li") || loaiCV.equalsIgnoreCase("QuanLi")) {
                    String phongBan = arr[7];
                    String chiNhanhQL = arr[8];
                    double phuCapQL = Double.parseDouble(arr[9]);
                    nv = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSoLuong, phongBan, chiNhanhQL, phuCapQL);
                }

                if (nv != null) dsNhanVien.add(nv);
            }

            System.out.println("Da doc file thanh cong (" + dsNhanVien.size() + " nhan vien).");
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }

    // =====================================
    // GHI FILE CSV
    // =====================================
    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
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
        do {
            System.out.println("\n===== MENU QUAN LI NHAN VIEN =====");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Xoa nhan vien");
            System.out.println("3. Sua thong tin nhan vien");
            System.out.println("4. Tim kiem nhan vien");
            System.out.println("5. Sap xep nhan vien theo luong");
            System.out.println("6. Hien thi danh sach nhan vien");
            System.out.println("7. Doc danh sach nhan vien tu file");
            System.out.println("8. Ghi danh sach nhan vien ra file");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> themNhanVien();
                case 2 -> xoaNhanVien();
                case 3 -> suaNhanVien();
                case 4 -> timNhanVien();
                case 5 -> {
                    sapXepNVTheoMa();
                    System.out.println("Da sap xep nhan vien theo luong!");
                }
                case 6 -> hienThi();
                case 7 -> docFile("NhanVien.csv");
                case 8 -> ghiFile("NhanVien.csv");
                case 0 -> System.out.println("Thoat khoi menu quan li nhan vien.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    // =====================================
    // CAC PHUONG THUC CON
    // =====================================
    private void themNhanVien() {
        System.out.println("\nChon loai nhan vien muon them:");
        System.out.println("1. Nhan vien ban hang");
        System.out.println("2. Nhan vien dung bep");
        System.out.println("3. Nhan vien quan li");
        System.out.print("Nhap lua chon: ");
        int loaiNV = sc.nextInt();
        sc.nextLine();
        String maNV;
        do{
            System.out.print("Nhap ma NV: ");
            maNV = sc.nextLine();
            if (timKiem(maNV) != null)
                System.out.println("Mã nhân viên đã tồn tại, vui lòng nhập lại!");
        }while (timKiem(maNV) != null);
        System.out.print("Nhap ho ten: ");
        String hoTen = sc.nextLine();
        System.out.print("Nhap loai cong viec: ");
        String loaiCV = sc.nextLine();
        System.out.print("Nhap CMND: ");
        String cmnd = sc.nextLine();
        System.out.print("Nhap so ngay nghi: ");
        int ngayNghi = sc.nextInt();
        System.out.print("Nhap nam vao lam: ");
        int namVaoLam = sc.nextInt();
        System.out.print("Nhap he so luong: ");
        double heSo = sc.nextDouble();
        sc.nextLine();

        NhanVien nv = null;
        switch (loaiNV) {
            case 1 -> {
                System.out.print("Nhap doanh thu: ");
                double doanhThu = sc.nextDouble();
                System.out.print("Nhap doanh thu toi thieu: ");
                double doanhThuMin = sc.nextDouble();
                nv = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, doanhThu, doanhThuMin, heSo);
            }
            case 2 -> {
                System.out.print("Nhap so luong order: ");
                int soLuongOrder = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhap quay phu trach: ");
                String quayPhuTrach = sc.nextLine();
                nv = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, soLuongOrder, quayPhuTrach);
            }
            case 3 -> {
                System.out.print("Nhap phong ban: ");
                String phongBan = sc.nextLine();
                System.out.print("Nhap chi nhanh quan ly: ");
                String chiNhanhQL = sc.nextLine();
                System.out.print("Nhap phu cap quan ly: ");
                double phuCapQL = sc.nextDouble();
                nv = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, phongBan, chiNhanhQL, phuCapQL);
            }
            default -> System.out.println("Loai nhan vien khong hop le!");
        }

        if (nv != null) {
            them(nv);
            ghiFile(TEN_FILE);
            System.out.println("Da them nhan vien!");
        }
    }

    private void xoaNhanVien() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String ma = sc.nextLine();
        xoa(ma);
        ghiFile(TEN_FILE);
        System.out.println("Da xoa nhan vien neu ton tai.");
    }

    private void suaNhanVien() {
        System.out.print("Nhap ma nhan vien can sua: ");
        String maNV = sc.nextLine();

        System.out.println("\nChon loai nhan vien muon sua:");
        System.out.println("1. Nhan vien ban hang");
        System.out.println("2. Nhan vien dung bep");
        System.out.println("3. Nhan vien quan li");
        System.out.print("Nhap lua chon: ");
        int loaiNV = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ho ten moi: ");
        String hoTen = sc.nextLine();
        System.out.print("Nhap loai cong viec moi: ");
        String loaiCV = sc.nextLine();
        System.out.print("Nhap CMND moi: ");
        String cmnd = sc.nextLine();
        System.out.print("Nhap so ngay nghi moi: ");
        int ngayNghi = sc.nextInt();
        System.out.print("Nhap nam vao lam moi: ");
        int namVaoLam = sc.nextInt();
        System.out.print("Nhap he so luong moi: ");
        double heSo = sc.nextDouble();
        sc.nextLine();

        NhanVien nvMoi = null;
        switch (loaiNV) {
            case 1 -> {
                System.out.print("Nhap doanh thu moi: ");
                double doanhThu = sc.nextDouble();
                System.out.print("Nhap doanh thu toi thieu moi: ");
                double doanhThuMin = sc.nextDouble();
                nvMoi = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, doanhThu, doanhThuMin, heSo);
            }
            case 2 -> {
                System.out.print("Nhap so luong order moi: ");
                int soLuongOrder = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhap quay phu trach moi: ");
                String quayPhuTrach = sc.nextLine();
                nvMoi = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, soLuongOrder, quayPhuTrach);
            }
            case 3 -> {
                System.out.print("Nhap phong ban moi: ");
                String phongBan = sc.nextLine();
                System.out.print("Nhap chi nhanh quan ly moi: ");
                String chiNhanhQL = sc.nextLine();
                System.out.print("Nhap phu cap quan ly moi: ");
                double phuCapQL = sc.nextDouble();
                nvMoi = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, namVaoLam, heSo, phongBan, chiNhanhQL, phuCapQL);
            }
            default -> System.out.println("Loai nhan vien khong hop le!");
        }

        if (nvMoi != null) {
            sua(maNV, nvMoi);
            ghiFile(TEN_FILE);
            System.out.println("Da sua thong tin nhan vien thanh cong!");
        }
    }

    private void timNhanVien() {
        System.out.print("Nhap ma nhan vien can tim: ");
        String ma = sc.nextLine();
        Object obj = timKiem(ma);
        if (obj instanceof NhanVien nv) {
            System.out.println("Tim thay nhan vien:");
            nv.xuatNhanVien();
        } else {
            System.out.println("Khong tim thay nhan vien co ma " + ma);
        }
    }
}
