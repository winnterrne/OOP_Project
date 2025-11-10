package DichVu;


public class DoUong extends SanPham{
    protected String donViTinh;
    protected double dungTich;

    public DoUong() {

    }
    public DoUong(String maSanPham, String tenSanPham,int soLuong , double giaThanh, String donViTinh, double dungTich) {
        super(maSanPham, tenSanPham,soLuong, giaThanh);
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

    public double getDungTich() {
        return dungTich;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    public void setDungTich(double dungTich) {
        this.dungTich = dungTich;
    }
    @Override
    public double tinhTien(int soLuong) {
        return soLuong * giaThanh;
    }

    @Override
    public String toCSV() {
        return "DoUong, " + super.toCSV() + "," + donViTinh + "," + dungTich;
    }

    @Override
    public void xuatSanPham() {
        System.out.println("---------------------------------------------------------");
        super.xuatSanPham();
        System.out.println("Loai: " + donViTinh);
        System.out.println("Dung Tich: " + dungTich);
    }
}
