package models.cards.ironclad_cards.attack.common;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Headbutt Karte.
 * @author OF Daniel Willig
 */
public class HeadbuttCard extends AttackCard {
    /**
     * Constructor HeadbuttCard.
     */
    public HeadbuttCard() {
        super("Headbutt", "Deal 9 damage. Put a card from your discard pile on top of your draw pile.", 1, 9, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {

        System.out.print("Choose an enemy to target: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage());

        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> deck = battleDeck.getDeck();
        List<Card> discardPile = battleDeck.getDiscardPile();

        System.out.print("Choose a card to put from your discard pile onto the top of your draw pile: ");
        int targetCard = new Scanner(System.in).nextInt() - 1;
        deck.add(discardPile.get(targetCard));

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
