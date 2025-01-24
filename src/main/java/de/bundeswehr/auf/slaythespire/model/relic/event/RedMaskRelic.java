package de.bundeswehr.auf.slaythespire.model.relic.event;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.relic.structure.EventTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class RedMaskRelic extends EventTypeRelic {

    public RedMaskRelic() {
        super("Red Mask", "At the start of each combat, apply 1 Weak to ALL enemies.", RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new WeakDebuff(), 1);
        }
    }

}