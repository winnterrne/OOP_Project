package NhanSu;

import java.time.LocalDate;

public class QuanLi extends NhanVien {
    private String phongBan, chiNhanhQL;
    private double phuCapQL;

    public String getPhongBan () {return phongBan;}
    public void setPhongBan (String phongBan) {this.phongBan = phongBan;}
    public String getChiNhanhQL () {return chiNhanhQL;}
    public void setChiNhanhQL (String chiNhanhQL) {this.chiNhanhQL = chiNhanhQL;}
    public double getPhuCapQL () {return phuCapQL;}
    public void setPhuCapQL (double phuCapQL) {this.phuCapQL = phuCapQL;}

    public QuanLi() {

    }

    public QuanLi (String maNV, String hoTen, String loaiCongViec, String cmnd, int soNgayNghi, int namVaoLam, double heSoLuong, String phongBan, String chiNhanhQL, double phuCapQL){
        super(hoTen, loaiCongViec, cmnd, soNgayNghi, namVaoLam, heSoLuong);
        this.maNV = maNV;
        this.phongBan = phongBan;
        this.chiNhanhQL = chiNhanhQL;
        this.phuCapQL = phuCapQL;
    }

    @Override
    public char xepLoai (){
        return 'A';
    }

    @Override
    public double tinhLuong (){
        return heSoLuong * luongCoBan + phuCapQL * 1000000;
    }

    @Override
    public void xuatNhanVien (){
        super.xuatNhanVien();
        System.out.println("Phong ban: " + getPhongBan() + " | Chi nhanh quan ly: " + getChiNhanhQL() + " | He so phu cap Quan ly: " + getPhuCapQL());
    }

    @Override
    public double phuCapThamNien() {
        int namLamViec = LocalDate.now().getYear() - getNamVaoLam();
        if (namLamViec >= 5) return 0.15 * tinhLuong();
        else if (namLamViec >= 3) return 0.1 * tinhLuong();
        else return 0;
    }

    public void danhGiaNhanVien (NhanVien nv){
        String ketqua;
        if (nv instanceof NhanVienBanHang bh){
            if (bh.getDoanhThu() >= bh.getDoanhThuToiThieu()) ketqua = bh.getMaNV() + " | " + bh.getHoTen() + " : "  + " Hoàn thành tốt doanh thu ";
            else ketqua = "Chưa đạt doanh thu ";
        }else if (nv instanceof NhanVienDungBep bep){
            if (bep.getSoLuongOrder() >= 100) ketqua = bep.getMaNV() + " | " + bep.getHoTen() + " : " + "Nhân viên bếp năng xuất cao ";
            else ketqua = "Cần cãi thiện năng xuất chế biến ";
        }else ketqua = "Nhân viên chưa có dữ liệu đánh giá ";
        System.out.println("Đánh giá của quản lí: " + ketqua);
    }
    public String toCSV() {
        return "QuanLi, " + super.toCSV() + "," + phongBan + "," + chiNhanhQL + "," +phuCapQL;
    }

    @Override
    public void xuatLuongNhanVien() {
        super.xuatLuongNhanVien();
        System.out.println("Lương: " + tinhLuong());
    }

    @Override
    public void xuatNhanVienTheoXepLoai() {
        super.xuatNhanVienTheoXepLoai();
        System.out.println("Xếp loại: " + xepLoai());
    }
}
