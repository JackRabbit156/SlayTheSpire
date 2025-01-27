package de.bundeswehr.auf.slaythespire.model.relic.ironclad;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.*;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Resetable;

public class RedSkullRelic extends PlayerTypeRelic implements AdditionallyTriggered, Resetable {

    private boolean triggered;

    public RedSkullRelic() {
        super("Red Skull", "While your HP is at or below 50%, you have 3 additional Strength.",
                RelicRarity.COMMON, PlayerType.IRONCLAD, RelicTrigger.LOSE_HP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        if (!triggered && player.getCurrentHealth() <= player.getMaxHealth() / 2.0) {
            player.addEffect(new StrengthBuff(), 3);
        }
    }

    @Override
    public RelicTrigger getAdditionalTrigger() {
        return RelicTrigger.GAIN_HP;
    }

    @Override
    public RelicTrigger getResetTrigger() {
        return RelicTrigger.END_OF_COMBAT;
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        if (triggered && player.getCurrentHealth() > player.getMaxHealth() / 2.0) {
            player.addEffect(new StrengthBuff(), -3);
        }
    }

    @Override
    public void reset() {
        triggered = false;
    }

}