package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

/**
 * Die Poisoned Stab Karte.
 *
 * @author L Frank Rieger
 */
public class PoisonedStabCard extends AttackCard {

    public PoisonedStabCard() {
        super("Poisoned Stab", "Deal 6 damage. Apply 3 Poison.", 1, 6, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new PoisonDebuffEnemy(), 3);
    }

}