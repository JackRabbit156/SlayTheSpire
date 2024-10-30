package models.cards.ironclad_cards.attack.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class HeavyBladeCard extends AttackCard {
    public HeavyBladeCard() {
        super("Heavy Blade", "Deal 14 damage. Strength affects this card 3 times.", 2, 14, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());

        //TODO Buff Strength affects this card 3 times
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
