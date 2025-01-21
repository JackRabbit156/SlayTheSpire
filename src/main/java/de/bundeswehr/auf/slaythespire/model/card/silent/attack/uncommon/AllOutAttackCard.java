package de.bundeswehr.auf.slaythespire.model.card.silent.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die All-Out Attack Karte.
 *
 * @author L Frank Rieger
 */
public class AllOutAttackCard extends AttackCard {

    private static final Random rnd = new Random();

    public AllOutAttackCard() {
        super("All-Out Attack", "Deal 10 damage to ALL enemies. Discard 1 card at random.", 1, 10, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
        }

        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = gameContext.getBattleDeck().getHand();
        if (hand.size() > 1) {
            Card card;
            do {
                card = hand.get(rnd.nextInt(hand.size()));
            } while (card != this);
            battleDeck.discardCardFromHand(card);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}