package de.bundeswehr.auf.slaythespire.model.card;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.ConsoleAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladCardEnum;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.*;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare.BludgeonCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare.FeedCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare.ImmolateCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare.ReaperCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon.*;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.power.rare.BerserkCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.power.rare.JuggernautCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.power.uncommon.MetallicizeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common.ShrugItOffCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common.WarcryCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.rare.OfferingCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon.EntrenchCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon.GhostlyArmorCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.SilentCardEnum;
import de.bundeswehr.auf.slaythespire.model.card.silent.SilentDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.SilentStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.attack.common.NeutralizeCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.skill.common.SurvivorCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.*;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;

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
    public static Card assignCard(String cardName) {
        Card cardToTransform = null;

        switch (cardName) {
            // Ironclad
            // AttackCards
            // GeneralCards
            case "IroncladStrikeCard":
                cardToTransform = new IroncladStrikeCard();
                break;
            case "IroncladDefendCard":
                cardToTransform = new IroncladDefendCard();
                break;
            // CommonCards
            case "AngerCard":
                cardToTransform = new AngerCard();
                break;
            case "BashCard":
                cardToTransform = new BashCard();
                break;
            case "BodySlamCard":
                cardToTransform = new BodySlamCard();
                break;
            case "ClashCard":
                cardToTransform = new ClashCard();
                break;
            case "CleaveCard":
                cardToTransform = new CleaveCard();
                break;
            case "ClotheslineCard":
                cardToTransform = new ClotheslineCard();
                break;
            case "HeadbuttCard":
                cardToTransform = new HeadbuttCard();
                break;
            case "HeavyBladeCard":
                cardToTransform = new HeavyBladeCard();
                break;
            case "IronWaveCard":
                cardToTransform = new IronWaveCard();
                break;
            case "PommelStrikeCard":
                cardToTransform = new PommelStrikeCard();
                break;
            case "SwordBoomerangCard":
                cardToTransform = new SwordBoomerangCard();
                break;
            case "ThunderclapCard":
                cardToTransform = new ThunderclapCard();
                break;
            case "TwinStrikeCard":
                cardToTransform = new TwinStrikeCard();
                break;
            case "WildStrikeCard":
                cardToTransform = new WildStrikeCard();
                break;
            // RareCard
            case "BludgeonCard":
                cardToTransform = new BludgeonCard();
                break;
            case "FeedCard":
                cardToTransform = new FeedCard();
                break;
            case "ImmolateCard":
                cardToTransform = new ImmolateCard();
                break;
            case "ReaperCard":
                cardToTransform = new ReaperCard();
                break;
            // UncommonCard
            case "CarnageCard":
                cardToTransform = new CarnageCard();
                break;
            case "DropkickCard":
                cardToTransform = new DropkickCard();
                break;
            case "HemokinesisCard":
                cardToTransform = new HemokinesisCard();
                break;
            case "PummelCard":
                cardToTransform = new PummelCard();
                break;
            case "RecklessChargeCard":
                cardToTransform = new RecklessChargeCard();
                break;
            case "SeveralSoulCard":
                cardToTransform = new SeverSoulCard();
                break;
            case "UpperCutCard":
                cardToTransform = new UppercutCard();
                break;
            case "WhirlwindCard":
                cardToTransform = new WhirlwindCard();
                break;

            // Power
            // RareCard
            case "BerserkCard":
                cardToTransform = new BerserkCard();
                break;
            case "JuggernautCard":
                cardToTransform = new JuggernautCard();
                break;
            // UncommonCard
            case "MetallicizeCard":
                cardToTransform = new MetallicizeCard();
                break;

            // Skill
            // CommonCards
            case "ShrugItOffCard":
                cardToTransform = new ShrugItOffCard();
                break;
            case "WarcryCard":
                cardToTransform = new WarcryCard();
                break;
            // RareCard
            case "OfferingCard":
                cardToTransform = new OfferingCard();
                break;
            // UncommonCard
            case "EntrenchCard":
                cardToTransform = new EntrenchCard();
                break;
            case "GhostlyArmorCard":
                cardToTransform = new GhostlyArmorCard();
                break;


            // Silent
            // AttackCards
            // GeneralCards
            case "SilentStrikeCard":
                cardToTransform = new SilentStrikeCard();
                break;
            case "SilentDefendCard":
                cardToTransform = new SilentDefendCard();
                break;
            // CommonCards
            case "NeutralizeCard":
                cardToTransform = new NeutralizeCard();
                break;

            // Skill
            // CommonCards
            case "SurvivorCard":
                cardToTransform = new SurvivorCard();
                break;


            default: {
                System.out.println(cardName);
                System.out.println("ERROR IN DECKFACTORY");
                break;
            }
        }

        return cardToTransform;
    }

    public static PotionCard assignPotion(String potion) {
        switch (potion) {
            case "BLOCKPOTION":
                return new BlockPotion();
            case "BLOODPOTION":
                return new BloodPotion();
            case "DISTILLEDCHAOSPOTION":
                return new DistilledChaosPotion();
            case "ENERGYPOTION":
                return new EnergyPotion();
            case "EXPLOSIVEPOTION":
                return new ExplosivePotion();
            case "FIREPOTION":
                return new FirePotion();
            default:
                return new SwiftPotion();
        }
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
     * @return {PotionCard} Eine Trankkarte, die dem zufällig ausgewählten Trank entspricht.
     * Falls ein Fehler auftritt, kann die Methode in einem fehlerhaften Zustand enden.
     */
    public PotionCard generatePotion() {
        List<PotionEnum> availablePotions = Arrays.asList(PotionEnum.values());
        int randomNumber = rnd.nextInt(availablePotions.size());
        String potion = availablePotions.get(randomNumber).toString();
        return assignPotion(potion);
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
                ConsoleAssistent.print(Color.RED, "DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert.");
                break;
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

        ConsoleAssistent.print(Color.RED, "DeckFactory.class: Entfernung der Karte: " + selectedCard.getName());

        player.removeCardFromDeck(selectedCard);
    }

    private List<Card> initIroncladCards() {
        List<IroncladCardEnum> availableCards = Arrays.asList(IroncladCardEnum.values());
        for (int i = 0; i < this.amount; i++) {
            int randomNumber = rnd.nextInt(availableCards.size());

            String cardName = availableCards.get(randomNumber).toString();

            genDeck.add(assignCard(cardName));
        }
        return genDeck;
    }

    private List<Card> initSilentCards() {
        List<SilentCardEnum> availableCards = Arrays.asList(SilentCardEnum.values());
        for (int i = 0; i < this.amount; i++) {
            int randomNumber = rnd.nextInt(availableCards.size());

            String cardName = availableCards.get(randomNumber).toString();

            genDeck.add(assignCard(cardName));
        }
        return genDeck;
    }

}
