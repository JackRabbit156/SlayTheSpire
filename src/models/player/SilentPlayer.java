package models.player;


import helper.PathAssistent;
import javafx.stage.Stage;
import models.card.card_structure.Card;
import models.card.silent.SilentDefendCard;
import models.card.silent.SilentStrikeCard;
import models.card.silent.attack.common.NeutralizeCard;
import models.card.silent.skill.common.SurvivorCard;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relic.RingOfTheSnake;
import models.relic.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;

/**
 * Der Silent player.
 *
 * Diese Klasse repräsentiert den Silent-Spieler, eine von mehreren möglichen Spielerklassen im Spiel. Silent hat spezifische Attribute, eine Startreliktie und ein Deck von Karten, die für seine Spielweise geeignet sind.
 * Author:
 *
 * @author OF Daniel Willig
 */
public class SilentPlayer extends Player {
    // * Constructor *
    public SilentPlayer(Stage primaryStage) {
        super("Silent", 70, 3, PlayerType.SILENT, primaryStage);
        setImagePath(new PathAssistent().toPath(this));
        setAltImagePath(new PathAssistent().toAltPath(this,1));
        setGold(99);
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
            deck.add(new SilentStrikeCard());

        for (int i = 0; i < 5; i++)
            deck.add(new SilentDefendCard());

        deck.add(new SurvivorCard());

        deck.add(new NeutralizeCard());

        setDeck(deck);
    }
}
