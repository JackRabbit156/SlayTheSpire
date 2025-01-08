package de.bundeswehr.auf.slaythespire.model.enemy;

import de.bundeswehr.auf.slaythespire.controller.listener.EnemyEventListener;
import de.bundeswehr.auf.slaythespire.events.EnemyBlockEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class EnemyTest {

    private static class EnemyCardTest extends  EnemyCard{

        public EnemyCardTest() {
            super("IntentTest", "IntentDesc.", "*");
        }

        @Override
        public void playEnemy(GameContext gameContext, Enemy enemy) {}

    }

    private class TestEnemy extends Enemy {

        /**
         * Konstruktor für die Enemy-Klasse.
         * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
         *
         */
        public TestEnemy() {
            super("TestEnemy", 19, 20);
        }

        @Override
        public void attack(GameContext gameContext) {
            attacked = true;
        }

    }

    private static class TestController implements EnemyEventListener{

        @Override
        public void onDamageDealt(EnemyDamageEvent event) {}

        @Override
        public void onBlockReceived(EnemyBlockEvent event) {}

        @Override
        public void onDamageReceived(EnemyDamageEvent event) {}

        @Override
        public void onEnemyDeath(Enemy enemy) {}

    }

    @Mock
    private TestEnemy enemyTest;

    private GameContext gameContext;

    private boolean attacked = false;

    @BeforeEach
    void setUp() {
        attacked = false;
        IroncladPlayer player = new IroncladPlayer(null);

        TestController controller = new TestController();
        BattleDeck battleDeck = new BattleDeck(player.getDeck());

        enemyTest = new TestEnemy();
        enemyTest.addEnemyEventListener(controller);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyTest);

        gameContext = new GameContext(player, enemyList, battleDeck);
    }

    @Test
    void testConstructor() {

    }

    @Test
    void testGetName() {
        String expectedName = "TestEnemy";

        String actualName = enemyTest.getName();

        Assertions.assertEquals(expectedName, actualName, "Names do not fit");

    }

    @Test
    void testGetHealth() {
        int expectedHealth = enemyTest.getMaxHealth();

        int actualHealth = enemyTest.getHealth();

        Assertions.assertEquals(expectedHealth, actualHealth, "Health does not fit!");

        int expectedHealthAfterDamageTaken = enemyTest.getMaxHealth() - 5;
        enemyTest.takeDamage(5);
        int actualHealthAfterDamageTaken = enemyTest.getHealth();

        Assertions.assertEquals(expectedHealthAfterDamageTaken, actualHealthAfterDamageTaken, "Health does not fit!");
    }

    @Test
    void testGetMaxHealth(){
        int expectedMaxHealthMin = 19;
        int expectedMaxHealthMax = 20;

        int actualHealth = enemyTest.getMaxHealth();

        if(expectedMaxHealthMin > actualHealth || expectedMaxHealthMax < actualHealth)
            Assertions.fail("Health is not in the expected Range!!!");
    }

    @Test
    void testSetImagePath() {
        String expectedPath = "/images/enemy/act_one/AcidSlimeEnemy.png";

        enemyTest.setImagePath(expectedPath);

        String actualPath = enemyTest.getImagePath();

        Assertions.assertEquals(expectedPath, actualPath, "ImagePath is wrong!");
    }

    @Test
    void testAction() {
        EnemyCardTest enemyCardTest = new EnemyCardTest();
        enemyTest.setIntent(enemyCardTest);

        boolean expectedAttacked = true;

        enemyTest.action(gameContext);

        boolean actualAttacked = attacked;

        Assertions.assertEquals(expectedAttacked, actualAttacked, "Action can't be done");
    }

    @Test
    void testIsAlive() {
        boolean expectedIsAlive = true;

        boolean actualIsAlive = enemyTest.isAlive();

        Assertions.assertEquals(expectedIsAlive, actualIsAlive, "Enemy should be Alive");

        enemyTest.takeDamage(20);

        boolean expectedIsNotAlive = false;

        boolean actualIsNotAlive = enemyTest.isAlive();

        Assertions.assertEquals(expectedIsNotAlive, actualIsNotAlive, "Enemy should NOT be Alive");
    }

    @Test
    void testTakeDamage() {
        int expectedHp = enemyTest.getHealth() - 5;

        enemyTest.takeDamage(5);

        int actualHp = enemyTest.getHealth();

        Assertions.assertEquals(expectedHp, actualHp, "Enemy took wrong amount of damage!");
    }

    @Test
    void testSetBlock() {
        int expectedBlock = 5;

        enemyTest.setBlock(5);

        int actualBlock = enemyTest.getBlock();

        Assertions.assertEquals(expectedBlock, actualBlock, "Block Value is wrong");
    }

    @Test
    void testGetBlock() {
        int expectedBlock = 5;

        enemyTest.setBlock(5);

        int actualBlock = enemyTest.getBlock();

        Assertions.assertEquals(expectedBlock, actualBlock, "Block Value is wrong");
    }

    @Test
    void testAddBlock() {
        int expectedBlock = 5;

        enemyTest.addBlock(5);

        int actualBlock = enemyTest.getBlock();

        Assertions.assertEquals(expectedBlock, actualBlock, "Block Value is wrong");


        int expectedBlockAddition = 10;

        enemyTest.addBlock(5);

        int actualBlockAdded = enemyTest.getBlock();
        Assertions.assertEquals(expectedBlockAddition, actualBlockAdded, "Block Value is wrong");
    }

    @Test
    void testSetEnemyEventListener() {
        MockitoAnnotations.openMocks(this);

        TestController controller = new TestController();

        enemyTest.addEnemyEventListener(controller);

        Mockito.verify(enemyTest).addEnemyEventListener(controller);
    }

    @Test
    void testGetIntent() {
        EnemyCard expectedIntenBeforeSet = null;

        EnemyCard actualIntenBeforeSet = enemyTest.getIntent();
        Assertions.assertEquals(expectedIntenBeforeSet, actualIntenBeforeSet, "How could here be a Intent?...");


        EnemyCard expectedIntenAfterSet = new EnemyCardTest();

        enemyTest.setIntent(expectedIntenAfterSet);

        EnemyCard actualIntenAfterSet = enemyTest.getIntent();
        Assertions.assertEquals(expectedIntenAfterSet, actualIntenAfterSet, "No Intent set...");
    }

    @Test
    void testSetIntent() {
        MockitoAnnotations.openMocks(this);

        EnemyCard expectedIntenAfterSet = new EnemyCardTest();

        enemyTest.setIntent(expectedIntenAfterSet);

        Mockito.verify(enemyTest).setIntent(expectedIntenAfterSet);
    }

    @Test
    void testGetDeck() {
        List<EnemyCard> expectedEnemydeck = null;

        List<EnemyCard> actualEnemydeck = enemyTest.getEnemyDeck();

        Assertions.assertEquals(expectedEnemydeck, actualEnemydeck, "Why does Enemy have a Deck?");

        List<EnemyCard> expectedEnemydeckAfterSet = new ArrayList<>();

        enemyTest.setEnemyDeck(expectedEnemydeckAfterSet);
        List<EnemyCard> actualEnemydeckAfterSet = enemyTest.getEnemyDeck();


        Assertions.assertEquals(expectedEnemydeckAfterSet, actualEnemydeckAfterSet, "Why does Enemy have a Deck?");
    }

    @Test
    void testSetEnemyDeck() {
        MockitoAnnotations.openMocks(this);

        List<EnemyCard> enemyDeck = new ArrayList<>();

        enemyTest.setEnemyDeck(enemyDeck);

        Mockito.verify(enemyTest).setEnemyDeck(enemyDeck);
    }

}