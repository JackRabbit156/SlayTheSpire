package tests;

import controller.gui.BattleController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.enemy.Enemy;
import models.enemy.EnemyEnum;
import models.enemy.act_one.AcidSlimeEnemy;
import models.enemy.act_one.CultistEnemy;
import models.enemy.act_one.MadGremlinEnemy;
import models.enemy.act_one.boss.SlimeBoss;
import models.enemy.act_one.elites.GremlinNobElite;
import models.enemy.act_one.elites.LagavulinElite;
import models.enemy.act_two.ByrdEnemy;
import models.enemy.act_two.SphericGuardianEnemy;
import models.enemy.act_two.boss.BronzeAutomatonBoss;
import models.enemy.act_two.boss.TheChampBoss;
import models.enemy.act_two.elites.BookOfStabbingElite;
import models.enemy.act_two.elites.GremlinLeaderElite;
import models.map_elements.field_types.FieldEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author Keil, Vladislav
 */
public class BattleViewTester extends Application {
    Random randi = new Random();
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        GuiHelper.Scenes.startBattleScene(player, generateEnemiesActOne(), FieldEnum.ENEMYFIELD);
    }

    private List<Enemy> generateEnemiesActOne(){
        List<Enemy> enemies = new ArrayList<>();

        String[] possibleEnemies = {"AcidSlime", "Cultist", "MadGremlin"};

        int numberOfEnemies = randi.nextInt(4) + 1;

        for(int i = 0; i< numberOfEnemies; i++){
            int randomNumber = randi.nextInt(possibleEnemies.length);
            switch (randomNumber){
                case 0: enemies.add(new AcidSlimeEnemy()); break;
                case 1: enemies.add(new CultistEnemy()); break;
                case 2: enemies.add(new MadGremlinEnemy()); break;
                default:
                    System.out.println("Weird..."); break;
            }
        }
        return enemies;
    }

    private List<Enemy> generateEnemiesActTwo(){
        List<Enemy> enemies = new ArrayList<>();

        String[] possibleEnemies = {"Byrd", "Cultist", "SphericGuardian"};

        int numberOfEnemies = randi.nextInt(4) + 1;

        for(int i = 0; i< numberOfEnemies; i++){
            int randomNumber = randi.nextInt(possibleEnemies.length);
            switch (randomNumber){
                case 0: enemies.add(new ByrdEnemy()); break;
                case 1: enemies.add(new CultistEnemy()); break;
                case 2: enemies.add(new SphericGuardianEnemy()); break;
                default:
                    System.out.println("Weird..."); break;
            }
        }
        return enemies;
    }

    private List<Enemy> createBossEnemiesActTwo() {
        List<Enemy> enemies = new ArrayList<>();

        int randBoss = randi.nextInt(2);
        int randAmountEnemies = randi.nextInt(4);
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
            enemies.add(createEnemiesOfTypeActTwo(type));
        }
        return enemies;
    }

    public List<Enemy> createElitesEnemiesActTwo() {
        List<Enemy> enemies = new ArrayList<>();
        int randElite = randi.nextInt(2);
        int randAmountEnemies = randi.nextInt(2);
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
            enemies.add(createEnemiesOfTypeActTwo(type));
        }
        return enemies;
    }

    private Enemy createEnemiesOfTypeActTwo(EnemyEnum type) {
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

    private List<Enemy> createBossEnemiesActOne() {
        List<Enemy> enemies = new ArrayList<>();

        int randBoss = randi.nextInt(3);
        int randAmountEnemies = randi.nextInt(4);
        String type;
        switch (randBoss) {
            case 0:
                // 1 - SlimBoss
                enemies.add(new SlimeBoss());
                type = "Slime";
                break;
            case 1:
                // 2 - TheGuardian (Soll)
                enemies.add(new SlimeBoss());
//                type = "Guardian";
                type = "Slime";
                break;
            default:
                // 3 - Hexaghost (Kann)
                enemies.add(new SlimeBoss());
//                type = "Hexa";
                type = "Slime";
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(createEnemiesOfTypeActOne(type));
        }
        return enemies;
    }

    private List<Enemy> createElitesEnemiesActOne() {
        List<Enemy> enemies = new ArrayList<>();
        int randElite = randi.nextInt(2);
        int randAmountEnemies = randi.nextInt(2);
        String type;
        switch (randElite) {
            case 0:
                // 1 - Gremlin Nob
                enemies.add(new GremlinNobElite());
                type = "Goblin";
                break;
            default:
                // 2 - Lagavulin
                enemies.add(new LagavulinElite());
                type = "Lagavulin";
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(createEnemiesOfTypeActOne(type));
        }
        return enemies;
    }

    private Enemy createEnemiesOfTypeActOne(String type) {
        switch (type) {
            case "Hexa":
                return new MadGremlinEnemy();
            case "Guardian":
                return new CultistEnemy();
            case "Lagavulin":
                return new CultistEnemy();
            case "Goblin":
                return new MadGremlinEnemy();
            default: // "Slime"
                return new AcidSlimeEnemy();
        }
    }
}
