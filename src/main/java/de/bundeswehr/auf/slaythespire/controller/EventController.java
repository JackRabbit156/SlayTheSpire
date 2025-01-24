package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.EventView;
import de.bundeswehr.auf.slaythespire.gui.events.EventViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.event.act_one.*;
import de.bundeswehr.auf.slaythespire.model.event.act_two.*;
import de.bundeswehr.auf.slaythespire.model.event.general.*;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
        eventView = new EventView(Objects.requireNonNull(randomEvent(player)), player, this);
        eventView.getLeave().setOnMouseClicked(event -> GuiHelper.Scenes.startMapScene(player));
    }

    @Override
    public void clickedOnFullScreen() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    @Override
    public void discard() {
        eventView.discard();
    }

    public BorderPane getEventView() {
        return eventView.display();
    }

    private Event randomEvent(Player player) {
        // general events
        List<Class<? extends Event>> events = new ArrayList<>(Arrays.asList(
                BonfireSpirits.class, Duplicator.class, FaceTrader.class, GoldenShrine.class, NoteForYourself.class));
        if (player.getCurrentAct() == 1) {
            events.addAll(Arrays.asList(
                    BigFish.class, DeadAdventurer.class, Lab.class,  ScrapOoze.class,
                    TheCleric.class, TheSssssserpent.class, WheelOfChange.class, WorldOfGoo.class));
        }
        else if (player.getCurrentAct() == 2) {
            events.addAll(Arrays.asList(
                    Augmenter.class, CursedTome.class, ForgottenAltar.class, KnowingSkull.class,
                    MaskedBandits.class, TheJoust.class, TheLibrary.class, TheMausoleum.class, TheNest.class));
        }
        // TODO act 3
        Class<? extends Event> event = events.get(rnd.nextInt(events.size()));
        try {
            return event.getDeclaredConstructor(Player.class).newInstance(player);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LoggingAssistant.log(e, Color.RED);
            return null;
        }
    }

}
