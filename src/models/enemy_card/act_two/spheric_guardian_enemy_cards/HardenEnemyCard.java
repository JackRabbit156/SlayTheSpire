package models.enemy_card.act_two.spheric_guardian_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class HardenEnemyCard extends EnemyCard {
    public HardenEnemyCard() {
        super("Corrosive Spit", "Deals 10 damage, gains 15 block.");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(10, false);
        enemy.addBlock(15);
    }

    @Override
    public void play(GameContext gameContext) {
    }
}
