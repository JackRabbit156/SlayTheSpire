package de.bundeswehr.auf.slaythespire.model.relic.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

import java.util.List;

public class StoneCalendarRelic extends Relic implements Resetable {

    private int turn;

    public StoneCalendarRelic() {
        super("Stone Calendar", "At the end of turn 7, deal 52 damage to ALL enemies.",
                RelicRarity.UNCOMMON, RelicTrigger.END_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (++turn == 7) {
            Player player = gameContext.getPlayer();
            List<Enemy> allEnemies = gameContext.getEnemies();
            for (Enemy enemy : allEnemies) {
                player.dealDamage(gameContext, 52, enemy, this);
            }
        }
    }

    public void reset() {
        turn = 0;
    }

}