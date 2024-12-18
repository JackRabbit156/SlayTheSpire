package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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
