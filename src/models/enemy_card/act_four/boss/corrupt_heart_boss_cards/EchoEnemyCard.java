package models.enemy_card.act_four.boss.corrupt_heart_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Echo enemy card.
 *
 * @author OF Daniel Willig
 */
public class EchoEnemyCard extends EnemyCard {
    /**
     * Constructor Echo enemy card.
     */
    public EchoEnemyCard() {
        super("Echo", "Deals 40 damage.", "40");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(40, false);
    }
}
