package de.bundeswehr.auf.slaythespire.model.event.act_one;

import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Event bei dem man zwischen 3 Optionen auswählen kann
 *
 * @author Loeschner, Marijan
 */
public class BigFish extends Event {

    private final Button banana = new Button("\t[Banana] Heal 1/3 of your max HP.");
    private final Button box = new Button("\t[Box] We don't have a relic, stop looking!"); // nothing
    private final Button donut = new Button("\t[Donut] Max HP +5.");

    public BigFish(Player player) {
        super(player, "Big Fish", new Image("/images/event/act_one/BigFishEvent.png"),
                "\n\nAs you make your way down a long corridor you see a banana, a donut, and a box floating about. \n" +
                "No... upon closer inspection they are tied to strings coming from holes in the ceiling.\n " +
                "There is a quiet cackling from above as you approach the objects.\n");
    }

    @Override
    public Button getButton1() {
        banana.setOnAction(event -> {
            getPlayer().heal(getPlayer().getMaxHealth() / 3);
            donut.setVisible(false);
            banana.setVisible(false);
            box.setVisible(false);
        });
        return banana;
    }

    @Override
    public Button getButton2() {
        donut.setOnAction(event -> {
            getPlayer().gainMaxHp(5);
            donut.setVisible(false);
            banana.setVisible(false);
            box.setVisible(false);
        });
        return donut;
    }

    @Override
    public Button getButton3() {
        box.setOnAction(event -> {
            // TODO Hier würde ich meine Relics aufbewahren. WENN ICH WELCHE HÄTTE!
            getStory().setText("You grab the box. \n" +
                    "Inside you find Nothing!\n" +
                    "However, you really craved the donut...");
            donut.setVisible(false);
            banana.setVisible(false);
            box.setVisible(false);
        });
        return box;
    }

}
