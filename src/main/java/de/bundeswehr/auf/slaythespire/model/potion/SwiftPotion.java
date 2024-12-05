package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;

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
