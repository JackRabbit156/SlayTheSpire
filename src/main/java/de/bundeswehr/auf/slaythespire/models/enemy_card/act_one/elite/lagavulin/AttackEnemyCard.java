package de.bundeswehr.auf.slaythespire.models.enemy_card.act_one.elite.lagavulin;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

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
