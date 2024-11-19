package view.gui.layouts.layout_events;

import models.card.card_structure.Card;

public interface LootViewEvents {
    void onCardClick(Card card, int index);
    void onBackClicked();
}
