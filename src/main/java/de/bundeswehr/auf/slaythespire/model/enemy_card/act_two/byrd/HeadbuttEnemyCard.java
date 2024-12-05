package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Headbutt enemy card.
 *
 * @author OF Daniel Willig
 */
public class HeadbuttEnemyCard extends EnemyCard {
    /**
     * Constructor Headbutt enemy card.
     */
    public HeadbuttEnemyCard() {
        super("Headbutt", "Deals 3 damage.", "3");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(3, false);
    }
}
