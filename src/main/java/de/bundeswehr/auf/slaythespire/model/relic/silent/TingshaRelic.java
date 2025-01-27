package de.bundeswehr.auf.slaythespire.model.relic.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
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

public class TingshaRelic extends PlayerTypeRelic {

    private final static Random rnd = new Random();

    public TingshaRelic() {
        super("Tingsha", "Whenever you discard a card during your turn, deal 3 damage to a random enemy for each card discarded.",
                RelicRarity.COMMON, PlayerType.SILENT, RelicTrigger.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = new ArrayList<>(gameContext.getEnemies());
        allEnemies.sort((o1, o2) -> rnd.nextInt(3) - 1);
        for (Enemy enemy : allEnemies) {
            if (enemy.isAlive()) {
                player.dealDamage(gameContext, 3, enemy, this);
                break;
            }
        }
    }

}