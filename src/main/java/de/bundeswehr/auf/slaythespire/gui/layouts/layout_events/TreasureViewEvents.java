package de.bundeswehr.auf.slaythespire.gui.layouts.layout_events;

import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;
/**
 * @author Keil, Vladislav
 */
public interface TreasureViewEvents {
    void onCardClick(Card card, int index);
    void onGoldClick(int gold);
    void onPotionClick(PotionCard potion);
    void onBackClicked();
}
