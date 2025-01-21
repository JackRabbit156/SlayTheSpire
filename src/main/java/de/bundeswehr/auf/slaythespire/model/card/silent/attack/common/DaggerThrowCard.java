package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Dagger Throw Karte.
 *
 * @author L Frank Rieger
 */
public class DaggerThrowCard extends AttackCard implements CardEventListener {

    private GameContext gameContext;

    public DaggerThrowCard() {
        super("Dagger Throw", "Deal 9 damage. Draw 1 card. Discard 1 card.", 1, 9, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onCardClick(Card card) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.discardCardFromHand(card);

        super.played();
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        this.gameContext = gameContext;
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);

        battleDeck.chooseCardFromHand(this);
    }

    @Override
    public void played() {
        // do nothing, wait for user interaction
    }

}