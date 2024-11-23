package models.enemy_card.act_two.elites.gremlin_leader_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;

public class EncourageEnemyCard extends EnemyCard {
    public EncourageEnemyCard() {
        super("Encourage", "All enemies gain 6 Block.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        for (Enemy singleEnemy : gameContext.getEnemies()) {
            singleEnemy.addBlock(6);
        }
    }
}
