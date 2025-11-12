package DichVu;

public class HoaDonChuyenKhoan extends HoaDon implements ThanhToan{
    private String pt = " Chuyển Khoản ";
    public String getptThanhToan() {
        return pt;
    }
    public String toString() {
        return super.toString() + " Phương Thức Thanh Toán: " + pt;
    }
    public String toCSV() {
        return super.toCSV() + ", " + pt;
    }
}
