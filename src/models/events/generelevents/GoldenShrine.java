package models.events.generelevents;

import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.Random;
import java.util.Scanner;

/**
 * Erhöht Gold um 100
 * @author Keil, Vladislav
 */
public class GoldenShrine extends Event {
    Player player;
    Scanner scanner = new Scanner(System.in);

    public GoldenShrine(Player player) {
        super("Before you lies an elaborate shrine to an ancient spirit.", "Golden Shrine");

        this.player = player;
        startEvent();
    }

    @Override
    public void startEvent() {
        EventView.displayStory(getTitle(), getStory());

        player.increaseGold(100);
        System.out.println();
        System.out.printf("%s %d %s %n", "You got:", 100, "Gold");
        System.out.println();
        System.out.println("Press (0) for exit");
        scanner.nextInt();
    }
}
