package de.bundeswehr.auf.slaythespire.models.event.act_two;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy.act_two.ByrdEnemy;
import de.bundeswehr.auf.slaythespire.models.event.Event;
import de.bundeswehr.auf.slaythespire.models.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Der Spieler verliert all sein Gold, oder startet einen encounter
 *
 * @author  Loeschner, Marijan
 */
public class MaskedBandits extends Event {
    private List<Enemy> enemyList = new ArrayList<>();
    private static Image image = new Image("/images/event/act_two/beggar.jpg");
    private String title = "Lab";
    private String story = "\n\nYou encounter a group of bandits wearing large red masks.\n" +
            "Romeo: \"Hello, pay up to pass... a reasonable fee of ALL your gold will do! Heh heh!\"\n";
    private Button button1 = new Button("\t[Search] Optaion potions");
    private Button button2 = new Button("\t[Fight] Enter a combat against 3 Enemies");

    //TODO: Leave Button entfernen
    public MaskedBandits() {
        super();
    }


    @Override
    public String getTitle() {
        return title;
    }

    public String getStory() {
        return story;
    }

    public Image getImage() {
        return image;
    }

    public Button getButton1(Player player) {

        button1.setOnMouseClicked(event -> {
            player.decreaseGold(player.getGold());
            button1.setVisible(false);
            button2.setVisible(false);
            GuiHelper.Scenes.startMapScene(player);
        });
        return button1;
    }
    public Button getButton2(Player player) {

        button2.setOnMouseClicked(event -> {
            Enemy enemy1 = new ByrdEnemy();
            Enemy enemy2 = new ByrdEnemy();
            Enemy enemy3 = new ByrdEnemy();
            enemyList.add(enemy1);
            enemyList.add(enemy2);
            enemyList.add(enemy3);
            GuiHelper.Scenes.startBattleScene(player, enemyList, FieldEnum.ENEMYFIELD);
            button2.setVisible(false);
        });
        return button2;
    }

}
