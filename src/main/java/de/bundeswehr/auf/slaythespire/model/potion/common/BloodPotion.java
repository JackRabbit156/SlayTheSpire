package de.bundeswehr.auf.slaythespire.model.potion.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;

/**
 * Die Blood potion.
 *
 * @author OF Daniel Willig
 */
public class BloodPotion extends SkillPotion {

    /**
     * Constructor Blood potion.
     */
    public BloodPotion() {
        super("Blood Potion", "Heal for 20% of your Max HP.", CardRarity.COMMON);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.heal((int) (player.getMaxHealth() * 0.2));
    }

}
