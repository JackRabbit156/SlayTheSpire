package tests;

import models.events.Event;
import models.events.actone.*;
import models.events.generelevents.BonfireSpirits;
import models.events.generelevents.Duplicator;
import models.events.generelevents.GoldenShrine;
import models.events.generelevents.NoteForYourself;
import models.map_elements.field_types.EventField;
import models.player.player_structure.Player;

import java.util.Random;

public class EventViewControllerTester {
    public static void main(String[] args) {
        Player player = new TestPlayer();
        EventViewControllerTester tester = new EventViewControllerTester();

        Event randEvent = tester.randomEvent(player);
//        EventField event = new EventField(randEvent);
        EventField event = new EventField(new BigFish(player));

        event.doFieldThing(player);

    }



    public Event randomEvent(Player player) {
        Random randi = new Random();
        ActOneEventEnum[] events = ActOneEventEnum.values();
        ActOneEventEnum event = events[randi.nextInt(events.length)];
        switch (event) {
            case BigFish:
                return new BigFish(player);
            case BonfireSpirits:
                return new BonfireSpirits(player);
            case DeadAdventurer:
                return new DeadAdventurer(player);
            case Duplicator:
                return new Duplicator(player);
            case NoteForYourself:
                return new NoteForYourself(player);
            case ScrapOoze:
                return new ScrapOoze(player);
            case TheCleric:
                return new TheCleric(player);
            case TheSsssserpent:
                return new TheSssssserpent(player);
            case WorldofGoo:
                return new WorldOfGoo(player);
            default:
                return new GoldenShrine(player);
        }
    }


}
