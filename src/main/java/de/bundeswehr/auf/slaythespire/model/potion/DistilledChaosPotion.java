package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;

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
            Card card = deck.get(deck.size() - i);
            Playable playable = card.isPlayable(gameContext);
            if (playable.isPlayable()) {
                // TODO if choosing is necessary
                card.play(gameContext);
                // TODO increase?
                gameContext.getPlayer().increaseCurrentEnergy(card.getCost());
            }
        }

    }
}
