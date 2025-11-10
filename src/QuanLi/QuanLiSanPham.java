package QuanLi;

import DichVu.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public void xuatDanhSachSanPham() {
        System.out.println("=====DanhSachSanPham=====");
        for (SanPham sp : dsSanPham) {
            sp.xuatSanPham();
        }
        System.out.println("Tổng số sản phẩm: " + tongSoSP);
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
        if (!(newInfo instanceof SanPham spMoi)) return;

        for (SanPham sp : dsSanPham) {
            if (sp.getMaSanPham().equalsIgnoreCase(id)) {
                sp.setMaSanPham(spMoi.getMaSanPham());
                sp.setTenSanPham(spMoi.getTenSanPham());
                sp.setGiaThanh(spMoi.getGiaThanh());

                // Nếu là DoUong
                if (sp instanceof DoUong du && spMoi instanceof DoUong duMoi) {
                    du.setDonViTinh(duMoi.getDonViTinh());
                    du.setDungTich(duMoi.getDungTich());
                }

                // Nếu là ThucAn
                if (sp instanceof ThucAn ta && spMoi instanceof ThucAn taMoi) {
                    ta.setNguyenLieu(taMoi.getNguyenLieu());
                    ta.setThoiGianChuanBi(taMoi.getThoiGianChuanBi());
                    ta.loaithucan = taMoi.loaithucan;
                }

                // Nếu là Combo
                if (sp instanceof Combo c && spMoi instanceof Combo cMoi) {
                    c.setSoLuongMon(cMoi.getSoLuongMon());
                    c.setLuongNguoiAn(cMoi.getLuongNguoiAn());
                }
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
    public void docFile(String tenFile) {
        File f = new File(tenFile);
        if(!f.exists()) {
            System.out.println("File khong ton tai" + tenFile);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if(p.length < 7) continue;
                String loai = p[0];
                String ma = p[1];
                String ten = p[2];
                int sl = Integer.parseInt(p[3]);
                double gia = Double.parseDouble(p[4]);
                String t1 = p[5];
                String t2 = p[6];
                String t3 = p.length > 7 ? p[7] : " ";

                if(loai.equalsIgnoreCase("DoUong")) {
                    DoUong doUong = new DoUong(ma,ten,sl,gia,t1,Double.parseDouble(t2));
                    dsSanPham.add(doUong);
                }else if(loai.equalsIgnoreCase("ThucAn")) {
                    ThucAn thucAn = new ThucAn(ma,ten,sl,gia,t1,Integer.parseInt(t2));
                    if(!t3.isEmpty()) {
                        try {
                            thucAn.loaithucan = LoaiThucAn.valueOf(t3.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Gia tri cua t3 k hop le " + t3);
                            thucAn.loaithucan = null;
                        }
                    }
                    dsSanPham.add(thucAn);
                } else if (loai.equalsIgnoreCase("Combo")) {
                    Combo combo = new Combo(ma,ten,sl,gia,Integer.parseInt(t1),Integer.parseInt(t2));
                    dsSanPham.add(combo);
                }
            }
            System.out.println("Da Doc File Thanh Cong.");
        }catch (Exception e) {
            System.out.println("Loi doc ten file: " + e.getMessage());
        }
    }

    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for(SanPham sp : dsSanPham) {
                bw.write(sp.toCSV());
                bw.newLine();
            }
            System.out.println("Da Ghi Danh Sach San Pham Ra File: " + tenFile);
        }catch (IOException e) {
            System.out.println("Loi ghi file " + e.getMessage());
        }
    }
    Scanner sc = new Scanner(System.in);
    public void menu() {

        docFile("sanpham.csv");
        int choice;
        do {
            choice = Integer.parseInt(sc.nextLine());
            System.out.println("=====MENU=====");
            System.out.println("1. Xem Danh Sach San Pham ");
            System.out.println("2. Them San Pham Vao Danh Sach ");
            System.out.println("3. Xoa San Pham Ra Khoi Danh Sach ");
            System.out.println("4. Tim Kiem San Pham ");
            System.out.println("5. Luu San Pham Vao File ");
            System.out.println("6.Thoat Chuong Trinh ");


            switch (choice) {
                case 1 -> xuatDanhSachSanPham();
                case 2 ->  {
                    System.out.println("1. Do Uong ");
                    System.out.println("2. Thuc An ");
                    System.out.println("3. Combo ");
                    int loai = Integer.parseInt(sc.nextLine());

                    System.out.println("Ma San Pham: ");
                    String maSanPham = sc.nextLine();
                    System.out.println("Ten San Pham: ");
                    String tenSanPham = sc.nextLine();
                    System.out.println("So Luong: ");
                    int soLuong = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Gia Thanh: ");
                    double giaThanh = Double.parseDouble(sc.nextLine());

                    if(loai == 1) {
                        System.out.println("Don Vi Tinh: ");
                        String donViTinh = sc.nextLine();
                        System.out.println("Dung Tich: ");
                        double dungTich = Double.parseDouble(sc.nextLine());

                        DoUong doUong = new DoUong(maSanPham, tenSanPham, soLuong, giaThanh, donViTinh, dungTich);
                        them(doUong);
                    }
                    if (loai == 2) {
                        System.out.println("Nguyen Lieu: ");
                        String nguyenLieu = sc.nextLine();
                        System.out.println("Thoi Gian Chuan Bi: ");
                        int thoiGianChuanBi = Integer.parseInt(sc.nextLine());
                        System.out.println("Loai (CHIEN / XAO / HAP/ NUONG / NAU)");
                        String l = sc.nextLine();

                        ThucAn thucAn = new ThucAn(maSanPham, tenSanPham, soLuong, giaThanh, nguyenLieu, thoiGianChuanBi);
                        thucAn.loaithucan = LoaiThucAn.valueOf(l.toUpperCase());
                        them(thucAn);
                    }
                    if (loai == 3) {
                        System.out.println("So Luong Mon: ");
                        int soLuongMon = Integer.parseInt(sc.nextLine());
                        System.out.println("So Luong Nguoi An: ");
                        int soLuongNguoiAn = Integer.parseInt(sc.nextLine());

                        Combo combo = new Combo();
                        combo.setMaSanPham(maSanPham);
                        combo.setTenSanPham(tenSanPham);
                        combo.setSoLuong(soLuong);
                        combo.setGiaThanh(giaThanh);
                        combo.setSoLuongMon(soLuongMon);
                        combo.setLuongNguoiAn(soLuongNguoiAn);

                        them(combo);
                    }
                }
                case 3 -> {
                    System.out.println("Nhap Ma San Pham Can Xoa ");
                    sc.nextLine();
                    String maSanPham = sc.nextLine();
                    xoa(maSanPham);
                }
                case 4 -> {
                    sc.nextLine();
                    System.out.println("Tim Kiem San Pham: ");
                    String maSanPham = sc.nextLine();
                    SanPham sp = (SanPham) timKiem(maSanPham);

                    if(sp != null) {
                        System.out.println("Da Tim Thay San Pham: ");
                        sp.xuatSanPham();
                    }else {
                        System.out.println("Khong Tim Thay San Pham" + maSanPham);
                    }
                }
                case 5 -> {
                    sc.nextLine();
                    System.out.println("Nhap Ten File Can Luu");
                    String tenFile = sc.nextLine();
                    ghiFile(tenFile);
                }
                case 6 -> {
                    System.out.println("Da Thoat Chuong Trinh");
                }
                default -> {
                    System.out.println("Loi ");
                }
            }
        } while (choice != 6);
    }
}
