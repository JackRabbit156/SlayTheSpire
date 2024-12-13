package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

public interface CardDeathListener {

    default void onCardCanceled(Card card) {
        // do nothing
    }
    
    void onCardDeath(Card card);

}
