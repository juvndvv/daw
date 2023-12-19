public class Utils {
    public static boolean isNumeric(String s) {
        try {
            Double d = Double.parseDouble(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
