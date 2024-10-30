package models.player;

import models.cards.BashCard;
import models.cards.DefendCard;
import models.cards.StrikeCard;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

public class Ironclad extends Player {
    public Ironclad() {
        super("Ironclad", 80, 3);
        initDeck();
    }

    @Override
    protected void initDeck(){
        List<Card> deck = new ArrayList<>();
        for(int i =0; i< 5; i++)
            deck.add(new StrikeCard());

        for(int i =0; i< 4; i++)
            deck.add(new DefendCard());

        deck.add(new BashCard());

        setDeck(deck);
    }
}
