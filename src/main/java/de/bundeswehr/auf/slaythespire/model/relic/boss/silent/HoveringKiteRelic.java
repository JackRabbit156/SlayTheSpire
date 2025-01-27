package de.bundeswehr.auf.slaythespire.model.relic.boss.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.StarterTypeRelic;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class HoveringKiteRelic extends StarterTypeRelic implements Resetable {

    private boolean reset = true;

    public HoveringKiteRelic() {
        super("Hovering Kite", "The first time you discard a card each turn, gain 1 Energy.",
                PlayerType.SILENT, RelicTrigger.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (reset) {
            Player player = gameContext.getPlayer();
            player.gainEnergy(1);
            reset = false;
        }
    }

    @Override
    public RelicTrigger getResetTrigger() {
        return RelicTrigger.END_OF_TURN;
    }

    @Override
    public void reset() {
        reset = true;
    }

}