package de.bundeswehr.auf.slaythespire.model.relic.event;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.player.debuff.StrengthDownDebuffPlayer;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.EventTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class NeowsLamentRelic extends EventTypeRelic {

    private int amount;

    public NeowsLamentRelic() {
        super("Neow's Lament", "Enemies in your first 3 combats will have 1 HP.", RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (amount++ < 3) {
            List<Enemy> allEnemies = gameContext.getEnemies();
            for (Enemy enemy : allEnemies) {
                gameContext.setAttackContext(new AttackContext(null, enemy, enemy.getCurrentHealth() - 1,this));
                enemy.looseHp(gameContext);
            }
        }
    }

}