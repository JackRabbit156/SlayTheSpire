package view;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Scanner;

public class BattleView {

    public void display(Player player, List<Enemy> enemies, List<Card> hand) {
        displayTitle();

        System.out.printf("%-20s %-10s%n", "Player:", player.getName());
        System.out.printf("%-20s %d %n", "Block:", player.getBlock());
        System.out.printf("%-20s %s (%d/%d)%n", "HP:",getHpBar(player.getCurrentHealth(), player.getMaxHealth()), player.getCurrentHealth(), player.getMaxHealth());
        System.out.printf("%-20s %10s (%d/%d)%n", "Energy:", getHpBar(player.getCurrentEnergy(), player.getMaxEnergy()), player.getCurrentEnergy(), player.getMaxEnergy());

        for(int i = 0; i< enemies.size(); i++){
            String hpBar = getHpBar(enemies.get(i).getHealth(), enemies.get(i).getMaxHealth());
            System.out.printf("%" + 81 + "s(%d) %-20s%n", "Enemy: ", i + 1, enemies.get(i).getName());
            System.out.printf("%" + 80 + "s %-20s%n", "HP:", hpBar + " (" + enemies.get(i).getHealth() + "/" + enemies.get(i).getMaxHealth() + ")");
            System.out.printf("%" + 80 + "s %-20s%n%n", "Block:", enemies.get(i).getBlock());
        }

        // Display hand at the bottom
        System.out.println("Your Cards:");
        for (int i = 0; i < hand.size(); i++)
            System.out.printf("%d. %s%n", i + 1, hand.get(i));
        System.out.println();
    }

    private String getHpBar(int currentHp, int maxHp) {
        int barLength = 20;
        int filledLength = (int) ((double) currentHp / maxHp * barLength);
        return "[" + ConsoleAssistent.repeat(filledLength, "=") + ConsoleAssistent.repeat(barLength - filledLength, " ") + "]";
    }

    public void displayAttack(String attackerName, String targetName, int damage) {
        System.out.printf("%n%s attacks %s, %d damage!%n", attackerName, targetName, damage);
    }

    public void displayVictory() {
        System.out.println("\nYou have defeated all the enemies! Victory!");
    }

    public void displayDefeat() {
        ConsoleAssistent.sleep(1000);
        ConsoleAssistent.clearScreen();

        String gameOver= " _______  _______  __   __  _______    _______  __   __  _______  ______   \n" +
                "|       ||   _   ||  |_|  ||       |  |       ||  | |  ||       ||    _ |  \n" +
                "|    ___||  |_|  ||       ||    ___|  |   _   ||  |_|  ||    ___||   | ||  \n" +
                "|   | __ |       ||       ||   |___   |  | |  ||       ||   |___ |   |_||_ \n" +
                "|   ||  ||       ||       ||    ___|  |  |_|  ||       ||    ___||    __  |\n" +
                "|   |_| ||   _   || ||_|| ||   |___   |       | |     | |   |___ |   |  | |\n" +
                "|_______||__| |__||_|   |_||_______|  |_______|  |___|  |_______||___|  |_|";
        System.out.println(gameOver);
        System.out.println("\nYou have been defeated.");

        System.out.print("\n\nEnter (c)ontinue to get to the Main Menu: ");
        new Scanner(System.in).nextLine();
    }

    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   BATTLE VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
