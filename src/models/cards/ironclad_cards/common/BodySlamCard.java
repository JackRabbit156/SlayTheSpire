package models.cards.ironclad_cards.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class BodySlamCard extends AttackCard {
    public BodySlamCard() {
        super("Body Slam", "Deal damage equal to your Block.", 1, 0, CardRarity.COMMON); //TODO damage is 0, should be player.getBlock().
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

    //TODO AttackCard eventuell ändern, da damage in diesem Fall von player.getBlock() kommt.
    @Override
    public int dealDamage() {
        return 0;
    }

    public int dealDamage(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        return player.getBlock();
    }
}
