package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.gremlin_leader;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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
