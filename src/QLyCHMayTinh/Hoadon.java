
package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Hoadon implements Serializable {
    private String MaHD;
    protected String MaNV;
    protected String MaKH;
    private Date NgayxuatHD;
    private double Tienkhachtra;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Hoadon(String MaHD, String MaNV, String MaKH, Date NgayxuatHD) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.NgayxuatHD = NgayxuatHD;
    }

    public Hoadon() {
        this.MaHD = null;
        this.MaNV = null;
        this.MaKH = null;
        this.NgayxuatHD = new Date();
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

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public void setMaNV() {
        do {
            System.out.println("Nhap Ma NV phu trach: (VD: MNV-001)");
            this.MaNV = sc.nextLine();
            if (KTra.CheckMaNV(MaNV) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaNV.length() < 7)
                System.out.println("Vui long nhap Ma NV voi 7 ky tu!!!");
        } while (MaNV.isEmpty() || KTra.CheckMaNV(MaNV) != 1 || MaNV.length() < 7);
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setMaKH() {
        do {
            System.out.println("Nhap Ma KH: (VD: MKH-001)");
            this.MaKH = sc.nextLine();
            if (KTra.CheckMaKH(MaKH) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaKH.length() < 7)
                System.out.println("Vui long nhap Ma KH voi 7 ky tu!!!");
        } while (MaKH.isEmpty() || KTra.CheckMaKH(MaKH) != 1 || MaKH.length() < 7);
    }

    public Date getNgayxuatHD() {
        return NgayxuatHD;
    }

    public void setNgayxuatHD(Date NgayxuatHD) {
        this.NgayxuatHD = NgayxuatHD;
    }

    public void setNgayxuatHD() {
        System.out.println("Nhap ngay xuat HD: (dd/mm/yyyy) ");
        this.NgayxuatHD.NhapDate();
    }

    public double getTienkhachtra() {
        return Tienkhachtra;
    }

    public void setTienkhachtra(double xx) {
        this.Tienkhachtra = xx;
    }

    public void setTienkhachtra() {
        while (true) {
            String tmp;
            do {
                System.out.print("Nhap vao tien khach tra: ");
                tmp = sc.nextLine();
            } while (tmp.isEmpty());
            if (KTra.isNumber(tmp)) {
                Tienkhachtra = Double.parseDouble(tmp);
                break;
            } else {
                System.out.println("Tien phai la number");
            }
        }
    }

    public void NhapHD() {
        setMaNV();
        setMaKH();
        this.setNgayxuatHD();
        setTienkhachtra();
    }

    public void Copy(Hoadon a) {
        this.MaHD = a.getMaHD();
        this.MaKH = a.getMaKH();
        this.MaKH = a.getMaKH();
        this.NgayxuatHD = a.getNgayxuatHD();
        this.Tienkhachtra = a.getTienkhachtra();
    }

    public String toString() {
        return "{" + "MaHoaDon=" + MaHD + ", MaNhanVien=" + MaNV + ", MaKhachHang=" + MaKH + ", NgayBan=" + NgayxuatHD
                + ", TienKhach=" + Tienkhachtra + '}';
    }
}
