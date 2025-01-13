package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_shield;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

import java.util.List;

/**
 * Die Fortify enemy card.
 *
 * @author L Frank Rieger
 */
public class FortifyEnemyCard extends BlockEnemyCard {

    public FortifyEnemyCard() {
        super("Fortify", "All enemies gain 30 Block.", 30);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy e) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.gainBlock(getBlock(gameContext));
        }
    }

}
