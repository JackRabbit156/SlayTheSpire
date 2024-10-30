package models.cards.ironclad_cards.attack.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class BodySlamCard extends AttackCard {
    public BodySlamCard() {
        super("Body Slam", "Deal damage equal to your Block.", 1, 0, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage(gameContext));

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());

    }


    @Override
    public int dealDamage() {
        return 0;
    }

    public int dealDamage(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        return player.getBlock();
    }
}
