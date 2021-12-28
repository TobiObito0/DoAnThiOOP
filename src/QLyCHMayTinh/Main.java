package QLyCHMayTinh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    DSNhanVien DSNV;
    DSKhachHang DSKH;
    DSNhaCungCap DSNCC;
    DSPhieunhap DSPN;
    DSChiTietPhieuNhap DSCTPN;
    DSTaiKhoan DSTK;
    DSHoaDon DSHD;
    DSChiTietHoaDon DSCTHD;
    DSSanPham DSSP;

    Kiemtraloi Ktra = new Kiemtraloi();
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Main() {
        this.DSNV = new DSNhanVien();
        this.DSKH = new DSKhachHang();
        this.DSNCC = new DSNhaCungCap();
        this.DSPN = new DSPhieunhap();
        this.DSCTPN = new DSChiTietPhieuNhap();
        this.DSTK = new DSTaiKhoan();
        this.DSHD = new DSHoaDon();
        this.DSCTHD = new DSChiTietHoaDon();
        this.DSSP = new DSSanPham();

    }

    public void Ghifile() {
        DSNV.ghifile();
        DSKH.ghifile();
        DSNCC.ghifile();
        DSPN.ghifile();
        DSCTPN.ghifile();
        DSTK.ghifile();
        DSHD.ghifile();
        DSCTHD.ghifile();
        DSSP.ghifile();

    }

    public void Docfile() {
        DSNV.docfile();
        DSKH.docfile();
        DSNCC.docfile();
        DSPN.docfile();
        DSCTPN.docfile();
        DSTK.docfile();
        DSHD.docfile();
        DSCTHD.docfile();
        DSSP.docfile();

    }

    public void login() {

        System.out.print("\n    1.DANG NHAP VAO HE THONG ");
        System.out.print("\n    2.DANG XUAT KHOI HE THONG ");
        System.out.print("\n");
    }

    public void MenuNhanVien() {
        System.out.println("---------------MENU NHAN VIEN---------------");
        System.out.println("             1.Them hoa don ");
        System.out.println("             2.Them phieu nhap ");
        System.out.println("             3.Xuat thong tin chi tiet phieu nhap ");
        System.out.println("             4.Xuat thong tin chi tiet hoa don ");
        System.out.println("             5.Dang xuat ");
    }

    public void MenuAdmin() {
        System.out.println("---------------MENU ADMIN---------------");
        System.out.println("             1.Quan ly tai khoan ");
        System.out.println("             2.Quan ly nhan vien ");
        System.out.println("             3.Quan ly khach hang ");
        System.out.println("             4.Quan Ly phieu nhap ");
        System.out.println("             5.Quan ly san pham ");
        System.out.println("             6.Quan ly nha cung cap ");
        System.out.println("             7.Quan ly hoa don ");
        System.out.println("             8.Dang xuat ");

    }

    public void AdminTK() {
        System.out.println("---------------QUAN LY TAI KHOAN---------------");
        System.out.println("             1.Xem danh sach tai khoan ");
        System.out.println("             2.Them tai khoan ");
        System.out.println("             3.Sua tai khoan  ");
        System.out.println("             4.Xoa tai khoan ");
        System.out.println("             5.Tim Kiem ");
        System.out.println("             6.Thoat ");
    }

    public void AdminKH() {
        System.out.println("---------------QUAN LY KHACH HANG---------------");
        System.out.println("             1.Xem danh sach khach hang ");
        System.out.println("             2.Them khach hang ");
        System.out.println("             3.Sua khach hang ");
        System.out.println("             4.Xoa khach hang ");
        System.out.println("             5.Tim kiem ");
        System.out.println("             6.Thoat ");
    }

    public void AdminNV() {
        System.out.println("---------------QUAN LY NHAN VIEN---------------");
        System.out.println("             1.Them Nhan Vien ");
        System.out.println("             2.Sua Nhan Vien ");
        System.out.println("             3.Xoa Nhan Vien ");
        System.out.println("             4.Tim Kiem Nhan Vien ");
        System.out.println("             5.Xem danh sach nhan vien ");
        System.out.println("             6.Sua luong co ban cho nhan vien *");
        System.out.println("             7.Tinh luong cho nhan vien ");
        System.out.println("             8.Thoat ");
    }

    public void AdminPN() {
        System.out.println("---------------QUAN LY PHIEU NHAP---------------");
        System.out.println("             1.Them Phieu Nhap ");
        System.out.println("             2.Sua Phieu Nhap ");
        System.out.println("             3.Xoa Phieu Nhap ");
        System.out.println("             4.Tim Kiem Phieu Nhap ");
        System.out.println("             5.Xem Chi Tiet 1 Phieu Nhap ");
        System.out.println("             6.Xem Danh Sach Phieu Nhap ");
        System.out.println("             7.Thong ke ");
        System.out.println("             8.Thoat ");
    }

    public void AdminThongKe() {
        System.out.println("---------------THONG KE---------------");
        System.out.println("             1.Thong ke theo thang va nam ");
        System.out.println("             2.Thong ke theo nam ");
        System.out.println("             3.Thoat ");
    }

    public void AdminSanPham() {
        System.out.println("---------------QUAN LY SAN PHAM---------------");
        System.out.println("             1.Xem Danh Sach San Pham ");
        System.out.println("             2.Them San Pham ");
        System.out.println("             3.Sua San Pham ");
        System.out.println("             4.Xoa San Pham ");
        System.out.println("             5.Tim Kiem San Pham ");
        System.out.println("             6.Thoat ");
    }

    public void AdminNCC() {
        System.out.println("---------------QUAN LY NHA CUNG CAP---------------");
        System.out.println("             1.Them Nha Cung Cap ");
        System.out.println("             2.Sua Nha Cung Cap ");
        System.out.println("             3.Xoa Thong Tin Nha Cung Cap ");
        System.out.println("             4.Tim Kiem Thong Tin Nha Cung Cap ");
        System.out.println("             5.Xuat danh sach Nha Cung Cap ");
        System.out.println("             6.Thoat ");
    }

    public void AdminHD() {
        System.out.println("---------------QUAN LY HOA DON---------------");
        System.out.println("             1.Them Hoa Don ");
        System.out.println("             2.Sua Hoa Don ");
        System.out.println("             3.Xoa Hoa Don ");
        System.out.println("             4.Tiem Kiem Hoa Don ");
        System.out.println("             5.Xem TTCT cua 1 Hoa Don ");
        System.out.println("             6.Xem Danh Sach Hoa Don ");
        System.out.println("             7.Thong Ke ");
        System.out.println("             8.Thoat ");
    }

    public void MenuQuanLy() {
        System.out.println("---------------MENU QUAN LY---------------");
        System.out.println("             1.Quan ly nhan vien ");
        System.out.println("             2.Quan ly khach hang ");
        System.out.println("             3.Quan Ly phieu nhap ");
        System.out.println("             4.Quan ly san pham ");
        System.out.println("             5.Quan ly nha cung cap ");
        System.out.println("             6.Quan ly hoa don ");
        System.out.println("             7.Dang xuat ");
    }

    public void Run() {
        Docfile();
        Ghifile();
        Run1();
    }

    public void Run1() {
        String tmp = null;
        while (true) {

            login();
            System.out.println("");
            do {

                do {
                    System.out.print("Nhap vao lua chon cua ban : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if ((KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 2)) {
                    System.out.println("Lua chon khong hop le !!!");
                }
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 2);
            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DangNhap();
                    break;
                }
                case 2: {
                    return;
                }
            }
        }
    }

    public void DangNhap() {
        String Username;
        String Password;
        int count = 0;
        int i;

        System.out.println("---------------DANG NHAP---------------");
        // Nhap UserName
        while (true) {
            do {
                System.out.print("Nhap tai khoan : ");
                Username = sc.nextLine();
            } while (Username.isEmpty());
            for (i = 0; i < DSTK.getSLTK(); i++) {
                if (DSTK.DSTK[i].getUsername().equals(Username)) {
                    break;
                }
            }
            if (i < DSTK.getSLTK()) {
                break;
            } else {
                count++;
                System.out.printf("Sai tai khoan lan %d !!!\n", count);
            }
            if (count == 3) {
                return;
            }
        }
        count = 0;
        // Nhap Password
        while (true) {
            do {
                System.out.print("Nhap mat khau : ");
                Password = sc.nextLine();
            } while (Password.isEmpty());

            if (DSTK.DSTK[i].getPassword().equals(Password)) {
                break;
            } else {
                System.out.println("Sai mat khau ");
                count++;
                System.out.printf("Sai mat khau lan %d !!!\n", count);
            }
            if (count - 3 == 0) {
                return;
            }
        }

        count = 0;

        switch (DSTK.DSTK[i].getMaQuyen()) {
            case 1: {
                Admin();
                break;
            }
            case 2: {
                NV();
                break;
            }
            case 3: {
                Quanly();
                break;
            }
        }

    }

    public void QuanLyNV() {

        String tmp;
        while (true) {

            AdminNV();

            do {
                do {
                    System.out.print("\nNhap vao lua chon cua ban : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if ((KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8))
                    System.out.println("Lua chon khong hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8);
            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSNV.ThemNhieuNhanVien();
                    DSNV.ghifile();
                    break;
                }
                case 2: {
                    DSNV.SuaNhanVien();
                    DSNV.ghifile();
                    break;
                }
                case 3: {
                    DSNV.XoaNhanVien();
                    DSNV.ghifile();
                    break;
                }
                case 4: {
                    DSNV.TimkiemtheoID();
                    break;
                }
                case 5: {
                    DSNV.XuatDSNhanVien();
                    break;
                }
                case 6: {
                    DSNV.SuaLuongCoBan();
                    DSNV.ghifile();
                    break;
                }
                case 7: {
                    DSNV.TinhLuongChoNV();
                    DSNV.ghifile();
                    break;
                }
                case 8: {
                    return;
                }
            }
        }
    }

    public void NV() {
        String tmp;
        while (true) {

            MenuNhanVien();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 5)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 5);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSHD.Them1HD();
                    DSHD.ghifile();
                    DSCTHD.ghifile();
                    break;
                }
                case 2: {
                    DSPN.Them1PN();
                    DSPN.ghifile();
                    DSCTPN.ghifile();
                    break;
                }
                case 3: {
                    DSCTPN.XuatdsCTPN();
                    DSCTPN.ghifile();
                    break;
                }
                case 4: {
                    DSCTHD.XuatdsCTHD();
                    DSCTHD.ghifile();
                    break;
                }
                case 5: {
                    return;
                }
            }
        }
    }

    public void QuanLyTK() {
        String tmp;
        while (true) {
            AdminTK();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSTK.XemDSUser();
                    break;
                }
                case 2: {
                    DSTK.ThemUser();
                    DSTK.ghifile();
                    break;
                }
                case 3: {
                    DSTK.SuaUser();
                    DSTK.ghifile();
                    break;
                }
                case 4: {
                    DSTK.XoaUser();
                    DSTK.ghifile();
                    break;
                }
                case 5: {
                    DSTK.TimkiemUser1();
                    break;
                }
                case 6: {
                    return;
                }
            }
        }
    }

    public void QuanLyKH() {
        String tmp;
        while (true) {
            AdminKH();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSKH.XuatDSKhachHang();
                    break;
                }
                case 2: {
                    DSKH.ThemNhieuKhachHang();
                    DSKH.ghifile();
                    break;
                }
                case 3: {
                    DSKH.SuaKH();
                    DSKH.ghifile();
                    break;
                }
                case 4: {
                    DSKH.XoaKhachHang();
                    DSKH.ghifile();
                    break;
                }
                case 5: {
                    DSKH.timkiemkh();
                    break;
                }
                case 6: {
                    return;
                }

            }
        }
    }

    public void ThongKepn() {
        String tmp;
        while (true) {
            AdminThongKe();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 3)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 3);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSPN.thongkethang();
                    break;
                }
                case 2: {
                    DSPN.thongkenam();
                    break;
                }
                case 3: {
                    return;
                }
            }

        }
    }

    public void ThongKehd() {
        String tmp;
        while (true) {
            AdminThongKe();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 3)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 3);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSHD.thongkethang();
                    break;
                }
                case 2: {
                    DSHD.thongkenam();
                    break;
                }
                case 3: {
                    return;
                }
            }

        }
    }

    public void QuanLyPN() {
        String tmp;
        while (true) {
            AdminPN();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSPN.ThemNhieuPhieuNhap();
                    DSCTPN.ghifile();
                    DSPN.ghifile();
                    break;
                }
                case 2: {
                    DSPN.SuaPhieuNhap();
                    DSPN.ghifile();
                    DSPN.ghifile();
                    break;
                }
                case 3: {
                    DSPN.XoaPhieuNhap();
                    DSPN.ghifile();
                    DSPN.ghifile();
                    break;
                }
                case 4: {
                    DSPN.timkiemPn();
                    break;
                }
                case 5: {
                    DSCTPN.XuatdsCTPN();
                    break;
                }
                case 6: {
                    DSPN.XuatTTPhieuNhap();
                    break;
                }
                case 7: {
                    ThongKepn();
                    break;
                }
                case 8: {
                    return;
                }
            }
        }
    }

    public void QuanLySP() {
        String tmp;
        while (true) {
            AdminSanPham();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSSP.TTSP();
                    break;
                }
                case 2: {
                    DSPN.Them1PN();
                    DSPN.ghifile();
                    DSCTPN.ghifile();
                    break;
                }
                case 3: {
                    DSSP.SuaSP();
                    DSSP.ghifile();
                    break;
                }
                case 4: {
                    DSSP.XoaSP();
                    DSSP.ghifile();
                    break;
                }
                case 5: {
                    DSSP.timkiemSp();
                    break;
                }
                case 6: {
                    return;
                }

            }
        }
    }

    public void QuanLyNCC() {
        String tmp;
        while (true) {
            AdminNCC();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 6);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSNCC.ThemNCC();
                    DSNCC.ghifile();
                    break;
                }
                case 2: {
                    DSNCC.SuaNCC();
                    DSNCC.ghifile();
                    break;
                }
                case 3: {
                    DSNCC.XoaNCC();
                    DSNCC.ghifile();
                    break;
                }
                case 4: {
                    DSNCC.timkiemNCC();
                    break;
                }
                case 5: {
                    DSNCC.XuatdsNCC();
                    break;
                }
                case 6: {
                    return;
                }

            }
        }
    }

    public void QuanLyHD() {
        String tmp;
        while (true) {
            AdminHD();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8)
                    System.out.println("Vui long nhap lua chon hop le !!!");
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    DSHD.ThemNhieuHoaDon();
                    DSHD.ghifile();
                    DSCTHD.ghifile();
                    break;
                }
                case 2: {
                    DSHD.SuaHoaDon();
                    DSHD.ghifile();
                    DSCTHD.ghifile();
                    break;
                }
                case 3: {
                    DSHD.XoaHoaDon();
                    DSHD.ghifile();
                    DSCTHD.ghifile();
                    break;
                }
                case 4: {
                    DSHD.timkiemHD();
                    break;
                }
                case 5: {
                    DSCTHD.XuatdsCTHD();
                    break;
                }
                case 6: {
                    DSHD.XuatDSHoaDon();
                    break;
                }
                case 7: {
                    ThongKehd();
                    break;
                }
                case 8: {
                    return;
                }
            }
        }
    }

    public void Admin() {
        String tmp;
        while (true) {
            MenuAdmin();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 9) {
                    System.out.println("Vui long nhap lua chon hop le !!!");
                }
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 9);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    QuanLyTK();
                    break;
                }
                case 2: {
                    QuanLyNV();
                    break;
                }
                case 3: {
                    QuanLyKH();
                    break;
                }
                case 4: {
                    QuanLyPN();

                    break;
                }
                case 5: {
                    QuanLySP();
                    break;
                }
                case 6: {
                    QuanLyNCC();
                    break;
                }
                case 7: {
                    QuanLyHD();
                    break;
                }
                case 8: {
                    return;
                }
            }
        }
    }

    public void Quanly() {
        String tmp;
        while (true) {
            MenuQuanLy();
            do {
                do {
                    System.out.print("NHAP VAO LUA CHON : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
                if (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8) {
                    System.out.println("Vui long nhap lua chon hop le !!!");
                }
            } while (KTra.isNumber(tmp) == false || Integer.parseInt(tmp) < 1 || Integer.parseInt(tmp) > 8);

            switch (Integer.parseInt(tmp)) {
                case 1: {
                    QuanLyNV();
                    break;
                }
                case 2: {
                    QuanLyKH();
                    break;
                }
                case 3: {
                    QuanLyPN();
                    break;
                }
                case 4: {
                    QuanLySP();

                    break;
                }
                case 5: {
                    QuanLyNCC();
                    break;
                }

                case 6: {
                    QuanLyHD();
                    break;
                }
                case 7: {
                    return;
                }
            }
        }
    }

    public void runnn() {
        System.out.println("" + DSCTPN.getSLCTPN());
    }

    public static void main(String[] args) {
        Main Main = new Main();
        Main.Run();
    }
}
