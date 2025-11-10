package DichVu;

public class KhachHang {
    private HoaDon hoadon;
    private String maKH, tenKH, diaChi;
    private int soDienThoai;

    public String getMaKH () {return maKH;}
    public void setMaKH (String maKH) {this.maKH = maKH;}
    public String getTenKH () {return tenKH;}
    public void setTenKH (String tenKH) {this.tenKH = tenKH;}
    public String getDiaChi () {return diaChi;}
    public void setDiaChi (String diaChi) {this.diaChi = diaChi;}
    public int getSoDienThoai () {return soDienThoai;}
    public void setSoDienThoai (int soDienThoai) {this.soDienThoai = soDienThoai;}
    public HoaDon getHoadon() { return hoadon; }
    public void setHoadon(HoaDon hoadon) { this.hoadon = hoadon; }

    public KhachHang (){

    }

    public KhachHang (String maKH, String tenKH, String diaChi, int soDienThoai){
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public KhachHang(String maKH, String tenKH, String diaChi, int soDienThoai, HoaDon hoadon) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.hoadon = hoadon;
    }

    public void xuat(){
        System.out.println("Ma khach hang: " + this.maKH + " | Ten khach hang: " + this.tenKH + " | Dia chi: " + this.diaChi + " | So dien thoai: " + this.soDienThoai);
        if (hoadon != null) {
            hoadon.inHoaDon();
        } else {
            System.out.println("Không có hóa đơn liên kết với khách hàng này.");
        }
    }
}