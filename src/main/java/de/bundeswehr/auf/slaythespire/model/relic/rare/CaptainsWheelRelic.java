package de.bundeswehr.auf.slaythespire.model.relic.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class CaptainsWheelRelic extends Relic implements Resetable {

    private int turn;

    public CaptainsWheelRelic() {
        super("Captain's Wheel", "At the start of your 3rd turn, gain 18 Block.",
                RelicRarity.RARE, RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        if (++turn == 3) {
            Player player = gameContext.getPlayer();
            player.gainBlock(18);
        }
    }

    public void reset() {
        turn = 0;
    }

}