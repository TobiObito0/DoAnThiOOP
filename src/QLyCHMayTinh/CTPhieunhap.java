package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class CTPhieunhap implements Serializable {
    private String MaPN;
    private String MaSP;
    private double Gia;
    private int SLnhap;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public CTPhieunhap() {
        this.MaPN = null;
        this.MaSP = null;
        this.Gia = 0;
        this.SLnhap = 0;
    }

    public CTPhieunhap(String MaPN, String MaSP, double Gia, int SLnhap) {
        this.MaPN = MaPN;
        this.MaSP = MaSP;
        this.Gia = Gia;
        this.SLnhap = SLnhap;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public void setMaPN() {
        do {
            System.out.println("Nhap Ma PN: (VD: MPN-001)");
            this.MaPN = sc.nextLine();
            if (KTra.CheckMaPN(MaPN) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaPN.length() < 7)
                System.out.println("Vui long nhap Ma PN voi 7 ky tu!!!");
        } while (MaPN.isEmpty() || KTra.CheckMaPN(MaPN) != 1 || MaPN.length() < 7);
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setMaSP() {
        do {
            System.out.println("Nhap Ma SP: (VD: MSP-001)");
            this.MaSP = sc.nextLine();
            if (KTra.CheckMaSP(MaSP) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaSP.length() < 7)
                System.out.println("Vui long nhap Ma SP voi 7 ky tu!!!");
        } while (MaSP.isEmpty() || KTra.CheckMaSP(MaSP) != 1 || MaSP.length() < 7);
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public void setGia() {
        while (true) {
            String tmp;
            do {
                System.out.print("Nhap vao gia nhap cua SP : ");
                tmp = sc.nextLine();
            } while (tmp.isEmpty());
            if (KTra.isNumber(tmp) == true) {
                this.Gia = Double.parseDouble(tmp);
                break;
            } else {
                System.out.println("Nhap lai gia SP !!!");
            }
        }
    }

    public int getSLnhap() {
        return SLnhap;
    }

    public void setSLnhap(int SLnhap) {
        this.SLnhap = SLnhap;
    }

    public void setSLnhap() {
        while (true) {
            String tmp;
            do {
                System.out.print("Nhap so luong can nhap : ");
                tmp = sc.nextLine();
            } while (tmp.isEmpty());
            if (KTra.isNumber(tmp) == true) {
                this.SLnhap = Integer.parseInt(tmp);
                break;
            } else {
                System.out.println("Du lieu nhap vao khong hop le.Vui long nhap lai !!!");
            }
        }
    }

    public void NhapCTPhieu() {
        setMaSP();
        setGia();
        setSLnhap();
    }

    @Override
    public String toString() {
        return "{" + "MaPhieuNhap=" + MaPN + ", MaSanPham=" + MaSP + ", Gia=" + Gia + ", SoluongNhap=" + SLnhap + '}';
    }

    public void XuatCTphieunhap() {
        System.out.printf("%-12s%-12s%-12s%-20s\n", this.MaPN, this.MaSP, this.Gia, this.SLnhap);
    }

}
