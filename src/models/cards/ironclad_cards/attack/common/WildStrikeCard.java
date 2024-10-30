package models.cards.ironclad_cards.attack.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class WildStrikeCard extends AttackCard {
    public WildStrikeCard() {
        super("Wild Strike", "Deal 12 damage. Shuffle a Wound into your draw pile.", 1, 12, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());

//      TODO Kann Effect
//        Wound wound = new Wound();
//        player.getDeck().add(wound);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
