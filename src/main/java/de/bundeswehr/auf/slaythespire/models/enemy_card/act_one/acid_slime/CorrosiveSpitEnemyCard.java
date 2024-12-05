package de.bundeswehr.auf.slaythespire.models.enemy_card.act_one.acid_slime;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Corrosive spit enemy card.
 *
 * @author OF Daniel Willig
 */
public class CorrosiveSpitEnemyCard extends EnemyCard {
    /**
     * Constructor Corrosive spit enemy card.
     */
    public CorrosiveSpitEnemyCard() {
        super("Corrosive Spit", "Deals 11 damage.", "11");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(11, false);
    }
}
