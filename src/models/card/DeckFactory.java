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
import models.player.player_structure.Player;
import models.potion.*;
import models.potion.potion_structure.PotionCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class DeckFactory {
    private List<Card> genDeck;
    private Player player;
    private int amount;
    private CardType cardType = CardType.CURSE; //Platzhalter, damit irgendwas gesetzt ist, liegt an der AttackPotion!
    private Random randi = new Random();

    public DeckFactory(Player player, int amount) {
        this.player = player;
        this.amount = amount;
        this.genDeck = new ArrayList<>();
    }

    public DeckFactory(Player player, int amount, CardType cardType) {
        this.player = player;
        this.amount = amount;
        this.cardType = cardType;
        this.genDeck = new ArrayList<>();
    }

    /**
     * Gibt einen Zufälligen Potion aus.
     * @return PotionCard
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

    private PotionCard assignPotion(String potion) {
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

    public List<Card> init() {
        switch (player.getPlayerType()) {
            case WATCHER:
            case DEFECT:
            case SILENT:
                //TODO Weitere Charactere
//                break;
            case IRONCLAD:
                return initIroncladCards();
            default:
                init();
                break;
        }
        ConsoleAssistent.print(Color.RED, "DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert.");
        return null;
    }

    private List<Card> initIroncladCards() {
        List availableCards = Arrays.asList(IroncladCardEnum.values());

        if (availableCards == null) {
            ConsoleAssistent.print(Color.RED, "DeckFactory.class: Karten Initialisierung hat nicht korrekt funktioniert.");
        }


        if (cardType.equals(CardType.ATTACK)) {
            for (int i = 0; i < this.amount; i++) {
                int randomNumber = randi.nextInt(availableCards.size());

                String cardName = availableCards.get(randomNumber).toString();

                if (assignCard(cardName).getCardType().equals(cardType)) {
                    genDeck.add(assignCard(cardName));
                }
                else {
                    i--;
                }

            }
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

    public static Card assignCard(String cardName){
        Card cardToTransform = null;

        switch (cardName){
            // AttackCards
            // GeneralCards
            case "IroncladStrikeCard": cardToTransform = new IroncladStrikeCard(); break;
            case "IroncladDefendCard": cardToTransform = new IroncladDefendCard(); break;
            // CommonCards
            case "AngerCard": cardToTransform = new AngerCard(); break;
            case "BashCard": cardToTransform = new BashCard(); break;
            case "BodyslamCard": cardToTransform = new BodySlamCard(); break;
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
            case "SeveralSoulCard": cardToTransform = new SeverSoulCard(); break;
            case "UpperCutCard": cardToTransform = new UppercutCard(); break;
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
            case "ShrugltOffCard": cardToTransform = new ShrugItOffCard(); break;
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
}
