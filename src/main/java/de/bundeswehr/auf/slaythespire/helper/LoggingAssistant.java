package de.bundeswehr.auf.slaythespire.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Keil, Vladslav
 */
public class LoggingAssistant {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    /**
     * Dient zum farblichen System.out.print
     *
     * @param color Color Code {@link Color} Farben von
     * @param text  Text wird automatisch Resetet
     */
    public static void log(Color color, String text) {
        System.out.printf("%s [%s] %s%s%s%n", getTimeStamp(), getClassName(), color, text, Color.RESET);
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
        System.out.printf("%s [%s] %s%s%s%n", getTimeStamp(), getClassName(), sb, text, Color.RESET);
    }

    private static String getClassName() {
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        return className.substring(className.lastIndexOf(".") + 1);
    }

    private static String getTimeStamp() {
        return LocalDateTime.now().format(formatter);
    }

}
