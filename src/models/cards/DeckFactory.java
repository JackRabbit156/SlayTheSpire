package models.cards;

import helper.Color;
import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.cards.general_cards.DefendCard;
import models.cards.general_cards.StrikeCard;
import models.cards.ironclad_cards.attack.common.*;
import models.cards.ironclad_cards.attack.uncommon.*;
import models.cards.ironclad_cards.attack.rare.*;

import models.cards.ironclad_cards.power.rare.*;
import models.cards.ironclad_cards.power.uncommon.*;
import models.cards.ironclad_cards.skill.common.*;
import models.cards.ironclad_cards.skill.rare.*;
import models.cards.ironclad_cards.skill.uncommon.*;
import models.player.player_structure.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class DeckFactory {
    private Path sourceFilePath = Paths.get("src/resources/PlayerCards/");
    private Path characterFilePath;
    private List<Card> genDeck;
    private Player player;
    private int amount;
    private Random rand = new Random();
    /*
        Was soll er erfüllen
        Welcher Spieler?
        Wie viel Karten?
        Rückgabe von X Karten als Liste
     */

    public DeckFactory(Player player, int amount) {
        this.player = player;
        this.amount = amount;
        this.genDeck = new ArrayList<>();
    }

    public List<Card> init() {
        switch (player.getPlayerType()) {
            case WATCHER:
            case DEFECT:
            case SILENT:
                //TODO Weitere Charactere
//                characterFilePath = Paths.get("Silent/CardList");
//                break;
            case IRONCLAD:
                characterFilePath = Paths.get("Ironclad/CardList");
                return initIroncladCards();
            default:
                init();
                break;
        }
        ConsoleAssistent.print(Color.RED, "DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert.");
        return null;
    }

    private List<Card> initIroncladCards() {
        List<String> availableCards = readCardFile();

        for (int i = 0; i < this.amount; i++) {
            int randomNumber = rand.nextInt(availableCards.size());

            String cardName = availableCards.get(randomNumber);
            genDeck.add(assignCard(cardName));
        }
        return genDeck;
    }

    private Card assignCard(String cardName){
        Card cardToTransform = null;

        switch (cardName){
            // AttackCards
            // GeneralCards
            case "StrikeCard": cardToTransform = new StrikeCard(); break;
            case "DefendCard": cardToTransform = new DefendCard(); break;
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
            case "PerfectedStrikeCard": cardToTransform = new PerfectedStrikeCard(); break;
            case "PommelStrikeCard": cardToTransform = new PommelStrikeCard(); break;
            case "SwordBoomerangCard": cardToTransform = new SwordBoomerangCard(); break;
            case "ThunderclapCard": cardToTransform = new ThunderclapCard(); break;
            case "TwinStrikeCard": cardToTransform = new TwinStrikeCard(); break;
            case "WildStrikeCard": cardToTransform = new WildStrikeCard(); break;
            // RareCard
            case "BludgeonCard": cardToTransform = new BludgeonCard(); break;
            case "FeedCard": cardToTransform = new FeedCard(); break;
            case "FiendFireCard": cardToTransform = new FiendFireCard(); break;
            case "ImmolateCard": cardToTransform = new ImmolateCard(); break;
            case "ReaperCard": cardToTransform = new ReaperCard(); break;
            // UncommonCard
            case "BloodForBloodCard": cardToTransform = new BloodForBloodCard(); break;
            case "CarnageCard": cardToTransform = new CarnageCard(); break;
            case "DropkickCard": cardToTransform = new DropkickCard(); break;
            case "HemokinesisCard": cardToTransform = new HemokinesisCard(); break;
            case "PummelCard": cardToTransform = new PummelCard(); break;
            case "RampageCard": cardToTransform = new RampageCard(); break;
            case "RecklessChargeCard": cardToTransform = new RecklessChargeCard(); break;
            case "SearingBlowCard": cardToTransform = new SearingBlowCard(); break;
            case "SeverSoulCard": cardToTransform = new SeverSoulCard(); break;
            case "UppercutCard": cardToTransform = new UppercutCard(); break;
            case "WhirlwindCard": cardToTransform = new WhirlwindCard(); break;

            // Skill
            // RareCard
            case "BerserkCard": cardToTransform = new BerserkCard(); break;
            case "JuggernautCard": cardToTransform = new JuggernautCard(); break;
            // UncommonCard
            case "InflameCard": cardToTransform = new InflameCard(); break;
            case "MetallicizeCard": cardToTransform = new MetallicizeCard(); break;
            case "RuptureCard": cardToTransform = new RuptureCard(); break;

            // Power
            // CommonCards
            case "FlexCard": cardToTransform = new FlexCard(); break;
            case "ShrugItOffCard": cardToTransform = new ShrugItOffCard(); break;
            case "WarcryCard": cardToTransform = new WarcryCard(); break;
            // RareCard
            case "OfferingCard": cardToTransform = new OfferingCard(); break;
            // UncommonCard
            case "EntrenchCard": cardToTransform = new EntrenchCard(); break;
            case "GhostlyArmorCard": cardToTransform = new GhostlyArmorCard(); break;
            case "RageCard": cardToTransform = new RageCard(); break;
            case "SpotWeaknessCard": cardToTransform = new SpotWeaknessCard(); break;
            default:
                System.out.println("ERROR IN DECKFACTORY"); break;
        }

        return cardToTransform;
    }

    private List<String> readCardFile() {
        Path cardListPath = sourceFilePath.resolve(characterFilePath);

        if (!(new File(String.valueOf(cardListPath)).exists())) {
            ConsoleAssistent.print(Color.RED, "Datei existiert nicht: " + cardListPath.isAbsolute());
            return Collections.emptyList();
        }

        try {
            return Files.readAllLines(cardListPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
