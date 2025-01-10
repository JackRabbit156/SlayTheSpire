package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Entrench Karte.
 *
 * @author OF Daniel Willig
 */
public class EntrenchCard extends SkillCard {

    /**
     * Constructor Entrench card.
     */
    public EntrenchCard() {
        super("Entrench", "Double your Block.", 2, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainBlock(player.getBlock());

        player.decreaseCurrentEnergy(getCost());
    }

}
