package NhanSu;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;

public class NhanVienBanHang extends NhanVien {
    private double doanhThu, doanhThuToiThieu;

    public double getDoanhThu () {return doanhThu;}
    public void setDoanhThu (double doanhThu) {this.doanhThu = doanhThu;}
    public double getDoanhThuToiThieu () {return doanhThuToiThieu;}
    public void setDoanhThuToiThieu (double doanhThuToiThieu) {this.doanhThuToiThieu = doanhThuToiThieu;}

    public NhanVienBanHang (String maNV, String hoTen, String loaiCongViec, String cmnd, 
                       int soNgayNghi, int thamNien, double doanhThu, double doanhThuToiThieu, double heSoLuong){
        super(hoTen, loaiCongViec, cmnd, soNgayNghi, thamNien, heSoLuong);
        this.maNV = maNV;
        this.doanhThu = doanhThu;
        this.doanhThuToiThieu = doanhThuToiThieu;
    }

    @Override
    public char xepLoai(){
        double doanhSo = doanhThu/doanhThuToiThieu;
        char rank = 'D';
        if (doanhSo >= 2.0) return rank = 'A';
        if (doanhSo >= 1.0) return rank = 'B';
        if (doanhSo >= 0.5) return rank = 'C';
        return rank;
    }

    @Override
    public double tinhLuong (){
        if (doanhThu > doanhThuToiThieu) return heSoLuong * luongCoBan + 0.15*(doanhThu - doanhThuToiThieu);
        return heSoLuong * luongCoBan;
    }

    @Override
    public double phuCapThamNien (){
        int thamNien = LocalDate.now().getYear() - namVaoLam;
        if (thamNien >= 3) return 0.1*tinhLuong();
        else return 0;
    }

    public String toCSV() {
        return "NhanVienBanHang, " + super.toCSV() + "," + doanhThu + "," + doanhThuToiThieu;
    }

    @Override
    public void xuatNhanVien() {
        super.xuatNhanVien();
        System.out.println("Doanh Thu: " + getDoanhThu());
        System.out.println("Doanh Thu Toi Thieu: " + getDoanhThuToiThieu());
    }

    @Override
    public void xuatLuongNhanVien() {
        super.xuatLuongNhanVien();
        System.out.println("Luong: " + tinhLuong());
    }

    @Override
    public void xuatNhanVienTheoXepLoai() {
        super.xuatNhanVienTheoXepLoai();
        System.out.println("Loai: " + xepLoai());
    }
}
