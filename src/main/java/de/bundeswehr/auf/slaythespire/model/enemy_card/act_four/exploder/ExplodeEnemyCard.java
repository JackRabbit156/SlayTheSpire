package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.exploder;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;

/**
 * Die Explode enemy card.
 *
 * @author L Frank Rieger
 */
public class ExplodeEnemyCard extends AttackEnemyCard {

    public ExplodeEnemyCard() {
        super("Explode", "Dies and deals 30 damage.", 30);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.setCurrentHealth(0);

        super.playEnemy(gameContext, enemy);
    }
}
