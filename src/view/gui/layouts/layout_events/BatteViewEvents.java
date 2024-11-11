package view.gui.layouts.layout_events;

import models.cards.card_structure.Card;
import models.enemy.Enemy;

public interface BatteViewEvents {
    void onCardClick(Card card, int index);
    void onEnemyClick(Enemy enemy);
}