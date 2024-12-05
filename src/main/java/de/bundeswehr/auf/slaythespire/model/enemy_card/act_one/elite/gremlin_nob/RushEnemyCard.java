package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.gremlin_nob;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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
