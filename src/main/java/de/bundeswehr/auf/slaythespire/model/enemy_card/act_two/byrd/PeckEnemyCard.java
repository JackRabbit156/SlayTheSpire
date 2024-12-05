package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Peck enemy card.
 *
 * @author OF Daniel Willig
 */
public class PeckEnemyCard extends EnemyCard {
    /**
     * Constructor Peck enemy card.
     */
    public PeckEnemyCard() {
        super("Peck", "Deals 1 x 5 damage.", "1 x 5");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 5; i++) {
            player.decreaseCurrentHealth(1, false);
        }
    }
}
