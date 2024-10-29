package models.cards.ironclad_cards.attack.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.Scanner;

public class ClashCard extends AttackCard {
    public ClashCard() {
        super("Clash", "Can only be played if every card in your hand is an Attack. Deal 14 damage.", 0, 14, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {

        //TODO if Hand contains only AttackCards, do everything, else can't play.

        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;
        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        Player player = gameContext.getPlayer();


        enemy.takeDamage(dealDamage());

        player.loseEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
