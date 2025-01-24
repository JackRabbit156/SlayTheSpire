package de.bundeswehr.auf.slaythespire.model.relic.event;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.EventTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

public class GoldenIdolRelic extends EventTypeRelic {

    public GoldenIdolRelic() {
        super("Golden Idol", "Everything drops 25 gold extra.", RelicTrigger.GAIN_GOLD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.setGold(player.getGold() + 25);
        GameSettings.increaseGoldStats(25);
    }

}