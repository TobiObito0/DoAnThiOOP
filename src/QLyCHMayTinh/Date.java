package QLyCHMayTinh;

import java.io.Serializable;
import java.util.Scanner;

public class Date implements Serializable {
    private int ngay;
    private int thang;
    private int nam;
    private transient Kiemtraloi KTra = new Kiemtraloi();
    private transient Scanner sc = new Scanner(System.in);
    private static final long serialVersionUID = 1L;

    public Date() {
        this.ngay = 0;
        this.thang = 0;
        this.nam = 0;
    }

    public Date(int ngay, int thang, int nam) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay() {
        boolean Check = true;
        while (Check) {
            System.out.print("Nhap vao ngay : ");
            String tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                tmp = "NO";
            }
            if (KTra.isNumber(tmp) == true) {
                this.ngay = Integer.parseInt(tmp);
                Check = false;
            } else {
                System.out.println("Ngay phai la so !!");
            }
        }
    }

    public int getThang() {
        return thang;
    }

    public void setThang() {
        boolean Check = true;
        while (Check) {
            System.out.print("Nhap vao thang : ");
            String tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                tmp = "NO";
            }
            if (KTra.isNumber(tmp) == true && Integer.parseInt(tmp) > 0 && Integer.parseInt(tmp) < 13) {
                this.thang = Integer.parseInt(tmp);
                Check = false;
            } else {
                System.out.println("Thang phai la so va ( 0 < Thang < 13 )");
            }
        }
    }

    public int getNam() {
        return nam;
    }

    public void setNam() {
        boolean Check = true;
        while (Check) {
            System.out.print("Nhap vao nam : ");
            String tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                tmp = "NO";
            }
            if (KTra.isNumber(tmp) == true) {
                this.nam = Integer.parseInt(tmp);
                Check = false;
            } else {
                System.out.println("Nam phai la so");
            }
        }
    }

    public boolean CheckDate() {
        boolean Check = true;// = ;
        if (thang >= 1 && thang <= 12) {
            switch (thang) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12: {
                    if (ngay < 1 || ngay > 31)
                        Check = false;
                    break;
                }
                case 4:
                case 6:
                case 9:
                case 11: {
                    if (ngay < 1 || ngay > 30)
                        Check = false;
                    break;
                }
                case 2: {
                    if (((nam % 400 == 0) || (nam % 4 == 0 && nam % 100 != 0))) {
                        if (ngay < 1 || ngay > 29)
                            Check = false;
                    } else {
                        if (ngay < 1 || ngay > 28)
                            Check = false;
                    }
                    break;
                }
            }
        }
        return Check;
    }

    public void NhapDate() {
        do {
            setNgay();
            setThang();
            setNam();
            if (CheckDate() == false) {
                System.out.println("Nhap sai (dd/mm/yyyy)");
            }
        } while (CheckDate() == false);
    }

    @Override
    public String toString() {
        return ngay + "/" + thang + "/" + nam;
    }

}
