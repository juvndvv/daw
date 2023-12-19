import java.util.Scanner;

public class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(String date) throws IllegalArgumentException{
        if (!isLogic(date))
            throw new IllegalArgumentException("La fecha no es correcta");

        this.day = parseDay(date);
        this.month = parseMonth(date);
        this.year = parseYear(date);
    }

    public String toString() {
        return String.format("%02d-%02d-%04d", this.day, this.month, this.year);
    }

    @Override
    public int compareTo(Date other) {
        if (this.year > other.getYear())
            return 1;
        else if (this.year < other.getYear())
            return -1;

        else if (this.month > other.getMonth())
            return 1;
        else if (this.month < other.getMonth())
            return -1;
        
        else if (this.day > other.getDay())
            return 1;
        else if (this.day < other.getDay())
            return -1;
        
        return 0;   
    }

    public int daysBetween(Date other) {
        return other.toDays() - this.toDays();
    }

    public void next() {
        if (has31Days(this.month) && this.day < 31)
            this.day++;
        else if (this.month != 2 && this.day < 30)
            this.day++;
        else if (isLeapYear(this.year) && this.day < 29)
            this.day++;
        else if (isLeapYear(this.year) && this.day < 28)
            this.day++;
        
        else if (this.month != 12) {
            this.day = 1;
            this.month++;
        }
        
        else 
            this.day = 1;
            this.month = 1;
            this.year++;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int toDays() {
        int days = 0;

        days += this.day;

        int i = 1;
        while (i != this.month) {
            if (has31Days(this.month-i))
                days += 31;
            else if (isLeapYear(this.year) && this.month - i == 2)
                days += 29;
            else if (!isLeapYear(this.year) && this.month - i == 2)
                days += 28;
            else
                days += 30;
            
            i++;
        }

        days += ((int) (365.25f * (this.year - 1)));

        return days;
    }

    // Estaticos
    final static int[] month31days = { 1, 3, 5, 7, 8, 10, 12 };

    public static Date readDate() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        return new Date(entrada);
    }

    // Privados
    private static boolean has31Days(int month) {
        int i = 0;
        while (i < month31days.length) {
            if (month31days[i] == month)
                return true;
            else
                i++;
        }
        return false;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    private static boolean isFormatOk(String date) {
        return date.matches("\\d{2}[-]\\d{2}[-]\\d{4}");
    }

    private static boolean isLogic(String date) {
        if (!isFormatOk(date))
            return false;
        
        int day = parseDay(date);
        int month = parseMonth(date);
        int year = parseYear(date);

        if (month < 1 || month > 12)
            return false;

        else if (has31Days(month) && (day < 1 || day > 31))
            return false;
        else if (month != 2 && day < 1 || day > 30)
            return false;
        else if (isLeapYear(year) && (day < 1 || day > 29))
            return false;
        else if (!isLeapYear(year) && (day < 1 || day > 28))
            return false;
        
        return true;
    }

    private static int parseDay(String date) {
        if (date.charAt(0) == '0')
            return Integer.parseInt(date.substring(1, 2));
        return Integer.parseInt(date.substring(0, 2));
    }

    private static int parseMonth(String fecha) {
        if (fecha.charAt(3) == '0') {
            return Integer.parseInt(fecha.substring(4, 5));
        }
        return Integer.parseInt(fecha.substring(3, 5));
    }

    private static int parseYear(String fecha) {
        return Integer.parseInt(fecha.substring(6));
    }
}