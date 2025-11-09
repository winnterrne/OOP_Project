package QuanLi;

import java.io.*;
import java.util.*;

public class QuanLiCSVC extends QuanLiChung{
    private List<CoSoVatChat> dsCoSoVatChat = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private int soLuongBBG, soLuongDCA, soLuongDCN;

    public void them (Object obj){
        if (!(obj instanceof CoSoVatChat csvc)) return;
        String prefix = csvc.getMaCSVC().substring(0,3).toUpperCase();
        String maMoi = "";

        if (prefix.equals("BBG")){
            soLuongBBG++;
            maMoi = "BBG" + String.format("%03d", soLuongBBG);
        }else if (prefix.equals("DCA")){
            soLuongDCA++;
            maMoi = "DCA" + String.format ("%03d", soLuongDCA);
        }else if (prefix.equals("DCN")){
            soLuongDCN++;
            maMoi = "DCN" + String.format ("%03d", soLuongDCN);
        }else {
            System.out.println("Mã phải bắt đầu bằng BBG, DCA hoặc DCN");
            return;
        }
        csvc.setMaCSVC(maMoi);
        dsCoSoVatChat.add(csvc);
        System.out.println("Thêm thành công, Mã: " + maMoi);
    }

    public void xoa (String id){
        dsCoSoVatChat.removeIf(csvc -> csvc.getMaCSVC().equalsIgnoreCase(id));
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

        csvc.setTinhTrang(newInfo.getTinhTrang());
        csvc.setNgayBaoTriCuoi(newInfo.getNgayBaoTriCuoi());

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
    }

    public void hienThi (String prefix){
        String ten = "";
        int tongSoLuong = 0;
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
        do {
            System.out.println("\n=====CHỌN ĐỐI TƯỢNG QUẢN LÍ=====");
            System.out.println("1. Quản lí bộ bàn ghế");
            System.out.println("2. Quản lí dụng cụ ăn");
            System.out.println("3. Quan li dụng cụ nấu ăn");
            System.out.print("Mời nhập: ");
            n = sc.nextInt();
        }while (n < 1 || n > 3);
        sc.nextLine();

        int choice;
        do {
            System.out.println("\n=====QUẢN LÍ CƠ SỞ VẬT CHẤT=====");
            System.out.println("1. Thêm cơ sở vật chất");
            System.out.println("2. Xóa cơ sở vật chất");
            System.out.println("3. Sửa cơ sở vật chất");
            System.out.println("4. Tìm cơ sở vật chất");
            System.out.println("5. Sắp xếp thứ tự cơ sở vật chất");
            System.out.println("6. Hiển thị danh sách cơ sở vật chất");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: //Thêm
                    CoSoVatChat csvc = new CoSoVatChat();
                    System.out.print("Nhập tình trạng: ");
                    csvc.setTinhTrang(sc.nextLine());
                    System.out.print("Nhập ngày bảo trì cuối: ");
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
                CoSoVatChat csvt = new CoSoVatChat(ma,loai,tt,bt);
                dsCoSoVatChat.add(csvt);
            }
            for (CoSoVatChat csvt : dsCoSoVatChat) {
                String prefix = csvt.getMaCSVC().substring(0,3).toUpperCase();
                int num = Integer.parseInt(csvt.getMaCSVC().substring(3));

                switch (prefix) {
                    case "BBG" -> {
                        if (num > soLuongBBG) {
                            soLuongBBG = num;
                        }
                    }
                    case "DCA" -> {
                        if(num > soLuongDCA) {
                            soLuongDCA = num;
                        }
                    }
                    case "DCN" -> {
                        if(num > soLuongDCN) {
                            soLuongDCN = num;
                        }
                    }
                }
            }

            System.out.println("Doc File Thanh Cong: " + dsCoSoVatChat.size());

        }catch (Exception e) {
            System.out.println("Loi Doc File: " + e.getMessage());
        }
    }

    @Override
    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for(CoSoVatChat csvt : dsCoSoVatChat) {
                bw.write(csvt.toCSV());
                bw.newLine();
            }
        }catch (Exception e) {
            System.out.println("Loi Ghi File Khong Thanh Cong ");
        }
    }
}