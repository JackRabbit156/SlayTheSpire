package models.enemy_card.act_two.spheric_guardian_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;

public class ActivateEnemyCard extends EnemyCard {
    public ActivateEnemyCard() {
        super("Activate", "Gains 25 block.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(25);
    }

}
