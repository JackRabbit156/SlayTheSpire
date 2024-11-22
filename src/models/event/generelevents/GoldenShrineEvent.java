package models.event.generelevents;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Erhöht Gold um 100
 * @author Keil, Vladislav
 * @author Loeschner, Marijan
 */
public class GoldenShrineEvent extends Event {
  /*  private Player player;
    private Scanner scanner = new Scanner(System.in);

    public GoldenShrineEvent(Player player) {
        super("  Before you lies an elaborate shrine to an ancient spirit.", "Golden Shrine");
        setImagePath(new PathAssistent().toPath(this));

        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        //EventView.displayHead(getTitle(), getStory());

        player.increaseGold(100);
        System.out.println();
        System.out.printf("%s %d %s %n", "   You receive:", 100, "Gold");
        System.out.println();
        System.out.println("\tPress (0) for exit");
        scanner.next();
    }*/
}
