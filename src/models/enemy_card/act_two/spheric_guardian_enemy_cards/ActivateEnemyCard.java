package models.enemy_card.act_two.spheric_guardian_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class ActivateEnemyCard extends EnemyCard {
    public ActivateEnemyCard() {
        super("Activate", "Gains 25 block.");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        enemy.addBlock(25);
    }

    @Override
    public void play(GameContext gameContext) {
    }
}
