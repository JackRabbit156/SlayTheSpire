package de.bundeswehr.auf.slaythespire.gui.events;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;

public interface BattleViewEvents {

    void onCardClick(Card card, int index);
    void onEnemyClick(Enemy enemy);
    void onFullScreenClick();
    void onPlayerClick();
    void onEndTurnClick();

}