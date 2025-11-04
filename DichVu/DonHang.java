package DichVu;

public class DonHang {
    private SanPham sp;
    private int soLuong;

    public DonHang(SanPham sp, int soLuong) {
        this.sp = sp;
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong (int soLuong) {this.soLuong = soLuong;}

    public double tinhTien (){
        return sp.tinhTien(soLuong);
    }

    public void xuat (){
        System.out.println(sp.getMaSanPham() + " - " + sp.tenSanPham + " x " + soLuong + " = " + tinhTien());
    }
}