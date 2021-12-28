package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSTaiKhoan implements Serializable, docfileghifile {
    Taikhoan[] DSTK;
    private int SLTK;
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    public static int LengthSpace = 25;

    public DSTaiKhoan() {
        this.DSTK = null;
        this.SLTK = 0;
    }

    public DSTaiKhoan(Taikhoan[] DSTK, int SLTK) {
        this.DSTK = DSTK;
        this.SLTK = SLTK;
    }

    public int getSLTK() {
        return SLTK;
    }

    public void setSLTK(int SLTK) {
        this.SLTK = SLTK;
    }

    public void docfile() {
        DSTK = new Taikhoan[SLTK];
        try {
            FileInputStream fi = new FileInputStream("DSTKout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSTK = Arrays.copyOf(DSTK, DSTK.length + 1);
                Taikhoan obj = (Taikhoan) ois.readObject();
                DSTK[SLTK] = obj;
                SLTK++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSTKout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLTK; i++)
                oos.writeObject(DSTK[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean TimkiemMa(String s) {
        for (int i = 0; i < SLTK; i++) {
            if (DSTK[i].getUsername().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean TimkiemMa2(String s) {
        for (int i = 0; i < SLTK; i++) {
            if (DSTK[i].getMaTK().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void ThemUser() {
        Taikhoan[] ListTmp = new Taikhoan[SLTK + 1];
        for (int i = 0; i < SLTK + 1; i++) {
            ListTmp[i] = new Taikhoan();
            if (i != SLTK) {
                ListTmp[i].Copy(DSTK[i]);
            }
        }
        do {
            ListTmp[SLTK].setUsername();
            if (TimkiemMa(ListTmp[SLTK].getUsername())) {
                System.out.println("Username da duoc su dung");
            }
        } while (TimkiemMa(ListTmp[SLTK].getUsername()));
        do {
            ListTmp[SLTK].setMaTK();
            if (TimkiemMa2(ListTmp[SLTK].getMaTK())) {
                System.out.println("MTK da duoc su dung");
            }
        } while (TimkiemMa2(ListTmp[SLTK].getMaTK()));
        ListTmp[SLTK].setPassword();
        ListTmp[SLTK].setMaQuyen();
        SLTK += 1;
        DSTK = new Taikhoan[SLTK];
        for (int i = 0; i < SLTK; i++) {
            DSTK[i] = new Taikhoan();
            DSTK[i].Copy(ListTmp[i]);
        }
    }

    public void XoaUser() {
        System.out.println("Danh sach USER co trong he thong la :" + DSUser());

        String UName = null;
        do {
            System.out.print("Nhap UserName can xoa: ");
            UName = sc.nextLine();
        } while (UName.isEmpty());
        int i;
        for (i = 0; i < SLTK; i++) {
            if (DSTK[i].getUsername().equals(UName)) {
                for (int j = i; j < SLTK - 1; j++) {
                    DSTK[j].Copy(DSTK[j + 1]);
                }
                break;
            }
        }
        if (i >= SLTK) {
            System.out.println("Khong tim thay Username de xoa !!!");
            return;
        } else {
            SLTK -= 1;
            Taikhoan ListTmp[] = new Taikhoan[SLTK];
            for (i = 0; i < SLTK; i++) {
                ListTmp[i] = new Taikhoan();
                ListTmp[i].Copy(DSTK[i]);
            }
            DSTK = new Taikhoan[SLTK];
            for (i = 0; i < SLTK; i++) {
                DSTK[i] = new Taikhoan();
                DSTK[i].Copy(ListTmp[i]);
            }
        }
    }

    public String DSUser() {
        String s = "";
        for (int i = 0; i < DSTK.length; i++) {
            s += DSTK[i].getUsername() + ',';
        }
        return s;
    }

    public void SuaUser() {
        System.out.println("Danh sach USER co trong he thong la :" + DSUser());

        String UName;
        do {
            System.out.print("Nhap UserName can sua: ");
            UName = sc.nextLine();
        } while (UName.isEmpty());
        int i;
        for (i = 0; i < SLTK; i++) {
            if (DSTK[i].getUsername().equals(UName)) {
                break;
            }
        }
        if (i >= SLTK) {
            System.out.println("Khong tim thay Username de sua !!!");
            return;
        }

        String Username;
        do {
            do {
                System.out.print("Nhap vao Username MOI : ");
                Username = sc.nextLine();
            } while (Username.isEmpty());

            if (TimkiemMa(Username)) {
                System.out.println("Username da duoc su dung !!!!");
            }
        } while (TimkiemMa(Username));
        DSTK[i] = new Taikhoan();
        DSTK[i].setUsername(Username);
        DSTK[i].NhapUser();
    }

    public String PrintSpace(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void XemDSUser() {
        final int LengthSpace = 25;
        System.out.print("Ma TK" + PrintSpace(LengthSpace - "MaTK".length()));
        System.out.print("Username" + PrintSpace(LengthSpace - "Username".length()));
        System.out.print("Password" + PrintSpace(LengthSpace - "Password".length()));
        System.out.println("Quyen User");
        System.out.println("--------------------------------------------------------------------------------------");
        for (int i = 0; i < SLTK; i++) {
            System.out.print(DSTK[i].getMaTK() + PrintSpace(LengthSpace - DSTK[i].getMaTK().length()));
            System.out.print(DSTK[i].getUsername() + PrintSpace(LengthSpace - DSTK[i].getUsername().length()));
            System.out.print(DSTK[i].getPassword() + PrintSpace(LengthSpace - DSTK[i].getPassword().length()));
            String s = "";
            switch (DSTK[i].getMaQuyen()) {
                case 1: {
                    s = "Admin";
                    break;
                }
                case 2: {
                    s = "Nhan Vien";
                    break;
                }
                case 3: {
                    s = "Quan Ly";
                    break;

                }
            }
            System.out.println("" + s);
        }
    }

    public void TimkiemUser1() {
        final int LengthSpace = 25;
        String k;
        String chose;
        int tmp;
        XemDSUser();
        String UName;
        do {
            do {
                System.out.println("1.Nhap tim theo Username va ma Tai khoan!!! ");
                System.out.println("2.Nhap tim theo ma quyen!!!(so nguyen) 1.ADMIN 2.NHANVIEN 3.QUANLY");
                System.out.print("ban phim so: ");
                chose = sc.nextLine();
            } while (chose.isEmpty() || KTra.isNumber(chose) == false);
            do {
                if (Integer.parseInt(chose) < 1 || Integer.parseInt(chose) > 2) {
                    System.out.println("vui long nhap dung yeu cau!!!");
                    System.out.print("ban phim so: ");
                    chose = sc.nextLine();
                }
                if (Integer.parseInt(chose) == 1) {
                    System.out.println("nhap vao Ma Tai Khoan or Username can tim:");
                    UName = sc.nextLine();
                    System.out.print("Ma TK" + PrintSpace(LengthSpace - "MaTK".length()));
                    System.out.print("Username" + PrintSpace(LengthSpace - "Username".length()));
                    System.out.print("Password" + PrintSpace(LengthSpace - "Password".length()));
                    System.out.println("Quyen User");
                    System.out.println(
                            "--------------------------------------------------------------------------------------");
                    for (int i = 0; i < SLTK; i++) {
                        if (DSTK[i].getUsername().toLowerCase().contains(UName.toLowerCase())
                                || DSTK[i].getMaTK().toLowerCase().contains(UName.toLowerCase())) {
                            System.out.print(DSTK[i].getMaTK() + PrintSpace(LengthSpace - DSTK[i].getMaTK().length()));
                            System.out.print(
                                    DSTK[i].getUsername() + PrintSpace(LengthSpace - DSTK[i].getUsername().length()));
                            System.out.print(
                                    DSTK[i].getPassword() + PrintSpace(LengthSpace - DSTK[i].getPassword().length()));
                            String s = "";
                            switch (DSTK[i].getMaQuyen()) {
                                case 1: {
                                    s = "Admin";
                                    break;
                                }
                                case 2: {
                                    s = "Nhan Vien";
                                    break;
                                }
                                case 3: {
                                    s = "Quan Ly";
                                    break;

                                }
                            }
                            System.out.println("" + s);
                        }
                    }
                }
                if (Integer.parseInt(chose) == 2) {
                    System.out.println("nhap vao Ma Quyen: 1.ADMIN 2.NHANVIEN 3.QUANLY");
                    UName = sc.nextLine();
                    System.out.print("Ma TK" + PrintSpace(LengthSpace - "MaTK".length()));
                    System.out.print("Username" + PrintSpace(LengthSpace - "Username".length()));
                    System.out.print("Password" + PrintSpace(LengthSpace - "Password".length()));
                    System.out.println("Quyen User");
                    System.out.println(
                            "--------------------------------------------------------------------------------------");

                    for (int i = 0; i < SLTK; i++) {
                        if (DSTK[i].getMaQuyen() == Integer.parseInt(UName)) {
                            System.out.print(DSTK[i].getMaTK() + PrintSpace(LengthSpace - DSTK[i].getMaTK().length()));
                            System.out.print(
                                    DSTK[i].getUsername() + PrintSpace(LengthSpace - DSTK[i].getUsername().length()));
                            System.out.print(
                                    DSTK[i].getPassword() + PrintSpace(LengthSpace - DSTK[i].getPassword().length()));
                            String s = "";
                            switch (DSTK[i].getMaQuyen()) {
                                case 1: {
                                    s = "Admin";
                                    break;
                                }
                                case 2: {
                                    s = "Nhan Vien";
                                    break;
                                }
                                case 3: {
                                    s = "Quan Ly";
                                    break;
                                }
                            }
                            System.out.println("" + s);
                        }
                    }
                }
            } while (chose.isEmpty() || KTra.isNumber(chose) == false || Integer.parseInt(chose) < 1
                    || Integer.parseInt(chose) > 2);
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
        DSTaiKhoan ds = new DSTaiKhoan();
        ds.DSTK = new Taikhoan[4];
        ds.SLTK = 4;

        ds.DSTK[0] = new Taikhoan("MTK-000", "admin", "admin", 1);
        ds.DSTK[1] = new Taikhoan("MTK-001", "vantuyen", "tuyen123", 2);
        ds.DSTK[2] = new Taikhoan("MTK-002", "minhtu", "tu123", 3);

        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }
}
