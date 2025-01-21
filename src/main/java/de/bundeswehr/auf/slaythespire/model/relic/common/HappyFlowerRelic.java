package de.bundeswehr.auf.slaythespire.model.relic.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class HappyFlowerRelic extends Relic implements Resetable {

    private int turn;

    public HappyFlowerRelic() {
        super("Happy Flower", "Every 3 turns, gain 1 Energy.",
                RelicRarity.COMMON, RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (++turn % 3 == 0) {
            Player player = gameContext.getPlayer();
            player.gainEnergy(1);
        }
    }

    @Override
    public void reset() {
        turn = 0;
    }

}