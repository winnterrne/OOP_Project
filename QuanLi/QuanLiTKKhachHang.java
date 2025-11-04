package QuanLi;

import java.util.ArrayList;

import DichVu.KhachHang;

public class QuanLiTKKhachHang extends QuanLiChung {
    private String ngayTaoTK;
    private ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

    public void themKH(KhachHang kh) {
        dsKhachHang.add(kh);
        System.out.println("Đã thêm khách hàng: " + kh.getTenKH());
    }

    public KhachHang timTheoMa (String maKH){
        for (KhachHang kh : dsKhachHang){
            if (kh.getMaKH().equalsIgnoreCase(maKH)) return kh;
        }
        return null;
    }

    public boolean xoaKhachHang (String maKH){
        KhachHang kh = timTheoMa(maKH);
        if (kh != null){
            dsKhachHang.remove(kh);
            System.out.println("Đã xóa khách hàng: " + kh.getTenKH());
            return true;
        }
        return false;
    }

    public boolean capNhatKhachHang (String maKH, String tenMoi, int sdtMoi, String diaChiMoi){
        KhachHang kh = timTheoMa(maKH);
        if (kh != null){
            kh.setTenKH(tenMoi);
            kh.setDiaChi(diaChiMoi);
            kh.setSoDienThoai(sdtMoi);
            System.out.println("Cập nhật thành công khách hàng: " + maKH);
            return true;
        }
        return false;
    }
}
