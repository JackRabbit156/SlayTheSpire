package models.enemy_card.act_one.boss.the_guardian_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;

public class ChargingUpEnemyCard extends EnemyCard {
    public ChargingUpEnemyCard() {
        super("Charging Up", "Gains 15 block.");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(15);
    }
}
