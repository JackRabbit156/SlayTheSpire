package models.player;

import models.cards.card_structure.Card;
import models.cards.general_cards.DefendCard;
import models.cards.general_cards.StrikeCard;
import models.cards.ironclad_cards.attack.common.BashCard;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relics.BurningBloodRelic;
import models.relics.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Ironclad-Spieler, eine von mehreren möglichen Spielerklassen im Spiel.
 * Ironclad hat spezifische Attribute, eine Startreliktie und ein Deck von Karten, die für seine Spielweise
 * geeignet sind.
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class Ironclad extends Player {
    // * Constructor *
    public Ironclad() {
        super("Ironclad", 80, 3, PlayerType.IRONCLAD,"⚒");

        initRelic();
        initDeck();
    }


    // * Methods *
    @Override
    protected void initRelic() {
        Relic startRelic = new BurningBloodRelic();
        setRelic(startRelic);
    }


    @Override
    protected void initDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            deck.add(new StrikeCard());

        for (int i = 0; i < 4; i++)
            deck.add(new DefendCard());

        deck.add(new BashCard());

        setDeck(deck);
    }
}
