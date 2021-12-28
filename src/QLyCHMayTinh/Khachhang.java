package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Khachhang extends Connguoi implements Serializable {
    private String MaKH;
    private transient Kiemtraloi KTra = new Kiemtraloi();
    transient Scanner sc = new Scanner(System.in);

    public Khachhang(String MaKH, String HoTen, String GioiTinh, Date NgaySinh, String Phone, String DiaChi) {
        super(HoTen, GioiTinh, NgaySinh, Phone, DiaChi);
        this.MaKH = MaKH;
    }

    public Khachhang() {
        super();
        this.MaKH = null;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setMaKH() {
        do {
            System.out.println("Nhap Ma KH: (VD: MKH-011)");
            this.MaKH = sc.nextLine();
            if (KTra.CheckMaKH(MaKH) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaKH.length() < 7)
                System.out.println("Vui long nhap Ma KH voi 7 ky tu!!!");
        } while (MaKH.isEmpty() || KTra.CheckMaKH(MaKH) != 1 || MaKH.length() < 7);
    }

    public void NhapKH() {

        super.NhapConNguoi();
    }

    @Override
    public String toString() {
        return "{" + "Makhachhhang=" + MaKH + super.toString() + '}';
    }

    public void XuatKH() {
        System.out.printf("%-12s%-40s%-12s%-12s%-15s%-35s", this.getMaKH(), super.getHoTen(), super.getGioiTinh(),
                super.getNgaySinh(), super.getPhone(), super.getDiaChi());
    }
}
