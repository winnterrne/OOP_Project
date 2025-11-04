package DichVu.QuanLi;

import NhanSu.NhanVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

public class QuanLiNhanVien extends QuanLiChung {
    private int tongSoNhanVien;
    private List<NhanVien> dsNhanVien = new ArrayList<>();

    public QuanLiNhanVien() {
        super();
        tongSoNhanVien = 0;
    }

    @Override
    public void them(Object obj) {
        if (obj instanceof NhanVien nv) {
            dsNhanVien.add(nv);
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
            if (nv.getMaNV().equalsIgnoreCase(maNV) && newInfo instanceof NhanVien nvMoi) {
                nv.setHoTen(nvMoi.getHoTen());
                nv.setLoaiCongViec(nvMoi.getLoaiCongViec());
                nv.setHoTen(nvMoi.getHoTen());
                nv.setCmnd(nvMoi.getCmnd());
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

    public void sapXepNVTheoLuong() {
        dsNhanVien.sort(Comparator.comparingDouble(NhanVien::tinhLuong));
    }

    public void hienThi() {
        if (dsNhanVien.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }
        for (NhanVien nv : dsNhanVien) {
            nv.xuatNhanVien();
        }
    }

    // 🟩 Phương thức menu() nằm trong class này
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU QUAN LI NHAN VIEN =====");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Xoa nhan vien");
            System.out.println("3. Sua thong tin nhan vien");
            System.out.println("4. Tim kiem nhan vien");
            System.out.println("5. Sap xep nhan vien theo luong");
            System.out.println("6. Hien thi danh sach nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine(); // bỏ \n

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhap ma NV: ");
                    String ma = sc.nextLine();
                    System.out.print("Nhap ho ten: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhap loai cong viec: ");
                    String loai = sc.nextLine();
                    System.out.print("Nhap CMND: ");
                    String cmnd = sc.nextLine();
                    System.out.print("Nhap so ngay nghi: ");
                    int ngayNghi = sc.nextInt();
                    System.out.print("Nhap nam vao lam: ");
                    int nam = sc.nextInt();
                    System.out.print("Nhap he so luong: ");
                    double heSo = sc.nextDouble();

                    NhanVien nv = new NhanVienThuong(ma, ten, loai, cmnd, ngayNghi, nam, heSo);
                    them(nv);
                    System.out.println("✅ Da them nhan vien!");
                }

                case 2 -> {
                    System.out.print("Nhap ma nhan vien can xoa: ");
                    String ma = sc.nextLine();
                    xoa(ma);
                    System.out.println("✅ Da xoa nhan vien neu ton tai.");
                }

                case 3 -> {
                    System.out.print("Nhap ma nhan vien can sua: ");
                    String ma = sc.nextLine();
                    System.out.print("Nhap ho ten moi: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhap loai cong viec moi: ");
                    String loai = sc.nextLine();
                    System.out.print("Nhap CMND moi: ");
                    String cmnd = sc.nextLine();
                    System.out.print("Nhap so ngay nghi moi: ");
                    int ngayNghi = sc.nextInt();
                    System.out.print("Nhap nam vao lam moi: ");
                    int nam = sc.nextInt();
                    System.out.print("Nhap he so luong moi: ");
                    double heSo = sc.nextDouble();

                    NhanVien nvMoi = new NhanVienThuong(ma, ten, loai, cmnd, ngayNghi, nam, heSo);
                    sua(ma, nvMoi);
                    System.out.println("✅ Da sua thong tin nhan vien.");
                }

                case 4 -> {
                    System.out.print("Nhap ma nhan vien can tim: ");
                    String ma = sc.nextLine();
                    Object obj = timKiem(ma);
                    if (obj instanceof NhanVien nv) {
                        System.out.println("🔍 Tim thay nhan vien:");
                        nv.xuatNhanVien();
                    } else {
                        System.out.println("❌ Khong tim thay nhan vien co ma " + ma);
                    }
                }

                case 5 -> {
                    sapXepNVTheoLuong();
                    System.out.println("✅ Da sap xep nhan vien theo luong!");
                }

                case 6 -> hienThi();

                case 0 -> System.out.println("👋 Thoat khoi menu quan li nhan vien.");
                default -> System.out.println("❌ Lua chon khong hop le!");
            }

        } while (choice != 0);
    }

    // getter (nếu cần dùng ngoài class)
    public List<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }

}
