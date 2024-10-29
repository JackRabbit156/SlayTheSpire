package models.cards.ironclad_cards.uncommon;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class SearingBlowCard extends AttackCard {
    public SearingBlowCard() {
        super("Searing Blow", "Deal 12 damage. Can be Upgraded any number of times.", 2, 12, CardRarity.UNCOMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());

        //TODO upgrading
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
