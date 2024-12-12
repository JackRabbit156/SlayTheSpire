package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Die Warcry Karte.
 *
 * @author OF Daniel Willig
 */
public class WarcryCard extends SkillCard {

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
