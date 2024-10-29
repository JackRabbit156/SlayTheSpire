package view;

import models.cards.card_structure.Card;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class BattleView {

    public void display(Player player, List<Enemy> enemies, List<Card> hand) {
        displayTitle();

        System.out.printf("%-20s %-10s\n", "Player:", player.getName());
        System.out.printf("%-20s %d %n", "Block:", player.getBlock());
        System.out.printf("%-20s %s (%d/%d)%n", "HP:",getHpBar(player.getCurrentHealth(), player.getMaxHealth()), player.getCurrentHealth(), player.getMaxHealth());
        System.out.printf("%-20s %10s (%d/%d)%n", "Energy:", getHpBar(player.getCurrentEnergy(), player.getMaxEnergy()), player.getCurrentEnergy(), player.getMaxEnergy());

        for (Enemy enemy : enemies) {
            String hpBar = getHpBar(enemy.getHealth(), enemy.getMaxHealth());
            System.out.printf("%" + 80 + "s %-20s\n", "Enemy:", enemy.getName());
            System.out.printf("%" + 80 + "s %-20s\n\n", "HP:", hpBar + " (" + enemy.getHealth() + "/" + enemy.getMaxHealth() + ")");
        }

        // Display hand at the bottom
        System.out.println("Your Cards:");
        for (int i = 0; i < hand.size(); i++)
            System.out.printf("%d. %s\n", i + 1, hand.get(i));
        System.out.println();
    }

    private String getHpBar(int currentHp, int maxHp) {
        int barLength = 20;
        int filledLength = (int) ((double) currentHp / maxHp * barLength);
        return "[" + repeat(filledLength, "=") + repeat(barLength - filledLength, " ") + "]";
    }

    private String repeat(int length, String strToRepeat){
        String returnValue = "";

        for(int i =0; i< length; i++)
            returnValue += strToRepeat;

        return returnValue;
    }

    public void displayAttack(String attackerName, String targetName, int damage) {
        System.out.printf("\n%s attacks %s, %d damage!\n", attackerName, targetName, damage);
    }

    public void displayVictory() {
        System.out.println("\nYou have defeated all the enemies! Victory!");
    }

    public void displayDefeat() {
        System.out.println("\nYou have been defeated. Game Over.");
    }

    public void displayTitle() {
        System.out.println("\n" + repeat(80, "="));
        System.out.println(repeat(29, " ") +"<<<   BATTLE VIEW   >>>                  ");
        System.out.println(repeat(80, "=") + "\n");
    }
}
