package de.bundeswehr.auf.slaythespire.model.event.act_two;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_two.ByrdEnemy;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Der Spieler verliert all sein Gold, oder startet einen encounter
 *
 * @author  Loeschner, Marijan
 */
public class MaskedBandits extends Event {

    private final Button button1 = new Button("\t[Pay] "); // Lose ALL of your Gold and skip the fight.
    private final Button button2 = new Button("\t[Fight!] "); // Enter a combat against 3 Enemies

    public MaskedBandits(Player player) {
        super(player, "Masked Bandit",
            "\n\nYou encounter a group of bandits wearing large red masks.\n" +
                    "Romeo: \"Hello, pay up to pass... a reasonable fee of ALL your gold will do! Heh heh!\"\n");
        setImage(new PathAssistent().toPath(this));
        // TODO Leave Button nicht anzeigen
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            getPlayer().decreaseGold(getPlayer().getGold());
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        button2.setOnAction(event -> {
            List<Enemy> enemyList = new ArrayList<>();
            enemyList.add(new ByrdEnemy());
            enemyList.add(new ByrdEnemy());
            enemyList.add(new ByrdEnemy());
            button2.setVisible(false);
            button1.setVisible(false);
            GuiHelper.Scenes.startBattleScene(getPlayer(), enemyList, FieldEnum.ENEMY_FIELD);
        });
        return button2;
    }

}
