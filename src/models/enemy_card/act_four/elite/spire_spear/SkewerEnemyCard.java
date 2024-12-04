package models.enemy_card.act_four.elite.spire_spear;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Skewer enemy card.
 *
 * @author OF Daniel Willig
 */
public class SkewerEnemyCard extends EnemyCard {
    /**
     * Constructor Skewer enemy card.
     */
    public SkewerEnemyCard() {
        super("Skewer", "Deals 10 x 3 damage.", "10 x 3");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 3; i++) {
            player.decreaseCurrentHealth(10, false);
        }
    }
}
