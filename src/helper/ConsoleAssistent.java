package helper;
/**
 * @author Keil, Vladslav
 */
public class ConsoleAssistent {
    private static StringBuilder sb = new StringBuilder();

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * @param length      gewünschte wiederholung
     * @param strToRepeat verlängerter String
     * @return Verlängertes String
     */
    public static String repeat(int length, String strToRepeat) {
        StringBuilder returnValue = new StringBuilder("");

        for (int i = 0; i < length; i++)
            returnValue.append(strToRepeat);

        return returnValue.toString();
    }

    /**
     * Dient zum farblichen System.out.print
     *
     * @param color Color Code {@link Color} Farben von
     * @param text  Text wird automatisch Resetet
     */
    public static void println(Color color, String text) {
        System.out.printf("%s%s%s%n", color, text, Color.RESET);
    }

    /**
     * Dient zu farblichen String Ausgabe.
     * @param color Color Code {@link Color} Farben von
     * @param text Text wird automatisch Resetet
     * @return StringBuilder String
     */
    public static String printStr(Color color, String text) {
         return sb.append(color).append(text).append(Color.RESET).toString();

    }
    /**
     * Dient zum farblichen System.out.print
     *
     * @param color Color Code {@link Color} Farben von
     * @param text  Text wird automatisch Resetet
     */
    public static void print(Color color, String text) {
        System.out.printf("%s%s%s%n", color, text, Color.RESET);
    }


}
