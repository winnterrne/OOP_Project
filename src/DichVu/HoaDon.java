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
        if (ngayXuatHoaDon == null || ngayXuatHoaDon.isEmpty()) return 1.0;

        try {
            java.time.LocalDate date = java.time.LocalDate.parse(ngayXuatHoaDon, 
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            java.time.DayOfWeek day = date.getDayOfWeek();

            if (day == DayOfWeek.TUESDAY) {
                return 0.85;
            }
        } catch (Exception e) {
        }
        return 1.0;
    }
    public double tongTienSauKhuyenMai() {
        return tinhTongTien() * apDungKhuyenMai();
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

    @Override
    public String toString() {
        return "Hóa đơn " + maHoaDon + " - Ngày: " + ngayXuatHoaDon + 
            " - Tổng: " + String.format("%,.0f", tongTienSauKhuyenMai()) + "đ";
    }

    public String toCSV() {
        return maHoaDon + "," + maVanChuyen + "," + ngayXuatHoaDon;
    }
}