package DichVu;

public class HoaDonTienMat extends HoaDon implements ThanhToan{
    private String pt = " Tiền Mặt ";
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
