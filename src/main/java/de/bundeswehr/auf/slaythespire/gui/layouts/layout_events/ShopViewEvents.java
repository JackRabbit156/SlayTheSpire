package de.bundeswehr.auf.slaythespire.gui.layouts.layout_events;

import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.Relic;
/**
 * @author Keil, Vladislav
 */
public interface ShopViewEvents {

    void onCardClick(Card card, int index);

    void onFullscreenClick();

    void onPotionClick(PotionCard potion);
    void onRelicClick(Relic relic, int index);

    void onBackClicked();

}