package DichVu;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class HoaDon implements KhuyenMai{
    private String maHoaDon;
    private String maVanChuyen;
    private String ngayXuatHoaDon;
    private List<DonHang> dsDonHang = new ArrayList<>();

    public HoaDon() {

    }
    public HoaDon(String maHoaDon, String maVanChuyen, String ngayXuatHoaDon) {
        this.maHoaDon = maHoaDon;
        this.maVanChuyen = maVanChuyen;
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }

    public void themDonHang(DonHang donHang) {
        dsDonHang.add(donHang);
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public String getMaVanChuyen() {
        return maVanChuyen;
    }
    public void setMaVanChuyen(String maVanChuyen) {
        this.maVanChuyen = maVanChuyen;
    }
    public String getNgayXuatHoaDon() {
        return ngayXuatHoaDon;
    }
    public void setNgayXuatHoaDon(String ngayXuatHoaDon) {
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }
    public double tinhTongTien() {
        double sum = 0;
        for(DonHang donHang : dsDonHang) {
            sum += donHang.tinhTien();
        }
        return sum;
    }

    @Override
    public double apDungKhuyenMai() {
        double giaGoc = 0;
        java.time.DayOfWeek ngay = java.time.LocalDate.now().getDayOfWeek();
        if(ngay == DayOfWeek.TUESDAY) {
            return giaGoc * 0.85;
        }
        return giaGoc;
    }
    public double tongTienSauKhuyenMai() {
        return apDungKhuyenMai() * tinhTongTien();
    }
    public void inHoaDon() {
        System.out.println("===== HÓA ĐƠN =====");
        for (DonHang dh : dsDonHang) {
            dh.xuat();
        }
        System.out.println("Tổng cộng: " + tinhTongTien());
        System.out.println("===================");
        System.out.println();
        System.out.println("Tong Hoa Don: " + tongTienSauKhuyenMai());
        System.out.println("===================");
    }

    public List<DonHang> getDsDonHang() {
        return dsDonHang;
    }
}