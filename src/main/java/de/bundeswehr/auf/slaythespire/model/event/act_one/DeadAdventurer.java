package de.bundeswehr.auf.slaythespire.model.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Der Spieler kann mit einer bestimmten Wahrscheinlichkeit Gold finden
 *
 * @author  Loeschner, Marijan
 */
public class DeadAdventurer extends Event {

    public DeadAdventurer(Player player) {
        super(player, "Dead Adventurer", new Image("/images/event/act_one/DeadAdventurerEvent.png"),
                "\n\nYou come across a dead adventurer on the floor.\n" +
                        "His pants have been stolen! Also, it looks as though he's been gouged and trampled by a horned beast. \n" +
                        "Though his possessions are still intact, you're in no mind to find out what happened here...\n"
        );
    }

    @Override
    public Button getButton1(){
        Button search = new Button("\t[Search] Find Loot. "); // 50% chance to find 30 gold
        search.setOnAction(event -> {
            boolean chance = rnd.nextInt(100) <= 50;
            if (chance) {
                getPlayer().gainGold(30);
            }
            search.setVisible(false);
        });
        return search;
    }

}
