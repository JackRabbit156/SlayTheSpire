package de.bundeswehr.auf.slaythespire.helper;

/**
 * @author Keil, Vladslav
 */
public class ConsoleAssistant {

    /**
     * Dient zum farblichen System.out.print
     *
     * @param color Color Code {@link Color} Farben von
     * @param text  Text wird automatisch Resetet
     */
    public static void log(Color color, String text) {
        System.out.printf("%s%s%s%n", color, text, Color.RESET);
    }

    /**
     * Dient zu farblichen String Ausgabe.
     *
     * @param text   Text wird automatisch resetet
     * @param colors Color Code {@link Color} Farben von
     */
    public static void log(String text, Color... colors) {
        StringBuilder sb = new StringBuilder("");
        for (Color color : colors) {
            sb.append(color);
        }
        System.out.printf("%s%s%s%n", sb, text, Color.RESET);
    }

}
