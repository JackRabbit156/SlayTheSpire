package de.bundeswehr.auf.slaythespire.model.player;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.BashCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.ironclad.common.BurningBloodRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;

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

    public IroncladPlayer(Stage primaryStage) {
        super("Ironclad", 80, 3, PlayerType.IRONCLAD, primaryStage);
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
            deck.add(new IroncladStrikeCard());
        }

        for (int i = 0; i < 4; i++) {
            deck.add(new IroncladDefendCard());
        }

        deck.add(new BashCard());

        setDeck(deck);
    }

    @Override
    protected void initRelic() {
        Relic startRelic = new BurningBloodRelic();
        addRelic(startRelic);
    }

}
