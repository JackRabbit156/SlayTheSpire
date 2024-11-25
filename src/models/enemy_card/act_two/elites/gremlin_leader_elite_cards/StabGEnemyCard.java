package models.enemy_card.act_two.elites.gremlin_leader_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Stab GremlinLeader enemy card.
 *
 * @author OF Daniel Willig
 */
public class StabGEnemyCard extends EnemyCard {
    /**
     * Constructor Stab g enemy card.
     */
    public StabGEnemyCard() {
        super("Stab", "Deals 6 x 3 damage.", "6 x 3");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 3; i++) {
            player.decreaseCurrentHealth(6, false);
        }
    }
}
