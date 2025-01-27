package de.bundeswehr.auf.slaythespire.model.relic.ironclad;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.AdditionallyTriggered;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class SelfFormingClayRelic extends PlayerTypeRelic implements AdditionallyTriggered, Resetable {

    private boolean trigger;

    public SelfFormingClayRelic() {
        super("Self-Forming Clay", "Whenever you lose HP in combat, gain 3 Block next turn.",
                RelicRarity.UNCOMMON, PlayerType.IRONCLAD, RelicTrigger.LOSE_HP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        trigger = true;
    }

    @Override
    public RelicTrigger getAdditionalTrigger() {
        return RelicTrigger.BEGIN_OF_TURN;
    }

    @Override
    public RelicTrigger getResetTrigger() {
        return RelicTrigger.END_OF_COMBAT;
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        if (trigger) {
            Player player = gameContext.getPlayer();
            player.gainBlock(3);
            trigger = false;
        }
    }

    @Override
    public void reset() {
        trigger = false;
    }

}