package models.enemy_card.act_two.elites.gremlin_leader_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class EncourageEnemyCard extends EnemyCard {
    public EncourageEnemyCard() {
        super("Encourage", "Everybody gains 6 block..");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        for (Enemy singleEnemy : gameContext.getEnemies()) {
            singleEnemy.addBlock(6);
        }
    }

    @Override
    public void play(GameContext gameContext) {
        for (Enemy singleEnemy : gameContext.getEnemies()) {
            singleEnemy.addBlock(6);
        }
    }
}
