package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.bronze_automaton;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Flail enemy card.
 *
 * @author OF Daniel Willig
 */
public class FlailEnemyCard extends EnemyCard {
    /**
     * Constructor Flail enemy card.
     */
    public FlailEnemyCard() {
        super("Flail", "Deals 7 x 2 damage.", "7 x 2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 2; i++) {
            player.decreaseCurrentHealth(7, false);
        }
    }
}
