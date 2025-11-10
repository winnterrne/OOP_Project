package DichVu;

public class DonHang {
    private SanPham sp;
    private int soLuong;
    private String maDH;

    public DonHang (){

    }

    public DonHang(SanPham sp, int soLuong) {
        this.sp = sp;
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong (int soLuong) {this.soLuong = soLuong;}

    public String getMaDH () {return maDH;}
    public void setMaDH (String maDH) {this.maDH = maDH;}

    public double tinhTien (){
        if (sp == null) return 0;
        return sp.tinhTien(soLuong);
    }

    public void xuat (){
        if (sp == null) {
            System.out.println("Sản phẩm chưa được gán cho đơn hàng!");
            return;
        }
        System.out.println(sp.getMaSanPham() + " - " + sp.getTenSanPham() + " x " + soLuong + " = " + tinhTien());
    }
}