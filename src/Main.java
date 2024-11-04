import controller.BattleViewController;
import controller.MapViewController;
import controller.menus.MainMenuViewController;
import helper.Color;
import models.cards.DeckFactory;
import controller.menus.MainMenuViewController;
import models.enemy.Enemy;
import models.enemy.SpikeSlime;
import models.player.Ironclad;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Ironclad player = new Ironclad();
        MapViewController map = new MapViewController(player);
        
        /*Ironclad player = new Ironclad();

        List<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new AcidSlime());
        enemies.add(new Cultist());
        enemies.add(new SpikeSlime());

        BattleViewController controller = new BattleViewController(player, enemies);
        controller.startBattle();*/
    }
}