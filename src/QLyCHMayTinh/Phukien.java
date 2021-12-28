package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Phukien extends SanPham implements Serializable {
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Phukien() {
        super();
    }

    public Phukien(String MaSP, String LoaiSP, String TenSP, double Gia, int Soluong) {
        super(MaSP, "Phu kien", TenSP, Gia, Soluong);
    }

    @Override
    public void NhapSP() {
        super.setMaSP();
        super.setLoaiSP("Phu kien");
        super.setTenSP();
        super.setGia();
        super.setSoluong();
    }

    public void XuatTTTD() {
        super.XuatTTSP();
    }

    @Override
    public String toString() {
        return "{" + super.toString() + '}';
    }

}
