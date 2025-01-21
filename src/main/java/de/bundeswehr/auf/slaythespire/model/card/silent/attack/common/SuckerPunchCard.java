package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

/**
 * Die Sucker Punch Karte.
 *
 * @author L Frank Rieger
 */
public class SuckerPunchCard extends AttackCard {

    public SuckerPunchCard() {
        super("Sucker Punch", "Deal 7 damage. Apply 1 Weak.", 1, 7, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new WeakDebuff(), 1);
    }

}