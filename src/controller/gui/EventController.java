package controller.gui;

import helper.GuiHelper;
import javafx.scene.layout.BorderPane;
import models.event.Event;
import models.event.act_one.*;
import models.event.act_two.*;
import models.event.generelevents.BonfireSpirits;
import models.event.generelevents.Duplicator;
import models.event.generelevents.GoldenShrine;
import models.event.generelevents.NoteForYourself;
import models.player.player_structure.Player;
import view.gui.EventView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Controller für die Events mit Event Randomizer
 *
 * @author Loeschner, Marijan
 */
public class EventController {
    private List<Event> generalEvents = new ArrayList<>();
    private Random rand = new Random();
    private int randomIndex;

    public BorderPane getEventView(Player player){
        EventView ev = new EventView(randomEvent(player), player);
        ev.getLeave().setOnMouseClicked(event -> {
            GuiHelper.Scenes.startMapScene(player);
        });
        return ev.display();
    }

    public Event randomEvent(Player player) {
        Event bigFish = new BigFish();
        Event deadAdventurer = new DeadAdventurer();
        Event theCleric = new TheCleric();
        Event theSerpent = new TheSssssserpent();
        Event noteforyou = new NoteForYourself();
        Event scrapOoze = new ScrapOoze();
        Event worldOfGoo = new WorldOfGoo();
        Event bonfireSpirits = new BonfireSpirits();
        Event goldenShrine = new GoldenShrine();
        Event duplicator = new Duplicator();
        Event wheelOfChange = new WheelOfChange();
        Event theNest = new TheNest();
        Event theMausoleum = new TheMausoleum();
        Event theJoust =new TheJoust();
        Event lab = new Lab();
        Event maskedBandits = new MaskedBandits();
        Event theLib = new TheLibrary();
        Event knowingSkull = new KnowingSkull();
        Event cursedTome = new CursedTome();

        generalEvents.addAll(Arrays.asList(noteforyou, bonfireSpirits, goldenShrine, duplicator));
        if(player.getCurrentAct() == 1){
            generalEvents.addAll(Arrays.asList(bigFish, deadAdventurer, lab, scrapOoze, theCleric, theSerpent, wheelOfChange, worldOfGoo));
        }
        else if(player.getCurrentAct() == 2) {
            generalEvents.addAll(Arrays.asList(cursedTome, knowingSkull, maskedBandits, theJoust, theLib, theMausoleum, theNest));
        }
        randomIndex = rand.nextInt(generalEvents.size());
        return generalEvents.get(randomIndex);
    }

}
