package de.bundeswehr.auf.slaythespire.gui.layouts.layout_events;

import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;

public interface BattleViewEvents {

    void onCardClick(Card card, int index);
    void onEnemyClick(Enemy enemy);
    void onFullscreenClick();
    void onPlayerClick();
    void onEndTurnClick();

}