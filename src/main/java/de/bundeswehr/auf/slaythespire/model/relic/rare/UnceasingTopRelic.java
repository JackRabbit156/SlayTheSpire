package de.bundeswehr.auf.slaythespire.model.relic.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class UnceasingTopRelic extends Relic {

    public UnceasingTopRelic() {
        super("Unceasing Top", "Whenever you have no cards in hand during your turn, draw a card.",
                RelicRarity.UNCOMMON, RelicTrigger.PLAY_CARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        if (battleDeck.getHand().size() == 1) {
            battleDeck.drawCard(1);
        }
    }

}