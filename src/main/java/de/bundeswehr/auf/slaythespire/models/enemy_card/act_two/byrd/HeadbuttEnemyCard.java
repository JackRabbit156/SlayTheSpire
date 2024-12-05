package de.bundeswehr.auf.slaythespire.models.enemy_card.act_two.byrd;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

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
