package QuanLi;

import DichVu.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class QuanLiSanPham extends QuanLiChung {
    private int tongSoSP;
    private String hanSD;
    private Scanner sc = new Scanner(System.in);
    List<SanPham> dsSanPham =  new ArrayList<>();
    private final String TEN_FILE = "src/sanpham.csv";

    public double tinhGiaTriTonKho() {
        double tong = 0;
        for (SanPham sp : dsSanPham) {
            tong += sp.getGiaThanh() * sp.getSoLuong();
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
            tongSoSP++;
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
    public void xoa(String maSP) {
        boolean removed = dsSanPham.removeIf(hd -> hd.getMaSanPham().equalsIgnoreCase(maSP));
        if (removed) {
            tongSoSP--;
            System.out.println("Đã xóa hóa đơn " + maSP);
        } else {
            System.out.println("Không tìm thấy hóa đơn có mã " + maSP);
        }
    }

    @Override
    public Object timKiem(String maSanPham) {
        for (SanPham sp : dsSanPham) {
            if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) return sp;
        }
        return null;
    }

	public void sapXep(){
		dsSanPham.sort(Comparator.comparing(SanPham::getMaSanPham));
		System.out.println("Đã sắp xêp sản phẩm theo mã");
	}

	public void hienThi() {
		System.out.println("\n===== DANH SÁCH SẢN PHẨM =====");

		for (SanPham sp : dsSanPham) {
			if (sp instanceof DoUong || sp instanceof ThucAn) {
				sp.xuatSanPham();
			}
		}

		System.out.println("\n===== DANH SÁCH COMBO =====");
		for (SanPham sp : dsSanPham) {
			if (sp instanceof Combo) {
				sp.xuatSanPham();
			}
		}

		System.out.println("==============================");
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
                String loai = p[0].trim();
                String ma = p[1].trim();
                String ten = p[2].trim();
                int sl = Integer.parseInt(p[3].trim());
                double gia = Double.parseDouble(p[4].trim());
                String t1 = p[5].trim();
                String t2 = p[6].trim();
                String t3 = p.length > 7 ? p[7].trim() : " ";

                if(loai.equalsIgnoreCase("DoUong")) {
                    DoUong doUong = new DoUong(ma,ten,sl,gia,t1,Double.parseDouble(t2));
                    dsSanPham.add(doUong);
					tongSoSP++;
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
					tongSoSP++;
                } else if (loai.equalsIgnoreCase("Combo")) {
                    Combo combo = new Combo(ma,ten,sl,gia,Integer.parseInt(t1),Integer.parseInt(t2));
                    dsSanPham.add(combo);
					tongSoSP++;
                }
            }
            System.out.println("Da Doc File Thanh Cong.");
        }catch (Exception e) {
            System.out.println("Loi doc ten file: " + e.getMessage());
        }
    }

    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
			bw.write("Loai,Ma,Ten,SoLuong,GiaThanh,T1,T2,T3");
            bw.newLine();
            for(SanPham sp : dsSanPham) {
                bw.write(sp.toCSV());
                bw.newLine();
            }
            System.out.println("Da Ghi Danh Sach San Pham Ra File: " + tenFile);
        }catch (IOException e) {
            System.out.println("Loi ghi file " + e.getMessage());
        }
    }

    //========================MENU=======================
    public void menu() {
        docFile(TEN_FILE);
        int choice;
        do {
            System.out.println("\n===== MENU QUAN LI SAN PHAM =====");
            System.out.println("1. Them san pham");
            System.out.println("2. Xoa san pham");
            System.out.println("3. Sua thong tin san pham");
            System.out.println("4. Tim kiem san pham");
            System.out.println("5. Sap xep san pham theo ma");
            System.out.println("6. Hien thi danh sach san pham");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("1. Do Uong ");
                    System.out.println("2. Thuc An ");
                    System.out.println("3. Combo ");
					System.out.print("Moi chon: ");
                    int loai = Integer.parseInt(sc.nextLine());

                    System.out.println("Ma San Pham: ");
                    String maSanPham = sc.nextLine();
                    System.out.println("Ten San Pham: ");
                    String tenSanPham = sc.nextLine();
                    System.out.println("So Luong: ");
                    int soLuong = Integer.parseInt(sc.nextLine());
                    System.out.println("Gia Thanh: ");
                    double giaThanh = Double.parseDouble(sc.nextLine());

                    if(loai == 1) {
                        System.out.println("Don vi tinh: ");
                        String donViTinh = sc.nextLine();
                        System.out.println("Dung tich: ");
                        double dungTich = Double.parseDouble(sc.nextLine());
                        DoUong doUong = new DoUong(maSanPham, tenSanPham, soLuong, giaThanh, donViTinh, dungTich);
                        them(doUong);
                    }
                    if (loai == 2) {
                        System.out.println("Nguyen lieu: ");
                        String nguyenLieu = sc.nextLine();
                        System.out.println("Thoi gian chuan bi: ");
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
                    ghiFile(TEN_FILE);
					break;
                }
                case 2 -> {
                    System.out.print("Nhap Ma San Pham Can Xoa: ");
                    String maSanPham = sc.nextLine();
                    xoa(maSanPham);
                    ghiFile(TEN_FILE);
					break;
                }
                case 3 -> {
					System.out.println("1. Do Uong ");
                    System.out.println("2. Thuc An ");
                    System.out.println("3. Combo ");
                    int loai = Integer.parseInt(sc.nextLine());
					System.out.print ("Nhập mã sản phẩm cần sửa: ");
					String ma = sc.nextLine();
					SanPham spSua = (SanPham) timKiem(ma);
					if (spSua == null) {
						System.out.println("Không tìm thấy sản phẩm với mã " + ma);
						break;
					}

					System.out.print("Tên sản phẩm: ");
					String tenSanPham = sc.nextLine();
					System.out.print("Số lượng: ");
					int soLuong = Integer.parseInt(sc.nextLine());
					System.out.print("Giá thành: ");
					double giaThanh = Double.parseDouble(sc.nextLine());

					spSua.setTenSanPham(tenSanPham);
					spSua.setSoLuong(soLuong);
					spSua.setGiaThanh(giaThanh);

					if (loai == 1 && spSua instanceof DoUong) {
						System.out.print("Đơn vị tính: ");
						String donViTinh = sc.nextLine();
						System.out.print("Dung tích: ");
						double dungTich = Double.parseDouble(sc.nextLine());

						((DoUong) spSua).setDonViTinh(donViTinh);
						((DoUong) spSua).setDungTich(dungTich);
					} else if (loai == 2 && spSua instanceof ThucAn) {
						System.out.print("Nguyên liệu: ");
						String nguyenLieu = sc.nextLine();
						System.out.print("Thời gian chuẩn bị: ");
						int thoiGianChuanBi = Integer.parseInt(sc.nextLine());
						System.out.print("Loại (CHIEN / XAO / HAP / NUONG / NAU): ");
						String l = sc.nextLine();

						((ThucAn) spSua).setNguyenLieu(nguyenLieu);
						((ThucAn) spSua).setThoiGianChuanBi(thoiGianChuanBi);
					} else if (loai == 3 && spSua instanceof Combo) {
						System.out.print("Số lượng món: ");
						int soLuongMon = Integer.parseInt(sc.nextLine());
						System.out.print("Số lượng người ăn: ");
						int soLuongNguoiAn = Integer.parseInt(sc.nextLine());

						((Combo) spSua).setSoLuongMon(soLuongMon);
						((Combo) spSua).setLuongNguoiAn(soLuongNguoiAn);
					} else {
						System.out.println("Loại sản phẩm không khớp với mã đã chọn!");
					}

					ghiFile(TEN_FILE);
					System.out.println("Sửa sản phẩm thành công!");
					break;
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
					break;
                }

                case 5 -> {
                    sapXep();
					ghiFile(TEN_FILE);
					break;
                }

                case 6 -> {
                    hienThi();
					break;
                }
				case 0 -> {
					System.out.println("Thoát quản lí");
					break;
				}
                default -> {
                    System.out.println("Loi ");
					break;
                }
            }
        } while (choice != 0);
    }
}
