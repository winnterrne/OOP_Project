package DichVu;

import java.util.ArrayList;
import java.util.List;

abstract public class SanPham {
    protected String maSanPham;
    protected String tenSanPham;
    protected int soLuong;
    protected double giaThanh;

    public SanPham() {

    }
    public SanPham(String maSanPham, String tenSanPham, int soLuong ,double giaThanh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaThanh = giaThanh;
    }
    public String getMaSanPham() {
        return maSanPham;
    }
    public String getTenSanPham() {
        return tenSanPham;
    }
    public double getGiaThanh() {
        return giaThanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }
    public double tinhTien(int soLuong) {
        return soLuong * giaThanh;
    }
    abstract public void xuatSanPham();

}