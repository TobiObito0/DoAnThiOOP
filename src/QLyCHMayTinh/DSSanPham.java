package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSSanPham implements Serializable, docfileghifile {
    SanPham[] DSSP;
    private int SLSP;
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    public static int LengthSpace = 25;

    public DSSanPham() {
        this.DSSP = null;
        this.SLSP = 0;
    }

    public DSSanPham(SanPham[] DSSP, int SLSP) {
        this.DSSP = DSSP;
        this.SLSP = SLSP;
    }

    public int getSLSP() {
        return SLSP;
    }

    public void setSLSP(int SLSP) {
        this.SLSP = SLSP;
    }

    public void docfile() {
        DSSP = new SanPham[SLSP];
        try {
            FileInputStream fi = new FileInputStream("DSSPout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSSP = Arrays.copyOf(DSSP, DSSP.length + 1);
                SanPham obj = (SanPham) ois.readObject();
                DSSP[SLSP] = obj;
                SLSP++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSSPout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLSP; i++)
                oos.writeObject(DSSP[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void XuatdsSP() {
        System.out.printf("%-15s%-70s%-15s%-15s%-15s", "Ma SP", "Ten SP", "Loai SP", "Gia SP", "So luong SP");
        System.out.print(
                "\n=========================================================================================================================================================================================================================\n");
        for (int i = 0; i < SLSP; i++) {
            DSSP[i].XuatSP();
            System.out.print(
                    "\n=========================================================================================================================================================================================================================\n");
        }
    }

    public int Get_SLSP(String MaSP) {
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(MaSP)) {
                return DSSP[i].getSoluong();
            }
        }
        return 0;
    }

    public int GetSLMa() {
        int count = 0;
        for (int i = 0; i < DSSP.length; i++) {
            count++;
        }
        return count;
    }

    public void setSP(String MaSP, int SL) {
        int sum = 0;
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(MaSP)) {
                sum = DSSP[i].getSoluong();
                DSSP[i].setSoluong(sum + SL);
            }
        }
    }

    public void setSPtru(String MaSP, int SL) {
        int sum = 0;
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(MaSP)) {
                sum = DSSP[i].getSoluong();
                DSSP[i].setSoluong(sum - SL);
            }
        }
    }

    public void setSPkhong(String MaSP) {
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(MaSP)) {
                DSSP[i].setSoluong(0);
            }
        }
    }

    public void setGiaa(String x, double gia) {
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(x)) {
                DSSP[i].setGia(gia + 5000);
            }
        }
    }

    public void ThemSP() {
        int sl, c;
        int k1 = 0;
        String s;
        SanPham sv;
        System.out.println("Nhap so luong san pham can them");
        sl = sc.nextInt();
        for (int i = 0; i < sl; i++) {
            sv = new SanPham();
            System.out.println("Hay chon loai san pham muon them!!!");
            System.out.println("1.May Tinh");
            System.out.println("2.Phu Kien");
            System.out.print("Bam phim so: ");
            c = sc.nextInt();
            switch (c) {
                case 1:
                    sv = new Maytinh();
                    sv.setMaSP();
                    if (TimkiemMa(sv.getMaSP()) == 1) {
                        sv.setSoluong();
                        sv.setLoaiSP("May Tinh");
                        sv.setTenSP(Getten(sv.getMaSP()));
                        setSP(sv.getMaSP(), sv.getSoluong());
                        sv.setGia(Getgia(sv.getMaSP()));
                        DSSP = Arrays.copyOf(DSSP, DSSP.length + 1);
                        DSSP[SLSP] = sv;
                        SLSP++;
                    } else {
                        sv.setLoaiSP("May tinh");
                        sv.setGia();
                        sv.setTenSP();
                        sv.setSoluong();
                        DSSP = Arrays.copyOf(DSSP, DSSP.length + 1);
                        DSSP[SLSP] = sv;
                        SLSP++;
                    }
                    break;
                case 2:
                    sv = new Phukien();
                    sv.setMaSP();
                    if (TimkiemMa(sv.getMaSP()) == 1) {
                        sv.setSoluong();
                        sv.setTenSP(Getten(sv.getMaSP()));
                        setSP(sv.getMaSP(), sv.getSoluong());
                        sv.setGia(Getgia(sv.getMaSP()));
                        setSP(sv.getMaSP(), sv.getSoluong());
                        DSSP = Arrays.copyOf(DSSP, DSSP.length + 1);
                        DSSP[SLSP] = sv;
                        SLSP++;
                    } else {
                        sv.setLoaiSP("Phu kien");
                        sv.setGia();
                        sv.setTenSP();
                        sv.setSoluong();
                        DSSP = Arrays.copyOf(DSSP, DSSP.length + 1);
                        DSSP[SLSP] = sv;
                        SLSP++;
                    }
                    break;
            }
        }
    }

    public void Them1SP(String x) {
        int c;
        SanPham sv = new SanPham();
        System.out.println("Hay chon loai san pham muon!!!");
        System.out.print("Bam phim so: ");
        System.out.println("1.May tinh");
        System.out.println("2.Phu kien");
        c = sc.nextInt();
        switch (c) {
            case 1:
                sv = new Maytinh();
                sv.setMaSP(x);
                sv.setLoaiSP("May tinh");
                sv.setGia();
                sv.setTenSP();
                sv.setSoluong();
                DSSP = Arrays.copyOf(DSSP, DSSP.length + 1);
                DSSP[SLSP] = sv;
                SLSP++;
                break;
            case 2:
                sv = new Phukien();
                sv.setMaSP(x);
                sv.setLoaiSP("Phu Kien");
                sv.setGia();
                sv.setTenSP();
                sv.setSoluong();
                DSSP = Arrays.copyOf(DSSP, DSSP.length + 1);
                DSSP[SLSP] = sv;
                SLSP++;
                break;
        }
    }

    public void XoaSP() {
        if (SLSP == 0) {
            System.out.println("He thong khong co san pham nao het !!!");
            return;
        }
        System.out.println("Danh sach Ma San Pham co trong he thong la : " + ListSP());

        String msv;
        do {
            System.out.print("Nhap Ma SP can xoa: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLSP; i++)
            if (DSSP[i].getMaSP().equalsIgnoreCase(msv)) {
                for (int j = i; j < SLSP - 1; j++)
                    DSSP[j] = DSSP[j + 1];
                SLSP--;
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay Ma SP can xoa");
    }

    public void Timkiemid() {
        int kt = 0;
        String msv;
        System.out.print("Nhap ma SP can tim: ");
        msv = sc.nextLine();
        for (int i = 0; i < SLSP; i++)
            if (DSSP[i].getMaSP().equalsIgnoreCase(msv)) {
                kt = 1;
                DSSP[i].toString();
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay");
    }

    public int TimkiemMa(String x) {
        for (int i = 0; i < SLSP; i++)
            if (DSSP[i].getMaSP().equals(x))
                return 1;
        return 0;
    }

    public void Timkiemname() {
        int kt = 0;
        String tkname;
        System.out.print("Nhap ten sach can tim: ");
        tkname = sc.nextLine();
        System.out.printf("%-15s%-70s%-15s%-15s%-15s", "Ma SP", "Ten SP", "Loai SP", "Gia SP", "So luong SP");
        System.out.print(
                "\n=========================================================================================================================================================================================================================\n");
        for (int i = 0; i < SLSP; i++)
            if (DSSP[i].getTenSP().equalsIgnoreCase(tkname)) {
                kt++;
                DSSP[i].XuatSP();
                System.out.print("\n");
            }
        if (kt == 0)
            System.out.println("Khong tim thay");
    }

    public void SuaSP() {
        int c;
        String c1;
        if (SLSP == 0) {
            System.out.println("He thong khong co san pham nao het !!!");
            return;
        }
        System.out.println("Danh sach ma san pham co trong he thong la : " + ListSP());
        String msv;
        do {
            System.out.print("Nhap Ma SP can sua: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLSP; i++)
            if (DSSP[i].getMaSP().equalsIgnoreCase(msv)) {
                DSSP[i] = new SanPham();
                System.out.println("1.May Tinh");
                System.out.println("2.Phu Kien");
                System.out.print("Bam phim so: ");
                c1 = sc.nextLine();
                c = Integer.parseInt(c1);
                switch (c) {
                    case 1:
                        DSSP[i] = new Maytinh();
                        DSSP[i].setMaSP(msv);
                        DSSP[i].NhapSP();
                        DSSP[i].setLoaiSP("May tinh");
                        break;
                    case 2:
                        DSSP[i] = new Phukien();
                        DSSP[i].setMaSP(msv);
                        DSSP[i].NhapSP();
                        DSSP[i].setLoaiSP("Phu kien");
                        break;
                }
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay SP de sua!!!");
    }

    public String ListSP() {
        String s = "";
        if (SLSP == 0) {
            return null;
        }
        for (int i = 0; i < SLSP; i++) {
            s += DSSP[i].getMaSP();
            if (i != SLSP - 1) {
                s += ',';
            }
        }
        return s;
    }

    public void XuatTTSP() {
        System.out.println("Danh sach ma SP co trong he thong la [ " + ListSP() + "]");
        String Mahd;
        if (ListSP() == null) {
            System.out.println("He thong khong co SP");
            return;
        }

        do {
            System.out.print("Nhap Ma SP can xuat TT : ");
            Mahd = sc.nextLine();
        } while (Mahd.isEmpty());
        if (TimkiemMa(Mahd) == 0) {
            System.out.println("Ma SP khong ton tai");
            return;
        }
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(Mahd)) {
                DSSP[i].XuatSP();
                break;
            }
        }

    }

    public void ThayDoiSLSP(String MaSP, int SLMoi) {
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(MaSP)) {
                DSSP[i].setSoluong(SLMoi);
            }
        }
    }

    public double Getgia(String MaSP) {
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(MaSP)) {
                return DSSP[i].getGia();
            }
        }
        return 0;
    }

    public String Getten(String MaSP) {
        for (int i = 0; i < DSSP.length; i++) {
            if (DSSP[i].getMaSP().equals(MaSP)) {
                return DSSP[i].getTenSP();
            }
        }
        return null;
    }

    public String TT_SP() {
        String s = "";
        for (int i = 0; i < SLSP; i++) {
            s += DSSP[i].getMaSP() + ',';
        }
        return s;
    }

    public void TTSP() {
        final int LengthSpace = 25;
        System.out.print("Ma SP" + PrintSpace(LengthSpace - "Ma SP".length()));
        System.out.print("|Loai SP" + PrintSpace(LengthSpace - "Loai SP".length()));
        System.out.print("|Ten SP" + PrintSpace(LengthSpace - "Ten SP".length()));
        System.out.print("|So luong SP" + PrintSpace(LengthSpace - "So luong SP".length()));
        System.out.print("     |Gia SP\n");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < SLSP; i++) {
            System.out.print(DSSP[i].getMaSP() + PrintSpace(LengthSpace - DSSP[i].getMaSP().length()));
            System.out.print(" " + DSSP[i].getLoaiSP() + PrintSpace(LengthSpace - DSSP[i].getLoaiSP().length()));
            System.out.print(" " + DSSP[i].getTenSP() + PrintSpace(LengthSpace - DSSP[i].getTenSP().length()));
            System.out
                    .print(" " + DSSP[i].getSoluong() + PrintSpace(25 - String.valueOf(DSSP[i].getSoluong()).length()));
            System.out.println(" " + DSSP[i].getGia());
        }
    }

    public String PrintSpace(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void timkiemSp() {
        final int LengthSpace = 25;
        String k;
        int tmp;
        String UName;
        do {
            do {
                System.out.println("Nhap vao Ma San Pham or Ten San Pham or Loai San Pham or Gia:");
                System.out.print("Moi nhap:: ");
                UName = sc.nextLine();
            } while (UName.isEmpty());
            System.out.print("Ma SP" + PrintSpace(LengthSpace - "Ma SP".length()));
            System.out.print("|Loai SP" + PrintSpace(LengthSpace - "Loai SP".length()));
            System.out.print("|Ten SP" + PrintSpace(LengthSpace - "Ten SP".length()));
            System.out.print("|So luong SP" + PrintSpace(LengthSpace - "So luong SP".length()));
            System.out.print("|Gia SP\n");
            System.out.println(
                    "-------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < SLSP; i++) {
                if (DSSP[i].getMaSP().toLowerCase().contains(UName.toLowerCase())
                        || DSSP[i].getTenSP().toLowerCase().contains(UName.toLowerCase())
                        || DSSP[i].getLoaiSP().toLowerCase().contains(UName.toLowerCase())
                        || DSSP[i].getGia().equals(UName)) {
                    System.out.print(DSSP[i].getMaSP() + PrintSpace(LengthSpace - DSSP[i].getMaSP().length()));
                    System.out
                            .print(" " + DSSP[i].getLoaiSP() + PrintSpace(LengthSpace - DSSP[i].getLoaiSP().length()));
                    System.out.print(" " + DSSP[i].getTenSP() + PrintSpace(LengthSpace - DSSP[i].getTenSP().length()));
                    System.out.print(" " + DSSP[i].getSoluong()
                            + PrintSpace(LengthSpace - String.valueOf(DSSP[i].getSoluong()).length()));
                    System.out.print(" " + DSSP[i].getGia() + "\n");
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

    public static void Ghi() {
        DSSanPham ds = new DSSanPham();
        ds.SLSP = 8;
        ds.DSSP = new SanPham[ds.SLSP];

        ds.DSSP[0] = new Maytinh("MSP-001", "Lap top", "Asus Tuf Gaming", 2000, 100);
        ds.DSSP[1] = new Maytinh("MSP-002", "Lap top", "Lenovo", 1000, 100);
        ds.DSSP[2] = new Phukien("MSP-003", "PC", "Dell Vostro 3681", 500, 200);
        ds.DSSP[3] = new Phukien("MSP-004", "PC", "ACER AS XC-895", 700, 50);
        ds.DSSP[4] = new Phukien("MSP-005", "Chuot gaming", "Logitech G Pro X", 150, 200);
        ds.DSSP[5] = new Phukien("MSP-006", "Chuot gaming", "Razer Deathadder V2", 100, 120);

        ds.ghifile();
    }

    public static void main(String[] args) {

        Ghi();
    }
}
