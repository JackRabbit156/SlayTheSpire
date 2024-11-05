package view;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;

import java.util.List;

/**
 * @author Keil, Vladislav
 * @author Loeschner, Marijan
 */
public class TreasureView {
    public void display(List<Card> availableCards) {
        ConsoleAssistent.clearScreen();

        displayTitle();

        System.out.println("*******************************************************************************\n" +
                "          |                   |                  |                     |\n" +
                " _________|________________.=\"\"_;=.______________|_____________________|_______\n" +
                "|                   |  ,-\"_,=\"\"     `\"=.|                  |\n" +
                "|___________________|__\"=._o`\"-._        `\"=.______________|___________________\n" +
                "          |                `\"=._o`\"=._      _`\"=._                     |\n" +
                " _________|_____________________:=._o \"=._.\"_.-=\"'\"=.__________________|_______\n" +
                "|                   |    __.--\" , ; `\"=._o.\" ,-\"\"\"-._ \".   |\n" +
                "|___________________|_._\"  ,. .` ` `` ,  `\"-._\"-._   \". '__|___________________\n" +
                "          |           |o`\"=._` , \"` `; .\". ,  \"-._\"-._; ;              |\n" +
                " _________|___________| ;`-.o`\"=._; .\" ` '`.\"\\` . \"-._ /_______________|_______\n" +
                "|                   | |o;    `\"-.o`\"=._``  '` \" ,__.--o;   |\n" +
                "|___________________|_| ;     (#) `-.o `\"=.`_.--\"_o.-; ;___|___________________\n" +
                "____/______/______/___|o;._    \"      `\".o|o_.--\"    ;o;____/______/______/____\n" +
                "/______/______/______/_\"=._o--._        ; | ;        ; ;/______/______/______/_\n" +
                "____/______/______/______/__\"=._o--._   ;o|o;     _._;o;____/______/______/____\n" +
                "/______/______/______/______/____\"=._o._; | ;_.--\"o.--\"_/______/______/______/_\n" +
                "____/______/______/______/______/_____\"=.o|o_.--\"\"___/______/______/______/____\n" +
                "/______/______/______/______/______/______/______/______/______/______/______/_\n" +
                "*******************************************************************************");

        System.out.printf("%s %n", "Treasure-Cards:");
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        displayCards(availableCards);
        displayCardChoiceMenu(availableCards.size());
        System.out.println();
    }

    /**
     * Darstellung der auswählbaren Karten.
     * @param availableCards    List<Card> auswählbare Karten
     */
    public void displayCards(List<Card> availableCards) {
        for (int i = 0; i < availableCards.size(); i++) {
            System.out.printf("%2d. - %-10s %-5s %-15s%n", i + 1, availableCards.get(i).getCardRarity(), availableCards.get(i).getName(), availableCards.get(i).getDescription());
        }
    }

    /**
     * Darstellung des auswählbaren Karten oder für's verlassen.
     * @param treasureCards    int 1 - [ Anzahl der möglichen auswählbaren Karten ]
     */
    public void displayCardChoiceMenu(int treasureCards) {
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        System.out.printf("%s %n", "(1-" + treasureCards + ") Choose Card");
    }

    /**
     * Darstellung Titel
     */
    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   TREASURE VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
