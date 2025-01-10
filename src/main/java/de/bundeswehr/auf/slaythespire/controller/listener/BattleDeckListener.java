package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

import java.util.List;

public interface BattleDeckListener extends CardEventListener {

    void chooseCard(List<Card> cards, CardEventListener eventListener);

    void onCardDrawn(Card card);

    void onCardFill();

}
