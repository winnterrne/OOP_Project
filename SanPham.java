package DichVu;

import java.util.ArrayList;
import java.util.List;

abstract public class SanPham {
    protected String maSanPham;
    protected String tenSanPham;
    protected double giaThanh;

    public SanPham() {

    }
    public SanPham(String maSanPham, String tenSanPham, double giaThanh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
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
    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }
    abstract public void xuatSanPham();

}