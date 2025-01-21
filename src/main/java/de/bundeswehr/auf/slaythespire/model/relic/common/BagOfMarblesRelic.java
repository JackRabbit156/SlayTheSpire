package de.bundeswehr.auf.slaythespire.model.relic.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class BagOfMarblesRelic extends Relic {

    public BagOfMarblesRelic() {
        super("Bag of Marbles", "At the start of each combat, apply 1 Vulnerable to ALL enemies.",
                RelicRarity.COMMON, RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new VulnerableDebuff(), 1);
        }
    }

}
