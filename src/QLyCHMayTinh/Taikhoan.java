
package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Taikhoan implements Serializable {
    private String MaTK;
    private String username;
    private String password;
    private int MaQuyen;

    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);

    public Taikhoan() {
        this.username = null;
        this.password = null;
        this.MaQuyen = 0;
        this.MaTK = null;
    }

    public Taikhoan(String MaTK, String username, String password, int MaQuyen) {
        this.MaTK = MaTK;
        this.username = username;
        this.password = password;
        this.MaQuyen = MaQuyen;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public void setMaTK() {
        do {
            System.out.println("Nhap Ma TK: (VD: MTK-001)");
            this.MaTK = sc.nextLine();
            if (KTra.CheckMaTK(MaTK) != 1)
                System.out.println("Khong dung du lieu. Xin nhap lai !!!");
            else if (MaTK.length() < 7)
                System.out.println("Vui long nhap Ma TK voi 7 ky tu!!!");
        } while (MaTK.isEmpty() || KTra.CheckMaTK(MaTK) != 1 || MaTK.length() < 7);
    }

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen() {
        while (true) {
            String tmp;
            do {
                System.out.print("Nhap vao quyen (1.Admin/2.Nhanvien/3.Quanly) : ");
                tmp = sc.nextLine();
            } while (tmp.isEmpty());
            if (KTra.isNumber(tmp) == true && Integer.parseInt(tmp) >= 1 && Integer.parseInt(tmp) <= 3) {
                MaQuyen = Integer.parseInt(tmp);
                break;
            } else {
                System.out.println("Vui long nhap lai Ma Quyen(1 or 2 or 3)!!!");
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        do {
            do {
                System.out.print("Nhap Username: ");
                username = sc.nextLine();
            } while (this.username.isEmpty());
            if (KTra.Kiemtrakhoangcach(username) == false) {
                System.out.println("User khong duoc chua khoang trang");
            } else if (username.length() > 15) {
                System.out.println("Username qua dai !!!");
            }
        } while (KTra.Kiemtrakhoangcach(username) == false || username.length() > 15);
    }

    public void setUsername(String username) {
        if (KTra.Kiemtrakhoangcach(username) == false || username.length() > 15) {
            System.out.println("User khong duoc chua khoang trang or User qua dai");
            System.out.println("Vui long nhap lai");
            setUsername();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        do {
            do {
                System.out.print("Nhap Password : ");
                password = sc.nextLine();
            } while (this.password.isEmpty());
            if (KTra.Kiemtrakhoangcach(password) == false) {
                System.out.println("Password khong duoc chua khoang trang");
            }
        } while (KTra.Kiemtrakhoangcach(password) == false);
    }

    public void NhapUser() {
        setMaTK();
        setUsername();
        setPassword();
        setMaQuyen();
    }

    public void Copy(Taikhoan a) {
        this.MaTK = a.getMaTK();
        this.username = a.getUsername();
        this.password = a.getPassword();
        this.MaQuyen = a.getMaQuyen();
    }

    @Override
    public String toString() {
        return "{" + "MaTaikhoan=" + MaTK + "Username=" + username + ", Password=" + password + ", Quyen=" + MaQuyen
                + '}';
    }
}
