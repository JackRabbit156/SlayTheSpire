package view.gui.layouts.layout_events;

import models.card.card_structure.Card;
import models.potion.potion_structure.PotionCard;
import models.relic.relic_structure.Relic;
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