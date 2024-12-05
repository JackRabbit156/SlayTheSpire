package de.bundeswehr.auf.slaythespire.models.enemy_card.act_four.spiker;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

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
