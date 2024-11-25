package models.card.ironclad_cards;

/**
 * @author Keil, Vladislav
 */
public enum IroncladCardEnum {
    STRIKE_CARD("IroncladStrikeCard"),
    DEFEND_CARD("IroncladDefendCard"),
    ANGER_CARD("AngerCard"),
    BASH_CARD("BashCard"),
    BODY_SLAM_CARD("BodySlamCard"),
    CLASH_CARD("ClashCard"),
    CLEAVE_CARD("CleaveCard"),
    CLOTHESLINE_CARD("ClotheslineCard"),
    HEADBUTT_CARD("HeadbuttCard"),
    HEAVY_BLADE_CARD("HeavyBladeCard"),
    IRON_WAVE_CARD("IronWaveCard"),
    PERFECTED_STRIKE_CARD("PerfectedStrikeCard"),
    POMMEL_STRIKE_CARD("PommelStrikeCard"),
    SWORD_BOOMERANG_CARD("SwordBoomerangCard"),
    THUNDERCLAP_CARD("ThunderclapCard"),
    TWIN_STRIKE_CARD("TwinStrikeCard"),
    WILD_STRIKE_CARD("WildStrikeCard"),
    BLUDGEON_CARD("BludgeonCard"),
    FEED_CARD("FeedCard"),
    FIEND_FIRE_CARD("FiendFireCard"),
    IMMOLATE_CARD("ImmolateCard"),
    REAPER_CARD("ReaperCard"),
    BLOOD_FOR_BLOOD_CARD("BloodForBloodCard"),
    CARNAGE_CARD("CarnageCard"),
    DROPKICK_CARD("DropkickCard"),
    HEMOKINESIS_CARD("HemokinesisCard"),
    PUMMEL_CARD("PummelCard"),
    RAMPAGE_CARD("RampageCard"),
    RECKLESS_CHARGE_CARD("RecklessChargeCard"),
    SEARING_BLOW_CARD("SearingBlowCard"),
    SEVER_SOUL_CARD("SeveralSoulCard"),
    UPPERCUT_CARD("UpperCutCard"),
    WHIRLWIND_CARD("WhirlwindCard"),
    BERSERK_CARD("BerserkCard"),
    JUGGERNAUT_CARD("JuggernautCard"),
    INFLAME_CARD("InflameCard"),
    METALLICIZE_CARD("MetallicizeCard"),
    RUPTURE_CARD("RuptureCard"),
    FLEX_CARD("FlexCard"),
    SHRUG_IT_OFF_CARD("ShrugItOffCard"),
    WARCRY_CARD("WarcryCard"),
    OFFERING_CARD("OfferingCard"),
    ENTRENCH_CARD("EntrenchCard"),
    GHOSTLY_ARMOR_CARD("GhostlyArmorCard"),
    RAGE_CARD("RageCard"),
    SPOT_WEAKNESS_CARD("SpotWeaknessCard");

    private final String name;

    IroncladCardEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
