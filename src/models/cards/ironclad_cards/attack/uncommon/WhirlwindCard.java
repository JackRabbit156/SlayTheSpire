package models.cards.ironclad_cards.attack.uncommon;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

/**
 * Die Whirlwind Karte.
 *
 * @author OF Daniel Willig
 */
public class WhirlwindCard extends AttackCard {
    /**
     * Constructor Whirlwind card.
     */
    public WhirlwindCard() {
        super("Whirlwind", "Deal 8 damage to ALL enemies X times.", 1, 5, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        int times;
        do {
            System.out.print("Choose X: ");
            times = new Scanner(System.in).nextInt();

        } while (times > player.getCurrentEnergy() || times < 1);


        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        for (int i = 0; i < times; i++) {
            enemy.takeDamage(dealDamage());
        }


        player.decreaseCurrentEnergy(getCost() * times);
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
