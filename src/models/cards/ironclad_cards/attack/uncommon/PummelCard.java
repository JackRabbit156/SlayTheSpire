package models.cards.ironclad_cards.attack.uncommon;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class PummelCard extends AttackCard {
    public PummelCard() {
        super("Pummel", "Deal 2 damage 4 times. Exhaust.", 1, 2, CardRarity.UNCOMMON, CardGrave.EXHAUST);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        for (int i = 0; i < 4; i++) {
            enemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
