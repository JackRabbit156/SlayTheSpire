package de.bundeswehr.auf.slaythespire.gui.events;

import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;
/**
 * @author Keil, Vladislav
 */
public interface LootViewEvents {
    void onCardClick(Card card, int index);
    void onGoldClick(int gold);
    void onPotionClick(PotionCard potion);
    void onBackClicked();
}
