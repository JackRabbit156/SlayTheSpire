package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.lagavulin;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Attack enemy card.
 *
 * @author OF Daniel Willig
 */
public class AttackEnemyCard extends EnemyCard {
    /**
     * Constructor Attack enemy card.
     */
    public AttackEnemyCard() {
        super("Attack", "Deal 18 damage.", "18");
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(18, false);
    }
}
