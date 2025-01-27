package de.bundeswehr.auf.slaythespire.model.relic.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.EffectContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheSpecimenRelic extends PlayerTypeRelic {

    private final static Random rnd = new Random();

    public TheSpecimenRelic() {
        super("The Specimen", "Whenever an enemy dies, transfer any Poison it has to a random enemy.",
                RelicRarity.COMMON, PlayerType.SILENT, RelicTrigger.LOSE_HP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        AttackContext attackContext = gameContext.getAttackContext();
        Entity target = attackContext.getTarget();
        if (target == player) {
            return;
        }
        PoisonDebuffEnemy poisonEffect = new PoisonDebuffEnemy();
        int poisonCounter = target.getEffectCounter(poisonEffect);
        if (!target.isAlive() && poisonCounter > 0) {
            List<Enemy> allEnemies = new ArrayList<>(gameContext.getEnemies());
            allEnemies.sort((o1, o2) -> rnd.nextInt(3) - 1);
            for (Enemy enemy : allEnemies) {
                if (enemy.isAlive()) {
                    enemy.addEffect(poisonEffect, poisonCounter);
                    break;
                }
            }
        }
    }

}