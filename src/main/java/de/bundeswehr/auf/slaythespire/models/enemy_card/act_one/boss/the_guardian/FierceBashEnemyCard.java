package de.bundeswehr.auf.slaythespire.models.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Fierce bash enemy card.
 *
 * @author OF Daniel Willig
 */
public class FierceBashEnemyCard extends EnemyCard {
    /**
     * Constructor Fierce bash enemy card.
     */
    public FierceBashEnemyCard() {
        super("Fierce Bash", "Deals 32 damage.", "32");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(32, false);
    }
}
