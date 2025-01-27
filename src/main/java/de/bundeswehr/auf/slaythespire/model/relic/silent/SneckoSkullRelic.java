package de.bundeswehr.auf.slaythespire.model.relic.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.EffectContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.StarterTypeRelic;

public class SneckoSkullRelic extends PlayerTypeRelic {

    public SneckoSkullRelic() {
        super("Snecko Skull", "Whenever you apply Icon Poison Poison, apply an additional 1 Icon Poison Poison.",
                RelicRarity.COMMON, PlayerType.SILENT, RelicTrigger.EFFECT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        EffectContext effectContext = gameContext.getEffectContext();
        if (effectContext.getEffect() instanceof PoisonDebuffEnemy) {
            Entity source = effectContext.getSource();
            Player player = gameContext.getPlayer();
            if (source == player) {
                Entity target = effectContext.getTarget();
                target.addEffect(new PoisonDebuffEnemy(), 1);
            }
        }
    }

}