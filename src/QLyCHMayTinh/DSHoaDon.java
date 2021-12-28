package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSHoaDon implements Serializable, docfileghifile {
    Hoadon[] DSHD;
    private int SLHD;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    public static int LengthSpace = 25;

    public DSHoaDon() {
        this.DSHD = null;
        this.SLHD = 0;
    }

    public DSHoaDon(Hoadon[] DSHD, int SLHD) {
        this.DSHD = DSHD;
        this.SLHD = SLHD;
    }

    public int getSLHD() {
        return SLHD;
    }

    public void setSLHD(int SLHD) {
        this.SLHD = SLHD;
    }

    public void docfile() {
        DSHD = new Hoadon[SLHD];
        try {
            FileInputStream fi = new FileInputStream("DSHDout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSHD = Arrays.copyOf(DSHD, DSHD.length + 1);
                Hoadon obj = (Hoadon) ois.readObject();
                DSHD[SLHD] = obj;
                SLHD++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSHDout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLHD; i++)
                oos.writeObject(DSHD[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void XoaHoaDon() {
        System.out.println("Danh sach ma hoa don co trong he thong la : " + ListHD());
        if (SLHD == 0) {
            System.out.println("He thong khong co hoa don nao het !!!");
            return;
        }
        String msv;
        DSChiTietHoaDon cthd = new DSChiTietHoaDon();
        cthd.docfile();
        do {
            System.out.print("Nhap ma hoa don can xoa: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLHD; i++)
            if (DSHD[i].getMaHD().equalsIgnoreCase(msv)) {
                for (int j = i; j < SLHD - 1; j++)
                    DSHD[j] = DSHD[j + 1];
                SLHD--;
                cthd.Xoa1CTHD(msv);
                cthd.ghifile();
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay ma hoa don can xoa");
    }

    public int TimkiemMa(String s) {
        for (int i = 0; i < SLHD; i++) {
            if (DSHD[i].getMaHD().equals(s)) {
                return 1;
            }
        }
        return 0;
    }

    public void Them1HD() {
        DSChiTietHoaDon cthd = new DSChiTietHoaDon();
        cthd.docfile();
        DSNhanVien DSNV = new DSNhanVien();
        DSNV.docfile();
        Hoadon sv = new Hoadon();
        String msv;
        msv = sc.nextLine();
        if (DSNV.TimkiemMa(msv) == false) {
            do {
                System.out.print("Nhap vao ma nhan vien cua ban: ");
                msv = sc.nextLine();
                if (DSNV.TimkiemMa(msv) == false) {
                    System.out.println("Ma nhan vien khong ton tai !!!");
                }
            } while (DSNV.TimkiemMa(msv) == false);
        }
        sv.setMaNV(msv);
        DSKhachHang DSKH = new DSKhachHang();
        DSKH.docfile();
        DSKH.ListMaKH();
        String mkh;
        int k = 0;
        do {
            System.out.println("Khach hang co the thanh vien chua??? 1.(YES) / 2.(NO)");
            mkh = sc.nextLine();
            if (KTra.isNumber(mkh) == false)
                System.out.println("Vui long nhap dung yeu cau !!!");
            else
                k = Integer.parseInt(mkh);
        } while (KTra.isNumber(mkh) == false || k < 1 || k > 2);

        switch (k) {
            case 1: {
                String Makh;
                do {
                    System.out.println("Danh sach ma khach hang co san la : " + DSKH.ListMaKH());
                    System.out.print("Nhap vao ma khach hang : ");
                    Makh = sc.nextLine();
                    if (DSKH.TimkiemMa(Makh) == false) {
                        System.out.println("Khong tim thay ma khach hang!!!");
                    }
                } while (DSKH.TimkiemMa(Makh) == false);
                sv.setMaKH(Makh);
                break;
            }
            case 2: {
                System.out.println("Tao the thanh vien cho khach hang!!!");
                DSKH.Them1KH();
                DSKH.ghifile();
                sv.setMaKH(DSKH.DSKH[DSKH.getSLKH() - 1].getMaKH());
                break;
            }
        }
        sv.setMaHD();
        if (TimkiemMa(sv.getMaHD()) == 1) {
            do {
                System.out.println("Ma HD da ton tai trong he thong!!!");
                System.out.println("Vui long nhap lai !!!!!");
                System.out.println("Danh sach ma hoa dơn co san la: " + ListHD());
                sv.setMaHD();
            } while (TimkiemMa(sv.getMaHD()) == 1);
        }
        sv.setTienkhachtra();
        sv.setNgayxuatHD();

        cthd.ThemCTHD(sv.getMaHD(), sv.getTienkhachtra());
        cthd.ghifile();
        DSHD = Arrays.copyOf(DSHD, DSHD.length + 1);
        DSHD[SLHD] = sv;
        SLHD++;
    }

    public void ThemNhieuHoaDon() {
        DSChiTietHoaDon cthd = new DSChiTietHoaDon();
        cthd.docfile();
        DSNhanVien DSNV = new DSNhanVien();
        DSNV.docfile();
        int sl;
        String c;
        Hoadon[] sv;
        System.out.print("Nhap so luong hoa don muon them: ");
        sl = sc.nextInt();
        String msv;
        msv = sc.nextLine();
        if (DSNV.TimkiemMa(msv) == false) {
            do {
                System.out.print("Nhap vao ma nhan vien(Admin) cua ban: ");
                msv = sc.nextLine();
                if (DSNV.TimkiemMa(msv) == false) {
                    System.out.println("Ma nhan vien(Admin) khong ton tai !!!");
                }
            } while (DSNV.TimkiemMa(msv) == false);
        }
        sv = new Hoadon[sl];
        for (int i = 0; i < sl; i++) {
            sv[i] = new Hoadon();
            DSKhachHang DSKH = new DSKhachHang();
            DSKH.docfile();
            DSKH.ListMaKH();
            String mkh;
            int k = 0;
            do {
                System.out.println("Khach hang co the thanh vien chua??? 1.(YES) / 2.(NO)");
                mkh = sc.nextLine();
                if (KTra.isNumber(mkh) == false)
                    System.out.println("Vui long nhap dung yeu cau !!!");
                else
                    k = Integer.parseInt(mkh);
            } while (KTra.isNumber(mkh) == false || k < 1 || k > 2);

            switch (k) {
                case 1: {
                    String Makh;
                    do {
                        System.out.println("Danh sach Ma KH co san la : " + DSKH.ListMaKH());
                        System.out.print("Nhap vao ma KH : ");
                        Makh = sc.nextLine();
                        if (DSKH.TimkiemMa(Makh) == false) {
                            System.out.println("Khong tim thay ma khach hang!!!");
                        }
                    } while (DSKH.TimkiemMa(Makh) == false);
                    sv[i].setMaKH(Makh);
                    break;
                }
                case 2: {
                    System.out.println("Tao the thanh vien cho khach hang!!!");
                    DSKH.Them1KH();
                    DSKH.ghifile();
                    sv[i].setMaKH(DSKH.DSKH[DSKH.getSLKH() - 1].getMaKH());
                    break;
                }
            }
            sv[i].setMaHD();
            if (TimkiemMa(sv[i].getMaHD()) == 1) {
                do {
                    System.out.println("Ma HD da ton tai trong he thong!!!");
                    System.out.println("Vui long nhap lai !!!!!");
                    System.out.println("Danh sach Ma HD co san la: " + ListHD());
                    sv[i].setMaHD();
                } while (TimkiemMa(sv[i].getMaHD()) == 1);
            }
            sv[i].setMaNV(msv);
            sv[i].setTienkhachtra();
            sv[i].setNgayxuatHD();
            cthd.ThemCTHD(sv[i].getMaHD(), sv[i].getTienkhachtra());
            cthd.ghifile();
        }
        for (int i = 0; i < sl; i++) {
            DSHD = Arrays.copyOf(DSHD, DSHD.length + 1);
            DSHD[SLHD] = sv[i];
            SLHD++;
        }
    }

    public void SuaHoaDon() {
        System.out.println("Danh sach ma hoa don co trong he thong la : " + ListHD());
        if (SLHD == 0) {
            System.out.println("He thong khong co hoa don nao het !!!");
            return;
        }
        String msv, c;
        DSChiTietHoaDon cthd = new DSChiTietHoaDon();
        cthd.docfile();
        do {
            System.out.print("Nhap ma khach hang can sua: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int i;
        for (i = 0; i < SLHD; i++)
            if (DSHD[i].getMaHD().equalsIgnoreCase(msv)) {
                DSHD[i] = new Hoadon();
                DSHD[i].NhapHD();
                DSHD[i].setMaHD(msv);
                cthd.ghifile();
                System.out.print("Ban co muon sua chi tiet hoa don??. Bam so 1(YES)/0(NO): ");
                c = sc.nextLine();
                int k = Integer.parseInt(c);
                if (k == 1) {
                    cthd.Xuat1dsCTHD(DSHD[i].getMaHD());
                    cthd.SuaCTHD();
                    cthd.ghifile();
                    break;
                }
                if (k == 0)
                    break;
            }
        if (i >= SLHD) {
            System.out.println("Khong tim thay ma hoa don de sua !!!");
            return;
        }
    }

    public String ListHD() {
        String s = "";
        if (SLHD == 0) {
            return null;
        }
        for (int i = 0; i < SLHD; i++) {
            s += DSHD[i].getMaHD();
            if (i != SLHD - 1) {
                s += ',';
            }
        }
        return s;
    }

    public String PrintSpace(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void XuatDSHoaDon() {
        if (DSHD == null) {
            System.out.println("Danh sach rong !!!!");
            return;
        }
        final int LengthSpace = 25;
        System.out.print("Ma HD" + PrintSpace(LengthSpace - "Ma HD".length()));
        System.out.print("|Ma NV" + PrintSpace(LengthSpace - "Ma NV".length()));
        System.out.print("|Ma KH" + PrintSpace(LengthSpace - "Ma KH".length()));
        System.out.println("|Ngay Ban");
        for (int i = 0; i < DSHD.length; i++) {
            System.out.print(DSHD[i].getMaHD() + PrintSpace(LengthSpace - DSHD[i].getMaHD().length()));
            System.out.print("|" + DSHD[i].getMaNV() + PrintSpace(LengthSpace - DSHD[i].getMaNV().length()));
            System.out.print("|" + DSHD[i].getMaKH() + PrintSpace(LengthSpace - DSHD[i].getMaKH().length()));
            System.out.println("|" + DSHD[i].getNgayxuatHD().toString());
        }
    }

    public void timkiemHD() {
        final int LengthSpace = 25;
        String k;
        int tmp;
        String UName;
        do {
            do {
                System.out.println(
                        "Nhap vao Ma Hoa Don or Ma Nhan Vien or Ma Khach Hang or Ngay Ban Loai San Pham or Gia:");
                System.out.print("ban phim so: ");
                UName = sc.nextLine();
            } while (UName.isEmpty());
            System.out.print("Ma HD" + PrintSpace(LengthSpace - "Ma HD".length()));
            System.out.print("|Ma NV" + PrintSpace(LengthSpace - "Ma NV".length()));
            System.out.print("|Ma KH" + PrintSpace(LengthSpace - "Ma KH".length()));
            System.out.println("|Ngay Ban");
            for (int i = 0; i < DSHD.length; i++) {
                if (DSHD[i].getMaHD().toLowerCase().contains(UName.toLowerCase())
                        || DSHD[i].getMaKH().toLowerCase().contains(UName.toLowerCase())
                        || DSHD[i].getMaNV().toLowerCase().contains(UName.toLowerCase())
                        || DSHD[i].getNgayxuatHD().toString().contains(UName)) {
                    System.out.print(DSHD[i].getMaHD() + PrintSpace(LengthSpace - DSHD[i].getMaHD().length()));
                    System.out.print("|" + DSHD[i].getMaNV() + PrintSpace(LengthSpace - DSHD[i].getMaNV().length()));
                    System.out.print("|" + DSHD[i].getMaKH() + PrintSpace(LengthSpace - DSHD[i].getMaKH().length()));
                    System.out.println("|" + DSHD[i].getNgayxuatHD().toString());
                }
            }
            System.out.print("Ban muon tim tiep hay khong??  1.(YES) / 2.(NO)");
            do {
                System.out.print("Ban muon tim tiep hay khong??  1.(YES) / 2.(NO)");
                System.out.print("ban phim so: ");
                k = sc.nextLine();
                if (KTra.isNumber(k) == false || Integer.parseInt(k) < 1 || Integer.parseInt(k) > 2)
                    System.out.println("Vui long nhap dung yeu cau !!!");
                else
                    tmp = Integer.parseInt(k);
            } while (KTra.isNumber(k) == false || Integer.parseInt(k) < 1 || Integer.parseInt(k) > 2);
        } while (KTra.isNumber(k) == false || Integer.parseInt(k) == 1);
    }

    public void thongkethang() {
        final int LengthSpace = 25;
        DSChiTietHoaDon DS = new DSChiTietHoaDon();
        DS.docfile();
        String k;
        int tmp;
        String UName;
        String UName2;
        double tong = 0, tong1 = 0;
        do {
            System.out.print("nhap vao thang can thong ke: ");
            UName = sc.nextLine();
            System.out.print("nhap vao nam can thong ke: ");
            UName2 = sc.nextLine();
        } while (KTra.isNumber(UName) == false || KTra.isNumber(UName2) == false || UName.isEmpty()
                || UName2.isEmpty());
        System.out.print("thang/nam" + PrintSpace(LengthSpace - "thang/nam".length()));
        System.out.print("|Ma HD" + PrintSpace(LengthSpace - "Ma HD".length()));
        System.out.print("|Ma KH" + PrintSpace(LengthSpace - "Ma KH".length()));
        System.out.println("|Doanh thu" + PrintSpace(LengthSpace - "Doanh thu".length()));
        for (int i = 0; i < DSHD.length; i++) {
            if (DSHD[i].getNgayxuatHD().getThang() == Integer.parseInt(UName)
                    && DSHD[i].getNgayxuatHD().getNam() == Integer.parseInt(UName2)) {
                System.out.print(DSHD[i].getNgayxuatHD().getThang() + "/" + DSHD[i].getNgayxuatHD().getNam()
                        + PrintSpace(LengthSpace
                                - (DSHD[i].getNgayxuatHD().getThang() + "/" + DSHD[i].getNgayxuatHD().getNam())
                                        .length()));
                System.out.print(DSHD[i].getMaHD() + PrintSpace(LengthSpace - DSHD[i].getMaHD().length()));
                System.out.print(DSHD[i].getMaKH() + PrintSpace(LengthSpace - DSHD[i].getMaKH().length()));
                tong1 = DS.tongCTHD(DSHD[i].getMaHD());
                System.out.println(tong1 + PrintSpace(LengthSpace - 5));
                tong += tong1;
            }
        }
        System.out.println(PrintSpace(45 - 5) + "Tong Doanh thu=" + tong);
    }

    public void thongkenam() {
        final int LengthSpace = 25;
        DSChiTietHoaDon DS = new DSChiTietHoaDon();
        DS.docfile();
        String k;
        int tmp;
        double tong = 0, tong1 = 0;
        String UName;
        do {
            System.out.print("nhap vao nam can thong ke: ");
            UName = sc.nextLine();
        } while (KTra.isNumber(UName) == false || UName.isEmpty());
        System.out.print("nam" + PrintSpace(LengthSpace - "nam".length()));
        System.out.print("|Doanh thu" + PrintSpace(LengthSpace - "Doanh thu".length()));
        for (int i = 0; i < DSHD.length; i++) {
            if (DSHD[i].getNgayxuatHD().getNam() == Integer.parseInt(UName)) {
                System.out.print(DSHD[i].getNgayxuatHD().getNam()
                        + PrintSpace(LengthSpace - ("" + DSHD[i].getNgayxuatHD().getNam()).length()));
                System.out.print(DSHD[i].getMaHD() + PrintSpace(LengthSpace - DSHD[i].getMaHD().length()));
                System.out.print(DSHD[i].getMaKH() + PrintSpace(LengthSpace - DSHD[i].getMaKH().length()));
                tong = DS.tongCTHD(DSHD[i].getMaHD());
                System.out.println(tong1 + PrintSpace(LengthSpace - 5));
                tong += tong1;
            }
        }
        System.out.println(PrintSpace(45 - 5) + "Tong Doanh thu=" + tong);
    }

    public static void Ghi() {
        DSHoaDon ds = new DSHoaDon();
        ds.DSHD = new Hoadon[5];
        ds.SLHD = 5;

        ds.DSHD[0] = new Hoadon("MHD-001", "MNV-001", "MKH-001", new Date(18, 10, 2021));
        ds.DSHD[1] = new Hoadon("MHD-002", "MNV-002", "MKH-002", new Date(29, 9, 2019));
        ds.DSHD[2] = new Hoadon("MHD-003", "MNV-003", "MKH-003", new Date(15, 11, 2021));
        ds.DSHD[3] = new Hoadon("MHD-003", "MNV-003", "MKH-004", new Date(15, 11, 2021));
        ds.DSHD[4] = new Hoadon("MHD-004", "MNV-004", "MKH-004", new Date(20, 10, 2021));

        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }

}
