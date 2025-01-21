package de.bundeswehr.auf.slaythespire.model.card.silent.power.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Envenom Karte.
 *
 * @author L Frank Rieger
 */
public class EnvenomCard extends PowerCard {

    public EnvenomCard() {
        super("Envenom", "Whenever an attack deals unblocked damage, apply 1 Poison.", 2, CardRarity.RARE, CardGrave.NONE, CardTrigger.PLAY_ATTACK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        AttackContext attackContext = gameContext.getAttackContext();
        Entity source = attackContext.getSource();
        Entity target = attackContext.getTarget();
        Player player = gameContext.getPlayer();
        if (source == player && attackContext.getDamage() > 0) {
            target.addEffect(new PoisonDebuffEnemy(), 1);
        }
    }

}