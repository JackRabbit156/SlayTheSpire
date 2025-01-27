package de.bundeswehr.auf.slaythespire.model.relic.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class HornCleatRelic extends Relic implements Resetable {

    private int turn;

    public HornCleatRelic() {
        super("Horn Cleat", "At the start of your 2nd turn, gain 14 Block.",
                RelicRarity.UNCOMMON, RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (++turn == 2) {
            Player player = gameContext.getPlayer();
            player.gainBlock(14);
        }
    }

    @Override
    public RelicTrigger getResetTrigger() {
        return RelicTrigger.END_OF_COMBAT;
    }

    public void reset() {
        turn = 0;
    }

}