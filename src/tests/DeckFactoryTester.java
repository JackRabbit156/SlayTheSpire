package tests;

import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.player.IroncladPlayer;

import java.util.ArrayList;
import java.util.List;

public class DeckFactoryTester {
    public static void main(String[] args) {
        IroncladPlayer player = new IroncladPlayer();
        for (int i = 0; i < 100; i++) {
            DeckFactory deckFactory = new DeckFactory(player, 5);
            List<Card> cards = deckFactory.init();
        }
    }
}
