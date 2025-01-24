package de.bundeswehr.auf.slaythespire.model.enemy.act_one;

import de.bundeswehr.auf.slaythespire.controller.listener.EmptyPlayerEventListener;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AcidSlimeEnemyTest {

    private class TestBattleListener extends EmptyPlayerEventListener {

    }

    private AcidSlimeEnemy acidSlimeEnemy;
    private BattleDeck battleDeck;
    private GameContext gameContext;
    private IroncladPlayer player;


    @BeforeEach
    void setUp() {
        TestBattleListener testBattleListener = new TestBattleListener();
        player = new IroncladPlayer(null);
        player.addPlayerEventListener(testBattleListener);

        battleDeck = new BattleDeck(player.getDeck());

        acidSlimeEnemy = new AcidSlimeEnemy();

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(acidSlimeEnemy);

        gameContext = new GameContext(player, enemyList, battleDeck);
    }

    @Test
    void testAttack() {
        int expectedPlayerHealth = player.getMaxHealth() - 11;

        acidSlimeEnemy.attack(gameContext);

        int actualPlayerHealth = player.getCurrentHealth();

        Assertions.assertEquals(expectedPlayerHealth, actualPlayerHealth, "Expected Damage does not fit!");
    }

    @Description("Checks if the enemy specific Information fits")
    @Test
    void testConstructor() {
        String expectedName = "Acid Slime (L)";
        int expectedMaxHealthMin = 65;
        int expectedMaxHealthMax = 69;
        int expectedDeckSize = 1;
        String expectedPath = "/images/enemy/act_one/AcidSlimeEnemyTest.png";

        String actualName = acidSlimeEnemy.getName();
        int actualHealth = acidSlimeEnemy.getMaxHealth();
        int actualCurrentHealth = acidSlimeEnemy.getCurrentHealth();
        int actualDeckSize = acidSlimeEnemy.getEnemyDeck().size();
        String actualPath = new PathAssistent().toPath(this);

        Assertions.assertEquals(expectedName, actualName, "Expected Name does not match set Name!");

        Assertions.assertEquals(actualHealth, actualCurrentHealth, "Current Health does not match Max Health!");

        if (expectedMaxHealthMin > actualHealth || expectedMaxHealthMax < actualHealth) {
            Assertions.fail("Health is not in the expected Range!!!");
        }

        Assertions.assertEquals(expectedDeckSize, actualDeckSize, "Deck Size of Enemy does not fit the expected Value!");

        Assertions.assertEquals(expectedPath, actualPath, "Path is wrong!");
    }


}