package de.bundeswehr.auf.slaythespire.model.card.silent.power.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die Caltrops Karte.
 *
 * @author L Frank Rieger
 */
public class CaltropsCard extends PowerCard {

    public CaltropsCard() {
        super("Caltrops", "Whenever you are attacked, deal 3 damage back.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.PLAY_ATTACK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Entity source = gameContext.getAttackContext().getSource();
        Entity target = gameContext.getAttackContext().getTarget();
        target.dealDamage(gameContext, 3, source, this);
    }

}