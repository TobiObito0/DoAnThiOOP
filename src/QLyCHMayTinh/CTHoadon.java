package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class CTHoadon implements Serializable {
    private String MaHD;
    private String MaSP;
    private double Gia;
    private int SoLuong;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public CTHoadon(String MaHD, String MaSP, int SoLuong, double Gia) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
    }

    public CTHoadon() {
        this.MaHD = null;
        this.MaSP = null;
        this.SoLuong = 0;
        this.Gia = 0;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaHD() {
        do {
            System.out.println("Nhap Ma HD: (VD: MHD-001)");
            this.MaHD = sc.nextLine();
            if (KTra.CheckMaHD(MaHD) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaHD.length() < 7)
                System.out.println("Vui long nhap Ma HD voi 7 ky tu!!!");
        } while (MaHD.isEmpty() || KTra.CheckMaHD(MaHD) != 1 || MaHD.length() < 7);
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

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setSoLuong() {
        while (true) {
            String tmp;
            do {
                System.out.print("Nhap so luong can nhap : ");
                tmp = sc.nextLine();
            } while (tmp.isEmpty());
            if (KTra.isNumber(tmp) == true) {
                this.SoLuong = Integer.parseInt(tmp);
                break;
            } else {
                System.out.println("Du lieu nhap vao khong hop le.Vui long nhap lai !!!");
            }
        }
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public void setGia() {
        this.Gia = Gia;
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

    public void NhapCTHD() {
        setMaSP();
        setSoLuong();
        setGia();
    }

    public void Copy(CTHoadon a) {
        this.MaHD = a.getMaHD();
        this.MaSP = a.getMaSP();
        this.SoLuong = a.getSoLuong();
        this.Gia = a.getGia();
    }

    public double TongHD() {
        return this.Gia * this.SoLuong;
    }

    @Override
    public String toString() {
        return "{ MaHoaDon = " + this.getMaHD() + ", Gia = " + this.getGia() + ",Soluong = " + this.getSoLuong() + "}";
    }

    public void XuatCThoadon() {
        System.out.printf("%-12s%-12s%-12s%-20s\n", this.getMaHD(), this.getMaSP(), this.getSoLuong(), this.getGia());
    }
}
