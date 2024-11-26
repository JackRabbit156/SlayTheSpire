package models.potion;

import models.battle.GameContext;
import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardType;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

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
