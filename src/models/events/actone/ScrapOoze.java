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
        super("  As you walk into the room you hear a gurgling and the grinding of metals. \n" +
                "\tBefore you is a slime-like creature that ate too much scrap for its own good. \n" +
                "\tFrom the center of the creature you see glints of strange light, perhaps something magical? \n" +
                "\tIt looks like you can get some treasure if you just reach inside its... opening. \n" +
                "\tHowever, the acid and sharp objects may hurt.", "Scrap Ooze");
        this.player = player;
    }

    @Override
    public void startEvent() {
        EventView.displayHead(getTitle(), getStory());
        System.out.println("\t1. Reach into the Ooze\n\t2. Leave\n\n");
        System.out.print("\tChoose an option: ");
        int options = scanner.nextInt();
        if (options == 1) {
            player.setCurrentHealth(player.getCurrentHealth() - 3);
            System.out.println("\n\tOuch!\n\tAll you find is corroded metal and a bit of burning pain.");
        }
        else if (options == 2) {
            System.out.println("\n\tYou decide to leave the area.\n\tThe slime pays no attention, content with its metal");
        }
        else {
            System.out.println("\n\tWrong input, please try agian...");
            startEvent();
        }
    }
}