package DichVu;

public class DonHang {
    private SanPham sp;
    private int soLuong;
    private String maDonHang;

    public DonHang(SanPham sp, int soLuong, String maDonHang) {
        this.sp = sp;
        this.soLuong = soLuong;
        this.maDonHang = maDonHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong (int soLuong) {this.soLuong = soLuong;}

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public double tinhTien (){
        return sp.tinhTien(soLuong);
    }

    public void xuat (){
        System.out.println(maDonHang + ": " + sp.getMaSanPham() + " - " + sp.tenSanPham + " x " + soLuong + " = " + tinhTien());
    }
    public SanPham getSp() {
        return sp;
    }
}