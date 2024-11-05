package models.events.actone;

import models.cards.card_structure.Card;
import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.List;
import java.util.Scanner;
/**
 * Der Spieler kann  im austausch gegen Gold 1. die MaxHP erhöhen, oder 2.Eine Karte aus dem Spielerdeck entfernen
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class TheCleric extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public TheCleric(Player player) {
        super("A strange blue humanoid with a golden helm(?) approaches you with a huge smile.\n" +
                "\"Hello friend! I am Cleric! Are you interested in my services?!\" " +
                "the creature shouts, loudly.", "The Cleric");
        this.player = player;
    }

    @Override
    public void startEvent() {
        List<Card> deck = player.getDeck();
        Card exchangeCard;
        EventView.displayStory(getTitle(), getStory());
        System.out.println("1. Heal\n2. purify\n3. Leave\n\nChoose an option: ");
        switch (scanner.nextInt()){
            case 1:
                System.out.println("A warm golden light envelops your body and dissipates.\n" +
                        "The creature grins.\nCleric: \"Cleric best healer. Have a good day!\"");
                //Heilt den Spieler um 25% der Max HP im tausch für 35 Gold.
                player.increaseCurrentHealth(player.getMaxHealth() / 4);
                player.setGold(-35);
            case 2:
                System.out.println("A cold blue flame envelops your body and dissipates.\n" +
                        "The creature grins.\nCleric: \"Cleric talented. Have a good day!\"\n");
                EventView.viewDeck(player);
                System.out.print("\nWhich Card do you want to remove from your Deck? ");
                exchangeCard = deck.get(scanner.nextInt());
                deck.remove(exchangeCard);
                player.setGold(-50);
            case 3:
                System.out.println("You don't trust this \"Cleric\", so you leave.");
            default:
                System.out.println("Wrong input, try again...");
                startEvent();
        }
    }
}
