package helper;

//TODO FarbCodes als Enum

/**
 * @author Keil, Vladslav
 */
public class ConsoleAssistent {

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
     * Dient zum farblichen System.out.print
     *
     * @param color Color Code {@link Color} Farben von
     * @param text  Text wird automatisch Resetet
     */
    public static void print(Color color, String text) {
        System.out.printf("%s%s%s%n", color, text, Color.RESET);
    }

    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }
}
