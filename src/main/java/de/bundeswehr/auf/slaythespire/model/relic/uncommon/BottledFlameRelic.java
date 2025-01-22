package de.bundeswehr.auf.slaythespire.model.relic.uncommon;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.effect.buff.ThornsBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class BottledFlameRelic extends Relic implements CardEventListener {

    private Card card;

    public BottledFlameRelic() {
        super("Bottled Flame", "Upon pick up, choose an Attack. Start each combat with this card in your hand.",
                RelicRarity.UNCOMMON, RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.removeCardFromDrawPile(card);
        battleDeck.addToHand(card);
    }

    @Override
    public void onCardClick(Card card) {
        this.card = card;
    }

}