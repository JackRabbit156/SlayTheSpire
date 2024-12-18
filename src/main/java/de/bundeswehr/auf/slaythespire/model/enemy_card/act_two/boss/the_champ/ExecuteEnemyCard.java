package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Execute enemy card.
 *
 * @author OF Daniel Willig
 */
public class ExecuteEnemyCard extends EnemyCard {
    /**
     * Constructor Execute enemy card.
     */
    public ExecuteEnemyCard() {
        super("Execute", "Deals 10 x 2 damage.", "10 x 2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 2; i++) {
            player.decreaseCurrentHealth(10, false);
        }
    }
}
