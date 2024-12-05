package de.bundeswehr.auf.slaythespire.models.player;


import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.card.silent.SilentDefendCard;
import de.bundeswehr.auf.slaythespire.models.card.silent.SilentStrikeCard;
import de.bundeswehr.auf.slaythespire.models.card.silent.attack.common.NeutralizeCard;
import de.bundeswehr.auf.slaythespire.models.card.silent.skill.common.SurvivorCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.PlayerType;
import de.bundeswehr.auf.slaythespire.models.relic.RingOfTheSnake;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;

/**
 * Der Silent player.
 * <p>
 * Diese Klasse repräsentiert den Silent-Spieler, eine von mehreren möglichen Spielerklassen im Spiel. Silent hat spezifische Attribute, eine Startreliktie und ein Deck von Karten, die für seine Spielweise geeignet sind.
 * Author:
 *
 * @author OF Daniel Willig
 */
public class SilentPlayer extends Player {

    public SilentPlayer(Stage primaryStage) {
        super("Silent", 70, 3, PlayerType.SILENT, primaryStage);
        setImagePath(new PathAssistent().toPath(this));
        setAltImagePath(new PathAssistent().toAltPath(this, 1));
        setGold(99);
        initRelic();
        initDeck();
    }

    @Override
    protected void initDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new SilentStrikeCard());
        }

        for (int i = 0; i < 5; i++) {
            deck.add(new SilentDefendCard());
        }

        deck.add(new SurvivorCard());

        deck.add(new NeutralizeCard());

        setDeck(deck);
    }

    @Override
    protected void initRelic() {
        Relic startRelic = new RingOfTheSnake();
        setRelic(startRelic);
    }

}
