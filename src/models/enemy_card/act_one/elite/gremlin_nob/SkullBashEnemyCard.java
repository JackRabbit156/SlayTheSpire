package models.enemy_card.act_one.elite.gremlin_nob;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Skull bash enemy card.
 *
 * @author OF Daniel Willig
 */
public class SkullBashEnemyCard extends EnemyCard {
    /**
     * Constructor Skull bash enemy card.
     */
    public SkullBashEnemyCard() {
        super("Skull Bash", "Deals 6 damage.", "6");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(6, false);
    }
}
