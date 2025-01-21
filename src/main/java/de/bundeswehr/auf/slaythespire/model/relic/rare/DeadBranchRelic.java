package de.bundeswehr.auf.slaythespire.model.relic.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class DeadBranchRelic extends Relic {

    public DeadBranchRelic() {
        super("Dead Branch", "Whenever you Exhaust a card, add a random card to your hand.",
                RelicRarity.RARE, RelicTrigger.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        Player player = gameContext.getPlayer();
        DeckFactory deckFactory = new DeckFactory(player, 1);
        battleDeck.addToHand(deckFactory.init(false).get(0));
    }

}