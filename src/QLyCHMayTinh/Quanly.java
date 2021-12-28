
package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Quanly extends Nhanvien implements Serializable {
    protected int songay;
    protected String chucvu;
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Quanly() {
        super();
        this.chucvu = "Quan Ly";
        this.songay = 0;
    }

    public Quanly(String MaNv, int luongcoban, String HoTen, String GioiTinh, Date NgaySinh, String Phone,
            String DiaChi) {
        super(MaNv, luongcoban, HoTen, GioiTinh, NgaySinh, Phone, DiaChi);
        this.chucvu = "Quan Ly";
        this.songay = songay;
    }

    public int getSongay() {
        return songay;
    }

    public void setSongay(int songay) {
        this.songay = songay;
    }

    public void setSongay() {
        while (true) {
            System.out.print("Nhap so ngay lam viec cua nhan vien : ");
            String tmp = sc.nextLine();
            while (tmp.isEmpty()) {
                System.out.print("Nhap so ngay lam viec cua nhan vien : ");
                tmp = sc.nextLine();
            }
            if (KTra.isNumber(tmp) == true) {
                this.songay = Integer.parseInt(tmp);
                break;
            } else {
                System.out.println("so ngay lam viec phai la so");
            }
        }
    }

    public String getChucvu() {
        return "Quan Ly";
    }

    @Override
    public double TinhLuong() {
        return luongcoban * 6;
    }

    public void Copy(Quanly a) {
        this.MaNv = a.getMaNv();
        this.HoTen = a.getHoTen();
        this.NgaySinh = a.getNgaySinh();
        this.GioiTinh = a.getGioiTinh();
        this.Phone = a.getPhone();
        this.DiaChi = a.getDiaChi();
        this.luongcoban = a.getLuongcoban();
    }

    @Override
    public void NhapNV() {
        super.NhapNV();
        setSongay(songay);
        this.chucvu = "Quan Ly";
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ", Songaylamviec" + songay + "}";
    }
}
