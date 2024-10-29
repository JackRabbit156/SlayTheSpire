package models.cards.ironclad_cards.uncommon;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class BloodForBloodCard extends AttackCard {
    public BloodForBloodCard() {
        super("Blood for Blood", "Costs 1 less Energy for each time you lose HP this combat.Deal 18 damage.", 4, 18, CardRarity.UNCOMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost()); //TODO minus each time you lost HP this combat
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
