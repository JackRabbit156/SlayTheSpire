package de.bundeswehr.auf.slaythespire.model.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Der Spieler kann wetten und mit einer bestimmten Wahrscheinlichkeit Gold gewinnen
 *
 * @author  Loeschner, Marijan
 */
public class TheJoust extends Event {

    private final Button button1 = new Button("\t[Murderer] Bet 50 Gold. Win 100 Gold."); // 70 % chance to win
    private final Button button2 = new Button("\t[Owner] Bet 50 Gold. Win 250 Gold."); // 30% chance to win

    public TheJoust(Player player) {
        super(player, "The Joust", new Image("/images/event/act_two/joust.jpg"),
            "\n\nAs you make your way through the large buildings you come across a long narrow bridge \n" +
                "and spot knights on either side, facing one another. You approach…\n" +
                "Knight: \"HALT!\"\n");
    }

    @Override
    public Button getButton1(){
        button1.setOnAction(event -> {
            boolean chance = rnd.nextInt(100) <= 70;
            if (chance) {
                getPlayer().gainGold(50);
            }
            else {
                getPlayer().decreaseGold(50);
            }
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        boolean chance = rnd.nextInt(100) <= 30;
        button2.setOnAction(event -> {
            if (chance) {
                getPlayer().gainGold(200);
            }
            else {
                getPlayer().decreaseGold(50);
            }
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button2;
    }

}
