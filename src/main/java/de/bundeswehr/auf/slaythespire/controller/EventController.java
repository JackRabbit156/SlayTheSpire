package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.EventView;
import de.bundeswehr.auf.slaythespire.gui.events.EventViewEvents;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.event.act_one.*;
import de.bundeswehr.auf.slaythespire.model.event.act_two.*;
import de.bundeswehr.auf.slaythespire.model.event.general.BonfireSpirits;
import de.bundeswehr.auf.slaythespire.model.event.general.Duplicator;
import de.bundeswehr.auf.slaythespire.model.event.general.GoldenShrine;
import de.bundeswehr.auf.slaythespire.model.event.general.NoteForYourself;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Controller für die Events mit Event Randomizer
 *
 * @author Loeschner, Marijan
 */
public class EventController implements Controller, EventViewEvents {

    private static final Random rnd = new Random();

    private final Player player;
    private final EventView eventView;

    public EventController(Player player) {
        this.player = player;
        eventView = new EventView(randomEvent(player), player, this);
        eventView.getLeave().setOnMouseClicked(event -> GuiHelper.Scenes.startMapScene(player));
    }

    @Override
    public void clickedOnFullScreen() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    public BorderPane getEventView() {
        return eventView.display();
    }

    private Event randomEvent(Player player) {
        // general events
        List<Event> events = new ArrayList<>(Arrays.asList(
                new NoteForYourself(player), new BonfireSpirits(player),
                new GoldenShrine(player), new Duplicator(player)));
        // act 1
        if (player.getCurrentAct() == 1) {
            events.addAll(Arrays.asList(
                    new BigFish(player), new DeadAdventurer(player),
                    new Lab(player),  new ScrapOoze(player),
                    new TheCleric(player), new TheSssssserpent(player),
                    new WheelOfChange(player), new WorldOfGoo(player)));
        }
        // act 2
        else if (player.getCurrentAct() == 2) {
            events.addAll(Arrays.asList(
                    new CursedTome(player), new KnowingSkull(player),
                    new MaskedBandits(player), new TheJoust(player),
                    new TheLibrary(player), new TheMausoleum(player),
                    new TheNest(player)));
        }
        // TODO act 3
        int randomIndex = rnd.nextInt(events.size());
        return events.get(randomIndex);
    }

}
