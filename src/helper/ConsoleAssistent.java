package helper;

public class ConsoleAssistent {
    //TODO FarbCodes als Enum
//    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_BLACK = "\u001B[30m";
//    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_GREEN = "\u001B[32m";
//    public static final String ANSI_YELLOW = "\u001B[33m";
//    public static final String ANSI_BLUE = "\u001B[34m";
//    public static final String ANSI_PURPLE = "\u001B[35m";
//    public static final String ANSI_CYAN = "\u001B[36m";
//    public static final String ANSI_WHITE = "\u001B[37m";
    Enum COLOR

    {
        BLACK {
        @Override
        public String toString () {

        }
    }
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //TODO System.out.print Funktionen, mit Farbparametern setzten.
    // printf(Enum COLOR, String text)
    /* Example
     public void printf(Enum COLOR, String text) {
        System.out.printf("%s %s %s\n", COLOR.code, text, ANSI_RESET);
     }
     */
    // print(Enum COLOR, String text)

}
