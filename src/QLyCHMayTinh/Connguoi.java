package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Connguoi implements Serializable {
    protected String HoTen;
    protected String GioiTinh;
    Date NgaySinh;
    protected String Phone;
    protected String DiaChi;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Connguoi() {
        this.HoTen = null;
        this.GioiTinh = null;
        this.NgaySinh = new Date();
        this.Phone = null;
        this.DiaChi = null;
    }

    public Connguoi(String HoTen, String GioiTinh, Date NgaySinh, String Phone, String DiaChi) {
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.Phone = Phone;
        this.DiaChi = DiaChi;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen() {
        do {
            System.out.print("Nhap ho ten: ");
            this.HoTen = sc.nextLine();
        } while (HoTen.isEmpty());
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setGioiTinh() {
        do {
            do {
                System.out.print("Nhap gioi tinh: ");
                this.GioiTinh = sc.nextLine();
            } while (GioiTinh.isEmpty());
            if (CheckGioiTinh() == false) {
                System.out.println("Gioi tinh la (Nam/Nu): ");
            }
        } while (CheckGioiTinh() == false);
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh() {
        System.out.println("Nhap ngay thang nam sinh (dd/mm/yyyy): ");
        this.NgaySinh.NhapDate();
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi() {
        do {
            System.out.print("Nhap dia chi: ");
            this.DiaChi = sc.nextLine();
        } while (DiaChi.isEmpty());
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone() {
        do {
            do {
                System.out.print("Nhap so dien thoai: ");
                this.Phone = sc.nextLine();
            } while (Phone.isEmpty());
            if (KTra.isNumber(Phone) == false) {
                System.out.println("So dien thoai phai la so");
            } else if (Phone.length() < 8 || Phone.length() > 10) {
                System.out.println("So dien thoai 8 -> 10 so");
            }
        } while (KTra.isNumber(Phone) == false || Phone.length() < 8 || Phone.length() > 10);
    }

    public void NhapConNguoi() {
        setHoTen();
        setNgaySinh();
        setGioiTinh();
        setPhone();
        setDiaChi();
    }

    public boolean CheckGioiTinh() {
        return (getGioiTinh().equalsIgnoreCase("Nam") || getGioiTinh().equalsIgnoreCase("Nu"));
    }

    @Override
    public String toString() {
        return "{" + "hoTen=" + HoTen + ", gioiTinh=" + GioiTinh + ", ngaySinh=" + NgaySinh + ", phone=" + Phone
                + ", diaChi=" + DiaChi + '}';
    }

}
