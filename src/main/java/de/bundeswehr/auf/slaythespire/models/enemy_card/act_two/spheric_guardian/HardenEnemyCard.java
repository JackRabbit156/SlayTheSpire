package de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.spheric_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Harden enemy card.
 *
 * @author OF Daniel Willig
 */
public class HardenEnemyCard extends EnemyCard {
    /**
     * Constructor Harden enemy card.
     */
    public HardenEnemyCard() {
        super("Corrosive Spit", "Deals 10 damage, gains 15 block.", "10");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(10, false);
        enemy.addBlock(15);
    }
}
