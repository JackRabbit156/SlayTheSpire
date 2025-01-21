package de.bundeswehr.auf.slaythespire.model.relic.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class PearRelic extends Relic {

    public PearRelic() {
        super("Pear", "Upon pickup, raise your Max HP by 10.",
                RelicRarity.UNCOMMON, RelicTrigger.PICKUP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainMaxHp(10);
    }

}