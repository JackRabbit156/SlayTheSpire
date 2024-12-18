package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.acid_slime;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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
