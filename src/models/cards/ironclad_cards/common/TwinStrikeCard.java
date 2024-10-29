package models.cards.ironclad_cards.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class TwinStrikeCard extends AttackCard {
    public TwinStrikeCard() {
        super("Twin Strike", "Deal 5 damage twice.", 1, 5, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        for (int i = 0; i < 2; i++) {
            enemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
