package de.bundeswehr.auf.slaythespire.models.potion;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardType;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;

/**
 * Die Swift potion.
 *
 * @author OF Daniel Willig
 */
public class SwiftPotion extends PotionCard {

    /**
     * Constructor Swift potion.
     */
    public SwiftPotion() {
        super("Swift Potion", "Draw 3 cards.",  CardRarity.COMMON,  CardType.SKILL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(3);
    }
}
