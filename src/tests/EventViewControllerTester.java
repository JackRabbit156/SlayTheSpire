package tests;

import models.event.Event;
import models.event.act_one.*;
import models.event.generelevents.BonfireSpiritsEvent;
import models.event.generelevents.DuplicatorEvent;
import models.event.generelevents.GoldenShrineEvent;
import models.event.generelevents.NoteForYourselfEvent;
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
            case BIG_FISH:
                return new BigFishEvent(player);
            case BONFIRE_SPIRITS:
                return new BonfireSpiritsEvent(player);
            case DEAD_ADVENTURER:
                return new DeadAdventurerEvent(player);
            case DUPLICATOR:
                return new DuplicatorEvent(player);
            case NOTE_FOR_YOURSELF:
                return new NoteForYourselfEvent(player);
            case SCRAP_OOZE:
                return new ScrapOozeEvent(player);
            case THE_CLERIC:
                return new TheClericEvent(player);
            case THE_SSSSSERPENT:
                return new TheSssssserpentEvent(player);
            case WORLDOF_GOO:
                return new WorldOfGooEvent(player);
            default:
                return new GoldenShrineEvent(player);
        }
    }


}
