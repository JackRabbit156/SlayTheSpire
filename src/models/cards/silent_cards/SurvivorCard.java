package models.cards.silent_cards;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Scanner;

public class SurvivorCard extends SkillCard {
    public SurvivorCard() {
        super("Survivor", "Gain 8 Block. Discard 1 card.", 1, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = gameContext.getBattleDeck().getHand();

        player.setBlock(8);

        System.out.print("Choose a card to discard: ");
        int targetIndex = new Scanner(System.in).nextInt() - 1;
        Card targetCard = hand.get(targetIndex);

        battleDeck.discardCardFromHand(targetCard);


        player.decreaseCurrentEnergy(getCost());
    }
}