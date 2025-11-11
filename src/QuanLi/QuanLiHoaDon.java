package QuanLi;

import DichVu.DonHang;
import DichVu.HoaDon;
import DichVu.SanPham;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class QuanLiHoaDon extends QuanLiChung{
    private int tongSoHoaDon;
    List<HoaDon> dsHoaDon = new ArrayList<>();
    public int getTongSoHoaDon() {return this.tongSoHoaDon;}
    public void setTongSoHoaDon(int tongSoHoaDon) {this.tongSoHoaDon = tongSoHoaDon;}
    private Scanner sc = new Scanner(System.in);
    private final String TEN_FILE = "src/hoadon.csv";
    private QuanLiSanPham qlsp;

    public QuanLiHoaDon() {
        qlsp = new QuanLiSanPham();
        qlsp.docFile("src/sanpham.csv");
    }

    public double tinhDoanhThuTheoNgay(String ngay) {
        return dsHoaDon.stream()
                .filter(dh -> dh.getNgayXuatHoaDon().equals(ngay))
                .mapToDouble(HoaDon::apDungKhuyenMai)
                .sum();
    }

    @Override
    public void them(Object obj) {
        if (!(obj instanceof HoaDon hd)) {
            System.out.println("Đối tượng truyền vào không phải hóa đơn!");
            return;
        }

        if (timKiem(hd.getMaHoaDon()) != null) {
            System.out.println("Hóa đơn đã tồn tại, không thể thêm trùng!");
            return;
        }

        dsHoaDon.add(hd);
        tongSoHoaDon++;
        System.out.println("Thêm hóa đơn thành công! Mã: " + hd.getMaHoaDon());
    }


    @Override
    public void xoa(String maHD) {
        boolean removed = dsHoaDon.removeIf(hd -> hd.getMaHoaDon().equalsIgnoreCase(maHD));
        if (removed) {
            tongSoHoaDon--;
            System.out.println("Đã xóa hóa đơn " + maHD);
        } else {
            System.out.println("Không tìm thấy hóa đơn có mã " + maHD);
        }
    }

    @Override
    public void sua(String maHD, Object newInfo) {
        if (!(newInfo instanceof HoaDon hoaDonMoi)) {
            System.out.println("Dữ liệu mới không hợp lệ!");
            return;
        }

        HoaDon hd = (HoaDon) timKiem(maHD);
        if (hd == null) {
            System.out.println("Không tìm thấy hóa đơn có mã " + maHD);
            return;
        }

        hd.setMaVanChuyen(hoaDonMoi.getMaVanChuyen());
        hd.setNgayXuatHoaDon(hoaDonMoi.getNgayXuatHoaDon());
        System.out.println("Đã cập nhật thông tin hóa đơn " + maHD);
    }


    @Override
    public Object timKiem(String maHD) {
        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaHoaDon().equalsIgnoreCase(maHD)) return hd;
        }
        return null;
    }

    public void sapXepTheoMaHD (){
        dsHoaDon.sort(Comparator.comparing(HoaDon::getMaHoaDon));
        System.out.println("Đã sắp xếp hóa đơn theo mã");
    }

    public void hienThi() {
        System.out.println("\n===== DANH SÁCH HÓA ĐƠN =====");
        dsHoaDon.forEach(HoaDon::inHoaDon);
        System.out.println("==============================");
    }


    public void menu (){
        int choice;
        docFile(TEN_FILE);
        do{
            System.out.println("\n=====QUẢN LÍ HÓA ĐƠN=====");
            System.out.println("1.Thêm hóa đơn");
            System.out.println("2.Xóa hóa đơn");
            System.out.println("3.Sửa hóa đơn");
            System.out.println("4.Tìm kiếm hóa đơn");
            System.out.println("5.Sắp xếp hóa đơn");
            System.out.println("6.Hiển thị hóa đơn");
            System.out.println("7. Thêm đơn hàng vào hóa đơn");
            System.out.println("8. Xem doanh thu theo ngày");
            System.out.println("0.Thoát");
            System.out.print("Mời chọn: ");
            choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1: //Thêm
                    HoaDon hd = new HoaDon();
                    System.out.print("Nhập mã hóa đơn: ");
                    hd.setMaHoaDon(sc.nextLine().trim());
                    System.out.print("Nhập mã vận chuyển: ");
                    hd.setMaVanChuyen(sc.nextLine());
                    System.out.print("Nhập ngày xuất hóa đơn (dd/MM/yyyy, để trống để dùng ngày hiện tại): ");
                    String ngayXuat = sc.nextLine().trim();
                    if (ngayXuat.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        ngayXuat = sdf.format(new Date()); // Gán ngày hiện tại: 10/11/2025
                    }
                    hd.setNgayXuatHoaDon(ngayXuat);
                    them(hd);
                    ghiFile(TEN_FILE);
                    break;

                case 2: //Xóa
                    System.out.print("Nhập mã cần xóa: ");
                    String maXoa = sc.nextLine();
                    xoa(maXoa);
                    ghiFile(TEN_FILE);
                    break;

                case 3: //Sửa
                    HoaDon hdmoi = new HoaDon();
                    System.out.print("Nhập mã cần sửa: ");
                    String maSua = sc.nextLine();
                    System.out.print("Nhập mã vận chuyển mới: ");
                    hdmoi.setMaVanChuyen(sc.nextLine().trim());
                    System.out.print("Nhập ngày xuất hóa đơn mới: ");
                    hdmoi.setNgayXuatHoaDon(sc.nextLine().trim());
                    sua(maSua, hdmoi);
                    ghiFile(TEN_FILE);
                    break;

                case 4: //Tìm kiếm
                    System.out.print("Nhập mã cần tìm: ");
                    String maTim = sc.nextLine();
                    HoaDon tim = (HoaDon) timKiem(maTim);
                    if (tim != null) tim.inHoaDon();
                    else System.out.println("Không tìm thấy hóa đơn có mã " + maTim);
                    break;

                case 5: //Sắp xếp
                    sapXepTheoMaHD();
                    ghiFile(TEN_FILE);
                    break;

                case 6: //Hiển thị
                    hienThi();
                    break;

                case 7: // Thêm đơn hàng vào hóa đơn
                    System.out.print("Nhập mã hóa đơn: ");
                    String maHD = sc.nextLine().trim();
                    HoaDon hdon = (HoaDon) timKiem(maHD);
                    if (hdon == null) {
                        System.out.println("Không tìm thấy hóa đơn có mã " + maHD);
                        break;
                    }
                    System.out.print("Nhập mã đơn hàng: ");
                    String maDH = sc.nextLine().trim();
                    System.out.print("Nhập mã sản phẩm: ");
                    String maSP = sc.nextLine().trim();
                    System.out.print("Nhập số lượng: ");
                    int soLuong = Integer.parseInt(sc.nextLine().trim());

                    SanPham sp = null;
                    for (SanPham s : qlsp.dsSanPham) {
                        if (s.getMaSanPham().equalsIgnoreCase(maSP)) {
                            sp = s;
                            break;
                        }
                    }
                    if (sp == null) {
                        System.out.println("Không tìm thấy sản phẩm có mã: " + maSP);
                        break;
                    }
                    DonHang dh = new DonHang(sp, soLuong, maDH);
                    hdon.themDonHang(dh);
                    ghiFile(TEN_FILE);
                    System.out.println("Thêm đơn hàng thành công!");
                    break;

                case 8: // Xem doanh thu theo ngày
                    System.out.print("Nhập ngày cần xem doanh thu (dd/MM/yyyy): ");
                    String ngay = sc.nextLine().trim();
                    double doanhThu = tinhDoanhThuTheoNgay(ngay);
                    System.out.println("Doanh thu ngày " + ngay + ": " + doanhThu + " VND");
                    break;
                    case 0:
                        System.out.println("Thoát khỏi menu quản lý");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                        break;
                }
        }while (choice != 0);
    }

    @Override
    public void docFile(String tenFile) {
        File f = new File(tenFile);
        if(!f.exists()) {
            System.out.println("File Khong Ton Tai");
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            br.readLine();
            dsHoaDon.clear();

            while((line = br.readLine()) != null) {
                String [] p = line.split(",");
                if(p.length < 3) continue;;

                String mahd = p[0].trim();
                String mavc = p[1].trim();
                String nxhd = p[2].trim();

                HoaDon hd = null;
                for (HoaDon hdon : dsHoaDon){
                    if (hdon.getMaHoaDon().equalsIgnoreCase(mahd)){
                        hd = hdon;
                        break;
                    }
                }
                
                if (hd == null) {
                    hd = new HoaDon(mahd, mavc, nxhd);
                    dsHoaDon.add(hd);
                    tongSoHoaDon++;
                }
                if (p.length >= 6 && !p[3].isEmpty() && !p[4].isEmpty() && !p[5].isEmpty()) {
                    String maDH = p[3].trim();
                    String maSP = p[4].trim();
                    int soLuong;
                    try {
                        soLuong = Integer.parseInt(p[5].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Số lượng không hợp lệ: " + line);
                        continue;
                    }

                    SanPham sp = null;
                    for (SanPham s : qlsp.dsSanPham){
                        if (s.getMaSanPham().equalsIgnoreCase(maSP)){
                            sp = s;
                            break;
                        }
                    }
                    if (sp == null) {
                        System.out.println("Không tìm thấy sản phẩm có mã: " + maSP);
                        continue;
                    }

                    DonHang dh = new DonHang(sp, soLuong, maDH);
                    hd.themDonHang(dh);
                }
            }
            tongSoHoaDon = dsHoaDon.size();
            System.out.println("Doc File Thanh Cong " + dsHoaDon.size() + "Hoa Don ");

        }catch (Exception e) {
            System.out.println("Loi Doc File" + e.getMessage());
        }
    }
    public void docFileDonHang(String tenFile) {
        File f = new File(tenFile);
        if (!f.exists()) {
            System.out.println("Lỗi Đọc File");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            br.readLine(); // Bỏ qua dòng tiêu đề

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 4) continue;

                String maDH = p[0].trim();
                String maHD = p[1].trim();
                String maSP = p[2].trim();
                int soLuong;
                try {
                    soLuong = Integer.parseInt(p[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Số lượng không hợp lệ: " + line);
                    continue;
                }

                SanPham sp = null;
                for (SanPham s : qlsp.dsSanPham){
                    if (s.getMaSanPham().equalsIgnoreCase(maSP)){
                        sp = s;
                        break;
                    }
                }
                if (sp == null) {
                    System.out.println("Không tìm thấy sản phẩm có mã: " + maSP);
                    continue;
                }

                DonHang dh = new DonHang(sp, soLuong, maDH);

                HoaDon hd = null;
                for (HoaDon hdon : dsHoaDon){
                    if (hdon.getMaHoaDon().equalsIgnoreCase(maHD)){
                        hd = hdon;
                        break;
                    }
                }
                if (hd == null) {
                    hd = new HoaDon();
                    hd.setMaHoaDon(maHD);
                    hd.setMaVanChuyen("VC001"); // Giá trị mặc định
                    hd.setNgayXuatHoaDon(new SimpleDateFormat("dd/MM/yyyy").format(new Date())); // Ngày hiện tại: 10/11/2025
                    dsHoaDon.add(hd);
                    tongSoHoaDon++;
                }
                hd.themDonHang(dh);
            }
            System.out.println("Đọc File Thành Công: " + tenFile);
        } catch (Exception e) {
            System.out.println("Đọc file không thành công: " + e.getMessage());
        }
    }


    @Override
    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {

            bw.write("MaHD,MaVC,NgayXuat,MaDH,MaSP,SoLuong");
            bw.newLine();

            for (HoaDon hd : dsHoaDon) {
                List<DonHang> dsDonHang = hd.getDsDonHang();
                if (dsDonHang.isEmpty()){
                    bw.write(hd.getMaHoaDon() + "," + hd.getMaVanChuyen() + "," + hd.getNgayXuatHoaDon() + ",,,");
                    bw.newLine();
                }else{
                    for (DonHang dh : dsDonHang){
                        bw.write(hd.getMaHoaDon() + "," +
                            hd.getMaVanChuyen() + "," +
                            hd.getNgayXuatHoaDon() + "," +
                            dh.getMaDonHang() + "," +
                            dh.getSp().getMaSanPham() + "," +
                            dh.getSoLuong());
                        bw.newLine();
                    }
                }
            }

            System.out.println("Ghi hoa don thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi ghi file hóa đơn: " + e.getMessage());
        }
    }

}
