package de.bundeswehr.auf.slaythespire.model.relic.boss.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.StarterTypeRelic;

public class WristBladeRelic extends StarterTypeRelic {

    public WristBladeRelic() {
        super("Wrist Blade", "Attacks that cost 0 deal 4 additional damage.",
                PlayerType.SILENT, RelicTrigger.PLAY_ATTACK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        AttackContext attackContext = gameContext.getAttackContext();
        if (attackContext.getAction() instanceof AttackCard) {
            AttackCard card = (AttackCard) attackContext.getAction();
            if (card.getCost() == 0) {
                Entity source = attackContext.getSource();
                Entity target = attackContext.getTarget();
                source.dealDamage(gameContext, 4, target, this);
            }
        }
    }

}