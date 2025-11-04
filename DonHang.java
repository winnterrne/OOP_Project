package DichVu;

import java.util.ArrayList;
import java.util.List;

public class DonHang {
    private String tenSanPham;
    private String maSanPham;
    private int soLuong;
    private List<SanPham> dsSanPham = new ArrayList<>();
    private HoaDon hoaDon;

    public DonHang() {

    }
    public DonHang(String tenSanPham, String maSanPham, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }
    public String setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }
    public String setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
    public int setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}