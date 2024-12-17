package de.bundeswehr.auf.slaythespire.model.card.ironclad;

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
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

/**
 * @author Keil, Vladislav
 */
public enum IroncladCards {
    
    STRIKE_CARD(IroncladStrikeCard.class.getSimpleName()) {
        
        @Override
        @SuppressWarnings("unchecked")
        public IroncladStrikeCard create() {
            return new IroncladStrikeCard();
        }
        
    },
    DEFEND_CARD(IroncladDefendCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public IroncladDefendCard create() {
            return new IroncladDefendCard();
        }
    },
    ANGER_CARD(AngerCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public AngerCard create() {
            return new AngerCard();
        }
    },
    BASH_CARD(BashCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public BashCard create() {
            return new BashCard();
        }
    },
    BODY_SLAM_CARD(BodySlamCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public BodySlamCard create() {
            return new BodySlamCard();
        }
    },
    CLASH_CARD(ClashCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public ClashCard create() {
            return new ClashCard();
        }
    },
    CLEAVE_CARD(CleaveCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public CleaveCard create() {
            return new CleaveCard();
        }
    },
    CLOTHESLINE_CARD(ClotheslineCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public ClotheslineCard create() {
            return new ClotheslineCard();
        }
    },
    HEADBUTT_CARD(HeadbuttCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public HeadbuttCard create() {
            return new HeadbuttCard();
        }
    },
    HEAVY_BLADE_CARD(HeavyBladeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public HeavyBladeCard create() {
            return new HeavyBladeCard();
        }
    },
    IRON_WAVE_CARD(IronWaveCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public IronWaveCard create() {
            return new IronWaveCard();
        }
    },
    POMMEL_STRIKE_CARD(PommelStrikeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public PommelStrikeCard create() {
            return new PommelStrikeCard();
        }
    },
    SWORD_BOOMERANG_CARD(SwordBoomerangCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public SwordBoomerangCard create() {
            return new SwordBoomerangCard();
        }
    },
    THUNDERCLAP_CARD(ThunderclapCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public ThunderclapCard create() {
            return new ThunderclapCard();
        }
    },
    TWIN_STRIKE_CARD(TwinStrikeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public TwinStrikeCard create() {
            return new TwinStrikeCard();
        }
    },
    WILD_STRIKE_CARD(WildStrikeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public WildStrikeCard create() {
            return new WildStrikeCard();
        }
    },
    BLUDGEON_CARD(BludgeonCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public BludgeonCard create() {
            return new BludgeonCard();
        }
    },
    FEED_CARD(FeedCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public FeedCard create() {
            return new FeedCard();
        }
    },
    IMMOLATE_CARD(ImmolateCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public ImmolateCard create() {
            return new ImmolateCard();
        }
    },
    REAPER_CARD(ReaperCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public ReaperCard create() {
            return new ReaperCard();
        }
    },
    CARNAGE_CARD(CarnageCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public CarnageCard create() {
            return new CarnageCard();
        }
    },
    DROPKICK_CARD(DropkickCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public DropkickCard create() {
            return new DropkickCard();
        }
    },
    HEMOKINESIS_CARD(HemokinesisCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public HemokinesisCard create() {
            return new HemokinesisCard();
        }
    },
    PUMMEL_CARD(PummelCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public PummelCard create() {
            return new PummelCard();
        }
    },
    RECKLESS_CHARGE_CARD(RecklessChargeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public RecklessChargeCard create() {
            return new RecklessChargeCard();
        }
    },
    SEVER_SOUL_CARD(SeverSoulCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public SeverSoulCard create() {
            return new SeverSoulCard();
        }
    },
    UPPERCUT_CARD(UppercutCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public UppercutCard create() {
            return new UppercutCard();
        }
    },
    WHIRLWIND_CARD(WhirlwindCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public WhirlwindCard create() {
            return new WhirlwindCard();
        }
    },
    BERSERK_CARD(BerserkCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public BerserkCard create() {
            return new BerserkCard();
        }
    },
    JUGGERNAUT_CARD(JuggernautCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public JuggernautCard create() {
            return new JuggernautCard();
        }
    },
    METALLICIZE_CARD(MetallicizeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public MetallicizeCard create() {
            return new MetallicizeCard();
        }
    },
    SHRUG_IT_OFF_CARD(ShrugItOffCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public ShrugItOffCard create() {
            return new ShrugItOffCard();
        }
    },
    WARCRY_CARD(WarcryCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public WarcryCard create() {
            return new WarcryCard();
        }
    },
    OFFERING_CARD(OfferingCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public OfferingCard create() {
            return new OfferingCard();
        }
    },
    ENTRENCH_CARD(EntrenchCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public EntrenchCard create() {
            return new EntrenchCard();
        }
    },
    GHOSTLY_ARMOR_CARD(GhostlyArmorCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public GhostlyArmorCard create() {
            return new GhostlyArmorCard();
        }
    };

    private final String name;

    IroncladCards(String name) {
        this.name = name;
    }
    
    public abstract <C extends Card> C create();

    @Override
    public String toString() {
        return name;
    }
    
}
