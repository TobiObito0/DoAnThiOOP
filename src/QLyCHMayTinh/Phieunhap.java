package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Phieunhap implements Serializable {
    private String maPN;
    private String maNCC;
    private String maNV;
    private Date Ngaynhap;
    private double DonGia;
    private int SLnhap;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Phieunhap() {
        this.maPN = null;
        this.maNCC = null;
        this.maNV = null;
        this.Ngaynhap = new Date();
        this.DonGia = 0;
        this.SLnhap = 0;
    }

    public Phieunhap(String maPN, String maNCC, String maNV, Date Ngaynhap) {
        this.maPN = maPN;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.Ngaynhap = Ngaynhap;
        this.DonGia = 0;
        this.SLnhap = 0;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setMaNCC() {
        do {
            System.out.println("Nhap Ma NCC: (VD: MNCC011)");
            this.maNCC = sc.nextLine();
            if (KTra.CheckMaNCC(maNCC) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (maNCC.length() < 7)
                System.out.println("Vui long nhap Ma NCC voi 7 ky tu!!!");
        } while (maNCC.isEmpty() || KTra.CheckMaNCC(maNCC) != 1 || maNCC.length() < 7);
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setMaNV() {
        do {
            System.out.println("Nhap Ma NV phu trach: (VD: MNV-001)");
            this.maNV = sc.nextLine();
            if (KTra.CheckMaNV(maNV) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (maNV.length() < 7)
                System.out.println("Vui long nhap Ma NV phu trach voi 7 ky tu!!!");
        } while (maNV.isEmpty() || KTra.CheckMaNV(maNV) != 1 || maNV.length() < 7);
    }

    public void setNgaynhap(Date Ngaynhap) {
        this.Ngaynhap = Ngaynhap;
    }

    public void setNgaynhap() {
        System.out.println("Nhap ngay nhap PN: (dd/mm/yyyy) ");
        Ngaynhap.NhapDate();

    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public void setMaPN() {
        do {
            System.out.println("Nhap Ma PN: (VD: MPN-011)");
            this.maPN = sc.nextLine();
            if (KTra.CheckMaPN(maPN) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (maPN.length() < 7)
                System.out.println("Vui long nhap Ma PN voi 7 ky tu!!!");
        } while (maPN.isEmpty() || KTra.CheckMaPN(maPN) != 1 || maPN.length() < 7);
    }

    public String getmaPN() {
        return maPN;
    }

    public String getmaNCC() {
        return maNCC;
    }

    public String getmaNV() {
        return maNV;
    }

    public Date getngaynhap() {
        return Ngaynhap;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public void setDonGia() {
        while (true) {
            String tmp;
            do {
                System.out.print("Nhap vao gia nhap cua SP : ");
                tmp = sc.nextLine();
            } while (tmp.isEmpty());
            if (KTra.isNumber(tmp) == true) {
                this.DonGia = Double.parseDouble(tmp);
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

    public void Copy(Phieunhap a) {
        this.maPN = a.maPN;
        this.maNV = a.maNV;
        this.maNCC = a.maNCC;
        this.Ngaynhap = a.Ngaynhap;
        this.SLnhap = a.SLnhap;
        this.DonGia = a.DonGia;

    }

    public void Nhapphieunhap() {
        setMaNCC();
        setMaNV();
        setNgaynhap();
        setDonGia();
        setSLnhap();
    }

    @Override
    public String toString() {
        return "{" + ", MaPhieuNhap=" + maPN + ",MaNV = " + maNV + "MaNCC=" + maNCC + ", NgayNhap=" + Ngaynhap
                + ", SoLuong=" + SLnhap + ", DonGia" + DonGia + '}';
    }

    public void Xuatphieunhap() {
        System.out.printf("%-12s%-12s%-12s%-15s", this.maPN, this.maNV, this.maNCC, this.Ngaynhap, this.SLnhap,
                this.DonGia);
    }

}
