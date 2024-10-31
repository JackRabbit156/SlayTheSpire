package models.cards.silent_cards;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class NeutralizeCard extends AttackCard {
    public NeutralizeCard() {
        super("Neutralize", "Deal 3 damage. Apply 1 Weak.", 0, 3, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        //TODO Apply Debuff 1 Weak
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}