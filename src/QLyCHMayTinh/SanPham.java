package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class SanPham implements Serializable {
    protected String MaSP;
    protected String TenSP;
    protected String LoaiSP;
    protected double Gia;
    protected int Soluong;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public SanPham(String MaSP, String LoaiSP, String TenSP, double Gia, int Soluong) {
        this.MaSP = MaSP;
        this.LoaiSP = LoaiSP;
        this.TenSP = TenSP;
        this.Gia = Gia;
        this.Soluong = Soluong;
    }

    public SanPham(String MaSP, String TenSP, double Gia, int Soluong) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Gia = Gia;
        this.Soluong = Soluong;
    }

    public SanPham() {
        this.MaSP = null;
        this.LoaiSP = null;
        this.TenSP = null;
        this.Gia = 0;
        this.Soluong = 0;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public void setGia() {
        while (true) {
            String tmp;
            System.out.print("Nhap vao gia nhap cua SP : ");
            tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                do {
                    System.out.print("Nhap vao gia nhap cua SP : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
            }
            if (KTra.isNumber(tmp) == true) {
                this.Gia = Double.parseDouble(tmp);
                break;
            } else {
                System.out.println("Nhap lai gia SP !!!");
            }
        }
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

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public void setLoaiSP() {
        do {
            System.out.println("Nhap Loai SP: ");
            this.LoaiSP = sc.nextLine();
        } while (LoaiSP.isEmpty());
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setTenSP() {
        do {
            System.out.println("Nhap Ten SP: ");
            this.TenSP = sc.nextLine();
        } while (TenSP.isEmpty());
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public void setSoluong() {
        while (true) {
            String tmp;
            System.out.print("Nhap so luong can nhap : ");
            tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                do {
                    System.out.print("Nhap so luong can nhap : ");
                    tmp = sc.nextLine();
                } while (tmp.isEmpty());
            }
            if (KTra.isNumber(tmp) == true) {
                this.Soluong = Integer.parseInt(tmp);
                break;
            } else {
                System.out.println("Du lieu nhap vao khong hop le.Vui long nhap lai !!!");
            }
        }
    }

    public void NhapSP() {
        setMaSP();
        setTenSP();
        setLoaiSP();
        setGia();
        setSoluong();
    }

    public void NhapSP1() {
        setTenSP();
        setGia();
        setSoluong();
    }

    public void XuatTTSP() {
        System.out.println("***********************************");
        System.out.println("Ma san pham : " + MaSP);
        System.out.println("Ten san pham : " + TenSP);
        System.out.println("Loai san pham : " + LoaiSP);
        System.out.println("Gia : " + Gia);
        System.out.println("So luong : " + Soluong);
        System.out.println("***********************************");
    }

    @Override
    public String toString() {
        return "{" + "MaSP=" + MaSP + ", TenSP=" + TenSP + ", LoaiSP=" + LoaiSP + ", Gia=" + Gia + ", Soluong="
                + Soluong + '}';
    }

    public void XuatSP() {
        System.out.printf("%-15s%-70s%-15s%-15s%-15s", this.MaSP, this.TenSP, this.LoaiSP, this.Gia, this.Soluong);
    }

}