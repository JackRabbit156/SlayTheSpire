package de.bundeswehr.auf.slaythespire.gui.events;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
/**
 * @author Keil, Vladislav
 */
public interface ShopViewEvents {

    void onCardClick(Card card);

    void onFullscreenClick();

    void onPotionClick(PotionCard potion);
    void onRelicClick(Relic relic, int index);

    void onBackClicked();

}