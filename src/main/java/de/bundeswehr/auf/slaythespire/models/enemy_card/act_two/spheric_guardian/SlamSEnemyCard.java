package de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.spheric_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Slam SphericGuardian enemy card.
 *
 * @author OF Daniel Willig
 */
public class SlamSEnemyCard extends EnemyCard {
    /**
     * Constructor Slam s enemy card.
     */
    public SlamSEnemyCard() {
        super("Slam", "Deals 10 x 2 damage.", "10 x 2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 2; i++) {
            player.decreaseCurrentHealth(10, false);
        }
    }
}
