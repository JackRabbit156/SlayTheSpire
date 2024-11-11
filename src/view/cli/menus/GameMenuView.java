package view.cli.menus;

import helper.ConsoleAssistent;
import models.game_settings.GameSettings;
import models.game_settings.structure.GameMode;

/**
 * @author Keil, Vladislav
 */
public class GameMenuView {

    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   GAME MENU    >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }

    /**
     * Darstellung der GameMenü-Optionen
     */
    public void displayMenu(){
        ConsoleAssistent.clearScreen();

        displayTitle();
        System.out.println();
        System.out.printf("%d. %s%s%n", 1, "Save Game", GameSettings.getGameMode() == GameMode.HARDCORE ? "(Not Available)" : "");
        System.out.printf("%d. %s%n", 2, "Load Game");
        System.out.printf("%d. %s%n", 3, "Change Difficulty");
        System.out.printf("%d. %s%n", 4, "Back to app.Main Menu");
        System.out.printf("%d. %s%n", 5, "Quite Game");
        System.out.println();
        System.out.printf("%d. %s%n", 0, "Back to Game");
        System.out.println();
    }
}
