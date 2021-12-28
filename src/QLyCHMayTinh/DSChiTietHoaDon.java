package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSChiTietHoaDon implements Serializable, docfileghifile {
    CTHoadon[] DSCTHD;
    private int SLCTHD;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public DSChiTietHoaDon() {
        this.DSCTHD = null;
        this.SLCTHD = 0;
    }

    public DSChiTietHoaDon(CTHoadon[] DSCTHD, int SLCTHD) {
        this.DSCTHD = DSCTHD;
        this.SLCTHD = SLCTHD;
    }

    public void docfile() {
        DSCTHD = new CTHoadon[SLCTHD];
        try {
            FileInputStream fi = new FileInputStream("DSCTHDout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSCTHD = Arrays.copyOf(DSCTHD, DSCTHD.length + 1);
                CTHoadon obj = (CTHoadon) ois.readObject();
                DSCTHD[SLCTHD] = obj;
                SLCTHD++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSCTHDout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLCTHD; i++)
                oos.writeObject(DSCTHD[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ThemCTHD(String x, double xx) {
        int sl;
        CTHoadon sv;
        DSSanPham DSSP = new DSSanPham();
        DSSP.docfile();
        double sum = 0;
        int k = 0;
        String s;
        do {
            s = "";
            sv = new CTHoadon();
            sv.setMaHD(x);
            sv.setMaSP();
            sv.setSoLuong();
            if (sv.getSoLuong() > DSSP.Get_SLSP(sv.getMaSP())) {
                do {
                    System.out.println("Trong kho khong du san pham!!!");
                    System.out.println("Chi con:" + DSSP.Get_SLSP(sv.getMaSP()) + " SP!!!");
                    System.out.println("Nhap lai so luong: ");
                    sv.setSoLuong();
                } while (sv.getSoLuong() > DSSP.Get_SLSP(sv.getMaSP()));
            } else if (sv.getSoLuong() <= 0) {
                do {
                    System.out.println("Vui long nhap so luong SP > 0");
                    sv.setSoLuong();
                    if (sv.getSoLuong() > DSSP.Get_SLSP(sv.getMaSP())) {
                        do {
                            System.out.println("Trong kho khong du san pham!!!");
                            System.out.println("Chi con:" + DSSP.Get_SLSP(sv.getMaSP()) + " SP!!!");
                            System.out.println("Nhap lai so luong: ");
                            sv.setSoLuong();
                        } while (sv.getSoLuong() > DSSP.Get_SLSP(sv.getMaSP()));
                    }
                } while (sv.getSoLuong() <= 0);
            }
            DSSP.setSPtru(sv.getMaSP(), sv.getSoLuong());
            for (int i = 0; i < DSSP.getSLSP(); i++) {
                if (sv.getMaSP().equals(DSSP.DSSP[i].getMaSP())) {
                    sv.setGia(DSSP.DSSP[i].getGia());
                    break;
                }
            }
            sum += sv.getSoLuong() * sv.getGia();
            DSCTHD = Arrays.copyOf(DSCTHD, DSCTHD.length + 1);
            DSCTHD[SLCTHD] = sv;
            SLCTHD++;
            System.out.println("Ban co muon nhap them chi tiet hoa don khong??? 1(Yes) / 2(No) ");
            s = sc.nextLine();
            if (KTra.isNumber(s) == false)
                System.out.println("Vui long nhap dung yeu cau !!!");
            else
                k = Integer.parseInt(s);
        } while (KTra.isNumber(s) == false || k == 1);
        Hoadon HD = new Hoadon();
        double tien;
        HD.setTienkhachtra(xx);
        do {
            tien = HD.getTienkhachtra();
            if (tien < sum) {
                double sss = sum - tien;
                System.out.println("Khach hang dua thieu tien!!");
                System.out.println("Thieu: " + sss + "VND");
                HD.setTienkhachtra();
            }
        } while (tien < sum);
        if (sum > tien) {
            double tong = tien - sum;
            System.out.println("Tra lai cho khach" + tong + "tien thua!!!");
        } else if (sum == tien) {
            System.out.println("Cam on quy khach!!! Mong quy khach lan sau ghe qua");
        }
    }

    public void Xoa1CTHD(String x) {
        for (int i = 0; i < SLCTHD; i++)
            if (DSCTHD[i].getMaHD().equalsIgnoreCase(x)) {
                for (int j = i; j < SLCTHD - 1; j++)
                    DSCTHD[j] = DSCTHD[j + 1];
                SLCTHD--;
            }
    }

    public void XoaCTHD(String MaHD) {
        int i = 0;
        // Tim User co Username la UseName
        while (i < SLCTHD) {
            while (i < SLCTHD && DSCTHD[i].getMaHD().equals(MaHD)) {
                for (int j = i; j < SLCTHD - 1; j++) {
                    DSCTHD[j].Copy(DSCTHD[j + 1]);
                }
                SLCTHD--;
            }
            i++;
        }

        CTHoadon ListTmp[] = new CTHoadon[SLCTHD];
        for (i = 0; i < SLCTHD; i++) {
            ListTmp[i] = new CTHoadon();
            ListTmp[i].Copy(DSCTHD[i]);
        }
        DSCTHD = new CTHoadon[SLCTHD];
        for (i = 0; i < SLCTHD; i++) {
            DSCTHD[i] = new CTHoadon();
            DSCTHD[i].Copy(ListTmp[i]);
        }
    }

    public void XuatCTHoaDon(String MahD) {
        System.out.println("Ma SP\t|So Luong\t|\tGia");
        for (int i = 0; i < SLCTHD; i++) {
            if (DSCTHD[i].getMaHD().equals(MahD)) {
                System.out.printf("%s\t|\t%d\t|\t%f\n", DSCTHD[i].getMaSP(), DSCTHD[i].getSoLuong(),
                        DSCTHD[i].getGia());
            }
        }
    }

    public String PrintSpace(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void SuaMaHD(String x, String y) {
        for (int i = 0; i < SLCTHD; i++)
            if (DSCTHD[i].getMaHD().equals(x)) {
                DSCTHD[i].setMaHD(y);
            }
    }

    public void SuaCTHD() {
        DSHoaDon HD = new DSHoaDon();
        if (SLCTHD == 0) {
            System.out.println("He thong khong co hoa don nao het !!!");
            return;
        }
        System.out.println("Danh sach ma hoa don co trong he thong la : " + HD.ListHD());
        String msv;
        do {
            System.out.print("Nhap Ma HD can sua: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLCTHD; i++)
            if (DSCTHD[i].getMaHD().equalsIgnoreCase(msv)) {
                DSCTHD[i] = new CTHoadon();
                System.out.println("Thong tin CTHD truoc khi sua: ");
                System.out.printf("%%-12s%-12s%-12s%-20s\n", "Ma HD", "Ma SP", "So luong", "Gia");
                DSCTHD[i].XuatCThoadon();
                System.out
                        .println("----------------------------------------------------------------------------------");
                System.out
                        .println("-------------------------------Sua Thong Tin CTHD---------------------------------");
                DSCTHD[i].setSoLuong();
                DSCTHD[i].setGia();
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Ma HD khong co trong he thong!!!");
    }

    public void Xuat1dsCTHD(String s) {
        double sum = 0;
        System.out.printf("%-12s%-12s%-12s%-20s%-15s\n", "Ma HD", "Ma SP", "Don Gia", "So Luong", "Gia tong");
        for (int j = 0; j < SLCTHD; j++) {
            if (DSCTHD[j].getMaHD().equals(s)) {
                DSCTHD[j].XuatCThoadon();
                sum = DSCTHD[j].getSoLuong() * DSCTHD[j].getGia();
                System.out.printf("%-15f", +sum);
                System.out.print("\n");
            }
        }
    }

    public void XuatdsCTHD() {
        DSHoaDon HD = new DSHoaDon();
        HD.docfile();
        String s;
        double tong = 0;
        double sum = 0;
        int k = 0;
        do {
            DSHoaDon DSHD = new DSHoaDon();
            DSHD.docfile();
            do {
                System.out.println("Danh sach Ma HD co san la: " + HD.ListHD());
                System.out.print("Nhap ma HD can xem chi tiet: ");

                s = sc.nextLine();
                if (HD.TimkiemMa(s) == 0)
                    System.out.println("Ma HD ban nhap khong dung!!!");
            } while (HD.TimkiemMa(s) == 0);
            if (DSHD.TimkiemMa(s) == 1) {
                System.out.printf("%-12s%-12s%-12s%-20s%-15s\n", "Ma HD", "Ma SP", "Don Gia", "So Luong", "Gia tong");
                for (int j = 0; j < DSCTHD.length; j++) {
                    if (DSCTHD[j].getMaHD().equals(s)) {
                        tong += DSCTHD[j].getSoLuong() * DSCTHD[j].getGia();
                        sum = DSCTHD[j].getSoLuong() * DSCTHD[j].getGia();
                        DSCTHD[j].XuatCThoadon();
                        System.out.printf("%-15f", +sum);
                        System.out.print("\n");
                        sum = 0;
                    }
                }
                System.out.println("\t\t\t\t\t\t\tTong chi phi: " + tong);
            } else
                System.out.println("Khong co ma phieu nhap can xem chi tiet. Moi nhap lai");
            k = DSHD.TimkiemMa(s);
        } while (k != 1);
    }

    public double tongCTHD(String x) {
        double sum = 0;
        for (int i = 0; i < SLCTHD; i++) {
            if (DSCTHD[i].getMaHD().equals(x)) {
                sum += DSCTHD[i].getGia();
            }
        }
        return sum;
    }

    public static void Ghi() {
        DSChiTietHoaDon ds = new DSChiTietHoaDon();
        ds.DSCTHD = new CTHoadon[6];
        ds.SLCTHD = 6;

        ds.DSCTHD[0] = new CTHoadon("MHD-001", "MSP-001", 2500, 1);
        ds.DSCTHD[1] = new CTHoadon("MHD-002", "MSP-002", 100, 1);
        ds.DSCTHD[2] = new CTHoadon("MHD-003", "MSP-003", 1250, 1);
        ds.DSCTHD[3] = new CTHoadon("MHD-004", "MSP-004", 3000, 1);
        ds.DSCTHD[4] = new CTHoadon("MHD-004", "MSP-002", 50, 1);
        ds.DSCTHD[5] = new CTHoadon("MHD-004", "MSP-003", 250, 1);

        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }
}
