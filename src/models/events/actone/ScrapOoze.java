package models.events.actone;

import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.Scanner;
/**
 * Der Spieler kann 3 hp verlieren
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class ScrapOoze extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public ScrapOoze(Player player) {
        super("As you walk into the room you hear a gurgling and the grinding of metals. " +
                "Before you is a slime-like creature that ate too much scrap for its own good. " +
                "From the center of the creature you see glints of strange light, perhaps something magical? " +
                "It looks like you can get some treasure if you just reach inside its... opening. " +
                "However, the acid and sharp objects may hurt.", "Scrap Ooze");
        this.player = player;
    }

    @Override
    public void startEvent() {
        EventView.displayStory(getTitle(), getStory());
        System.out.println("1. Reach into the Ooze\n2. Leave\n\nChoose an option: ");
        int options = scanner.nextInt();
        if (options == 1) {
            player.setCurrentHealth(player.getCurrentHealth() - 3);
            System.out.println("Ouch!\nAll you find is corroded metal and a bit of burning pain.");
        }
        else if (options == 2) {
            System.out.println("You decide to leave the area.\nThe slime pays no attention, content with its metal");
        }
        else {
            System.out.println("Wrong input, please try agian...");
            startEvent();
        }
    }
}