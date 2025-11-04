package DichVu.QuanLi;

import DichVu.KhachHang;

import java.util.ArrayList;
import java.util.List;
//import java.

public class QuanLi_TK_KhachHang extends QuanLiChung {
    private String maKH, tenKH, ngayTaoTK;
    List<KhachHang> dsKhachHang = new ArrayList<>();
    public QuanLi_TK_KhachHang() {
        this.maKH = "2B";
        this.tenKH = "NULL";
//        this.sinhNhat = "01/01/0001";
        this.ngayTaoTK = "";
    }

    @Override
    public void them(Object obj) {
        if(obj instanceof KhachHang kh){
            dsKhachHang.add(kh);
        }
    }

    @Override
    public void xoa(String maKH) {
        for(KhachHang kh : dsKhachHang){
            if(kh.getMaKH().equals(maKH)){
                dsKhachHang.remove(kh);
            }
        }
    }

    @Override
    public void sua(String maKH, Object newInfo) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKH().equalsIgnoreCase(maKH) && newInfo instanceof KhachHang khMoi) {
                kh.setMaKH(khMoi.getMaKH());
                kh.setTenKH(khMoi.getTenKH());
                kh.setDiaChi(khMoi.getDiaChi());
                kh.setSoDienThoai(khMoi.getSoDienThoai());
            }
        }
    }

    @Override
    public Object timKiem(String maKH) {
        for (KhachHang khachHang : dsKhachHang) {
            if (khachHang.getMaKH().equalsIgnoreCase(maKH)) return khachHang;
        }
        return null;
    }
}
