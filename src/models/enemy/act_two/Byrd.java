package models.enemy.act_two;

import models.GameContext;
import models.enemy.Enemy;

public class Byrd extends Enemy {
    public Byrd() {
        super("Byrd", 25, 31);
        setBlock(40);
    }

    @Override
    public void attack(GameContext gameContext) {

    }
}
