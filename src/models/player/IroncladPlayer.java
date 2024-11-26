package models.player;

import helper.PathAssistent;
import javafx.stage.Stage;
import models.card.card_structure.Card;
import models.card.ironclad_cards.IroncladDefendCard;
import models.card.ironclad_cards.IroncladStrikeCard;
import models.card.ironclad_cards.attack.common.BashCard;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relic.BurningBloodRelic;
import models.relic.relic_structure.Relic;

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
    public IroncladPlayer(Stage primaryStage) {
        super("Ironclad", 80, 3, PlayerType.IRONCLAD, primaryStage);
        setImagePath(new PathAssistent().toPath(this));
        setAltImagePath(new PathAssistent().toAltPath(this,1));
        setGold(99);

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
            deck.add(new IroncladStrikeCard());

        for (int i = 0; i < 4; i++)
            deck.add(new IroncladDefendCard());

        deck.add(new BashCard());

        setDeck(deck);
    }
}
