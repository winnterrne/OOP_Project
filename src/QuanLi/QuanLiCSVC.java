package QuanLi;

import java.io.*;
import java.util.*;

public class QuanLiCSVC extends QuanLiChung{
    private List<CoSoVatChat> dsCoSoVatChat = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private final String TEN_FILE = "src/cosovatchat.csv";

    public String timMaLonNhat (String prefix){
        int max = 0;
        String maMax = null;
        for (CoSoVatChat c : dsCoSoVatChat){
            String ma = c.getMaCSVC();
            if (ma.startsWith(prefix) && ma.length() > 3){
                try{
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max){
                        max = so;
                        maMax = ma;
                    }
                } catch (NumberFormatException e){

                }
            }
        }
        return maMax;
    }

    @Override
    public void them(Object obj) {
       if (!(obj instanceof CoSoVatChat csvc)) {
           System.out.println("Đối tượng truyền vào không phải CoSoVatChat!");
           return;
        }

       String ma = csvc.getMaCSVC();
       if (ma == null || ma.length() < 3) {
           System.out.println("Mã CSVC không hợp lệ!");
            return;
        }

        for (CoSoVatChat c : dsCoSoVatChat){
            if (c.getMaCSVC().equalsIgnoreCase(ma)){
                System.out.println("Mã này đã tồn tại");
                String prefix = ma.substring(0, 3).toUpperCase();
                String maLonNhat = timMaLonNhat(prefix);
                if (maLonNhat != null){
                    System.out.println("Mã lớn nhất hiện tại của loại " + prefix + " là: " + maLonNhat);
                }else System.out.println("Không tìm thấy mã nào cùng loại");
                System.out.println("Vui lòng nhập lại mã khác");
                return;
            }
        }

        dsCoSoVatChat.add(csvc);
        ghiFile(TEN_FILE);
        System.out.println("Thêm thành công!");
    }

    public void xoa (String id){
        boolean removed = dsCoSoVatChat.removeIf(kh -> kh.getMaCSVC().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Da xoa CSVC co ma: " + id);
            ghiFile(TEN_FILE); // ghi lại ngay sau khi xóa
        } else {
            System.out.println("Khong tim thay CSVC can xoa!");
        }

    }

    public void sua(String id, Object obj) {
        if (!(obj instanceof CoSoVatChat newInfo)) {
            System.out.println("Dữ liệu truyền vào không hợp lệ!");
            return;
        }

        CoSoVatChat csvc = timKiem(id);
        if (csvc == null) {
            System.out.println("Không tìm thấy mã CSVC này!");
            return;
        }

        csvc.setLoai(newInfo.getLoai());
        csvc.setTinhTrang(newInfo.getTinhTrang());
        csvc.setNgayBaoTriCuoi(newInfo.getNgayBaoTriCuoi());

        ghiFile(TEN_FILE);
        System.out.println("Đã cập nhật thông tin CSVC có mã " + id);
    }

    public CoSoVatChat timKiem (String id){
        for (CoSoVatChat c : dsCoSoVatChat){
            if (c.getMaCSVC().equalsIgnoreCase(id)){
                return c;
            }
        }
        return null;
    }

    public void sapXep() {
        dsCoSoVatChat.sort(Comparator.comparing(CoSoVatChat::getMaCSVC));
        System.out.println("Đã sắp xếp danh sách theo mã CSVC (A-Z)!");
        ghiFile(TEN_FILE);
    }

    public void hienThi (String prefix){
        String ten = "";
        int tongSoLuong = 0;
        if (prefix == null || prefix.isEmpty()) {
            System.out.println("Vui lòng chọn loại CSVC trước khi hiển thị!");
            return;
        }
        if (prefix.equals("BBG")) ten = "Bộ bàn ghế";
        else if (prefix.equals("DCA")) ten = "Dụng cụ ăn";
        else if (prefix.equals("DCN")) ten = "Dụng cụ nấu ăn";

        System.out.println("\n=====DANH SÁCH " + ten + "=====");
        boolean coDuLieu = false;

        for (CoSoVatChat c : dsCoSoVatChat) {
            if (c.getMaCSVC().startsWith(prefix)) {
                tongSoLuong++;
                coDuLieu = true;
                System.out.println("Mã: " + c.getMaCSVC() +
                        " | Tình trạng: " + c.getTinhTrang() +
                        " | Bảo trì: " + c.getNgayBaoTriCuoi());
            }
        }

        System.out.println("--------------------------------");
        System.out.println("TỔNG SỐ LƯỢNG " + ten + ": " + tongSoLuong + " cái");

        if (!coDuLieu) {
            System.out.println("Chưa có dữ liệu nào!");
        }
        System.out.println("================================");
    }

    

    public void menu (){
        int n;
        String prefix = "";
        docFile(TEN_FILE);

        do {
            System.out.println("\n=====CHỌN ĐỐI TƯỢNG QUẢN LÍ=====");
            System.out.println("1. Quản lí bộ bàn ghế");
            System.out.println("2. Quản lí dụng cụ ăn");
            System.out.println("3. Quan li dụng cụ nấu ăn");
            System.out.print("Mời nhập: ");
            n = sc.nextInt();
        }while (n < 1 || n > 3);
        sc.nextLine();

        switch (n) {
            case 1:
                prefix = "BBG";
                break;
            case 2:
                prefix = "DCA";
                break;
            case 3:
                prefix = "DCN";
                break;
            default:
                break;
        }
        int choice;
        do {
            System.out.println("\n=====QUẢN LÍ CƠ SỞ VẬT CHẤT=====");
            System.out.println("1. Thêm cơ sở vật chất");
            System.out.println("2. Xóa cơ sở vật chất");
            System.out.println("3. Sửa cơ sở vật chất");
            System.out.println("4. Tìm cơ sở vật chất");
            System.out.println("5. Sắp xếp thứ tự cơ sở vật chất");
            System.out.println("6. Hiển thị danh sách cơ sở vật chất");
            System.out.println("7. Kiểm tra bảo trì thiết bị ");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: //Thêm
                    CoSoVatChat csvc = new CoSoVatChat();
                    String ma;
                    while (true) {
                        System.out.print("Nhập mã CSVC (phải bắt đầu bằng " + prefix + ", ví dụ: " + prefix + "001): ");
                        ma = sc.nextLine().trim().toUpperCase();
                        
                        if (!ma.startsWith(prefix)) {
                            System.out.println("Lỗi: Mã phải bắt đầu bằng " + prefix + "!");
                            continue;
                        }
                        if (ma.length() < 4) {
                            System.out.println("Lỗi: Mã quá ngắn! Ví dụ đúng: " + prefix + "001");
                            continue;
                        }
                        if (!ma.substring(3).matches("\\d+")) {
                            System.out.println("Lỗi: Phần sau " + prefix + " phải là số! Ví dụ: " + prefix + "001");
                            continue;
                        }
                        if (timKiem(ma) != null) {
                            System.out.println("Lỗi: Mã " + ma + " đã tồn tại!");
                            continue;
                        }
                        break;
                    }
                    
                    csvc.setMaCSVC(ma);
                    System.out.print("Nhập loại CSVC: ");
                    csvc.setLoai(sc.nextLine());
                    System.out.print("Nhập tình trạng: ");
                    csvc.setTinhTrang(sc.nextLine());
                    System.out.print("Nhập ngày bảo trì cuối (dd/MM/yyyy): ");
                    csvc.setNgayBaoTriCuoi(sc.nextLine());
                    
                    them(csvc);
                    break;
                case 2: //Xóa
                    System.out.print("Nhập mã cần xóa: ");
                    xoa(sc.nextLine().toUpperCase());
                    break;
                case 3: //Sửa
                    System.out.print ("Nhập mã cần sửa: ");
                    String maSua = sc.nextLine().toUpperCase();
                    CoSoVatChat capNhat = new CoSoVatChat();
                    System.out.print("Nhập loại mới: ");
                    capNhat.setLoai(sc.nextLine());
                    System.out.print("Tình trạng mới: ");
                    capNhat.setTinhTrang(sc.nextLine());
                    System.out.print("Ngày bảo trì mới: ");
                    capNhat.setNgayBaoTriCuoi(sc.nextLine());
                    sua(maSua, capNhat);
                    break;
                case 4: //Tìm kiếm
                    System.out.print("Nhập mã cần tìm: ");
                    CoSoVatChat tim = timKiem(sc.nextLine().toUpperCase());
                    if (tim != null) System.out.println ("Tìm thấy: " + tim);
                    else System.out.println ("Không tìm thấy mã này");
                    break;
                case 5: //Sắp xếp
                    sapXep();
                    break;
                case 6: //Hiển thị
                    hienThi(prefix);
                    break;
                case 7:
                    System.out.println("--Danh Sách Thiết Bị Cần Bảo Trì--");
                    for (CoSoVatChat coSoVatChat : dsCoSoVatChat) {
                        System.out.println("* " + coSoVatChat.getMaCSVC() + " : " + coSoVatChat.getLoai());
                        coSoVatChat.baoTriThietBi();
                        System.out.println();
                    }
                    break;
                case 0:
                    System.out.println("---Thoát khỏi giao diện quản lý---");
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
            System.out.println("File khong ton tai: ");
            return;
        }
        dsCoSoVatChat.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(f))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String []p = line.split(",");
                if(p.length < 4) continue;
                String ma = p[0].trim();
                String loai = p[1].trim();
                String tt = p[2].trim();
                String bt = p[3].trim();
                CoSoVatChat csvt = new CoSoVatChat(ma,tt,bt,loai);
                dsCoSoVatChat.add(csvt);
            }
            System.out.println("Doc File Thanh Cong: " + dsCoSoVatChat.size());
        }catch (Exception e) {
            System.out.println("Loi Doc File: " + e.getMessage());
        }
    }

    @Override
    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            bw.write("MaCSVC,Loai,TinhTrang,NgayBaoTriCuoi");
            bw.newLine();
            for(CoSoVatChat csvt : dsCoSoVatChat) {
                bw.write(csvt.toCSV());
                bw.newLine();
            }
        }catch (Exception e) {
            System.out.println("Loi Ghi File Khong Thanh Cong ");
        }
    }
}