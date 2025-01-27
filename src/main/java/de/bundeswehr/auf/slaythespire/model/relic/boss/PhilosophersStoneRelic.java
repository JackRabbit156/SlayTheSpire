package de.bundeswehr.auf.slaythespire.model.relic.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.AdditionallyTriggered;
import de.bundeswehr.auf.slaythespire.model.relic.structure.BossTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class PhilosophersStoneRelic extends BossTypeRelic implements AdditionallyTriggered {

    public PhilosophersStoneRelic() {
        super("Philosopher's Stone", "Gain 1 Energy at the start of each turn. ALL enemies start with 1 Strength.",
                RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainEnergy(1);
    }

    @Override
    public RelicTrigger getAdditionalTrigger() {
        return RelicTrigger.START_OF_COMBAT;
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new StrengthBuff(), 1);
        }
    }

}
