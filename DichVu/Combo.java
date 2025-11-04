package DichVu;

import org.w3c.dom.ls.LSOutput;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Combo extends SanPham implements KhuyenMai{
    protected int soLuongMon;
    protected int luongNguoiAn;
    protected List<SanPham> dsSanPham = new ArrayList<>();

    public void themMon(SanPham sp) {
        dsSanPham.add(sp);
        capNhatGiaThanh();
        System.out.println("Da them mon" + sp.getTenSanPham());
    }
    public void xoaMon(String tenMon) {
        boolean xoa = dsSanPham.removeIf(sp -> sp.getTenSanPham().equalsIgnoreCase(tenMon));
        if(xoa) {
            capNhatGiaThanh();
            System.out.println("Da xoa mon " + tenMon);
        }else {
            System.out.println("Khong co mon ");
        }
    }
    public double capNhatGiaThanh() {
        double tong = 0;
        for(SanPham sp : dsSanPham) {
            tong += sp.getGiaThanh();
        }
        super.setGiaThanh(tong);
        return tong;
    }

    @Override
    public double apDungKhuyenMai() {
        double giaGoc = giaThanh;
        java.time.DayOfWeek ngay = java.time.LocalDate.now().getDayOfWeek();
        if(ngay == DayOfWeek.TUESDAY) {
            return giaGoc * 0.85;
        }
        return giaGoc;
    }

    @Override
    public double tinhTien(int soLuong) {
        return soLuong * giaThanh;
    }

    @Override
    public void xuatSanPham() {
        System.out.println("Combo: " + luongNguoiAn + " nguoi gom: ");
        for(SanPham sp : dsSanPham) {
            System.out.println(" " + sp.getTenSanPham() + " " + sp.getGiaThanh());
        }
        double giaGoc = capNhatGiaThanh();
        double giaSauKhuyenMai = apDungKhuyenMai();
        System.out.println("Gia Goc: " + giaGoc);
        System.out.println("Gia Sau Khuyen Mai: " + giaSauKhuyenMai);
    }
}
