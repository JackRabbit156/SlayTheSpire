package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.boss.corrupt_heart;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Echo enemy card.
 *
 * @author OF Daniel Willig
 */
public class EchoEnemyCard extends EnemyCard {
    /**
     * Constructor Echo enemy card.
     */
    public EchoEnemyCard() {
        super("Echo", "Deals 40 damage.", "40");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(40, false);
    }
}
