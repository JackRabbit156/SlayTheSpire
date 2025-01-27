package de.bundeswehr.auf.slaythespire.model.relic.boss.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.StarterTypeRelic;

public class RingOfTheSerpentRelic extends StarterTypeRelic {

    public RingOfTheSerpentRelic() {
        super("Ring of the Serpent", "At the start of your turn, draw 1 additional card.",
                PlayerType.SILENT, RelicTrigger.BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
    }

}