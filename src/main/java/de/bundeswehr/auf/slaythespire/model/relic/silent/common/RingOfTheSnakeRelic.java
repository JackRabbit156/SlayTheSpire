package de.bundeswehr.auf.slaythespire.model.relic.silent.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.StarterTypeRelic;

/**
 * The type Ring of the snake.
 *
 * @author OF Daniel Willig
 */
public class RingOfTheSnakeRelic extends StarterTypeRelic {

    /**
     * Constructor Ring of the snake.
     */
    public RingOfTheSnakeRelic() {
        super("Ring of the Snake", "At the start of each combat, draw 2 additional cards.",
                RelicRarity.COMMON, PlayerType.SILENT, RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(2);
    }

}