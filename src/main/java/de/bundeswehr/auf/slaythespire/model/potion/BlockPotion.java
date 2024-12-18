package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;

/**
 * Die Block potion.
 *
 * @author OF Daniel Willig
 */
public class BlockPotion extends Potion {

    /**
     * Constructor Block potion.
     */
    public BlockPotion() {
        super("Block Potion", "Gain 12 Block.", CardRarity.COMMON, CardType.SKILL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseBlock(12);
    }
}
