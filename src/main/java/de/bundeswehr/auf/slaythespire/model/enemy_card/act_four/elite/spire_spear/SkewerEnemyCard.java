package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_spear;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Skewer enemy card.
 *
 * @author OF Daniel Willig
 */
public class SkewerEnemyCard extends EnemyCard {
    /**
     * Constructor Skewer enemy card.
     */
    public SkewerEnemyCard() {
        super("Skewer", "Deals 10 x 3 damage.", "10 x 3");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 3; i++) {
            player.decreaseCurrentHealth(10, false);
        }
    }
}
