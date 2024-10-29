package models.cards.ironclad_cards.uncommon;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class WhirlwindCard extends AttackCard {
    public WhirlwindCard() {
        super("Whirlwind", "Deal 8 damage to ALL enemies X times.", 1, 5, CardRarity.UNCOMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        int times;
        do {
            System.out.print("Choose X: ");
            times = new Scanner(System.in).nextInt();

        } while (times > player.getCurrentEnergy() || times < 1);


        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        for (int i = 0; i < times; i++) {
            enemy.takeDamage(dealDamage());
        }


        player.loseEnergy(getCost() * times);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
