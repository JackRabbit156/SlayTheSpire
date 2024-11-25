package models.potion;

import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.card.card_structure.CardType;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

/**
 * Die Energy potion.
 *
 * @author OF Daniel Willig
 */
public class EnergyPotion extends PotionCard {

    /**
     * Constructor Energy potion.
     */
    public EnergyPotion() {
        super("Energy Potion", "Gain 2 Energy.",  CardRarity.COMMON,  CardType.SKILL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentEnergy(2);
    }
}
