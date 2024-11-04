import controller.BattleViewController;
import controller.MapViewController;
import controller.menus.MainMenuViewController;
import helper.Color;
import models.cards.DeckFactory;
import models.enemy.Enemy;
import models.enemy.SpikeSlime;
import models.player.Ironclad;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        MapViewController map = new MapViewController(player);
        MainMenuViewController menu = new MainMenuViewController();
        menu.startMenu();


        /*Ironclad player = new Ironclad();
        MapViewController map = new MapViewController(player, false);*/

//        System.out.println(Color.values().length);
        /*Ironclad player = new Ironclad();

        List<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new AcidSlime());
        enemies.add(new Cultist());
        enemies.add(new SpikeSlime());

        BattleViewController controller = new BattleViewController(player, enemies);
        controller.startBattle();*/
    }
}