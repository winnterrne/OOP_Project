package DichVu;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Combo extends SanPham implements KhuyenMai{
    public int soLuongMon;
    public int luongNguoiAn;
    protected List<SanPham> dsSanPham = new ArrayList<>();

    public Combo() {

    }
    public Combo(String maSanPham, String tenSanPham, int soLuong, double giaThanh,int soLuongMon, int luongNguoiAn) {
        super(maSanPham, tenSanPham, soLuong, giaThanh);
        this.soLuongMon = soLuongMon;
        this.luongNguoiAn = luongNguoiAn;
    }

    public int getSoLuongMon() {
        return soLuongMon;
    }
    public void setSoLuongMon(int soLuongMon) {
        this.soLuongMon = soLuongMon;
    }
    public int getLuongNguoiAn() {
        return luongNguoiAn;
    }
    public void setLuongNguoiAn(int luongNguoiAn) {
        this.luongNguoiAn = luongNguoiAn;
    }


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
    public void setDsSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
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
        double giaGoc = getGiaThanh();
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
    public String toCSV() {
        return "Combo, " + super.toCSV() + "," + soLuongMon + "," + luongNguoiAn;
    }

    @Override
    public void xuatSanPham() {
        System.out.println("Combo: " + luongNguoiAn + " người gồm: ");
        for(SanPham sp : dsSanPham) {
            System.out.println("Mã Món: " + sp.getMaSanPham() + " | " + " Tên Món: " + sp.getTenSanPham());
        }
        double giaSauKhuyenMai = apDungKhuyenMai();
        System.out.println("Gía Gốc: " + getGiaThanh());
        System.out.println("Giá Sau Khuyến Mãi (nếu có): " + giaSauKhuyenMai);
        System.out.println("-----------------------");
    }
}
