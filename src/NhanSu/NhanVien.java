package NhanSu;

abstract public class NhanVien implements PhuCapThamNien {
    protected String maNV, hoTen, loaiCongViec, cmnd;
    protected int soNgayNghi, namVaoLam;
    protected double heSoLuong;
    protected static final int luongCoBan = 4000000;

    public String getMaNV () {return maNV;}
    public void setMaNV (String maNV) {this.maNV = maNV;}
    public String getHoTen () {return hoTen;}
    public void setHoTen (String hoTen) {this.hoTen = hoTen;}
    public String getLoaiCongViec () {return loaiCongViec;}

    public String getCmnd () {return cmnd;}
    public void setLoaiCongViec (String loaiCongViec) {this.loaiCongViec = loaiCongViec;}
    public void setCmnd (String cmnd) {this.cmnd = cmnd;}
    public int getSoNgayNghi () {return soNgayNghi;}
    public void setSoNgayNghi (int soNgayNghi) {this.soNgayNghi = soNgayNghi;}
    public int getNamVaoLam () {return namVaoLam;}

    public void setNamVaoLam (int namVaoLam) {this.namVaoLam = namVaoLam;}

    public double getHeSoLuong() {
        return heSoLuong;
    }
    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    abstract public char xepLoai();
    abstract public double tinhLuong();

    public NhanVien (){

    }

    public NhanVien (String hoTen, String loaiCongViec, String cmnd, int soNgayNghi, int namVaoLam, double heSoLuong){
        this.hoTen = hoTen;
        this.loaiCongViec = loaiCongViec;
        this.cmnd = cmnd;
        this.soNgayNghi = soNgayNghi;
        this.namVaoLam = namVaoLam;
        this.heSoLuong = heSoLuong;
    }

    public String toCSV() {
        return maNV + "," + hoTen + "," +loaiCongViec + "," + cmnd + "," + soNgayNghi + "," + namVaoLam + "," + heSoLuong;
    }
    
    public void xuatNhanVien(){
        System.out.println("Ma nhan vien: " + getMaNV() + " | Ho ten: " + getHoTen() + " | Chung minh nhan dan: " + getCmnd() + " | Loai cong viec: " + getLoaiCongViec() + " | So ngay nghi: " +  getSoNgayNghi() + " | Nam vao lam: " + getNamVaoLam());
    }
}