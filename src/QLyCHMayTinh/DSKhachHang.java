package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DSKhachHang implements Serializable, docfileghifile {
    Khachhang[] DSKH;
    private int SLKH;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    public static int LengthSpace = 25;

    public DSKhachHang(Khachhang[] DSKH, int SLKH) {
        this.DSKH = DSKH;
        this.SLKH = SLKH;
    }

    public DSKhachHang() {
        this.DSKH = null;
        this.SLKH = 0;
    }

    public int getSLKH() {
        return SLKH;
    }

    public void setSLKH(int SLKH) {
        this.SLKH = SLKH;
    }

    public void docfile() {
        DSKH = new Khachhang[SLKH];
        try {
            FileInputStream fi = new FileInputStream("DSKHout.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                DSKH = Arrays.copyOf(DSKH, DSKH.length + 1);
                Khachhang obj = (Khachhang) ois.readObject();
                DSKH[SLKH] = obj;
                SLKH++;
            }
            ois.close();
            fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ghifile() {
        try {
            FileOutputStream f = new FileOutputStream("DSKHout.txt");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (int i = 0; i < SLKH; i++)
                oos.writeObject(DSKH[i]);
            oos.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Them1KH() {
        Khachhang sv = new Khachhang();
        sv.setMaKH();
        if (TimkiemMa(sv.getMaKH()) == true) {
            do {
                System.out.println("Ma KH da ton tai trong he thong!!!");
                System.out.println("Vui long nhap lai !!!!!");
                System.out.println("Danh sach MaKH co san la: " + ListMaKH());
                sv.setMaKH();
            } while (TimkiemMa(sv.getMaKH()) == true);
        }
        sv.NhapKH();
        DSKH = Arrays.copyOf(DSKH, DSKH.length + 1);
        DSKH[SLKH] = sv;
        SLKH++;
    }

    public void ThemNhieuKhachHang() {
        int sl;
        Khachhang[] sv;
        System.out.print("Nhap so luong khach hang muon them: ");
        sl = sc.nextInt();
        sv = new Khachhang[sl];
        for (int i = 0; i < sl; i++) {
            System.out.printf("Tao khach hang lan %d : ", (i + 1));
            sv[i] = new Khachhang();
            sv[i].setMaKH();
            if (TimkiemMa(sv[i].getMaKH()) == true) {
                do {
                    System.out.println("Ma KH da ton tai trong he thong!!!");
                    System.out.println("Vui long nhap lai !!!!!");
                    System.out.println("Danh sach MaKH co san la: " + ListMaKH());
                    sv[i].setMaKH();
                } while (TimkiemMa(sv[i].getMaKH()) == true);
            }
            sv[i].NhapKH();
            DSKH = Arrays.copyOf(DSKH, DSKH.length + 1);
            DSKH[SLKH] = sv[i];
            SLKH++;
        }
    }

    public String ListMaKH() {
        String s = "";
        for (int i = 0; i < SLKH; i++) {
            s += DSKH[i].getMaKH();
            if (i != SLKH - 1) {
                s += ',';
            }
        }
        return s;
    }

    public void XoaKhachHang() {
        if (SLKH == 0) {
            System.out.println("He thong khong co khach hang nao het !!!");
            return;
        }
        System.out.println("Danh sach ma khach hang co trong he thong la : " + ListMaKH());

        String msv;
        System.out.println("" + SLKH + "");
        do {
            System.out.print("Nhap Ma KH can tim: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int kt = 0;
        for (int i = 0; i < SLKH; i++)
            if (DSKH[i].getMaKH().equalsIgnoreCase(msv)) {
                for (int j = i; j < SLKH - 1; j++)
                    DSKH[j] = DSKH[j + 1];
                SLKH--;
                kt = 1;
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay MaKH can xoa");
    }

    public void TimkiemtheoID() {
        if (SLKH == 0) {
            System.out.println("He thong khong co khach hang nao het !!!");
            return;
        }
        System.out.println("Danh sach ma khach hang co trong he thong la : " + ListMaKH());

        int kt = 0;
        String msv;
        do {
            System.out.print("Nhap Ma KH can tim: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        for (int i = 0; i < SLKH; i++)
            if (DSKH[i].getMaKH().equalsIgnoreCase(msv)) {
                kt = 1;
                System.out.printf("\"%-12s%-30s%-12s%-12s%-15s%-35s\n", "Ma KH", "Ho ten KH", "Gioi Tinh", "Ngay Sinh",
                        "Phone", "Dia Chi");
                DSKH[i].XuatKH();
                break;
            }
        if (kt == 0)
            System.out.println("Khong tim thay Ma KH do");
    }

    public void SuaKH() {
        if (SLKH == 0) {
            System.out.println("He thong khong co khach hang nao het !!!");
            return;
        }
        System.out.println("Danh sach ma khach hang co trong he thong la : " + ListMaKH());

        String msv;
        do {
            System.out.print("Nhap Ma KH can sua: ");
            msv = sc.nextLine();
        } while (msv.isEmpty());
        int i;
        for (i = 0; i < SLKH; i++)
            if (DSKH[i].getMaKH().equalsIgnoreCase(msv)) {
                DSKH[i] = new Khachhang();
                System.out.println("Thong tin KH truoc khi sua: ");
                System.out.printf("\"%-12s%-40s%-12s%-12s%-15s%-35s\n", "Ma KH", "Ho ten KH", "Gioi Tinh", "Ngay Sinh",
                        "Phone", "Dia Chi");
                DSKH[i].XuatKH();
                System.out
                        .println("----------------------------------------------------------------------------------");
                System.out
                        .println("-------------------------------Sua Thong Tin KH-----------------------------------");
                DSKH[i].NhapKH();
                DSKH[i].setMaKH(msv);
                break;
            }
        if (i >= SLKH) {
            System.out.println("Khong tim thay Ma KH de sua !!!");
            return;
        }
    }

    public boolean TimkiemMa(String s) {
        for (int i = 0; i < DSKH.length; i++) {
            if (DSKH[i].getMaKH().equals(s)) {
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

    public void XuatDSKhachHang() {
        if (DSKH == null) {
            System.out.println("Danh sach rong !!!!");
            return;
        }
        final int LengthSpace = 25;
        System.out.print("Ma KH" + PrintSpace(LengthSpace - "Ma KH".length()));
        System.out.print("Ho ten" + PrintSpace(LengthSpace - "Ho ten".length()));
        System.out.print("Gioi tinh" + PrintSpace(LengthSpace - "Gioi tinh".length()));
        System.out.print("Ngay sinh" + PrintSpace(LengthSpace - "Ngay sinh".length()));
        System.out.print("So dien thoai" + PrintSpace(LengthSpace - "So dien thoai".length()));
        System.out.println("Dia Chi");
        for (int i = 0; i < SLKH; i++) {
            System.out.print(DSKH[i].getMaKH() + PrintSpace(LengthSpace - DSKH[i].getMaKH().length()));
            System.out.print(DSKH[i].getHoTen() + PrintSpace(LengthSpace - DSKH[i].getHoTen().length()));
            System.out.print(DSKH[i].getGioiTinh() + PrintSpace(LengthSpace - DSKH[i].getGioiTinh().length()));
            System.out.print(DSKH[i].getNgaySinh() + PrintSpace(LengthSpace - 10));
            System.out.print(DSKH[i].getPhone() + PrintSpace(LengthSpace - DSKH[i].getPhone().length()));
            System.out.println("|" + DSKH[i].getDiaChi());
        }
    }

    public void timkiemkh() {
        final int LengthSpace = 25;
        String k;
        int tmp;
        String UName;
        do {
            do {
                System.out.println("Nhap vao Ho ten or Ma Khach hang or So dien thoai or Dia chi:");
                System.out.print("Moi nhap: ");
                UName = sc.nextLine();
            } while (UName.isEmpty());
            System.out.print("Ma KH" + PrintSpace(LengthSpace - "Ma KH".length()));
            System.out.print("Ho ten" + PrintSpace(LengthSpace - "Ho ten".length()));
            System.out.print("Gioi tinh" + PrintSpace(LengthSpace - "Gioi tinh".length()));
            System.out.print("Ngay sinh" + PrintSpace(LengthSpace - "Ngay sinh".length()));
            System.out.print("So dien thoai" + PrintSpace(LengthSpace - "So dien thoai".length()));
            System.out.println("Dia Chi");
            for (int i = 0; i < DSKH.length; i++) {
                if (DSKH[i].getHoTen().toLowerCase().contains(UName.toLowerCase())
                        || DSKH[i].getMaKH().toLowerCase().contains(UName.toLowerCase())
                        || DSKH[i].getPhone().contains(UName)
                        || DSKH[i].getDiaChi().toLowerCase().contains(UName.toLowerCase())) {
                    System.out.print(DSKH[i].getMaKH() + PrintSpace(LengthSpace - DSKH[i].getMaKH().length()));
                    System.out.print(DSKH[i].getHoTen() + PrintSpace(LengthSpace - DSKH[i].getHoTen().length()));
                    System.out.print(DSKH[i].getGioiTinh() + PrintSpace(LengthSpace - DSKH[i].getGioiTinh().length()));
                    System.out.print(DSKH[i].getNgaySinh() + PrintSpace(LengthSpace - 10));
                    System.out.print(DSKH[i].getPhone() + PrintSpace(LengthSpace - DSKH[i].getPhone().length()));
                    System.out.println(DSKH[i].getDiaChi());
                }
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
        DSKhachHang ds = new DSKhachHang();
        ds.DSKH = new Khachhang[4];
        ds.SLKH = 4;

        ds.DSKH[0] = new Khachhang("MKH-001", "Nguyen Tu", "Nam", new Date(07, 9, 2002), "012346789", "Nha Trang");
        ds.DSKH[1] = new Khachhang("MKH-002", "Nguyen Trang", "Nu", new Date(7, 9, 2002), "012346789",
                "Tp.Ho Chi Minh");
        ds.DSKH[2] = new Khachhang("MKH-003", "Nhu Tuyen", "Nam", new Date(27, 12, 2002), "012346789", "Hai Duong");
        ds.DSKH[3] = new Khachhang("MKH-004", "Cao Thanh", "Nam", new Date(23, 10, 1996), "012346789",
                "Tp.Ho Chi Minh");
        ds.ghifile();
    }

    public static void main(String[] args) {
        Ghi();
    }
}
