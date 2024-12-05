package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.mad_gremlin;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Scratch enemy card.
 *
 * @author OF Daniel Willig
 */
public class ScratchEnemyCard extends EnemyCard {
    /**
     * Constructor Scratch enemy card.
     */
    public ScratchEnemyCard() {
        super("Scratch", "Deals 4 damage.", "4");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(4, false);
    }
}
