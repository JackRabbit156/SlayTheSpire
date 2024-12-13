package de.bundeswehr.auf.slaythespire.helper;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public enum Color {

    RESET("\u001B[0m"),
    BOLD("\u001B[1m"),
    ITALIC("\u001B[3m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    BLACK_BRIGHT("\u001B[90m"),
    RED_BRIGHT("\u001B[91m"),
    GREEN_BRIGHT("\u001B[92m"),
    YELLOW_BRIGHT("\u001B[93m"),
    BLUE_BRIGHT("\u001B[94m"),
    PURPLE_BRIGHT("\u001B[95m"),
    CYAN_BRIGHT("\u001B[96m"),
    WHITE_BRIGHT("\u001B[97m"),

    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    GRAY_BACKGROUND("\u001B[47m"),
    WHITE_BACKGROUND("\u001B[48m"),

    BLACK_BACKGROUND_BRIGHT("\033[0;100m"),
    RED_BACKGROUND_BRIGHT("\033[0;101m"),
    GREEN_BACKGROUND_BRIGHT("\033[0;102m"),
    YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),
    BLUE_BACKGROUND_BRIGHT("\033[0;104m"),
    PURPLE_BACKGROUND_BRIGHT("\033[0;105m"),
    CYAN_BACKGROUND_BRIGHT("\033[0;106m"),
    WHITEB_ACKGROUND_BRIGHT("\033[0;107m");

    private String asciiCode;

    Color(String asciiCode) {
        this.asciiCode = asciiCode;
    }

    @Override
    public String toString() {
        return asciiCode;
    }

}
