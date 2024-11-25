package models.enemy_card;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class InsultEnemyCard extends EnemyCard {
    public InsultEnemyCard() {
        super("Insult", "Insult", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        // nothing will happen
    }
}
