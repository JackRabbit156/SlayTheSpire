package models.enemy_card.act_two.elites.book_of_stabbing_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

public class MultiStabEnemyCard extends EnemyCard {
    public MultiStabEnemyCard() {
        super("Multi Stab", "Deals a lot of damage.");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        int attackDamage = 6 * randomAttack;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
    }

    @Override
    public void play(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        int attackDamage = 6 * randomAttack;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
    }
}
