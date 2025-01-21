package de.bundeswehr.auf.slaythespire.model.card;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Die DeckFactory-Klasse ist verantwortlich für die Erstellung des Kartendecks eines Spielers.
 * Sie initialisiert ein Deck mit einer bestimmten Anzahl von Karten.
 *
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class DeckFactory {

    private static final Random rnd = new Random();

    private final int amount;
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

    public static Card copy(Card card) {
        return cardFor(card.getClass().getSimpleName());
    }

    public static List<Card> copy(List<Card> cards) {
        List<Card> copy = new ArrayList<>();
        for (Card card : cards) {
            copy.add(copy(card));
        }
        return copy;
    }

    /**
     * Konstruktor für die DeckFactory, der ein Deck mit einer bestimmten Anzahl an Karten für den gegebenen Spieler erstellt.
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
     * @param player Der Spieler, dessen Deck erstellt werden soll.
     * @param amount Die Anzahl der Karten, die im Deck enthalten sein sollen.
     * @param cls    Der Kartentyp, der für das Deck verwendet wird (z.B. Angriff, Verteidigung, etc.).
     */
    public DeckFactory(Player player, int amount, Class<? extends Card> cls) {
        this(player, amount);
        // TODO implement type based generation
    }

    /**
     * Konstruktor für die DeckFactory, der ein Deck mit einer bestimmten Anzahl an Karten und einem spezifischen Kartentyp erstellt.
     *
     * @param player Der Spieler, dessen Deck erstellt werden soll.
     * @param amount Die Anzahl der Karten, die im Deck enthalten sein sollen.
     * @param rarity Die Seltenheit, die für das Deck verwendet wird.
     */
    public DeckFactory(Player player, int amount, CardRarity rarity) {
        this(player, amount);
        // TODO implement rarity based generation
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
    public List<Card> init(boolean duplicatesAllowed) {
        switch (player.getPlayerType()) {
            case IRONCLAD:
                return initIroncladCards(duplicatesAllowed);
            case SILENT:
                return initSilentCards(duplicatesAllowed);
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

        LoggingAssistant.log("DeckFactory.class: Entfernung der Karte: " + selectedCard.getName(), Color.YELLOW);

        player.removeCardFromDeck(selectedCard);
    }

    private List<Card> initCards(String packageName, boolean duplicatesAllowed) {
        List<Class<? extends Card>> availableCards = Model.cards(packageName);
        Set<Integer> indices = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            int randomNumber;
            do {
                randomNumber = rnd.nextInt(availableCards.size());
            } while (!duplicatesAllowed && indices.contains(randomNumber));
            indices.add(randomNumber);
            try {
                genDeck.add(availableCards.get(randomNumber).getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LoggingAssistant.log(e, Color.RED);
            }
            if (!duplicatesAllowed && indices.size() >= availableCards.size()) {
                break;
            }
        }
        return genDeck;
    }

    private List<Card> initIroncladCards(boolean duplicatesAllowed) {
        return initCards("ironclad", duplicatesAllowed);
    }

    private List<Card> initSilentCards(boolean duplicatesAllowed) {
        return initCards("silent", duplicatesAllowed);
    }

}
