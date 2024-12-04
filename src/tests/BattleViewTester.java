package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.battle.GameContext;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardType;
import models.enemy.Enemy;
import models.enemy.EnemyEnum;
import models.enemy.act_one.AcidSlimeEnemy;
import models.enemy.act_one.CultistEnemy;
import models.enemy.act_one.MadGremlinEnemy;
import models.enemy.act_one.boss.SlimeBoss;
import models.enemy.act_one.boss.TheGuardianBoss;
import models.enemy.act_one.elite.GremlinNobElite;
import models.enemy.act_one.elite.LagavulinElite;
import models.enemy.act_two.ByrdEnemy;
import models.enemy.act_two.SphericGuardianEnemy;
import models.enemy.act_two.boss.BronzeAutomatonBoss;
import models.enemy.act_two.boss.TheChampBoss;
import models.enemy.act_two.elite.BookOfStabbingElite;
import models.enemy.act_two.elite.GremlinLeaderElite;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.map_elements.field_types.FieldEnum;
import models.potion.potion_structure.PotionCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class BattleViewTester extends Application {

    private static class CheaterPotion extends PotionCard {

        public CheaterPotion() {
            super("Cheater Potion", "Deals 200 damage.", CardRarity.SPECIAL, CardType.POTION);
            setImagePath("/images/icon.png");
        }

        @Override
        public void play(GameContext gameContext) {
            Enemy enemy = gameContext.getSelectedEnemy();
            enemy.takeDamage(200);
        }

    }

    private static final Random rnd = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        player.getPotionCards().add(new CheaterPotion());
        player.getPotionCards().add(new CheaterPotion());
        player.getPotionCards().add(new CheaterPotion());
        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        GuiHelper.Scenes.startBattleScene(player, actOneGenerateEnemies(), FieldEnum.ENEMYFIELD);
//        GuiHelper.Scenes.startBattleScene(player, actOneGenerateElitesEnemies(), FieldEnum.ELITEFIELD);
//        GuiHelper.Scenes.startBattleScene(player, actOneGenerateBossEnemies(), FieldEnum.BOSSFIELD);
//
//        GuiHelper.Scenes.startBattleScene(player, actTwoGenerateEnemies(), FieldEnum.ENEMYFIELD);
//        GuiHelper.Scenes.startBattleScene(player, actTwoGenerateElitesEnemies(), FieldEnum.ELITEFIELD);
//        GuiHelper.Scenes.startBattleScene(player, actTwoGenerateBossEnemies(), FieldEnum.BOSSFIELD);
    }

    private List<Enemy> actOneGenerateBossEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        int randBoss = rnd.nextInt(3);
        int randAmountEnemies = rnd.nextInt(4);
        EnemyEnum type;
        switch (randBoss) {
            case 0:
                // 1 - SlimeBoss
                enemies.add(new SlimeBoss());
                type = EnemyEnum.SLIME;
                break;
            case 1:
                // 2 - TheGuardian (Soll)
                enemies.add(new TheGuardianBoss());
                type = EnemyEnum.GUARDIAN;
                break;
            default:
                // 3 - Hexaghost (Kann)
                enemies.add(new SlimeBoss());
                type = EnemyEnum.HEXA;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(actOneGenerateEnemiesOfType(type));
        }
        return enemies;
    }

    private List<Enemy> actOneGenerateElitesEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        int randElite = rnd.nextInt(2);
        int randAmountEnemies = rnd.nextInt(2);
        EnemyEnum type;
        switch (randElite) {
            case 0:
                // 1 - Gremlin Nob
                enemies.add(new GremlinNobElite());
                type = EnemyEnum.GOBLIN;
                break;
            default:
                // 2 - Lagavulin
                enemies.add(new LagavulinElite());
                type = EnemyEnum.LAGAVULIN;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(actOneGenerateEnemiesOfType(type));
        }
        return enemies;
    }

    private List<Enemy> actOneGenerateEnemies() {
        Class<?>[] possibleEnemies = {AcidSlimeEnemy.class, CultistEnemy.class, MadGremlinEnemy.class};

        int numberOfEnemies = GameSettings.getDifficultyLevel().getNumberOfEnemies();
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < numberOfEnemies; i++) {
            int randomNumber = rnd.nextInt(possibleEnemies.length);
            switch (randomNumber) {
                case 0:
                    enemies.add(new AcidSlimeEnemy());
                    break;
                case 1:
                    enemies.add(new CultistEnemy());
                    break;
                case 2:
                    enemies.add(new MadGremlinEnemy());
                    break;
                default:
                    System.out.println("Weird...");
                    break;
            }
        }
        return enemies;
    }

    private Enemy actOneGenerateEnemiesOfType(EnemyEnum type) {
        switch (type) {
            case HEXA:
                return new MadGremlinEnemy();
            case GUARDIAN:
                return new CultistEnemy(); // TODO: None
            case LAGAVULIN:
                return new CultistEnemy();
            case GOBLIN:
                return new MadGremlinEnemy();
            default: // SLIME
                return new AcidSlimeEnemy();
        }
    }

    private List<Enemy> actTwoGenerateBossEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        int randBoss = rnd.nextInt(2);
        int randAmountEnemies = rnd.nextInt(4);
        EnemyEnum type;
        switch (randBoss) {
            case 0:
                enemies.add(new BronzeAutomatonBoss());
                type = EnemyEnum.GUARDIAN;
                break;
            default:
                enemies.add(new TheChampBoss());
                type = EnemyEnum.LAGAVULIN;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(actTwoGenerateEnemiesOfType(type));
        }
        return enemies;
    }

    private List<Enemy> actTwoGenerateElitesEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        int randElite = rnd.nextInt(2);
        int randAmountEnemies = rnd.nextInt(2);
        EnemyEnum type;
        switch (randElite) {
            case 0:
                enemies.add(new GremlinLeaderElite());
                type = EnemyEnum.GOBLIN;
                break;
            default:
                enemies.add(new BookOfStabbingElite());
                type = EnemyEnum.STABBING;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(actTwoGenerateEnemiesOfType(type));
        }
        return enemies;
    }

    private List<Enemy> actTwoGenerateEnemies() {
        Class<?>[] possibleEnemies = {ByrdEnemy.class, CultistEnemy.class, SphericGuardianEnemy.class};

        int numberOfEnemies = GameSettings.getDifficultyLevel().getNumberOfEnemies();
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < numberOfEnemies; i++) {
            int randomNumber = rnd.nextInt(possibleEnemies.length);
            switch (randomNumber) {
                case 0:
                    enemies.add(new ByrdEnemy());
                    break;
                case 1:
                    enemies.add(new CultistEnemy());
                    break;
                case 2:
                    enemies.add(new SphericGuardianEnemy());
                    break;
                default:
                    System.out.println("Weird...");
                    break;
            }
        }
        return enemies;
    }

    private Enemy actTwoGenerateEnemiesOfType(EnemyEnum type) {
        switch (type) {
            case STABBING:
                return new ByrdEnemy();
            case GUARDIAN:
                return new SphericGuardianEnemy();
            case LAGAVULIN:
                return new CultistEnemy();
            default: // GREMLIN
                return new MadGremlinEnemy();
        }
    }

}
