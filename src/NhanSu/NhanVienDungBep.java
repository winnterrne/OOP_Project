package NhanSu;

import java.time.LocalDate;

public class NhanVienDungBep extends NhanVien {
    private int soLuongOrder;
    private String quayPhuTrach;

    public int getSoLuongOrder () {return soLuongOrder;}
    public void setSoLuongOrder (int soLuongOrder) {this.soLuongOrder = soLuongOrder;}
    public String getQuayPhuTrach () {return quayPhuTrach;}
    public void setQuayPhuTrach (String quayPhuTrach) {this.quayPhuTrach = quayPhuTrach;}

    public NhanVienDungBep (String maNV, String hoTen, String loaiCongViec, String cmnd, int soNgayNghi, int namVaoLam, double heSoLuong, int soLuongOrder, String quayPhuTrach){
        super(hoTen, loaiCongViec, cmnd, soNgayNghi, namVaoLam, heSoLuong);
        if (maNV.startsWith("DB")) this.maNV = maNV;
        else this.maNV = "DB" + maNV.substring(2);
        this.soLuongOrder = soLuongOrder;
        this.quayPhuTrach = quayPhuTrach;
    }

    @Override
    public char xepLoai (){
        char rank = 'D';
        if (soNgayNghi <= 1) return rank = 'A';
        if (soNgayNghi <= 3) return rank = 'B';
        if (soNgayNghi <= 5) return rank = 'C';
        return rank;
    }

    @Override
    public double tinhLuong (){
        return heSoLuong * luongCoBan;
    }

    @Override
    public void xuatNhanVien(){
        super.xuatNhanVien();
        System.out.println("Quay phu trach: " + quayPhuTrach + " | So luong order: " + soLuongOrder);
    }

    @Override
    public double phuCapThamNien (){
        int thamNien = LocalDate.now().getYear() - namVaoLam;
        if (thamNien >= 3) return 0.1*tinhLuong();
        else return 0;
    }
    public String toCSV() {
        return "NhanVienDungBep: " + super.toCSV() + "," + soLuongOrder + "," + quayPhuTrach;
    }
}