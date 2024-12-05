package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Swoop enemy card.
 *
 * @author OF Daniel Willig
 */
public class SwoopEnemyCard extends EnemyCard {
    /**
     * Constructor Swoop enemy card.
     */
    public SwoopEnemyCard() {
        super("Swoop", "Deals 12 damage.", "12");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(12, false);
    }
}
