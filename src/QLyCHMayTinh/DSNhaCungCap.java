package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSNhaCungCap implements Serializable, docfileghifile {
    NCC[] DSNCC;
    private int SLNCC;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    public static int LengthSpace = 25;

    public DSNhaCungCap() {
        this.DSNCC = null;
        this.SLNCC = 0;
    }

    public DSNhaCungCap(NCC[] DSNCC, int SLNCC) {
        this.DSNCC = DSNCC;
        this.SLNCC = SLNCC;
    }

    public int getSLNCC() {
        return SLNCC;
    }

    public void setSLNCC(int SLNCC) {
        this.SLNCC = SLNCC;
    }

    public void docfile() {
        DSNCC = new NCC[SLNCC];
        try {
            FileInputStream fi = new FileInputStream("DSNCCout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSNCC = Arrays.copyOf(DSNCC, DSNCC.length + 1);
                NCC obj = (NCC) ois.readObject();
                DSNCC[SLNCC] = obj;
                SLNCC++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSNCCout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLNCC; i++)
                oos.writeObject(DSNCC[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void XuatdsNCC() {
        System.out.printf("%-12s%-30s%-35s%-35s\n", "Ma NCC", "Ten", "So dien thoai", "Dia chi");
        for (int i = 0; i < SLNCC; i++) {
            DSNCC[i].xuatncc();
            System.out.print("\n");
        }
    }

    public void ThemNCC() {
        int sl;
        NCC[] sv;
        System.out.print("Nhap so luong NCC muon them: ");
        sl = sc.nextInt();
        sv = new NCC[sl];
        for (int i = 0; i < sl; i++) {
            sv[i] = new NCC();
            sv[i].setMaNCC();
            if (TimkiemMa(sv[i].getMaNCC()) == 1) {
                do {
                    System.out.println("Ma NCC da ton tai trong he thong!!!");
                    System.out.println("Vui long nhap lai !!!!!");
                    System.out.println("Danh sach Ma NCC co san la: " + ListMNCC());
                    sv[i].setMaNCC();
                } while (TimkiemMa(sv[i].getMaNCC()) == 1);
            }
            sv[i].NhapNCC();
        }
        for (int i = 0; i < sl; i++) {
            DSNCC = Arrays.copyOf(DSNCC, DSNCC.length + 1);
            DSNCC[SLNCC] = sv[i];
            SLNCC++;
        }

    }

    public void XoaNCC() {
        if (SLNCC == 0) {
            System.out.println("He thong khong co nha cung cap nao het !!!");
            return;
        }
        System.out.println("Danh sach ma nha cung cap co trong he thong la : " + ListMNCC());

        String msv;
        do {
            System.out.print("Nhap Ma NCC can xoa: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLNCC; i++)
            if (DSNCC[i].getMaNCC().equalsIgnoreCase(msv)) {
                for (int j = i; j < SLNCC - 1; j++)
                    DSNCC[j] = DSNCC[j + 1];
                kt = 1;
                SLNCC--;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay Ma NCC can xoa");
    }

    public void Timkiemid() {
        int kt = 0;
        String msv;
        System.out.print("Nhap ma so NCC can tim: ");
        msv = sc.nextLine();
        System.out.printf("%-12s%-30s%-15s%-35s\n", "Ma NCC", "Ten", "So dien thoai", "Dia chi");
        for (int i = 0; i < SLNCC; i++)
            if (DSNCC[i].getMaNCC().equalsIgnoreCase(msv)) {
                kt = 1;
                DSNCC[i].xuatncc();
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay");
    }

    public void Timkiemname() {
        int kt = 0;
        String tkname;
        System.out.print("Nhap ho va ten can tim: ");
        tkname = sc.nextLine();
        System.out.printf("%-12s%-30s%-15s%-35s\n", "Ma NCC", "Ten", "So dien thoai", "Dia chi");
        for (int i = 0; i < SLNCC; i++)
            if (DSNCC[i].getTenNCC().equalsIgnoreCase(tkname)) {
                kt++;
                DSNCC[i].xuatncc();
                System.out.print("\n");
            }
        if (kt == 0)
            System.out.println("Khong tim thay");
    }

    public void SuaNCC() {
        if (SLNCC == 0) {
            System.out.println("He thong khong co nha cung cap nao het !!!");
            return;
        }
        System.out.println("Danh sach ma nha cung cap co trong he thong la : " + ListMNCC());

        String msv;
        do {
            System.out.print("Nhap Ma NCC can sua: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        System.out.printf("%-12s%-12s%-12s%-20s\n", "Ma HD", "Ma SP", "Gia", "So luong");
        for (int j = 0; j < SLNCC; j++) {
            if (DSNCC[j].getMaNCC().equals(msv)) {
                DSNCC[j].xuatncc();
                System.out.print("\n");
            }
        }
        int kt = 0;
        for (int i = 0; i < SLNCC; i++)
            if (DSNCC[i].getMaNCC().equalsIgnoreCase(msv)) {
                DSNCC[i] = new NCC();
                DSNCC[i].setMaNCC(msv);
                DSNCC[i].NhapNCC();
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay Ma NCC can sua");
    }

    public int TimkiemMa(String x) {
        for (int i = 0; i < SLNCC; i++)
            if (DSNCC[i].getMaNCC().equals(x))
                return 1;
        return 0;
    }

    public void Them1NCC() {
        NCC sv = new NCC();
        sv.setMaNCC();
        if (TimkiemMa(sv.getMaNCC()) == 1) {
            do {
                System.out.println("Ma NCC da ton tai trong he thong!!!");
                System.out.println("Vui long nhap lai !!!!!");
                System.out.println("Danh sach Ma NCC co san la: " + ListMNCC());
                sv.setMaNCC();
            } while (TimkiemMa(sv.getMaNCC()) == 1);
        }
        sv.NhapNCC();
        DSNCC = Arrays.copyOf(DSNCC, DSNCC.length + 1);
        DSNCC[SLNCC] = sv;
        SLNCC++;
    }

    public String ListMNCC() {
        String s = "";
        for (int i = 0; i < SLNCC; i++) {
            s += DSNCC[i].getMaNCC() + ',';
        }
        return s;
    }

    public void timkiemNCC() {
        String k;
        int tmp;
        String UName;
        do {
            do {
                System.out.println("Nhap vao Ma NCC or Ten NCC or Ma Khach Hang or Dien Thoai NCC:");
                System.out.print("ban phim so: ");
                UName = sc.nextLine();
            } while (UName.isEmpty());
            System.out.printf("%-12s%-30s%-15s%-35s\n", "Ma NCC", "Ten", "So dien thoai", "Dia chi");
            for (int i = 0; i < SLNCC; i++) {
                if (DSNCC[i].getMaNCC().toLowerCase().contains(UName.toLowerCase())
                        || DSNCC[i].getTenNCC().toLowerCase().contains(UName.toLowerCase())
                        || DSNCC[i].getPhone().toLowerCase().contains(UName.toLowerCase())
                        || DSNCC[i].getMaNCC().toLowerCase().contains(UName.toLowerCase()))
                    DSNCC[i].xuatncc();
                System.out.print("\n");
            }
            do {
                System.out.println("Ban muon tim tiep hay khong??  1.(YES) / 2.(NO)");
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
        DSNhaCungCap ds = new DSNhaCungCap();
        ds.DSNCC = new NCC[4];
        ds.SLNCC = 4;

        ds.DSNCC[0] = new NCC("MNCC-001", "ABC01", "0123456789", "255/255/255/0");
        ds.DSNCC[1] = new NCC("MNCC-002", "ABC02", "0123456789", "255/255/255/1");
        ds.DSNCC[2] = new NCC("MNCC-003", "ABC03", "0123456789", "255/255/255/2");
        ds.DSNCC[3] = new NCC("MNCC-004", "ABC04", "0123456789", "255/255/255/3");

        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }
}
