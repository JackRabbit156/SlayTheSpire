package de.bundeswehr.auf.slaythespire.models.potion;

import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardType;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;

/**
 * Die Block potion.
 *
 * @author OF Daniel Willig
 */
public class BlockPotion extends PotionCard {

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
