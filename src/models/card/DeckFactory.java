package models.card;

import helper.Color;
import helper.ConsoleAssistent;
import models.card.card_structure.Card;
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
    private Random randi = new Random();

    public DeckFactory(Player player, int amount) {
        this.player = player;
        this.amount = amount;
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
            case "ATTACKPOTION": return new AttackPotion();
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
        for (int i = 0; i < this.amount; i++) {
            int randomNumber = randi.nextInt(availableCards.size());

            String cardName = availableCards.get(randomNumber).toString();

            genDeck.add(assignCard(cardName));
        }
        return genDeck;
    }

    public static Card assignCard(String cardName){
        Card cardToTransform = null;

        switch (cardName){
            // AttackCards
            // GeneralCards
            case "STRIKE_CARD": cardToTransform = new IroncladStrikeCard(); break;
            case "DEFEND_CARD": cardToTransform = new IroncladDefendCard(); break;
            // CommonCards
            case "ANGER_CARD": cardToTransform = new AngerCard(); break;
            case "BASH_CARD": cardToTransform = new BashCard(); break;
            case "BODY_SLAM_CARD": cardToTransform = new BodySlamCard(); break;
            case "CLASH_CARD": cardToTransform = new ClashCard(); break;
            case "CLEAVE_CARD": cardToTransform = new CleaveCard(); break;
            case "CLOTHESLINE_CARD": cardToTransform = new ClotheslineCard(); break;
            case "HEADBUTT_CARD": cardToTransform = new HeadbuttCard(); break;
            case "HEAVY_BLADE_CARD": cardToTransform = new HeavyBladeCard(); break;
            case "IRON_WAVE_CARD": cardToTransform = new IronWaveCard(); break;
            case "PERFECTED_STRIKE_CARD": cardToTransform = new PerfectedStrikeCard(); break;
            case "POMMEL_STRIKE_CARD": cardToTransform = new PommelStrikeCard(); break;
            case "SWORD_BOOMERANG_CARD": cardToTransform = new SwordBoomerangCard(); break;
            case "THUNDERCLAP_CARD": cardToTransform = new ThunderclapCard(); break;
            case "TWIN_STRIKE_CARD": cardToTransform = new TwinStrikeCard(); break;
            case "WILD_STRIKE_CARD": cardToTransform = new WildStrikeCard(); break;
            // RareCard
            case "BLUDGEON_CARD": cardToTransform = new BludgeonCard(); break;
            case "FEED_CARD": cardToTransform = new FeedCard(); break;
            case "FIEND_FIRE_CARD": cardToTransform = new FiendFireCard(); break;
            case "IMMOLATE_CARD": cardToTransform = new ImmolateCard(); break;
            case "REAPER_CARD": cardToTransform = new ReaperCard(); break;
            // UncommonCard
            case "BLOOD_FOR_BLOOD_CARD": cardToTransform = new BloodForBloodCard(); break;
            case "CARNAGE_CARD": cardToTransform = new CarnageCard(); break;
            case "DROPKICK_CARD": cardToTransform = new DropkickCard(); break;
            case "HEMOKINESIS_CARD": cardToTransform = new HemokinesisCard(); break;
            case "PUMMEL_CARD": cardToTransform = new PummelCard(); break;
            case "RAMPAGE_CARD": cardToTransform = new RampageCard(); break;
            case "RECKLESS_CHARGE_CARD": cardToTransform = new RecklessChargeCard(); break;
            case "SEARING_BLOW_CARD": cardToTransform = new SearingBlowCard(); break;
            case "SEVER_SOUL_CARD": cardToTransform = new SeverSoulCard(); break;
            case "UPPERCUT_CARD": cardToTransform = new UppercutCard(); break;
            case "WHIRLWIND_CARD": cardToTransform = new WhirlwindCard(); break;

            // Skill
            // RareCard
            case "BERSERK_CARD": cardToTransform = new BerserkCard(); break;
            case "JUGGERNAUT_CARD": cardToTransform = new JuggernautCard(); break;
            // UncommonCard
            case "INFLAME_CARD": cardToTransform = new InflameCard(); break;
            case "METALLICIZE_CARD": cardToTransform = new MetallicizeCard(); break;
            case "RUPTURE_CARD": cardToTransform = new RuptureCard(); break;

            // Power
            // CommonCards
            case "FLEX_CARD": cardToTransform = new FlexCard(); break;
            case "SHRUG_IT_OFF_CARD": cardToTransform = new ShrugItOffCard(); break;
            case "WARCRY_CARD": cardToTransform = new WarcryCard(); break;
            // RareCard
            case "OFFERING_CARD": cardToTransform = new OfferingCard(); break;
            // UncommonCard
            case "ENTRENCH_CARD": cardToTransform = new EntrenchCard(); break;
            case "GHOSTLY_ARMOR_CARD": cardToTransform = new GhostlyArmorCard(); break;
            case "RAGE_CARD": cardToTransform = new RageCard(); break;
            case "SPOT_WEAKNESS_CARD": cardToTransform = new SpotWeaknessCard(); break;
            default:
                System.out.println("ERROR IN DECKFACTORY"); break;
        }

        return cardToTransform;
    }
}
