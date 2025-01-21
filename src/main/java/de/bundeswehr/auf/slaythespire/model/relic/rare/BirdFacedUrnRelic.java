package de.bundeswehr.auf.slaythespire.model.relic.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class BirdFacedUrnRelic extends Relic {

    public BirdFacedUrnRelic() {
        super("Bird-Faced Urn", "Whenever you play a Power, heal 2 HP.",
                RelicRarity.RARE, RelicTrigger.PLAY_POWER);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.heal(2);
    }

}