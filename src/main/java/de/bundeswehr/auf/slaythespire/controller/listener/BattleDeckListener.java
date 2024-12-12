package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.gui.events.CardEvent;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

import java.util.List;

public interface BattleDeckListener {

    void chooseCard(List<Card> cards, CardEvent eventListener);

    void onCardFill();

}
