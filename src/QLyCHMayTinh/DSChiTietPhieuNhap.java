package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSChiTietPhieuNhap implements Serializable, docfileghifile {
    CTPhieunhap[] DSCTPN;
    private int SLCTPN;
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public DSChiTietPhieuNhap() {
        this.DSCTPN = null;
        this.SLCTPN = 0;
    }

    public DSChiTietPhieuNhap(CTPhieunhap[] DSCTPN, int SLCTPN) {
        this.DSCTPN = DSCTPN;
        this.SLCTPN = SLCTPN;
    }

    public int getSLCTPN() {
        return SLCTPN;
    }

    public void setSLCTPN(int SLCTPN) {
        this.SLCTPN = SLCTPN;
    }

    public void docfile() {
        DSCTPN = new CTPhieunhap[SLCTPN];
        try {
            FileInputStream fi = new FileInputStream("DSCTPNout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSCTPN = Arrays.copyOf(DSCTPN, DSCTPN.length + 1);
                CTPhieunhap obj = (CTPhieunhap) ois.readObject();
                DSCTPN[SLCTPN] = obj;
                SLCTPN++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSCTPNout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLCTPN; i++)
                oos.writeObject(DSCTPN[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void XuatdsCTPN() {
        DSPhieunhap DSPN = new DSPhieunhap();
        DSPN.docfile();
        String s;
        int tong = 0;
        double sum = 0;
        int k = 0;
        do {
            do {
                System.out.println("Danh sach Ma PN co san la: " + DSPN.ListMaPN());
                System.out.print("Nhap ma PN can xem chi tiet: ");
                s = sc.nextLine();
                if (DSPN.TimkiemMa(s) == 0)
                    System.out.println("Ma PN ban nhap khong dung!!!");
            } while (DSPN.TimkiemMa(s) == 0);
            if (DSPN.TimkiemMa(s) == 1) {
                System.out.printf("%-12s%-12s%-12s%-20s%-15s\n", "Ma PN", "Ma SP", "Don Gia", "So Luong", "Gia tong");
                for (int j = 0; j < DSCTPN.length; j++) {
                    if (DSCTPN[j].getMaPN().equals(s)) {
                        tong += DSCTPN[j].getSLnhap() * DSCTPN[j].getGia();
                        sum = DSCTPN[j].getSLnhap() * DSCTPN[j].getGia();
                        DSCTPN[j].XuatCTphieunhap();
                        System.out.printf("%-15f", +sum);
                        System.out.print("\n");
                        sum = 0;
                    }
                }
                System.out.println("\t\t\t\t\t\t\tTong chi phi: " + tong);
            } else
                System.out.println("Khong co ma phieu nhap can xem chi tiet. Moi nhap lai");
            k = DSPN.TimkiemMa(s);
        } while (k != 1);
    }

    public void Xuat1dsCTPN(String s) {
        double sum = 0;
        System.out.printf("%-12s%-12s%-12s%-20s%-15s\n", "Ma PN", "Ma SP", "Don Gia", "So Luong", "Gia tong");
        for (int j = 0; j < SLCTPN; j++) {
            if (DSCTPN[j].getMaPN().equals(s)) {
                DSCTPN[j].XuatCTphieunhap();
                sum = DSCTPN[j].getSLnhap() * DSCTPN[j].getGia();
                System.out.printf("%-15f", +sum);
                System.out.print("\n");
            }
        }
    }

    public void ThemCTPN(String x) {
        int sl;
        CTPhieunhap sv;
        DSSanPham DSSP = new DSSanPham();
        DSSP.docfile();
        double sum = 0;
        int k = 0;
        int k1 = 0;
        String s;
        do {
            s = "";
            sv = new CTPhieunhap();
            System.out.println("");
            System.out.println("Danh sach Ma SP co san la : " + DSSP.ListSP());
            System.out.println("Nhap vao Ma San Pham ban muon them!!!(co the them moi)");
            sv.setMaSP();
            String mkh;
            if (DSSP.TimkiemMa(sv.getMaSP()) == 0) {
                do {
                    System.out.println("San pham chua co trong cua hang.");
                    System.out.println("Ban co chac chan muon them vao cua hang khong??? 1.(YES) / 2.(NO)");
                    mkh = sc.nextLine();
                    if (KTra.isNumber(mkh) == false)
                        System.out.println("Vui long nhap dung yeu cau !!!");
                    else
                        k = Integer.parseInt(mkh);
                } while (KTra.isNumber(mkh) == false || k < 1 || k > 2);
                switch (k) {
                    case 1: {
                        System.out.println("Them thong tin san pham moi!!!");
                        DSSP.Them1SP(sv.getMaSP());
                        DSSP.ghifile();
                        sv.setMaPN(x);
                        sv.setSLnhap();
                        DSSP.docfile();
                        sv.setGia(DSSP.DSSP[DSSP.GetSLMa() - 1].getGia() - 5000);

                        DSCTPN = Arrays.copyOf(DSCTPN, DSCTPN.length + 1);
                        DSCTPN[SLCTPN] = sv;
                        SLCTPN++;

                        break;
                    }
                    case 2: {
                        break;
                    }
                }
            } else {
                sv.setMaPN(x);
                sv.setGia(DSSP.Getgia(sv.getMaSP()) - 5000);
                sv.setSLnhap();
                DSSP.setSP(sv.getMaSP(), sv.getSLnhap());
                DSCTPN = Arrays.copyOf(DSCTPN, DSCTPN.length + 1);
                DSCTPN[SLCTPN] = sv;
                SLCTPN++;
            }

            System.out.println("Ban co muon nhap them chi tiet phieu nhap khong??? 1(Yes) / 2(No) ");
            s = sc.nextLine();
            if (KTra.isNumber(s) == false)
                System.out.println("Vui long nhap dung yeu cau !!!");
            else
                k1 = Integer.parseInt(s);
        } while (KTra.isNumber(s) == false || k1 == 1);
    }

    public void Xoa1CTPN(String x) {
        for (int i = 0; i < SLCTPN; i++)
            if (DSCTPN[i].getMaPN().equalsIgnoreCase(x)) {
                for (int j = i; j < SLCTPN - 1; j++)
                    DSCTPN[j] = DSCTPN[j + 1];
                SLCTPN--;
            }
    }

    public void XoaCTPN() {
        String s;
        System.out.print("Nhap ma phieu nhap: ");
        s = sc.nextLine();
        System.out.printf("%-12s%-12s%-12s%-20s%-15s\n", "Ma PN", "Ma SP", "Don Gia", "So Luong", "Gia tong");
        for (int j = 0; j < SLCTPN; j++) {
            if (DSCTPN[j].getMaPN().equals(s)) {
                DSCTPN[j].XuatCTphieunhap();
                System.out.print("\n");
            }
        }
        String msv;
        System.out.print("Nhap ma SP cua chi tiet phieu nhap can xoa: ");
        msv = sc.nextLine();
        for (int i = 0; i < SLCTPN; i++)
            if (DSCTPN[i].getMaSP().equalsIgnoreCase(msv)) {
                for (int j = i; j < SLCTPN - 1; j++)
                    DSCTPN[j] = DSCTPN[j + 1];
                SLCTPN--;
                break;
            }
    }

    public int TimkiemMa(String s) {
        for (int i = 0; i < SLCTPN; i++) {
            if (DSCTPN[i].getMaPN().equals(s)) {
                return 1;
            }
        }
        return 0;
    }

    public void SuaCTPN(String s) {
        DSPhieunhap DS = new DSPhieunhap();
        if (SLCTPN == 0) {
            System.out.println("He thong khong co phieu nhap nao het !!!");
            return;
        }
        System.out.printf("%-12s%-12s%-12s%-20s\n", "Ma HD", "Ma SP", "Gia", "So luong");
        for (int j = 0; j < SLCTPN; j++) {
            if (DSCTPN[j].getMaPN().equals(s)) {
                DSCTPN[j].XuatCTphieunhap();
                System.out.print("\n");
            }
        }
        String msv, c;
        int k;
        do {
            int kt = 0;

            do {
                System.out.print("Nhap Ma SP can sua: ");
                msv = sc.nextLine();
            } while (msv.isEmpty());
            for (int i = 0; i < SLCTPN; i++)
                if (DSCTPN[i].getMaSP().equalsIgnoreCase(msv)) {
                    DSCTPN[i] = new CTPhieunhap();
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    System.out.println(
                            "-------------------------------Sua Thong Tin CTPN---------------------------------");
                    DSCTPN[i].setGia();
                    DSCTPN[i].setSLnhap();
                    kt = 1;
                    break;
                }
            if (kt == 0)
                System.out.println("Ma HD khong co trong he thong!!!");
            System.out.print("Ban co muon sua them ? Bam phim 1(YES)/ 0(NO): ");
            c = sc.nextLine();
            k = Integer.parseInt(c);
        } while (k != 0);
    }

    public void SuaMaPN(String x, String y) {
        for (int i = 0; i < SLCTPN; i++)
            if (DSCTPN[i].getMaPN().equals(x)) {
                DSCTPN[i].setMaPN(y);
            }
    }

    public double tongCTPN(String x) {
        double sum = 0;
        for (int i = 0; i < SLCTPN; i++) {
            if (DSCTPN[i].getMaPN().equals(x)) {
                sum += DSCTPN[i].getGia();
            }
        }
        return sum;
    }

    public static void Ghi() {
        DSChiTietPhieuNhap ds = new DSChiTietPhieuNhap();
        ds.DSCTPN = new CTPhieunhap[4];
        ds.SLCTPN = 4;

        ds.DSCTPN[0] = new CTPhieunhap("MPN-001", "MSP-001", 1750, 150);
        ds.DSCTPN[1] = new CTPhieunhap("MPN-002", "MSP-002", 900, 100);
        ds.DSCTPN[2] = new CTPhieunhap("MPN-003", "MSP-006", 75, 200);
        ds.DSCTPN[3] = new CTPhieunhap("MPN-004", "MSP-001", 1800, 100);

        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }
}
