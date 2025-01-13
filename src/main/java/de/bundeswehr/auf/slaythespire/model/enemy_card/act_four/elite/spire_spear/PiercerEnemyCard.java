package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_spear;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.BurnCard;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.MultiAttackEnemyCard;

import java.util.List;

/**
 * Die Piercer enemy card.
 *
 * @author L Frank Rieger
 */
public class PiercerEnemyCard extends EnemyCard {

    public PiercerEnemyCard() {
        super("Piercer", "All enemies gain 2 Strength.", "2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy e) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new StrengthBuff(), 2);
        }
    }
}
