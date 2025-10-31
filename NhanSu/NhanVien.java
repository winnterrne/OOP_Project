package NhanSu;

abstract public class NhanVien implements PhuCapThamNien {
    protected String maNV, hoTen, loaiCongViec, cmnd;
    protected int soNgayNghi, namVaoLam;
    protected double heSoLuong;
    protected static final int luongCoBan = 4000000;

    public String getMaNV () {return maNV;}
    public String getHoTen () {return hoTen;}
    public String getLoaiCongViec () {return loaiCongViec;}
    public String cmnd () {return cmnd;}
    public int getSoNgayNghi () {return soNgayNghi;}
    public int getNamVaoLam () {return namVaoLam;}

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
    
    public void xuatNhanVien(){
        System.out.println("Ma nhan vien: " + maNV + " | Ho ten: " + hoTen + " | Chung minh nhan dan: " + cmnd + " | Loai cong viec: " + loaiCongViec + " | So ngay nghi: " +  soNgayNghi + " | Nam vao lam: " + namVaoLam);
    }
}