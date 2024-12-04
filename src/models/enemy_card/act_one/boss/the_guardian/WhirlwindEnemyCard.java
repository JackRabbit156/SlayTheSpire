package models.enemy_card.act_one.boss.the_guardian;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Whirlwind enemy card.
 *
 * @author OF Daniel Willig
 */
public class WhirlwindEnemyCard extends EnemyCard {
    /**
     * Constructor Whirlwind enemy card.
     */
    public WhirlwindEnemyCard() {
        super("Whirlwind", "Deals 5 x 4 damage.", "5 x 4");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 4; i++) {
            player.decreaseCurrentHealth(5, false);
        }
    }
}
