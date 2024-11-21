package view.gui.layouts.layout_events;

import models.card.card_structure.Card;
import models.potion.potion_structure.PotionCard;
/**
 * @author Keil, Vladislav
 */
public interface LootViewEvents {
    void onCardClick(Card card, int index);
    void onGoldClick(int gold);
    void onPotionClick(PotionCard potion);
    void onBackClicked();
}
