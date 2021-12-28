package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Nhanvien extends Connguoi implements Serializable {
    protected String MaNv;
    protected int luongcoban;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Nhanvien() {
        super();
        this.MaNv = null;
        this.luongcoban = 0;
    }

    public Nhanvien(String MaNv, int luongcoban, String HoTen, String GioiTinh, Date NgaySinh, String Phone,
            String DiaChi) {
        super(HoTen, GioiTinh, NgaySinh, Phone, DiaChi);
        this.MaNv = MaNv;
        this.luongcoban = luongcoban;
    }

    public void setMaNv(String MaNv) {
        this.MaNv = MaNv;
    }

    public void setLuongcoban(int aa) {
        this.luongcoban = aa;
    }

    public String getMaNv() {
        return MaNv;
    }

    public void setMaNv() {
        do {
            System.out.println("Nhap Ma Nv: (VD: MNV-011)");
            this.MaNv = sc.nextLine();
            if (KTra.CheckMaNV(MaNv) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaNv.length() < 7)
                System.out.println("Vui long nhap Ma NV voi 7 ky tu!!!");
        } while (MaNv.isEmpty() || KTra.CheckMaNV(MaNv) != 1 || MaNv.length() < 7);
    }

    public int getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban() {
        while (true) {
            System.out.print("Nhap luong co ban cua nhan vien : ");
            String tmp = sc.nextLine();
            while (tmp.isEmpty()) {
                System.out.print("Nhap luong co ban cua nhan vien : ");
                tmp = sc.nextLine();
            }
            if (KTra.isNumber(tmp) == true) {
                this.luongcoban = Integer.parseInt(tmp);
                break;
            } else {
                System.out.println("Luong co ban phai la so");
            }
        }
    }

    public void NhapNV() {

        super.NhapConNguoi();
    }

    @Override
    public String toString() {
        return "{" + "MaNV=" + MaNv + ", " + super.toString() + ", LuongCoBan=" + luongcoban + '}';
    }

    abstract double TinhLuong();

    public void XuatNV() {
        System.out.printf("%-12s%-20s%-20s%-20s%-15s%-15s\n", this.getMaNv(), this.getHoTen(), this.getNgaySinh(),
                this.getGioiTinh(), this.getPhone(), this.getDiaChi());
    }

}
