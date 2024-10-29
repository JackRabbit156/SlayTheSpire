package models.cards.ironclad_cards;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class BodySlamCard extends AttackCard {
    public BodySlamCard() {
        super(name, description, cost, damage, rarity);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return 0;
    }
}
