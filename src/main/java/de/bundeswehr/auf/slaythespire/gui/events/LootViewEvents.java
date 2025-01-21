package de.bundeswehr.auf.slaythespire.gui.events;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;

/**
 * @author Keil, Vladislav
 */
public interface LootViewEvents {

    void onBackClicked();

    void onCardClick(Card card);

    void onFullScreenClick();

    void onGoldClick(int gold);

    void onPotionClick(Potion potion);

    void onRelicClick(Relic relic);

}
