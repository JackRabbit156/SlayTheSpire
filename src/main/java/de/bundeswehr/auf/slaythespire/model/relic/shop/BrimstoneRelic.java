package de.bundeswehr.auf.slaythespire.model.relic.shop;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.*;

import java.util.List;

public class BrimstoneRelic extends PlayerTypeRelic {

    public BrimstoneRelic() {
        super("Brimstone", "At the start of your turn, gain 2 Strength and ALL enemies gain 1 Strength.",
            RelicRarity.SHOP, PlayerType.IRONCLAD, RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), 2);
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new StrengthBuff(), 1);
        }
    }

}