package models.cards.ironclad_cards.attack.uncommon;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class RecklessChargeCard extends AttackCard {
    public RecklessChargeCard() {
        super("Reckless Charge", "Deal 7 damage. Shuffle a Dazed into your draw pile.", 0, 7, CardRarity.UNCOMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());

        //TODO deck.add dazed
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
