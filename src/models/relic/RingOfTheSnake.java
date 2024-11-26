package models.relic;

import helper.PathAssistent;
import models.battle.GameContext;
import models.player.player_structure.PlayerType;
import models.relic.relic_structure.PlayerTypeRelic;
import models.relic.relic_structure.RelicType;

/**
 * The type Ring of the snake.
 *
 * @author OF Daniel Willig
 */
public class RingOfTheSnake extends PlayerTypeRelic {
    /**
     * Constructor Ring of the snake.
     */
// * Constructor *
    public RingOfTheSnake() {
        super("Ring of the Snake", "At the start of each combat, draw 2 additional cards.", RelicType.STARTER, PlayerType.SILENT);
        setImagePath(new PathAssistent().toPath(this));
    }

    // * Methods *
    @Override
    public void getsUsed(GameContext gameContext) {

    }
}