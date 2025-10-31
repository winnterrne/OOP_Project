package DichVu;

public class ThucAn extends SanPham {
    protected String nguyenLieu;
    protected int thoiGianChuanBi;
    protected LoaiThucAn loaithucan;

    public ThucAn() {

    }
    public ThucAn(String maSanPham, String tenSanPham, double giaThanh, String nguyenLieu, int thoiGianChuanBi) {
        super(maSanPham, tenSanPham, giaThanh);
        this.nguyenLieu = nguyenLieu;
        this.thoiGianChuanBi = thoiGianChuanBi;
    }
    public String getNguyenLieu() {
        return nguyenLieu;
    }
    public int getThoiGianChuanBi() {
        return thoiGianChuanBi;
    }


    public void cachCheBien() {
        switch (loaithucan) {
            case CHIEN -> System.out.println("Chien nhiet do 170C trong 6 phut");
            case HAP -> System.out.println("Hap nuoc soi voi nhiet do 100C trong 4p");
            case XAO -> System.out.println("Uop gia vi roi xao trong 5p");
            case NUONG -> System.out.println("Uop gia vi roi nuong trong 4p");
        }
    }
    @Override
    public void xuatSanPham() {
        System.out.println("----------------------------------------------------");
        System.out.println("Ma San Pham: " + getMaSanPham());
        System.out.println("Ten San Pham: " + getTenSanPham());
        System.out.println("Gia Thanh: " + getGiaThanh());
        System.out.println("Nguyen Lieu: " + nguyenLieu);
        System.out.println("Thoi Gian Chuan Bi: " + thoiGianChuanBi);
    }
}
