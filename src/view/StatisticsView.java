package view;

import helper.ConsoleAssistent;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * @author Keil, Vladislav
 */
public class StatisticsView {
    private Scanner scanner = new Scanner(System.in);

    public void display(Player player) {
        displayTitle();

        System.out.printf("%s %n", "Player Statistics");
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        System.out.println();

        System.out.printf("%-20s %d%n", "Act:", player.getCurrentAct());
        System.out.printf("%-20s %d%n", "Distributed Damage:", GameSettings.getDistributedDamageStats());
        System.out.printf("%-20s %d%n", "Received Damage:", GameSettings.getReceivedDamageStats());
        System.out.printf("%-20s %d%n", "Received Gold:", GameSettings.getReceivedGoldStats());
        System.out.printf("%-20s %d%n", "Energy spent:", GameSettings.getEnergySpentStats());
//        System.out.printf("%-20s %d%n", "Current Playtime:", 12);
        System.out.println();
        System.out.println();


        displayMenu();
    }

    public void displayMenu() {
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        System.out.printf("%s%n", "(0) Leave Statistics Overview");
        scanner.next();
    }


    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   GAME STATICS VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
