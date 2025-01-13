package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.cultist;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.RitualBuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Lick enemy card.
 *
 * @author L Frank Rieger
 */
public class IncantationEnemyCard extends EnemyCard {

    public IncantationEnemyCard() {
        super("Incantation", "Gains 3 Ritual.", "3");
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addEffect(new RitualBuff(), 3);
    }


}
