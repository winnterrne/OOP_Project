package NhanSu;

import java.time.LocalDate;

public class QuanLi extends NhanVien {
    private String phongBan, chiNhanhQL;
    private double phuCapQL;

    public QuanLi (String maNV, String hoTen, String loaiCongViec, String cmnd, int soNgayNghi, int namVaoLam, double heSoLuong, String phongBan, String chiNhanhQL, double phuCapQL){
        super(hoTen, loaiCongViec, cmnd, soNgayNghi, namVaoLam, heSoLuong);
        if (maNV.startsWith("QL")) this.maNV = maNV;
        else maNV = "QL" + maNV.substring(2);
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
        System.out.println("Phong ban: " + phongBan + " | Chi nhanh quan ly: " + chiNhanhQL + " | He so phu cap Quan ly: " + phuCapQL);
    }

    @Override
    public double phuCapThamNien() {
        int namLamViec = LocalDate.now().getYear() - getNamVaoLam();
        if (namLamViec >= 5) return 0.15 * tinhLuong();
        else if (namLamViec >= 3) return 0.1 * tinhLuong();
        else return 0;
    }

    public String danhGiaNhanVien (NhanVien nv){
        String ketqua = "";
        if (nv instanceof NhanVienBanHang){
            NhanVienBanHang bh = (NhanVienBanHang) nv;
            if (bh.getDoanhThu() >= bh.getDoanhThuToiThieu()) ketqua = "Hoan thanh tot doanh thu";
            else ketqua = "Chua dat doanh thu yeu cau";
        }else if (nv instanceof NhanVienDungBep){
            NhanVienDungBep bep = (NhanVienDungBep) nv;
            if (bep.getSoLuongOrder() >= 100) ketqua = "Nhan vien bep nang suat cao";
            else ketqua = "Can cai thien nang suat che bien";
        }else ketqua = "Nhan vien chua co du lieu danh gia";
        return "Danh gia cua quan ly: " + ketqua;
    }
}
