package models.card.silent_cards;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.SkillCard;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Die Survivor Karte.
 *
 * @author OF Daniel Willig
 */
public class SurvivorCard extends SkillCard {
    /**
     * Constructor Survivor card.
     */
    public SurvivorCard() {
        super("Survivor", "Gain 8 Block. Discard 1 card.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = gameContext.getBattleDeck().getHand();

        player.setBlock(8);

        System.out.print("Choose a card to discard: ");

        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getBattleDeck().getStartHandSize());
        Card targetCard = hand.get(targetIndex);

        battleDeck.discardCardFromHand(targetCard);


        player.decreaseCurrentEnergy(getCost());
    }
}