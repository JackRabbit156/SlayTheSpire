package de.bundeswehr.auf.slaythespire.model.potion.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;

/**
 * Die Energy potion.
 *
 * @author OF Daniel Willig
 */
public class EnergyPotion extends SkillPotion {

    /**
     * Constructor Energy potion.
     */
    public EnergyPotion() {
        super("Energy Potion", "Gain 2 Energy.", CardRarity.COMMON);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainEnergy(2);
    }

}
