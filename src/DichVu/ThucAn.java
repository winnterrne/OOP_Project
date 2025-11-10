package DichVu;

public class ThucAn extends SanPham {
    protected String nguyenLieu;
    protected int thoiGianChuanBi;
    protected LoaiThucAn loaithucan;

    public ThucAn() {
    }

    public ThucAn(String maSanPham, String tenSanPham, int soLuong, double giaThanh,
                  String nguyenLieu, int thoiGianChuanBi, LoaiThucAn loaithucan) {
        super(maSanPham, tenSanPham, soLuong, giaThanh);
        this.nguyenLieu = nguyenLieu;
        this.thoiGianChuanBi = thoiGianChuanBi;
        this.loaithucan = loaithucan;
    }

    // ===== GETTERS & SETTERS =====
    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public int getThoiGianChuanBi() {
        return thoiGianChuanBi;
    }

    public void setThoiGianChuanBi(int thoiGianChuanBi) {
        this.thoiGianChuanBi = thoiGianChuanBi;
    }

    public LoaiThucAn getLoaithucan() {
        return loaithucan;
    }

    public void setLoaithucan(LoaiThucAn loaithucan) {
        this.loaithucan = loaithucan;
    }

    public void cachCheBien() {
        if (loaithucan == null) {
            System.out.println("Nau an nhu lon");
            return;
        }
        switch (loaithucan) {
            case CHIEN -> System.out.println("Chien nhiet do 170C trong 6 phut");
            case HAP -> System.out.println("Hap nuoc soi voi nhiet do 100C trong 4p");
            case XAO -> System.out.println("Uop gia vi roi xao trong 5p");
            case NUONG -> System.out.println("Uop gia vi roi nuong trong 4p");
            default -> System.out.println("Cach che bien chua ton tai");
        }
    }

    @Override
    public double tinhTien(int soLuong) {
        return soLuong * giaThanh;
    }

    @Override
    public void xuatSanPham() {
        System.out.println("----------------------------------------------------");
        System.out.println("Ma San Pham: " + getMaSanPham());
        System.out.println("Ten San Pham: " + getTenSanPham());
        System.out.println("Gia Thanh: " + getGiaThanh());
        System.out.println("So Luong: " + getSoLuong());
        System.out.println("Nguyen Lieu: " + nguyenLieu);
        System.out.println("Thoi Gian Chuan Bi: " + thoiGianChuanBi);
        System.out.println("Loai Thuc An: " + (loaithucan != null ? loaithucan : "Chua xac dinh"));
    }
}
