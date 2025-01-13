package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd;

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
public class CawEnemyCard extends EnemyCard {

    /**
     * Constructor Face slap enemy card.
     */
    public CawEnemyCard() {
        super("Caw", "Gains 1 Strength.", "1");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addEffect(new StrengthBuff(), 1);
    }

}
