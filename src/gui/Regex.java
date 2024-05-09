package gui;

import java.time.LocalDate;

public class Regex {
    public static boolean isNumber(String str) {
        return str.matches("\\d+");
    }

    public static boolean ktTen(String str) {
        return str.matches("^\\p{Lu}[\\p{Ll}\\s]*( \\p{Lu}[\\p{Ll}]*)*$");
    }

    public static boolean ktSDT(String str) {
        return str.matches("^0[0-9]{9}$");
    }

    public static boolean ktEmail(String str) {
        return str.matches("^[a-zA-Z][a-zA-Z0-9]+@gmail.com$");
    }

    public static boolean ktDateFormat(String str) {
        return str.matches("^([0-2][0-9]|(3)[0-1])\\/((0)[0-9]|(1)[0-2])\\/\\d{4}$");
    }

    public static boolean ktTruocNgayHT(String str) {
        String[] arr = str.split("/");
        int ngay = Integer.parseInt(arr[0]);
        int thang = Integer.parseInt(arr[1]);
        int nam = Integer.parseInt(arr[2]);
        LocalDate date = LocalDate.of(nam, thang, ngay);
        return date.isBefore(LocalDate.now());
    }

    public static boolean ktSauNgayHT(String str) {
        String[] arr = str.split("/");
        int ngay = Integer.parseInt(arr[0]);
        int thang = Integer.parseInt(arr[1]);
        int nam = Integer.parseInt(arr[2]);
        LocalDate date = LocalDate.of(nam, thang, ngay);
        return date.isAfter(LocalDate.now());
    }
}
