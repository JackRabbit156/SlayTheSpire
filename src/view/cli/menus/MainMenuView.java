package view.cli.menus;

import helper.ConsoleAssistent;

public class MainMenuView {

    /**
     * Darstellung des Titlescreens
     * @author Loeschner, Marijan
     */
    private void displayTitle(){
        String sts = "                                    \n" + ConsoleAssistent.repeat(40, " ") +
                "                                    \n" + ConsoleAssistent.repeat(40, " ") +
                "  .--.--.       ___      .--.--.    \n" + ConsoleAssistent.repeat(40, " ") +
                " /  /    '.   ,--.'|_   /  /    '.  \n" + ConsoleAssistent.repeat(40, " ") +
                "|  :  /`. /   |  | :,' |  :  /`. /  \n" + ConsoleAssistent.repeat(40, " ") +
                ";  |  |--`    :  : ' : ;  |  |--`   \n" + ConsoleAssistent.repeat(40, " ") +
                "|  :  ;_    .;__,'  /  |  :  ;_     \n" + ConsoleAssistent.repeat(40, " ") +
                " \\  \\    `. |  |   |    \\  \\    `.  \n" + ConsoleAssistent.repeat(40, " ") +
                "  `----.   \\:__,'| :     `----.   \\ \n" + ConsoleAssistent.repeat(40, " ") +
                "  __ \\  \\  |  '  : |__   __ \\  \\  | \n" + ConsoleAssistent.repeat(40, " ") +
                " /  /`--'  /  |  | '.'| /  /`--'  / \n" + ConsoleAssistent.repeat(40, " ") +
                "'--'.     /   ;  :    ;'--'.     /  \n" + ConsoleAssistent.repeat(40, " ") +
                "  `--'---'    |  ,   /   `--'---'   \n" + ConsoleAssistent.repeat(40, " ") +
                "               ---`-'               \n" + ConsoleAssistent.repeat(40, " ") +
                "                                    \n" + ConsoleAssistent.repeat(40, " ") +
                "                                   ";
        System.out.print(sts);
        System.out.println("\n");
    }

    /**
     * Darstellung der Menü-Optionen
     */
    public void displayMenu(){
        displayTitle();
        System.out.print("\n" +
                "\t1. New Game \n" +
                "\t2. Load Game \n" +
                "\t3. Delete Savegame \n" +
                "\t4. Credits \n" +
                "\t5. Quit\n\n" +
                "\tChoose an option: ");
    }
}
