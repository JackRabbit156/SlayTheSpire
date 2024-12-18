package de.bundeswehr.auf.slaythespire.model.card;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die DeckFactory-Klasse ist verantwortlich für die Erstellung und Verwaltung des Kartendecks eines Spielers.
 * Sie initialisiert ein Deck mit einer bestimmten Anzahl von Karten und einem optionalen Kartentyp.
 *
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class DeckFactory {

    private static final Random rnd = new Random();

    private final int amount;
    private CardType cardType = CardType.CURSE; //Platzhalter, damit irgendwas gesetzt ist, liegt an der AttackPotion!
    private final List<Card> genDeck;
    private final Player player;

    /**
     * Die Methode assignCard wird verwendet, um ein spezifisches Kartenobjekt basierend auf dem übergebenen Kartenname zu erstellen und zurückzugeben.
     * Sie überprüft den übergebenen Kartenname und instanziiert die entsprechende Kartenklasse.
     *
     * @param cardName - Der Name der Karte, die zugewiesen werden soll. Der Name entspricht verschiedenen Kartentypen
     * @return Card the assigned Card
     */
    public static Card cardFor(String cardName) {
        Card card = null;
        try {
            card = Model.ofCards("ironclad", cardName);
        } catch (IllegalArgumentException e) {
            try {
                card = Model.ofCards("silent", cardName);
            } catch (IllegalArgumentException e2) {
                LoggingAssistant.log("Error in DeckFactory creating Card: " + cardName, Color.RED);
            }
        }
        return card;
    }

    public static Potion potionFor(String potionName) {
        Potion potion = null;
        try {
            potion = Model.ofPotions(potionName);
        } catch (IllegalArgumentException e) {
            LoggingAssistant.log("Error in DeckFactory creating Potion: " + potionName, Color.RED);
        }
        return potion;
    }

    public static Relic relicFor(String relicName) {
        Relic relic = null;
        try {
            relic = Model.ofRelics(relicName);
        } catch (IllegalArgumentException e) {
            LoggingAssistant.log("Error in DeckFactory creating Relic: " + relicName, Color.RED);
        }
        return relic;
    }

    /**
     * Konstruktor für die DeckFactory, der ein Deck mit einer bestimmten Anzahl an Karten für den gegebenen Spieler erstellt.
     * Der Kartentyp wird auf den Standardwert "CURSE" gesetzt.
     *
     * @param player Der Spieler, dessen Deck erstellt werden soll.
     * @param amount Die Anzahl der Karten, die im Deck enthalten sein sollen.
     */
    public DeckFactory(Player player, int amount) {
        this.player = player;
        this.amount = amount;
        this.genDeck = new ArrayList<>();
    }

    /**
     * Konstruktor für die DeckFactory, der ein Deck mit einer bestimmten Anzahl an Karten und einem spezifischen Kartentyp erstellt.
     *
     * @param player   Der Spieler, dessen Deck erstellt werden soll.
     * @param amount   Die Anzahl der Karten, die im Deck enthalten sein sollen.
     * @param cardType Der Kartentyp, der für das Deck verwendet wird (z.B. Angriff, Verteidigung, etc.).
     */
    public DeckFactory(Player player, int amount, CardType cardType) {
        this.player = player;
        this.amount = amount;
        this.cardType = cardType;
        this.genDeck = new ArrayList<>();
    }

    /**
     * Generiert eine zufällige Trankkarte aus einer Liste verfügbarer Tränke.
     * Zunächst wird eine Liste aller verfügbaren Tränke erstellt. Danach wird ein zufälliger Trank ausgewählt
     * und eine entsprechende Trankkarte zurückgegeben.
     * Falls die Liste der verfügbaren Tränke nicht korrekt initialisiert wurde, wird eine Fehlermeldung ausgegeben.
     *
     * @return Eine Trankkarte, die dem zufällig ausgewählten Trank entspricht.
     * Falls ein Fehler auftritt, kann die Methode in einem fehlerhaften Zustand enden.
     */
    public Potion generatePotion() {
        List<Class<? extends Potion>> availablePotions = Model.potions();
        int randomNumber = rnd.nextInt(availablePotions.size());
        try {
            return availablePotions.get(randomNumber).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LoggingAssistant.log(e, Color.RED);
            return null;
        }
    }

    /**
     * Initialisiert das Kartendeck des Spielers basierend auf dem Spielertyp.
     * Je nach Spielertyp (WATCHER, DEFECT, SILENT, IRONCLAD) wird eine spezifische Methode zur Initialisierung der Karten aufgerufen.
     * Bei einem unbekannten Spielertyp wird die Methode rekursiv erneut aufgerufen.
     * Falls die Initialisierung fehlschlägt, wird eine Fehlermeldung ausgegeben.
     *
     * @return Eine Liste von Karten, die das Initialisierungsdeck des Spielers darstellt.
     * Im Falle eines Fehlers wird null zurückgegeben.
     */
    public List<Card> init() {
        switch (player.getPlayerType()) {
            case SILENT:
                return initSilentCards();
            case IRONCLAD:
                return initIroncladCards();
            case WATCHER:
            case DEFECT:
            default:
                LoggingAssistant.log("DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert: " + player.getPlayerType(), Color.RED);
        }
        return null;
    }

    /**
     * Entfernt eine zufällige Karte aus dem Deck des Spielers.
     *
     * @param player Der Spieler, aus dessen Deck eine zufällige Karte entfernt wird.
     */
    public void removeRandomCard(Player player) {
        List<Card> deck = player.getDeck();
        int randomNumber = rnd.nextInt(deck.size());
        Card selectedCard = deck.get(randomNumber);

        LoggingAssistant.log("DeckFactory.class: Entfernung der Karte: " + selectedCard.getName(), Color.RED);

        player.removeCardFromDeck(selectedCard);
    }

    private List<Card> initIroncladCards() {
        List<Class<? extends Card>> availableCards = Model.cards("ironclad");
        for (int i = 0; i < this.amount; i++) {
            int randomNumber = rnd.nextInt(availableCards.size());
            try {
                genDeck.add(availableCards.get(randomNumber).getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LoggingAssistant.log(e, Color.RED);
            }
        }
        return genDeck;
    }

    private List<Card> initSilentCards() {
        List<Class<? extends Card>> availableCards = Model.cards("silent");
        for (int i = 0; i < this.amount; i++) {
            int randomNumber = rnd.nextInt(availableCards.size());
            try {
                genDeck.add(availableCards.get(randomNumber).getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LoggingAssistant.log(e, Color.RED);
            }
        }
        return genDeck;
    }

}
