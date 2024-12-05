package view.gui.layouts.layout_events;

import models.card.card_structure.Card;
import models.enemy.Enemy;
import models.player.player_structure.Player;

public interface BattleViewEvents {

    void onCardClick(Card card, int index);
    void onEnemyClick(Enemy enemy);
    void onFullscreenClick();
    void onPlayerClick();
    void onEndTurnClick();

}