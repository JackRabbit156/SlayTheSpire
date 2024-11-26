package models.enemy_card.act_four.boss.corrupt_heart_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Blood shots enemy card.
 *
 * @author OF Daniel Willig
 */
public class BloodShotsEnemyCard extends EnemyCard {
    /**
     * Constructor Blood shots enemy card.
     */
    public BloodShotsEnemyCard() {
        super("Blood Shots", "Deal 2 x 12 damage.", "2 x 12");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 12; i++) {
            player.decreaseCurrentHealth(2, false);
        }
    }
}
