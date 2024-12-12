package de.bundeswehr.auf.slaythespire.gui.events;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

public interface CardEvent {

    default void disableCardSelection(){
        // no default action
    }

    void onCardClick(Card card);

}
