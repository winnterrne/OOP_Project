package DichVu.QuanLi;

import DichVu.DonHang;

import java.util.ArrayList;
import java.util.List;

public class QuanLiCSVC extends QuanLiChung{
    private int soLuongTBHu;
    List<CoSoVatChat> dsCoSoVatChat =  new ArrayList<>();
    public void kiemTraTinhTrang(){
        for(CoSoVatChat csvc : dsCoSoVatChat){
            System.out.println(csvc.tinhTrang);
        }
    }
    public QuanLiCSVC(int soLuongTBHu) {
        this.soLuongTBHu = soLuongTBHu;
    }
    public QuanLiCSVC() {
        this.soLuongTBHu = 0;
    }
    @Override
    public void them(Object obj) {
        if (obj instanceof CoSoVatChat csvc) {
            dsCoSoVatChat.add(csvc);
//            tongSoDH++;
        }
    }

//    @Override
//    public void xoa(int index) {
//        dsCoSoVatChat.removeIf(csvc -> csvc.getMaDH().equalsIgnoreCase(maDH));
//    }

    @Override
    public void sua(String maDH, Object newInfo) {
        for (DonHang dh : dsDonHang) {
            if (dh.getMaDH().equalsIgnoreCase(maDH) && newInfo instanceof DonHang dhMoi) {
                dh.setNgayLap(dhMoi.getNgayLap());
                dh.setTongTien(dhMoi.getTongTien());
            }
        }
    }

//    @Override
//    public Object timKiem(String maDH) {
//        for (DonHang dh : dsDonHang) {
//            if (dh.getMaDH().equalsIgnoreCase(maDH)) return dh;
//        }
//        return null;
//    }
}
