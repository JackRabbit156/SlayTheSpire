package models.cards.ironclad_cards.attack.common;

import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.general_cards.StrikeCard;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Scanner;

public class PerfectedStrikeCard extends AttackCard {
    public PerfectedStrikeCard() {
        super("Perfected Strike", "Deal 6 damage. Deals 2 additional damage for ALL your cards containing \"Strike\".", 2, 6, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Player player = gameContext.getPlayer();
        player.loseEnergy(getCost());

        PerfectedStrikeCard perfectedStrikeCard = new PerfectedStrikeCard();
        PommelStrikeCard pommelStrikeCard = new PommelStrikeCard();
        StrikeCard strikeCard = new StrikeCard();
        TwinStrikeCard twinStrikeCard = new TwinStrikeCard();
        WildStrikeCard wildStrikeCard = new WildStrikeCard();
        //SwiftStrikeCard swiftStrikeCard = new SwiftStrikeCard();
        int bonusDmg = 0;

        List<Card> deck = player.getDeck();

        for (Card card : deck) {
            if (card.equals(perfectedStrikeCard) || card.equals(pommelStrikeCard) || card.equals(strikeCard) || card.equals(twinStrikeCard) || card.equals(wildStrikeCard)) {
                bonusDmg += 2;
            }
        }

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage() + bonusDmg);

    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
