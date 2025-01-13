package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.gremlin_leader;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

/**
 * Die Encourage enemy card.
 *
 * @author OF Daniel Willig
 */
public class EncourageEnemyCard extends BlockEnemyCard {

    /**
     * Constructor Encourage enemy card.
     */
    public EncourageEnemyCard() {
        super("Encourage", "All enemies gain 3 Strength. All minions gain 6 Block.", 6);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy e) {
        for (Enemy enemy : gameContext.getEnemies()) {
            enemy.addEffect(new StrengthBuff(), 3);
            if (!(enemy instanceof Elite || enemy instanceof Boss)) {
                enemy.gainBlock(getBlock(gameContext));
            }
        }
    }

}
