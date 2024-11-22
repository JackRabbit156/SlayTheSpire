package models.enemy_card.act_two.boss.the_champ_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class DefensiveStanceEnemyCard extends EnemyCard {
    public DefensiveStanceEnemyCard() {
        super("Defensive Stance", "Gain 15 block.");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(15);
    }

    @Override
    public void play(GameContext gameContext) {
    }
}
