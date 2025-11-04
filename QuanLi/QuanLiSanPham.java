package DichVu.QuanLi;

import DichVu.SanPham;
import NhanSu.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class QuanLiSanPham extends QuanLiChung {
    private int tongSoSP;
    private String hanSD;
    List<SanPham> dsSanPham =  new ArrayList<>();
    public double tinhGiaTriTonKho() {
        double tong = 0;
        for (SanPham sp : dsSanPham) {
            tong += sp.getGiaThanh() * sp.getSoLuong(); // giá × số lượng
        }
        return tong;
    }

    @Override
    public void them(Object obj) {
        if(obj instanceof SanPham sp){
            dsSanPham.add(sp);
            System.out.println("Them thanh cong");
        }
    }

    @Override
    public void sua(String id, Object newInfo) {
        for (SanPham sp : dsSanPham) {
            if(sp.getMaSanPham().equalsIgnoreCase(sp.getMaSanPham()) && newInfo instanceof SanPham spMoi){
                sp.setMaSP(spMoi.getMaSanPham();
                sp.setTenSP(spMoi.getTenSanPham();
                sp.setGiaThanh();spMoi.getGiaThanh();
            }
        }
    }

    @Override
    public void xoa(String maSanPham) {
        dsSanPham.removeIf(sp   -> sp.getMaSanPham().equalsIgnoreCase(maSanPham));
    }

    @Override
    public Object timKiem(String maSanPham) {
        for (SanPham sp : dsSanPham) {
            if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) return sp;
        }
        return null;
    }
}
