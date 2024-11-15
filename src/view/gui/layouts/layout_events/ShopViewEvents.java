package view.gui.layouts.layout_events;

import models.cards.card_structure.Card;
import models.enemy.Enemy;
import models.potion.potion_structure.PotionCard;
import models.relics.relic_structure.Relic;

public interface ShopViewEvents {
    void onCardClick(Card card, int index);
    void onPotionClick(PotionCard potion, int index);
    void onRelicClick(Relic relic, int index);

    void onBackClick();
}