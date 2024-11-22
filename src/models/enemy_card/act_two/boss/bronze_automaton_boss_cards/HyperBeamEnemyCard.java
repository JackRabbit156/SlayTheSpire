package models.enemy_card.act_two.boss.bronze_automaton_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class HyperBeamEnemyCard extends EnemyCard {
    public HyperBeamEnemyCard() {
        super("Hyper Beam", "Deals 45 damage.");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(45, false);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(45, false);
    }
}
