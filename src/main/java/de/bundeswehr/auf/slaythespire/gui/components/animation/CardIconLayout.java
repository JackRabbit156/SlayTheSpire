package de.bundeswehr.auf.slaythespire.gui.components.animation;

import de.bundeswehr.auf.slaythespire.gui.components.IconOnlyLayout;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

public class CardIconLayout extends IconOnlyLayout {

    private final Card card;

    public CardIconLayout(Card card) {
        super(card.getImagePath());
        this.card = card;
    }

    @Override
    protected String getPath() {
        return card.getImagePath();
    }

    @Override
    protected double getSize() {
        return 50;
    }

}
