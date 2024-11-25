package models.enemy_card.act_four.spiker_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class CutEnemyCard extends EnemyCard {
    public CutEnemyCard() {
        super("Cut", "Deals 7 damage.", "7");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(7, false);
    }
}
