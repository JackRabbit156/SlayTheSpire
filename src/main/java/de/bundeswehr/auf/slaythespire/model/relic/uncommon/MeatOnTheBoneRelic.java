package de.bundeswehr.auf.slaythespire.model.relic.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class MeatOnTheBoneRelic extends Relic {

    public MeatOnTheBoneRelic() {
        super("Meat on the Bone", "If your HP is at or below 50% at the end of combat, heal 12 HP.",
                RelicRarity.UNCOMMON, RelicTrigger.END_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        if ((double) player.getCurrentHealth() / player.getMaxHealth() <= 0.5) {
            player.heal(12);
        }
    }

}