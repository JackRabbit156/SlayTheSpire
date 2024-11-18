package models.cards.ironclad_cards.skill.common;

import helper.PathAssistent;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Die Warcry Karte.
 *
 * @author OF Daniel Willig
 */
public class WarcryCard extends SkillCard{


    /**
     * Constructor Warcry card.
     */
    public WarcryCard() {
            super("Warcry", "Draw 1 card. Put a card from your hand onto the top of your draw pile.Exhaust.", 0, CardRarity.COMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();
            List<Card> deck = battleDeck.getDeck();
            List<Card> hand = battleDeck.getHand();

            battleDeck.drawCard(1);


            System.out.print("Choose a card to put from your hand onto the top of your draw pile: ");
            int targetCard = new Scanner(System.in).nextInt() - 1;
            deck.add(hand.get(targetCard));

            player.decreaseCurrentEnergy(getCost());
        }
}
