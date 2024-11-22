package models.enemy_card.act_two.boss.bronze_automaton_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class BoostEnemyCard extends EnemyCard {
    public BoostEnemyCard() {
        super("Boost", "Gains 9 block");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(9);
    }

    @Override
    public void play(GameContext gameContext) {
    }
}
