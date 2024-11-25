package models.enemy_card.act_two.boss.the_champ_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Heavy slash enemy card.
 *
 * @author OF Daniel Willig
 */
public class HeavySlashEnemyCard extends EnemyCard {
    /**
     * Constructor Heavy slash enemy card.
     */
    public HeavySlashEnemyCard() {
        super("Heavy Slash", "Deals 16 damage.", "16");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(16, false);
    }
}
