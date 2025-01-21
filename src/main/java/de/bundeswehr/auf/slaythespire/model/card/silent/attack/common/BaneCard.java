package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Bane Karte.
 *
 * @author L Frank Rieger
 */
public class BaneCard extends AttackCard {

    public BaneCard() {
        super("Bane", "Deal 7 damage. If the enemy is Poisoned, deal 7 damage again.", 1, 7, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        Player player = gameContext.getPlayer();
        Enemy enemy = gameContext.getSelectedEnemy();
        if (enemy.getEffectCounter(new PoisonDebuffEnemy()) > 0) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
        }
    }

}