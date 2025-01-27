package de.bundeswehr.auf.slaythespire.model.relic.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToughBandagesRelic extends PlayerTypeRelic {

    public ToughBandagesRelic() {
        super("Tough Bandages", "Whenever you discard a card during your turn, gain 3 Block.",
                RelicRarity.COMMON, PlayerType.SILENT, RelicTrigger.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainBlock(3);
    }

}