package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Whirlwind enemy card.
 *
 * @author OF Daniel Willig
 */
public class WhirlwindEnemyCard extends EnemyCard {
    /**
     * Constructor Whirlwind enemy card.
     */
    public WhirlwindEnemyCard() {
        super("Whirlwind", "Deals 5 x 4 damage.", "5 x 4");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 4; i++) {
            player.decreaseCurrentHealth(5, false);
        }
    }
}
