package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Face slap enemy card.
 *
 * @author OF Daniel Willig
 */
public class GloatEnemyCard extends EnemyCard {

    /**
     * Constructor Face slap enemy card.
     */
    public GloatEnemyCard() {
        super("Gloat", "Gains 2 Strength.", "2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addEffect(new StrengthBuff(), 2);
    }

}
