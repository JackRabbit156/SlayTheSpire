package models.player;


import models.cards.DefendCard;
import models.cards.NeutralizeCard;
import models.cards.StrikeCard;
import models.cards.SurvivorCard;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;
import models.relics.RingOfTheSnake;
import models.relics.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;

public class Silent extends Player {
    // * Constructor *
    public Silent() {
        super("Silent", 70, 3);
        initRelic();
        initDeck();
    }

    // * Methods *
    @Override
    protected void initRelic() {
        Relic startRelic = new RingOfTheSnake();
        setRelic(startRelic);
    }

    @Override
    protected void initDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            deck.add(new StrikeCard());

        for (int i = 0; i < 5; i++)
            deck.add(new DefendCard());

        deck.add(new SurvivorCard());

        deck.add(new NeutralizeCard());

        setDeck(deck);
    }
}
