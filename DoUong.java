package DichVu;

public class DoUong extends SanPham{
    protected String donViTinh;
    protected double dungTich;

    public DoUong() {

    }
    public DoUong(String maSanPham, String tenSanPham, double giaThanh, String donViTinh, double dungTich) {
        super(maSanPham, tenSanPham, giaThanh);
        if(donViTinh.compareToIgnoreCase("ket") !=0 && donViTinh.compareToIgnoreCase("thung") != 0 && donViTinh.compareToIgnoreCase("chai") !=0 && donViTinh.compareToIgnoreCase("lon") !=0) {
            this.donViTinh = "ket";
        }else {
            this.donViTinh = donViTinh;
        }
        this.dungTich = dungTich;
    }
    public String getDonViTinh() {
        return donViTinh;
    }

    @Override
    public void xuatSanPham() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Ma San Pham: " + getMaSanPham());
        System.out.println("Ten San Pham: " + getTenSanPham());
        System.out.println("Gia Thanh: " + getGiaThanh());
        System.out.println("Loai: " + donViTinh);
        System.out.println("Dung Tich: " + dungTich);
    }
}
