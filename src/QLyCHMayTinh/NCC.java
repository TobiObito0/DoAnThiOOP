
package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class NCC implements Serializable {
    private String MaNCC;
    private String TenNCC;
    private String Phone;
    private String DiaChi;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public NCC(String MaNCC, String TenNCC, String Phone, String DiaChi) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.Phone = Phone;
        this.DiaChi = DiaChi;
    }

    public NCC() {
        this.MaNCC = null;
        this.TenNCC = null;
        this.Phone = null;
        this.DiaChi = null;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setMaNCC() {
        do {
            System.out.println("Nhap Ma NCC: (VD: MNCC-01)");
            this.MaNCC = sc.nextLine();
            if (KTra.CheckMaNCC(MaNCC) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaNCC.length() < 7)
                System.out.println("Vui long nhap Ma NCC voi 7 ky tu!!!");
        } while (MaNCC.isEmpty() || KTra.CheckMaNCC(MaNCC) != 1 || MaNCC.length() < 7);
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public void setTenNCC() {
        do {
            System.out.println("Nhap Ten NCC: ");
            this.TenNCC = sc.nextLine();
        } while (TenNCC.isEmpty());
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setPhone() {
        do {
            do {
                System.out.print("Nhap phone: ");
                this.Phone = sc.nextLine();
            } while (Phone.isEmpty());
            if (KTra.isNumber(Phone) == false) {
                System.out.println("So dien thoai phai la so");
            } else if (Phone.length() < 9 || Phone.length() > 10) {
                System.out.println("So dien thoai 9 -> 10 so");
            }
        } while (KTra.isNumber(Phone) == false || Phone.length() < 8 || Phone.length() > 10);
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setDiaChi() {
        do {
            System.out.println("Nhap Dia Chi NCC: ");
            this.DiaChi = sc.nextLine();
        } while (DiaChi.isEmpty());
    }

    public void NhapNCC() {
        setTenNCC();
        setPhone();
        setDiaChi();
    }

    @Override
    public String toString() {
        return "{" + "MaNCC=" + MaNCC + ", TenNCC=" + TenNCC + ", Phone=" + Phone + ", DiaChi=" + DiaChi + '}';
    }

    public void xuatncc() {
        System.out.printf("%-12s%-30s%-35s%-15s\n", this.getMaNCC(), this.getTenNCC(), this.getPhone(),
                this.getDiaChi());
    }

}
