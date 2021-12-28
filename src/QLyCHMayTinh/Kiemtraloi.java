package QLyCHMayTinh;

import java.io.Serializable;

public class Kiemtraloi implements Serializable {
    protected String ma;

    public Kiemtraloi() {
        this.ma = null;
    }

    public Kiemtraloi(String ma) {
        this.ma = ma;
    }

    public boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    public boolean Kiemtrakhoangcach(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                return false;
            }
        }
        return true;
    }

    public int CheckMaNV(String ma) {
        String s = "MAD-", s1 = ma.substring(0, 4);
        String k = "MNV-", k1 = ma.substring(0, 4);
        if (s1.equals(s) || k1.equals(k) && ma.length() == 7)
            return 1;
        else
            return 0;
    }

    public int CheckMaKH(String ma) {
        String s = "MKH-", s1 = ma.substring(0, 4);
        if (s1.equals(s) && ma.length() == 7)
            return 1;
        else
            return 0;
    }

    public int CheckMaNCC(String ma) {
        String s = "MNCC-", s1 = ma.substring(0, 5);
        if (s1.equals(s) && ma.length() == 8)
            return 1;
        else
            return 0;
    }

    public int CheckMaPL(String ma) {
        String s = "MPL-", s1 = ma.substring(0, 4);
        if (s1.equals(s) && ma.length() == 7)
            return 1;
        else
            return 0;
    }

    public int CheckMaPN(String ma) {
        String s = "MPN-", s1 = ma.substring(0, 4);
        if (s1.equals(s) && ma.length() == 7)
            return 1;
        else
            return 0;
    }

    public int CheckMaHD(String ma) {
        String s = "MHD-", s1 = ma.substring(0, 4);
        if (s1.equals(s) && ma.length() == 7)
            return 1;
        else
            return 0;
    }

    public int CheckMaSP(String ma) {
        String s = "MSP-", s1 = ma.substring(0, 4);
        if (s1.equals(s) && ma.length() == 7)
            return 1;
        else
            return 0;
    }

    public int CheckMaTK(String ma) {
        String s = "MTK-", s1 = ma.substring(0, 4);
        if (s1.equals(s) && ma.length() == 7)
            return 1;
        else
            return 0;
    }
}
