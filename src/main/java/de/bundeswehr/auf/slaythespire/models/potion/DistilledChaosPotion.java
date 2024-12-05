package de.bundeswehr.auf.slaythespire.models.potion;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardType;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;

import java.util.List;

/**
 * Die Distilled chaos potion.
 *
 * @author OF Daniel Willig
 */
public class DistilledChaosPotion extends PotionCard {

    /**
     * Constructor Distilled chaos potion.
     */
    public DistilledChaosPotion() {
        super("Distilled Chaos", "Play the top 3 cards of your draw pile.",  CardRarity.UNCOMMON, CardType.SKILL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Card> deck = gameContext.getBattleDeck().getDeck();

        for (int i = 1; i <= 3; i++) {
            deck.get(deck.size() - i).play(gameContext);
            gameContext.getPlayer().increaseCurrentEnergy(deck.get(deck.size() - i).getCost());
        }

    }
}
