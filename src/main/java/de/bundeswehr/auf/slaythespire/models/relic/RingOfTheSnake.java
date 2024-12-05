package de.bundeswehr.auf.slaythespire.models.relic;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.PlayerType;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.RelicType;

/**
 * The type Ring of the snake.
 *
 * @author OF Daniel Willig
 */
public class RingOfTheSnake extends PlayerTypeRelic {

    /**
     * Constructor Ring of the snake.
     */
    public RingOfTheSnake() {
        super("Ring of the Snake", "At the start of each combat, draw 2 additional cards.", RelicType.STARTER, PlayerType.SILENT, RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void getsUsed(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(2);
    }

}