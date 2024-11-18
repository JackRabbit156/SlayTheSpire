package models.relics;

import models.battle.GameContext;
import models.player.player_structure.PlayerType;
import models.relics.relic_structure.PlayerTypeRelic;
import models.relics.relic_structure.RelicType;

/**
 * The type Ring of the snake.
 *
 * @author OF Daniel Willig
 */
//TODO adding flavor-text to all relics?
public class RingOfTheSnake extends PlayerTypeRelic {
    /**
     * Constructor Ring of the snake.
     */
// * Constructor *
    public RingOfTheSnake() {
        super("Ring of the Snake", "At the start of each combat, draw 2 additional cards.", RelicType.STARTER, PlayerType.SILENT);
    }

    // * Methods *
    @Override
    public void getsUsed(GameContext gameContext) {
        //TODO GameLogic
    }
}