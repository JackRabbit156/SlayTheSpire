package de.bundeswehr.auf.slaythespire.models.enemy_card.act_four.elite.spire_shield;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Smash enemy card.
 *
 * @author OF Daniel Willig
 */
public class SmashEnemyCard extends EnemyCard {
    /**
     * Constructor Smash enemy card.
     */
    public SmashEnemyCard() {
        super("Smash", "Deals 34 damage. Gains Block equal to its damage output.", "34");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        int oldHp = player.getCurrentHealth();
        player.decreaseCurrentHealth(34, false);
        enemy.addBlock(oldHp - player.getCurrentHealth());
    }
}
