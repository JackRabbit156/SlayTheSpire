package view.menus;

public class MainMenuView {

    /**
     * Darstellung des Titlescreens
     * @author Loeschner, Marijan
     */
    private void displayTitle(){
        String sts = "                                    \n" +
                "                                    \n" +
                "  .--.--.       ___      .--.--.    \n" +
                " /  /    '.   ,--.'|_   /  /    '.  \n" +
                "|  :  /`. /   |  | :,' |  :  /`. /  \n" +
                ";  |  |--`    :  : ' : ;  |  |--`   \n" +
                "|  :  ;_    .;__,'  /  |  :  ;_     \n" +
                " \\  \\    `. |  |   |    \\  \\    `.  \n" +
                "  `----.   \\:__,'| :     `----.   \\ \n" +
                "  __ \\  \\  |  '  : |__   __ \\  \\  | \n" +
                " /  /`--'  /  |  | '.'| /  /`--'  / \n" +
                "'--'.     /   ;  :    ;'--'.     /  \n" +
                "  `--'---'    |  ,   /   `--'---'   \n" +
                "               ---`-'               \n" +
                "                                    \n" +
                "                                   ";
        System.out.print(sts);
        System.out.println("\n");
    }

    /**
     * Darstellung der Menü-Optionen
     */
    public void displayMenu(){
        displayTitle();
        System.out.println("Choose an option: " + "\n\t1. New Game \n\t2. Load Game \n\t3. Delete Savegame \n\t4. Credits \n\t5. Quit");
    }
}
