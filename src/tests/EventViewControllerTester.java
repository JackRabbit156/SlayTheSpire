package tests;

import models.events.Event;
import models.events.act_one.*;
import models.events.generelevents.BonfireSpiritsEvent;
import models.events.generelevents.DuplicatorEvent;
import models.events.generelevents.GoldenShrineEvent;
import models.events.generelevents.NoteForYourselfEvent;
import models.map_elements.field_types.EventField;
import models.player.player_structure.Player;

import java.util.Random;

public class EventViewControllerTester {
    public static void main(String[] args) {
        Player player = new TestPlayer();
        EventViewControllerTester tester = new EventViewControllerTester();

        Event randEvent = tester.randomEvent(player);
//        EventField event = new EventField(randEvent);
        EventField event = new EventField(new BigFishEvent(player));

        event.doFieldThing(player);

    }



    public Event randomEvent(Player player) {
        Random randi = new Random();
        ActOneEventEnum[] events = ActOneEventEnum.values();
        ActOneEventEnum event = events[randi.nextInt(events.length)];
        switch (event) {
            case BigFish:
                return new BigFishEvent(player);
            case BonfireSpirits:
                return new BonfireSpiritsEvent(player);
            case DeadAdventurer:
                return new DeadAdventurerEvent(player);
            case Duplicator:
                return new DuplicatorEvent(player);
            case NoteForYourself:
                return new NoteForYourselfEvent(player);
            case ScrapOoze:
                return new ScrapOozeEvent(player);
            case TheCleric:
                return new TheClericEvent(player);
            case TheSsssserpent:
                return new TheSssssserpentEvent(player);
            case WorldofGoo:
                return new WorldOfGooEvent(player);
            default:
                return new GoldenShrineEvent(player);
        }
    }


}
