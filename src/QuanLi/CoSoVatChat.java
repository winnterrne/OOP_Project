package QuanLi;

public class CoSoVatChat {
    private String tinhTrang, ngayBaoTriCuoi, maCSVC, loai;

    public CoSoVatChat() {}

    public CoSoVatChat(String maCSVC, String tinhTrang, String ngayBaoTriCuoi, String loai) {
        this.tinhTrang = tinhTrang;
        this.ngayBaoTriCuoi = ngayBaoTriCuoi;
        this.loai = loai;
        if (maCSVC.startsWith("BBG") || maCSVC.startsWith("DCA") ||
                maCSVC.startsWith("DCN")) this.maCSVC = maCSVC;
        else this.maCSVC = "";
    }

    public void baoTriThietBi() {
        if (this.tinhTrang.equalsIgnoreCase("Hu") || this.tinhTrang.equalsIgnoreCase("Hu hong nhe")) {
            System.out.println("Thiết bị cần bảo trì!");
            System.out.println("Ngày bảo trì cuối: " + this.ngayBaoTriCuoi);
        } else {
            System.out.println("Thiết bị ổn định!");
            System.out.println("Ngày bảo trì cuối: " + this.ngayBaoTriCuoi);
        }
    }

    public String getMaCSVC() {return maCSVC;}
    public void setMaCSVC(String maCSVC) {this.maCSVC = maCSVC;}

    public String getTinhTrang() {return tinhTrang;}
    public void setTinhTrang(String tinhTrang) {this.tinhTrang = tinhTrang;}

    public String getNgayBaoTriCuoi() {return ngayBaoTriCuoi;}
    public void setNgayBaoTriCuoi(String ngayBaoTriCuoi) {this.ngayBaoTriCuoi = ngayBaoTriCuoi;}

    public String getLoai () {return loai;}
    public void setLoai (String loai) {this.loai = loai;}

    @Override
    public String toString() {
        return String.format("Mã: %s | Loại: %s | Tình trạng: %s | Ngày bảo trì cuối: %s",
                maCSVC, loai, tinhTrang, ngayBaoTriCuoi);
    }
    public String toCSV() {
        return maCSVC + "," + loai + "," + tinhTrang + "," + ngayBaoTriCuoi;
    }



}