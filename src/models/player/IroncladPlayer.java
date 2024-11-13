package models.player;

import helper.PathAssistent;
import models.card.card_structure.Card;
import models.card.general_cards.DefendCard;
import models.card.general_cards.StrikeCard;
import models.card.ironclad_cards.attack.common.BashCard;
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
public class IroncladPlayer extends Player {
    // * Constructor *
    public IroncladPlayer() {
        super("Ironclad", 80, 3, PlayerType.IRONCLAD,"⚒");
        setImagePath(new PathAssistent().toPath(this));

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
