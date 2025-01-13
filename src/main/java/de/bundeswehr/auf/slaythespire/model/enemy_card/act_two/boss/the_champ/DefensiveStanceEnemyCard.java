package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.MetallicizeBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;

/**
 * Die Defensive stance enemy card.
 *
 * @author OF Daniel Willig
 */
public class DefensiveStanceEnemyCard extends BlockEnemyCard {

    /**
     * Constructor Defensive stance enemy card.
     */
    public DefensiveStanceEnemyCard() {
        super("Defensive Stance", "Gains 15 Icon Block Block and 5 Metallicize.", 15);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        super.playEnemy(gameContext, enemy);

        enemy.addEffect(new MetallicizeBuff(), 5);
    }

}
