package view;

import helper.Color;
import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.CardType;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Diese Klasse repräsentiert die Ansicht für den Kampf im Spiel.
 * Sie ist verantwortlich für die Darstellung des Spielers, der Gegner,
 * der Lebenspunkte und der Kartenhand. Sie zeigt die verschiedenen
 * Informationen im Konsolenformat an und informiert den Spieler über
 * Kampfergebnisse, wie Angriffe, Siege oder Niederlagen.
 *
 * @author F Alexander Warawa
 * @author OF Daniel Willig
 */
public class BattleView {

    /**
     * Zeigt die aktuellen Informationen über den Spieler, die Gegner
     * und die Handkarten des Spielers an.
     *
     * @param player Der Spieler, dessen Informationen angezeigt werden.
     * @param enemies Die Liste der Gegner, die im aktuellen Kampf sind.
     * @param hand Die Handkarten des Spielers.
     */
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
        Card cardInHand;

        String cardName;
        String cardNameFormatted;
        String cardCost;
        String cardCostFormatted;
        String cardDesc;
        String cardDescFormatted;

        for (int i = 0; i < hand.size(); i++) {
            //sieht ÜBEL kacke aus, tut aber was es soll.
            cardInHand = hand.get(i);

            cardName = cardInHand.getName();
            cardCost = String.valueOf(cardInHand.getCost());
            cardDesc = cardInHand.getDescription();

            if (cardInHand.getCardType().equals(CardType.ATTACK)) {
                cardNameFormatted = ConsoleAssistent.printStr(cardName, Color.RED);
            }
            else if (cardInHand.getCardType().equals(CardType.SKILL)) {
                cardNameFormatted = ConsoleAssistent.printStr(cardName, Color.GREEN);
            }
            else if (cardInHand.getCardType().equals(CardType.POWER)) {
                cardNameFormatted = ConsoleAssistent.printStr(cardName, Color.BLUE);
            }
            else {
                cardNameFormatted = ConsoleAssistent.printStr(cardName, Color.CYAN);
            }

            if (cardInHand.getCardRarity().equals(CardRarity.COMMON)) {
                cardCostFormatted = ConsoleAssistent.printStr("〈" + cardCost + "〉", Color.BOLD, Color.BLACKBRIGHT);
            }
            else if (cardInHand.getCardRarity().equals(CardRarity.UNCOMMON)) {
                cardCostFormatted = ConsoleAssistent.printStr("〈" + cardCost + "〉", Color.BOLD, Color.WHITEBRIGHT);
            }
            else if (cardInHand.getCardRarity().equals(CardRarity.RARE)) {
                cardCostFormatted = ConsoleAssistent.printStr("〈" + cardCost + "〉", Color.BOLD, Color.YELLOWBRIGHT);
            }
            else {
                cardCostFormatted = ConsoleAssistent.printStr("〈" + cardCost + "〉", Color.BOLD, Color.CYANBRIGHT);
            }


            cardDescFormatted = ConsoleAssistent.printStr(cardDesc, Color.ITALIC);


            System.out.printf("%d. %-" + (cardNameFormatted.length() - cardName.length() + 17) + "s%s %s%n", i + 1, cardNameFormatted, cardCostFormatted, cardDescFormatted);
        }
        System.out.println();
    }

    /**
     * Generiert eine Lebensanzeige (HP-Bar) für den Spieler oder die Gegner.
     *
     * @param currentHp Die aktuellen Lebenspunkte.
     * @param maxHp Die maximalen Lebenspunkte.
     * @return Ein String, der die Lebensanzeige darstellt.
     */
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
