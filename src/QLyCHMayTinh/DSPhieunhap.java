package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSPhieunhap implements Serializable, docfileghifile {
    Phieunhap[] DSPN;
    private int SLPN;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    public static int LengthSpace = 25;

    public DSPhieunhap(Phieunhap[] DSPN, int SLPN) {
        this.DSPN = DSPN;
        this.SLPN = SLPN;
    }

    public DSPhieunhap() {
        this.DSPN = null;
        this.SLPN = 0;
    }

    public int getSLPN() {
        return SLPN;
    }

    public void setSLPN(int SLPN) {
        this.SLPN = SLPN;
    }

    public void docfile() {
        DSPN = new Phieunhap[SLPN];
        try {
            FileInputStream fi = new FileInputStream("DSPNout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSPN = Arrays.copyOf(DSPN, DSPN.length + 1);
                Phieunhap obj = (Phieunhap) ois.readObject();
                DSPN[SLPN] = obj;
                SLPN++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSPNout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLPN; i++)
                oos.writeObject(DSPN[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Them1PN() {
        DSChiTietPhieuNhap ctpn = new DSChiTietPhieuNhap();
        ctpn.docfile();
        DSNhanVien DSNV = new DSNhanVien();
        DSNV.docfile();
        Phieunhap sv = new Phieunhap();

        String msv;
        do {
            System.out.print("Nhap vao ma NV cua ban: ");
            msv = sc.nextLine();
            if (DSNV.TimkiemMa(msv) == false) {
                System.out.println("Ma nhan vien khong ton tai !!!");
            }
        } while (DSNV.TimkiemMa(msv) == false);
        sv.setMaNV(msv);
        DSNhaCungCap DSNCC = new DSNhaCungCap();
        DSNCC.docfile();
        DSNCC.ListMNCC();
        String mkh;
        int k = 0;
        do {
            System.out.println("Nha cung cap nay da hop tac voi cua hang hay chua??? 1.(YES) / 2.(NO)");
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
                    System.out.println("Danh sach Ma NCC co san la : " + DSNCC.ListMNCC());
                    System.out.print("Nhap vao ma NCC : ");
                    Makh = sc.nextLine();
                    if (DSNCC.TimkiemMa(Makh) == 0) {
                        System.out.println("Khong tim thay ma NCC!!!");
                    }
                } while (DSNCC.TimkiemMa(Makh) == 0);
                // DSKH.DiemKH(Makh, HD.getNgayxuatHD());
                // HD.setMaKH(Makh);
                sv.setMaNCC(Makh);
                break;
            }
            case 2: {
                System.out.println("Ky hop dong voi doi tac!!!");
                DSNCC.Them1NCC();
                DSNCC.ghifile();
                sv.setMaNCC(DSNCC.DSNCC[DSNCC.getSLNCC() - 1].getMaNCC());
                break;
            }
        }

        sv.setMaPN();
        if (TimkiemMa(sv.getmaPN()) == 1) {
            do {
                System.out.println("Ma PN da ton tai trong he thong!!!");
                System.out.println("Vui long nhap lai !!!!!");
                System.out.println("Danh sach Ma PN co san la: " + ListMaPN());
                sv.setMaPN();
            } while (TimkiemMa(sv.getmaPN()) == 1);
        }
        System.out.println("Nhap ngay xuat Phieu Nhap: (dd/mm/yyyy) ");
        sv.setNgaynhap();

        ctpn.ThemCTPN(sv.getmaPN());
        ctpn.ghifile();
        DSPN = Arrays.copyOf(DSPN, DSPN.length + 1);
        DSPN[SLPN] = sv;
        SLPN++;
    }

    public void ThemNhieuPhieuNhap() {
        int sl;
        System.out.print("Nhap so luong phieu nhap muon them: ");
        sl = sc.nextInt();
        DSChiTietPhieuNhap ctpn = new DSChiTietPhieuNhap();
        ctpn.docfile();
        DSNhanVien DSNV = new DSNhanVien();
        DSNV.docfile();
        Phieunhap[] sv;
        String msv;
        System.out.print("Nhap vao ma NV(Admin) cua ban: ");
        msv = sc.nextLine();
        if (DSNV.TimkiemMa(msv) == false) {
            do {
                System.out.print(" ");
                msv = sc.nextLine();
                if (DSNV.TimkiemMa(msv) == false) {
                    System.out.println("Ma nhan vien(Admin) khong ton tai !!!");
                }
            } while (DSNV.TimkiemMa(msv) == false);
        }
        DSNhaCungCap DSNCC = new DSNhaCungCap();
        DSNCC.docfile();
        DSNCC.ListMNCC();
        String mkh;
        int k = 0;
        String c;
        sv = new Phieunhap[sl];
        for (int i = 0; i < sl; i++) {
            sv[i] = new Phieunhap();
            do {
                System.out.println("Nha cung cap nay da hop tac voi cua hang hay chua??? 1.(YES) / 2.(NO)");
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
                        System.out.println("Danh sach Ma NCC co san la : " + DSNCC.ListMNCC());
                        System.out.print("Nhap vao ma NCC : ");
                        Makh = sc.nextLine();
                        if (DSNCC.TimkiemMa(Makh) == 0) {
                            System.out.println("Khong tim thay ma NCC!!!");
                        }
                    } while (DSNCC.TimkiemMa(Makh) == 0);
                    sv[i].setMaNCC(Makh);
                    break;
                }
                case 2: {
                    System.out.println("Ky hop dong voi doi tac!!!");
                    DSNCC.Them1NCC();
                    DSNCC.ghifile();
                    sv[i].setMaNCC(DSNCC.DSNCC[DSNCC.getSLNCC() - 1].getMaNCC());
                    break;
                }
            }
            sv[i].setMaPN();
            if (TimkiemMa(sv[i].getmaPN()) == 1) {
                do {
                    System.out.println("Ma PN da ton tai trong he thong!!!");
                    System.out.println("Vui long nhap lai !!!!!");
                    System.out.println("Danh sach Ma PN co san la: " + ListMaPN());
                    sv[i].setMaPN();
                } while (TimkiemMa(sv[i].getmaPN()) == 1);
            }
            sv[i].setMaNV(msv);
            sv[i].setNgaynhap();
            ctpn.ThemCTPN(sv[i].getmaPN());
            ctpn.ghifile();
        }
        for (int i = 0; i < sl; i++) {
            DSPN = Arrays.copyOf(DSPN, DSPN.length + 1);
            DSPN[SLPN] = sv[i];
            SLPN++;
        }
    }

    public void XoaPhieuNhap() {
        System.out.println("Danh sach ma phieu nhap co trong he thong la : " + ListMaPN());
        if (SLPN == 0) {
            System.out.println("He thong khong co phieu nhap nao het !!!");
            return;
        }
        String msv;
        DSChiTietPhieuNhap ctpn = new DSChiTietPhieuNhap();
        ctpn.docfile();
        do {
            System.out.print("Nhap Ma PN can xoa: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLPN; i++)
            if (DSPN[i].getmaPN().equalsIgnoreCase(msv)) {
                for (int j = i; j < SLPN - 1; j++)
                    DSPN[j] = DSPN[j + 1];
                SLPN--;
                ctpn.Xoa1CTPN(msv);
                ctpn.ghifile();
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay MaPN can xoa");
    }

    public void TimkiemtheoID() {
        System.out.println("Danh sach ma phieu nhap co trong he thong la : " + ListMaPN());
        if (SLPN == 0) {
            System.out.println("He thong khong co phieu nhap nao het !!!");
            return;
        }
        int kt = 0;
        String msv;
        do {
            System.out.print("Nhap Ma PN can tim: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        for (int i = 0; i < SLPN; i++)
            if (DSPN[i].getmaPN().equalsIgnoreCase(msv)) {
                kt = 1;
                DSPN[i].toString();
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay phieu nhap do");
    }

    public void SuaPhieuNhap() {
        System.out.println("Danh sach ma phieu nhap co trong he thong la : " + ListMaPN());
        if (SLPN == 0) {
            System.out.println("He thong khong co phieu nhap nao het !!!");
            return;
        }
        String msv, c;
        DSChiTietPhieuNhap ctpn = new DSChiTietPhieuNhap();
        ctpn.docfile();
        do {
            System.out.print("Nhap Ma PN can sua: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int i;
        for (i = 0; i < SLPN; i++)
            if (DSPN[i].getmaPN().equalsIgnoreCase(msv)) {
                DSPN[i] = new Phieunhap();
                DSPN[i].Nhapphieunhap();
                DSPN[i].setMaPN(msv);
                ctpn.ghifile();
                System.out.print("Ban co muon sua CT phieu nhap. Bam so 1(YES)/0(NO): ");
                c = sc.nextLine();
                int k = Integer.parseInt(c);
                if (k == 1) {
                    ctpn.Xuat1dsCTPN(DSPN[i].getmaPN());
                    ctpn.SuaCTPN(msv);
                    ctpn.ghifile();
                    break;
                }
                if (k == 0)
                    break;
            }
        if (i >= SLPN) {
            System.out.println("Khong tim thay Ma Phieu nhap de sua !!!");
            return;
        }
    }

    public String ListMaPN() {
        String s = "";
        for (int i = 0; i < SLPN; i++) {
            s += DSPN[i].getmaPN();
            if (i != SLPN - 1) {
                s += ',';
            }
        }
        return s;
    }

    public int TimkiemMa(String x) {
        for (int i = 0; i < SLPN; i++)
            if (DSPN[i].getmaPN().equals(x))
                return 1;
        return 0;
    }

    public String PrintSpace(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void XuatTTPhieuNhap() {
        final int LengthSpace = 25;
        if (DSPN == null || SLPN == 0) {
            System.out.println("Danh sach rong");
            return;
        }
        System.out.print("Ma PN" + PrintSpace(LengthSpace - "Ma PN".length()));
        System.out.print("|Ma NCC" + PrintSpace(LengthSpace - "Ma NCC".length()));
        System.out.print("|Ma NV" + PrintSpace(LengthSpace - "Ma NV".length()));
        System.out.println("|Ngay Nhap");

        System.out.println(
                "-------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < SLPN; i++) {
            System.out.print(DSPN[i].getmaPN() + PrintSpace(LengthSpace - DSPN[i].getmaPN().length()));
            System.out.print("|" + DSPN[i].getmaNCC() + PrintSpace(LengthSpace - DSPN[i].getmaNCC().length()));
            System.out.print("|" + DSPN[i].getmaNV().toString() + PrintSpace(LengthSpace - DSPN[i].getmaNV().length()));
            System.out.println("|" + DSPN[i].getngaynhap());
        }
    }

    public void timkiemPn() {
        final int LengthSpace = 25;
        String k;
        int tmp;
        String UName;
        do {
            do {
                System.out.println("Nhap vao Ho ten or Ma Khach hang or So dien thoai or Dia chi:");
                System.out.print("ban phim so: ");
                UName = sc.nextLine();
            } while (UName.isEmpty());
            System.out.print("Ma PN" + PrintSpace(LengthSpace - "Ma PN".length()));
            System.out.print("|Ma NCC" + PrintSpace(LengthSpace - "Ma NCC".length()));
            System.out.print("|Ma NV" + PrintSpace(LengthSpace - "Ma NV".length()));
            System.out.println("|Ngay Nhap");
            for (int i = 0; i < DSPN.length; i++) {
                if (DSPN[i].getmaPN().toLowerCase().contains(UName.toLowerCase())
                        || DSPN[i].getmaNCC().toLowerCase().contains(UName.toLowerCase())
                        || DSPN[i].getmaNV().toLowerCase().contains(UName.toLowerCase())
                        || DSPN[i].getngaynhap().toString().toLowerCase().contains(UName.toLowerCase())) {
                    System.out.print(DSPN[i].getmaPN() + PrintSpace(LengthSpace - DSPN[i].getmaPN().length()));
                    System.out.print("|" + DSPN[i].getmaNCC() + PrintSpace(LengthSpace - DSPN[i].getmaNCC().length()));
                    System.out.print(
                            "|" + DSPN[i].getmaNV().toString() + PrintSpace(LengthSpace - DSPN[i].getmaNV().length()));
                    System.out.println("|" + DSPN[i].getngaynhap() + "\n");
                }
            }
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
        DSChiTietPhieuNhap DS = new DSChiTietPhieuNhap();
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
        System.out.print("|Ma PN" + PrintSpace(LengthSpace - "Ma PN".length()));
        System.out.print("|Ma NCC" + PrintSpace(LengthSpace - "Ma NCC".length()));
        System.out.println("|Doanh thu" + PrintSpace(LengthSpace - "Doanh thu".length()));
        for (int i = 0; i < DSPN.length; i++) {
            if (DSPN[i].getngaynhap().getThang() == Integer.parseInt(UName)
                    && DSPN[i].getngaynhap().getNam() == Integer.parseInt(UName2)) {
                System.out.print(
                        DSPN[i].getngaynhap().getThang() + "/" + DSPN[i].getngaynhap().getNam() + PrintSpace(LengthSpace
                                - (DSPN[i].getngaynhap().getThang() + "/" + DSPN[i].getngaynhap().getNam()).length()));
                System.out.print(DSPN[i].getmaPN() + PrintSpace(LengthSpace - DSPN[i].getmaPN().length()));
                System.out.print(DSPN[i].getmaNCC() + PrintSpace(LengthSpace - DSPN[i].getmaNCC().length()));
                tong1 = DS.tongCTPN(DSPN[i].getmaPN());
                System.out.println(tong1 + PrintSpace(LengthSpace - 5));
                tong += tong1;
            }
        }
        System.out.println(PrintSpace(45 - 5) + "Tong Doanh thu=" + tong);
    }

    public void thongkenam() {
        final int LengthSpace = 25;
        DSChiTietPhieuNhap DS = new DSChiTietPhieuNhap();
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
        System.out.print("|Tien Nhap" + PrintSpace(LengthSpace - "Tien Nhap".length()));
        for (int i = 0; i < DSPN.length; i++) {
            if (DSPN[i].getngaynhap().getNam() == Integer.parseInt(UName)) {
                System.out.print(DSPN[i].getngaynhap().getNam()
                        + PrintSpace(LengthSpace - ("" + DSPN[i].getngaynhap().getNam()).length()));
                System.out.print(DSPN[i].getmaPN() + PrintSpace(LengthSpace - DSPN[i].getmaPN().length()));
                System.out.print(DSPN[i].getmaNCC() + PrintSpace(LengthSpace - DSPN[i].getmaNCC().length()));
                tong1 = DS.tongCTPN(DSPN[i].getmaPN());
                System.out.println(tong1 + PrintSpace(LengthSpace - 5));
                tong += tong1;
            }
        }
        System.out.println(PrintSpace(45 - 5) + "Tong Tien Tieu Hao=" + tong);
    }

    public static void Ghi() {
        DSPhieunhap ds = new DSPhieunhap();
        ds.DSPN = new Phieunhap[4];
        ds.SLPN = 4;

        ds.DSPN[0] = new Phieunhap("MPN-001", "MNCC-001", "MNV-001", new Date(1, 1, 2020));
        ds.DSPN[1] = new Phieunhap("MPN-002", "MNCC-002", "MNV-001", new Date(1, 6, 2020));
        ds.DSPN[2] = new Phieunhap("MPN-003", "MNCC-003", "MNV-002", new Date(1, 2, 2021));
        ds.DSPN[3] = new Phieunhap("MPN-004", "MNCC-001", "MNV-003", new Date(1, 6, 2021));

        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }

}