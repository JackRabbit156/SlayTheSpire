package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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
