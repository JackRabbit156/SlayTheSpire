package models.card;

import helper.Color;
import helper.ConsoleAssistent;
import models.card.card_structure.Card;
import models.card.card_structure.CardType;
import models.card.ironclad_cards.IroncladDefendCard;
import models.card.ironclad_cards.IroncladStrikeCard;
import models.card.ironclad_cards.IroncladCardEnum;
import models.card.ironclad_cards.attack.common.*;
import models.card.ironclad_cards.attack.uncommon.*;
import models.card.ironclad_cards.attack.rare.*;

import models.card.ironclad_cards.power.rare.*;
import models.card.ironclad_cards.power.uncommon.*;
import models.card.ironclad_cards.skill.common.*;
import models.card.ironclad_cards.skill.rare.*;
import models.card.ironclad_cards.skill.uncommon.*;
import models.card.silent_cards.SilentCardEnum;
import models.card.silent_cards.SilentDefendCard;
import models.card.silent_cards.SilentStrikeCard;
import models.card.silent_cards.attack.common.NeutralizeCard;
import models.card.silent_cards.skill.common.SurvivorCard;
import models.player.player_structure.Player;
import models.potion.*;
import models.potion.potion_structure.PotionCard;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<Card> genDeck;
    private Player player;
    private int amount;
    private CardType cardType = CardType.CURSE; //Platzhalter, damit irgendwas gesetzt ist, liegt an der AttackPotion!
    private Random randi = new Random();

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
     * @param player Der Spieler, dessen Deck erstellt werden soll.
     * @param amount Die Anzahl der Karten, die im Deck enthalten sein sollen.
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
     * @returns {PotionCard} Eine Trankkarte, die dem zufällig ausgewählten Trank entspricht.
     *                       Falls ein Fehler auftritt, kann die Methode in einem fehlerhaften Zustand enden.
     */
    public PotionCard generatePotion() {
        List availablePotions = Arrays.asList(PotionEnum.values());

        if (availablePotions == null) {
            ConsoleAssistent.print(Color.YELLOW, "DeckFactory.class: Potion Initialisierung hat nicht korrekt funktioniert.");
        }
        int randomNumber = randi.nextInt(availablePotions.size());
        String potion = availablePotions.get(randomNumber).toString();
        return assignPotion(potion);
    }

    public static PotionCard assignPotion(String potion) {
        switch (potion) {
            case "BLOCKPOTION": return new BlockPotion();
            case "BLOODPOTION": return new BloodPotion();
            case "DISTILLEDCHAOSPOTION": return new DistilledChaosPotion();
            case "ENERGYPOTION": return new EnergyPotion();
            case "EXPLOSIVEPOTION": return new ExplosivePotion();
            case "FIREPOTION": return new FirePotion();
            default: return new SwiftPotion();
        }
    }

    /**
     * Initialisiert das Kartendeck des Spielers basierend auf dem Spielertyp.
     * Je nach Spielertyp (WATCHER, DEFECT, SILENT, IRONCLAD) wird eine spezifische Methode zur Initialisierung der Karten aufgerufen.
     * Bei einem unbekannten Spielertyp wird die Methode rekursiv erneut aufgerufen.
     * Falls die Initialisierung fehlschlägt, wird eine Fehlermeldung ausgegeben.
     *
     * @returns {List<Card>} Eine Liste von Karten, die das Initialisierungsdeck des Spielers darstellt.
     *                       Im Falle eines Fehlers wird null zurückgegeben.
     */
    public List<Card> init() {
        switch (player.getPlayerType()) {
            case WATCHER:
            case DEFECT:
            case SILENT:
                return initSilentCards();
            case IRONCLAD:
                return initIroncladCards();
            default:
                init();
                break;
        }
        ConsoleAssistent.print(Color.RED, "DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert.");
        return null;
    }

    private List<Card> initSilentCards() {
        List availableCards = Arrays.asList(SilentCardEnum.values());

        if (availableCards == null) {
            ConsoleAssistent.print(Color.RED, "DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert.");
        }

        else {
            for (int i = 0; i < this.amount; i++) {
                int randomNumber = randi.nextInt(availableCards.size());

                String cardName = availableCards.get(randomNumber).toString();

                genDeck.add(assignCard(cardName));
            }
        }
        return genDeck;
    }

    /**
     * Entfernt eine zufällige Karte aus dem Deck des Spielers.
     *
     * @param player Der Spieler, aus dessen Deck eine zufällige Karte entfernt wird.
     */
    public void removeRandomCard(Player player) {
        List<Card> deck = player.getDeck();
        int randomNumber = randi.nextInt(deck.size());
        Card selectedCard = deck.get(randomNumber);

        ConsoleAssistent.print(Color.RED, "DeckFactory.class: Entfernung der Karte: " + selectedCard.getName());

        player.removeCardFromDeck(selectedCard);
    }

    private List<Card> initIroncladCards() {
        List availableCards = Arrays.asList(IroncladCardEnum.values());

        if (availableCards == null) {
            ConsoleAssistent.print(Color.RED, "DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert.");
        }

        else {
            for (int i = 0; i < this.amount; i++) {
                int randomNumber = randi.nextInt(availableCards.size());

                String cardName = availableCards.get(randomNumber).toString();

                genDeck.add(assignCard(cardName));
            }
        }
        return genDeck;
    }

    /**
     * Die Methode assignCard wird verwendet, um ein spezifisches Kartenobjekt basierend auf dem übergebenen Kartenname zu erstellen und zurückzugeben.
     * Sie überprüft den übergebenen Kartenname und instanziiert die entsprechende Kartenklasse.
     *
     * @param {String} cardName - Der Name der Karte, die zugewiesen werden soll. Der Name entspricht verschiedenen Kartentypen
     *                             wie "IroncladStrikeCard", "AngerCard", "BerserkCard" usw.
     *
     * @returns {Card} Das spezifische Card-Objekt, das dem übergebenen Kartenname entspricht.
     *                 Wird kein passender Kartenname gefunden, wird null zurückgegeben.
     *
     * @throws {IllegalArgumentException} Wenn ein unbekannter Kartenname übergeben wird, wird eine Fehlermeldung in der Konsole ausgegeben.
     *                                    In diesem Fall wird null zurückgegeben.
     *
     * @example
     * // Beispiel für die Zuweisung einer Karte
     * Card card = assignCard("IroncladStrikeCard");
     * // card ist dann eine Instanz von IroncladStrikeCard
     */
    public static Card assignCard(String cardName){
        Card cardToTransform = null;

        switch (cardName){
            // Ironclad
            // AttackCards
            // GeneralCards
            case "IroncladStrikeCard": cardToTransform = new IroncladStrikeCard(); break;
            case "IroncladDefendCard": cardToTransform = new IroncladDefendCard(); break;
            // CommonCards
            case "AngerCard": cardToTransform = new AngerCard(); break;
            case "BashCard": cardToTransform = new BashCard(); break;
            case "BodySlamCard": cardToTransform = new BodySlamCard(); break;
            case "ClashCard": cardToTransform = new ClashCard(); break;
            case "CleaveCard": cardToTransform = new CleaveCard(); break;
            case "ClotheslineCard": cardToTransform = new ClotheslineCard(); break;
            case "HeadbuttCard": cardToTransform = new HeadbuttCard(); break;
            case "HeavyBladeCard": cardToTransform = new HeavyBladeCard(); break;
            case "IronWaveCard": cardToTransform = new IronWaveCard(); break;
            case "PommelStrikeCard": cardToTransform = new PommelStrikeCard(); break;
            case "SwordBoomerangCard": cardToTransform = new SwordBoomerangCard(); break;
            case "ThunderclapCard": cardToTransform = new ThunderclapCard(); break;
            case "TwinStrikeCard": cardToTransform = new TwinStrikeCard(); break;
            case "WildStrikeCard": cardToTransform = new WildStrikeCard(); break;
            // RareCard
            case "BludgeonCard": cardToTransform = new BludgeonCard(); break;
            case "FeedCard": cardToTransform = new FeedCard(); break;
            case "ImmolateCard": cardToTransform = new ImmolateCard(); break;
            case "ReaperCard": cardToTransform = new ReaperCard(); break;
            // UncommonCard
            case "CarnageCard": cardToTransform = new CarnageCard(); break;
            case "DropkickCard": cardToTransform = new DropkickCard(); break;
            case "HemokinesisCard": cardToTransform = new HemokinesisCard(); break;
            case "PummelCard": cardToTransform = new PummelCard(); break;
            case "RecklessChargeCard": cardToTransform = new RecklessChargeCard(); break;
            case "SeveralSoulCard": cardToTransform = new SeverSoulCard(); break;
            case "UpperCutCard": cardToTransform = new UppercutCard(); break;
            case "WhirlwindCard": cardToTransform = new WhirlwindCard(); break;

            // Power
            // RareCard
            case "BerserkCard": cardToTransform = new BerserkCard(); break;
            case "JuggernautCard": cardToTransform = new JuggernautCard(); break;
            // UncommonCard
            case "MetallicizeCard": cardToTransform = new MetallicizeCard(); break;

            // Skill
            // CommonCards
            case "ShrugItOffCard": cardToTransform = new ShrugItOffCard(); break;
            case "WarcryCard": cardToTransform = new WarcryCard(); break;
            // RareCard
            case "OfferingCard": cardToTransform = new OfferingCard(); break;
            // UncommonCard
            case "EntrenchCard": cardToTransform = new EntrenchCard(); break;
            case "GhostlyArmorCard": cardToTransform = new GhostlyArmorCard(); break;


            // Silent
            // AttackCards
            // GeneralCards
            case "SilentStrikeCard": cardToTransform = new SilentStrikeCard(); break;
            case "SilentDefendCard": cardToTransform = new SilentDefendCard(); break;
            // CommonCards
            case "NeutralizeCard": cardToTransform = new NeutralizeCard(); break;

            // Skill
            // CommonCards
            case "SurvivorCard": cardToTransform = new SurvivorCard(); break;


            default: {
                System.out.println(cardName);
                System.out.println("ERROR IN DECKFACTORY");
                break;
            }
        }

        return cardToTransform;
    }
}
