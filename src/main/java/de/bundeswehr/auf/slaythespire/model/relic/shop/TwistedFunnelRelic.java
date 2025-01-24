package de.bundeswehr.auf.slaythespire.model.relic.shop;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class TwistedFunnelRelic extends PlayerTypeRelic {

    public TwistedFunnelRelic() {
        super("Twisted Funnel", "At the start of each combat, apply 4 Poison to ALL enemies.",
            RelicRarity.SHOP, PlayerType.SILENT, RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new PoisonDebuffEnemy(), 4);
        }
    }

}