package models.cards.ironclad_cards.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class ClotheslineCard extends AttackCard {
    public ClotheslineCard() {
        super("Clothesline", "Deal 12 damage. Apply 2 Icon Weak Weak.", 2, 12, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());

        //TODO Apply 2 Weak.
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
