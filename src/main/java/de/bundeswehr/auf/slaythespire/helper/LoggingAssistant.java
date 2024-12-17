package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Keil, Vladslav
 */
public class LoggingAssistant {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    /**
     * Dient zur Ausgabe in {@link Color#WHITE}, falls {@value GameSettings#DEBUG_MODE}.
     *
     * @param text Text wird automatisch resetet
     */
    public static void debug(String text) {
        if (GameSettings.DEBUG_MODE) {
            log(text, Color.WHITE);
        }
    }

    /**
     * Dient zu farblichen String Ausgabe.
     *
     * @param e      Es wird automatisch der Stacktrace ausgegeben
     * @param colors Color Code {@link Color} Farben von
     */
    public static void debug(Exception e, Color... colors) {
        if (GameSettings.DEBUG_MODE) {
            StringBuilder sb = new StringBuilder();
            append(sb, e);
            log(sb, colors);
        }
    }

    /**
     * Dient zu farblichen String Ausgabe.
     *
     * @param e      Es wird automatisch die localized message genutzt
     * @param colors Color Code {@link Color} Farben von
     */
    public static void log(Exception e, Color... colors) {
        log(e.getLocalizedMessage(), colors);
    }

    /**
     * Dient zu farblichen String Ausgabe.
     *
     * @param o      Es wird automatisch die toString-Methode des Objekts genutzt
     * @param colors Color Code {@link Color} Farben von
     */
    public static void log(Object o, Color... colors) {
        log(o.toString(), colors);
    }

    /**
     * Dient zu farblichen String Ausgabe.
     *
     * @param text   Text wird automatisch resetet
     * @param colors Color Code {@link Color} Farben von
     */
    public static void log(String text, Color... colors) {
        StringBuilder sb = new StringBuilder();
        for (Color color : colors) {
            sb.append(color);
        }
        System.out.printf("%s [%s] %s%s%s%n", getTimeStamp(), getClassName(), sb, text, Color.RESET);
    }

    private static void append(StringBuilder sb, Throwable e) {
        sb.append(e.getClass().getCanonicalName());
        sb.append(": ");
        sb.append(e.getLocalizedMessage());
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("\n");
            sb.append("            at ");
            sb.append(element.getClassName());
            sb.append(".");
            sb.append(element.getMethodName());
            sb.append("(");
            sb.append(element.getFileName());
            sb.append(":");
            sb.append(element.getLineNumber());
            sb.append(")");
        }
        if (e.getCause() != null) {
            sb.append("\n  Caused By: ");
            append(sb, e);
        }
    }

    private static String getClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[3];
        for (int i = 1; i < stackTrace.length; i++) {
            if (!LoggingAssistant.class.getName().equals(stackTrace[i].getClassName())) {
                element = stackTrace[i];
                break;
            }
        }
        String className = element.getClassName();
        return className.substring(className.lastIndexOf(".") + 1);
    }

    private static String getTimeStamp() {
        return LocalDateTime.now().format(formatter);
    }

}
