package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.BattleDeck;
import models.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
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
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
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
