package DichVu.QuanLi;

import DichVu.DonHang;

import java.util.ArrayList;
import java.util.List;

public class QuanLiDonHang extends QuanLiChung {
    private int tongSoDH;
    List<DichVu.DonHang> dsDonHang = new ArrayList<>();

    @Override
    public void Them(Object obj) {
        if (obj instanceof DonHang dh) {
            dsDonHang.add(dh);
            tongSoDH++;
        }
    }

    @Override
    public void Xoa(String maDH) {
        dsDonHang.removeIf(dh -> dh.getMaDH().equalsIgnoreCase(maDH));
    }

    @Override
    public void Sua(String maDH, Object newInfo) {
        for (DonHang dh : dsDonHang) {
            if (dh.getMaDH().equalsIgnoreCase(maDH) && newInfo instanceof DonHang dhMoi) {
                dh.setTenSanPham(dhMoi.getTenSanPham());
                dh.setMaSanPham(dhMoi.getMaSanPham() );
                dh.setSoLuong(dhMoi.getSoLuong() );
            }
        }
    }

    @Override
    public Object TimKiem(String maDH) {
        for (DonHang dh : dsDonHang) {
            if (dh.getMaDH().equalsIgnoreCase(maDH)) return dh;
        }
        return null;
    }

    public double tinhDoanhThuTheoNgay(String ngay) {
        return dsDonHang.stream()
                .filter(dh -> dh.getNgayLap().equals(ngay))
                .mapToDouble(DonHang::getTongTien)
                .sum();
    }
}
