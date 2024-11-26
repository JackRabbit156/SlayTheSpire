package models.enemy_card.act_one.elites.gremlin_nob_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Rush enemy card.
 *
 * @author OF Daniel Willig
 */
public class RushEnemyCard extends EnemyCard {
    /**
     * Constructor Rush enemy card.
     */
    public RushEnemyCard() {
        super("Rush", "Deals 14 damage.", "14");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(14, false);
    }
}
