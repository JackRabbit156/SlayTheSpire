package de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

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
