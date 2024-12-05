package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.slime_boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Slam enemy card.
 *
 * @author OF Daniel Willig
 */
public class SlamEnemyCard extends EnemyCard {
    /**
     * Constructor Slam enemy card.
     */
    public SlamEnemyCard() {
        super("Slam", "Deals 35 damage.", "35");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(35, false);
    }
}
