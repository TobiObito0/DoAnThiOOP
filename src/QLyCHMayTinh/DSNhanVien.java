
package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSNhanVien implements Serializable, docfileghifile {
    Nhanvien[] DSNV;
    private int SLNV;
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    public static int LengthSpace = 25;

    public DSNhanVien(Nhanvien[] DSNV, int SLNV) {
        this.DSNV = DSNV;
        this.SLNV = SLNV;
    }

    public DSNhanVien() {
        this.DSNV = null;
        this.SLNV = 0;
    }

    public int getSLNV() {
        return SLNV;
    }

    public void setSLNV(int SLNV) {
        this.SLNV = SLNV;
    }

    public void docfile() {
        DSNV = new Nhanvien[SLNV];
        try {
            FileInputStream fi = new FileInputStream("DSNVout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSNV = Arrays.copyOf(DSNV, DSNV.length + 1);
                Nhanvien obj = (Nhanvien) ois.readObject();
                DSNV[SLNV] = obj;
                SLNV++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSNVout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLNV; i++)
                oos.writeObject(DSNV[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ThemNhieuNhanVien() {
        int sl, c;
        Nhanvien[] sv;
        System.out.print("Nhap so luong nhan vien muon them: ");
        sl = sc.nextInt();
        sv = new Nhanvien[sl];
        for (int i = 0; i < sl; i++) {
            System.out.println("Ban muon them Nhan Vien o vi tri nao???");
            System.out.println("1.Quan Ly");
            System.out.println("2.Nhan vien Ban Hang");
            System.out.print("Bam phim so: ");
            c = sc.nextInt();
            switch (c) {
                case 1:
                    sv[i] = new Quanly();
                    sv[i].setMaNv();
                    if (TimkiemMa(sv[i].getMaNv()) == true) {
                        do {
                            System.out.println("Ma NV da ton tai trong he thong!!!");
                            System.out.println("Vui long nhap lai !!!!!");
                            System.out.println("Danh sach Ma NV co san la: " + ListMaNV());
                            sv[i].setMaNv();
                        } while (TimkiemMa(sv[i].getMaNv()) == true);
                    }
                    sv[i].NhapNV();
                    break;
                case 2:
                    sv[i] = new NvBanhang();
                    sv[i].setMaNv();
                    if (TimkiemMa(sv[i].getMaNv()) == true) {
                        do {
                            System.out.println("Ma NV da ton tai trong he thong!!!");
                            System.out.println("Vui long nhap lai !!!!!");
                            System.out.println("Danh sach Ma NV co san la: " + ListMaNV());
                            sv[i].setMaNv();
                        } while (TimkiemMa(sv[i].getMaNv()) == true);
                    }
                    sv[i].NhapNV();
                    break;
            }

        }
        for (int i = 0; i < sl; i++) {
            DSNV = Arrays.copyOf(DSNV, DSNV.length + 1);
            DSNV[SLNV] = sv[i];
            SLNV++;
        }
    }

    public void XoaNhanVien() {
        if (SLNV == 0) {
            System.out.println("He thong khong co nhan vien nao het !!!");
            return;
        }
        System.out.println("Danh sach ma nhan vien co trong he thong la : " + ListMaNV());

        String msv;
        do {
            System.out.print("Nhap Ma NV can tim: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLNV; i++)
            if (DSNV[i].getMaNv().equalsIgnoreCase(msv)) {
                for (int j = i; j < SLNV - 1; j++)
                    DSNV[j] = DSNV[j + 1];
                SLNV--;
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay MaNV can xoa");
    }

    public void TimkiemtheoID() {
        if (SLNV == 0) {
            System.out.println("He thong khong co nhan vien nao het !!!");
            return;
        }
        System.out.println("Danh sach ma nhan vien co trong he thong la : " + ListMaNV());

        int kt = 0;
        String msv;
        do {
            System.out.print("Nhap Ma NV can tim: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        for (int i = 0; i < SLNV; i++)
            if (DSNV[i].getMaNv().equals(msv)) {

                System.out.printf("%-12s%-20s%-20s%-20s%-15s%-15s\n", "Ma NV", "Ho ten NV", "Ngay Sinh", "Gioi Tinh",
                        "Phone", "Dia Chi");
                DSNV[i].XuatNV();
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay nhan vien!!!");
    }

    public void SuaNhanVien() {
        if (SLNV == 0) {
            System.out.println("He thong khong co nhan vien nao het !!!");
            return;
        }
        System.out.println("Danh sach ma nhan vien co trong he thong la : " + ListMaNV());
        String UName;
        do {
            System.out.print("Nhap MaNV can sua: ");
            UName = sc.nextLine();
        } while (UName.isEmpty());
        int i;
        for (i = 0; i < SLNV; i++) {
            if (DSNV[i].getMaNv().equals(UName)) {
                System.out.println("Thong tin NV truoc khi sua: ");
                System.out.printf("%-12s%-20s%-20s%-20s%-15s%-15s\n", "Ma NV", "Ho ten NV", "Ngay Sinh", "Gioi Tinh",
                        "Phone", "Dia Chi");
                DSNV[i].XuatNV();
                System.out
                        .println("----------------------------------------------------------------------------------");
                System.out
                        .println("-------------------------------Sua Thong Tin NV-----------------------------------");
                break;
            }
        }

        if (i >= SLNV) {
            System.out.println("Khong tim thay NV de sua!!!");
            return;
        }
        if (DSNV[i] instanceof Quanly) {

            DSNV[i] = new Quanly();
            Quanly tmp = (Quanly) DSNV[i];
            tmp.NhapNV();
            tmp.setMaNv(UName);
        } else {

            DSNV[i] = new NvBanhang();
            NvBanhang tmp = (NvBanhang) DSNV[i];
            tmp.NhapNV();
            tmp.setMaNv(UName);
        }
    }

    public String ListMaNV() {
        String s1 = "Quan ly : ";
        String s2 = "NV Ban hang : ";
        if (SLNV == 0) {
            return null;
        }
        for (int i = 0; i < SLNV; i++) {
            if (DSNV[i] instanceof Quanly) {
                s1 += DSNV[i].getMaNv() + ",";
            } else {
                s2 += DSNV[i].getMaNv() + ",";
            }
        }
        return "[" + s1 + "][" + s2 + "]";
    }

    public void SuaLuongCoBan() {
        String LuongCoBan;
        do {
            System.out.print("Nhap vao luong co ban moi (so nguyen): ");
            LuongCoBan = sc.nextLine();
            if (LuongCoBan.isEmpty()) {
                do {
                    System.out.print("Nhap vao luong co ban moi (so nguyen): ");
                    LuongCoBan = sc.nextLine();
                } while (LuongCoBan.isEmpty());
            }
            if (KTra.isNumber(LuongCoBan) == false) {
                System.out.println("Du lieu nhap vao khong hop le !!!");
            }
        } while (KTra.isNumber(LuongCoBan) == false);
        for (int i = 0; i < SLNV; i++) {
            DSNV[i].setLuongcoban(Integer.parseInt(LuongCoBan));
        }
    }

    public void TinhLuongChoNV() {
        if (SLNV == 0) {
            System.out.println("Khong co nhan vien nao het.Can tuyen them !!!!");
            return;
        }
        System.out.println("Ma NV co trong he thong : " + ListMaNV());
        String Manv;
        do {
            System.out.print("Nhap vao ma nhan vien can tinh luong : ");
            Manv = sc.nextLine();
        } while (Manv.isEmpty());

        int i;
        for (i = 0; i < SLNV; i++) {
            if (DSNV[i].getMaNv().equals(Manv)) {
                break;
            }
        }
        if (i >= SLNV) {
            System.out.println("Khong tim thay NV !!!");
            return;
        }

        System.out.printf("Luong nhan vien %s la %f\n", DSNV[i].getMaNv(), DSNV[i].TinhLuong());
    }

    public boolean TimkiemMa(String MaNV) {
        for (int i = 0; i < SLNV; i++) {
            if (DSNV[i].getMaNv().equals(MaNV)) {
                return true;
            }
        }
        return false;
    }

    public String PrintSpace(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void XuatDSNhanVien() {
        final int LengthSpace = 25;
        if (DSNV == null || SLNV == 0) {
            System.out.println("Danh sach rong");
            return;
        }
        System.out.print("Ma NV" + PrintSpace(LengthSpace - "Ma NV".length()));
        System.out.print("Ten NV" + PrintSpace(LengthSpace - "Ten NV".length()));
        System.out.print("Ngay Sinh" + PrintSpace(LengthSpace - "Ngay Sinh".length()));
        System.out.print("Gioi Tinh" + PrintSpace(LengthSpace - "Gioi Tinh".length()));
        System.out.print("Dia Chi" + PrintSpace(LengthSpace - "Dia Chi".length()));
        System.out.println("Phone");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < SLNV; i++) {
            System.out.print(DSNV[i].getMaNv() + PrintSpace(LengthSpace - DSNV[i].getMaNv().length()));
            System.out.print("" + DSNV[i].getHoTen() + PrintSpace(LengthSpace - DSNV[i].getHoTen().length()));
            System.out.print("" + DSNV[i].getNgaySinh().toString()
                    + PrintSpace(LengthSpace - DSNV[i].getNgaySinh().toString().length()));
            System.out.print("" + DSNV[i].getGioiTinh() + PrintSpace(LengthSpace - DSNV[i].getGioiTinh().length()));
            System.out.print(
                    "" + DSNV[i].getDiaChi() + PrintSpace(LengthSpace - String.valueOf(DSNV[i].getDiaChi()).length()));
            System.out.println("" + DSNV[i].getPhone());
        }
    }

    public static void Ghi() {
        DSNhanVien ds = new DSNhanVien();
        ds.DSNV = new Nhanvien[5];
        ds.SLNV = 5;

        ds.DSNV[0] = new NvBanhang("MNV-001", 2000, "Nguyen Hoang A", "Nam", new Date(12, 5, 1987), "0123456789",
                "123/1");
        ds.DSNV[1] = new NvBanhang("MNV-002", 2000, "Nguyen Thi B", "Nu", new Date(22, 9, 2005), "0123456789", "123/2");
        ds.DSNV[2] = new NvBanhang("MNV-003", 2000, "Nguyen Van C", "Nam", new Date(6, 5, 1999), "0123456789", "123/3");
        ds.DSNV[3] = new Quanly("MNV-004", 3000, "Nhu Van Tuyen", "Nam", new Date(22, 9, 2001), "0123456789", "123/2");
        ds.DSNV[4] = new Quanly("MNV-005", 3000, "Nguyen Minh Tu", "Nam", new Date(7, 9, 2002), "0123456789", "123/3");

        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }
}