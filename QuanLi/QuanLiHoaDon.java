package DichVu.QuanLi;

import DichVu.DonHang;
import DichVu.HoaDon;
import DichVu.SanPham;
import java.util.List;
import java.util.ArrayList;

public class QuanLiHoaDon extends QuanLiChung{
    private int tongSoHoaDon;
    List<HoaDon> dsHoaDon = new ArrayList<>();
    public int getTongSoHoaDon() {return this.tongSoHoaDon;}
    public void setTongSoHoaDon(int tongSoHoaDon) {this.tongSoHoaDon = tongSoHoaDon;}
    public QuanLiHoaDon() {}
    public double tinhDoanhThuTheoNgay(String ngay) {
        return dsHoaDon.stream()
                .filter(dh -> dh.getNgayLap().equals(ngay))
                .mapToDouble(HoaDon::tongTienSauKhuyenMai)
                .sum();
    }

    @Override
    public void them(Object obj) {
        if(obj instanceof HoaDon hd){
            dsHoaDon.add(hd);
        }
    }

    @Override
    public void xoa(String maHD) {
        dsHoaDon.removeIf(nv -> nv.getMaHoaDon().equalsIgnoreCase(maHD));
    }

    @Override
    public void sua(String maHD, Object newInfo) {
        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaHoaDon().equalsIgnoreCase(maHD) && newInfo instanceof HoaDon hoaDonMoi) {
                hd.setMaHoaDon(hoaDonMoi.getMaHoaDon());
                hd.setMaVanChuyen(hoaDonMoi.getMaVanChuyen());
//                nv.setLoaiCongViec(nvMoi.getLoaiCongViec());
            }
        }
    }

    @Override
    public Object timKiem(String maHD) {
        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaHoaDon().equalsIgnoreCase(maHD)) return hd;
        }
        return null;
    }
}
