package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.spiker;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Cut enemy card.
 *
 * @author OF Daniel Willig
 */
public class CutEnemyCard extends EnemyCard {
    /**
     * Constructor Cut enemy card.
     */
    public CutEnemyCard() {
        super("Cut", "Deals 7 damage.", "7");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(7, false);
    }
}
