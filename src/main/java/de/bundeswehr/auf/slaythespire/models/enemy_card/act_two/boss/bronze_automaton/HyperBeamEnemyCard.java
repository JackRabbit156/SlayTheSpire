package de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.boss.bronze_automaton;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Hyper beam enemy card.
 *
 * @author OF Daniel Willig
 */
public class HyperBeamEnemyCard extends EnemyCard {
    /**
     * Constructor Hyper beam enemy card.
     */
    public HyperBeamEnemyCard() {
        super("Hyper Beam", "Deals 45 damage.", "45");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(45, false);
    }
}
