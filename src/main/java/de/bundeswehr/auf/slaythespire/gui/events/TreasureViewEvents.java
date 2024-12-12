package de.bundeswehr.auf.slaythespire.gui.events;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;
/**
 * @author Keil, Vladislav
 */
public interface TreasureViewEvents {

    void onCardClick(Card card);
    void onGoldClick(int gold);
    void onPotionClick(PotionCard potion);
    void onBackClicked();

}
