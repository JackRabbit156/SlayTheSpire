package de.bundeswehr.auf.slaythespire.model.relic.ironclad;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.EffectContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class ChampionBeltRelic extends PlayerTypeRelic {

    public ChampionBeltRelic() {
        super("Champion Belt", "Whenever you apply Vulnerable, also apply 1 Weak.",
                RelicRarity.RARE, PlayerType.IRONCLAD, RelicTrigger.EFFECT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        EffectContext effectContext = gameContext.getEffectContext();
        if (effectContext.getEffect() instanceof VulnerableDebuff) {
            Entity source = effectContext.getSource();
            Player player = gameContext.getPlayer();
            if (source == player) {
                Entity target = effectContext.getTarget();
                target.addEffect(new WeakDebuff(), 1);
            }
        }
    }

}