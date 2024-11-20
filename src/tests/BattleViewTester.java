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
import models.enemy.act_one.bosses.SlimeBoss;
import models.enemy.act_one.elites.GremlinNobElite;
import models.enemy.act_one.elites.LagavulinElite;
import models.enemy.act_two.ByrdEnemy;
import models.enemy.act_two.SphericGuardianEnemy;
import models.enemy.act_two.boss.BronzeAutomaton;
import models.enemy.act_two.boss.TheChamp;
import models.enemy.act_two.elites.BookOfStabbing;
import models.enemy.act_two.elites.GremlinLeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleViewTester extends Application {
    Random randi = new Random();
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();

        BattleController battleController = new BattleController(player, createElitesEnemiesActTwo());
        
        
        Scene scene = new Scene(battleController.getBattleView(), 1920, 1080);
        player.setPrimaryStage(primaryStage);

        GuiHelper.Scenes.startScene(primaryStage, scene, "Slay the Spire - JavaFX");
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

    /**
     * @author Keil, Vladislav
     * @return List of Enemies
     */
    private List<Enemy> createBossEnemiesActTwo() {
        List<Enemy> enemies = new ArrayList<>();

        int randBoss = randi.nextInt(2);
        int randAmountEnemies = randi.nextInt(4);
        EnemyEnum type;
        switch (randBoss) {
            case 0:
                enemies.add(new BronzeAutomaton());
                type = EnemyEnum.GUARDIAN;
                break;
            default:
                enemies.add(new TheChamp());
                type = EnemyEnum.LAGAVULIN;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(createEnemiesOfTypeActTwo(type));
        }
        return enemies;
    }


    /**
     * @author Keil, Vladislav
     * @return List of Enemies
     */
    public List<Enemy> createElitesEnemiesActTwo() {
        List<Enemy> enemies = new ArrayList<>();
        int randElite = randi.nextInt(2);
        int randAmountEnemies = randi.nextInt(2);
        EnemyEnum type;
        switch (randElite) {
            case 0:
                enemies.add(new GremlinLeader());
                type = EnemyEnum.GOBLIN;
                break;
            default:
                enemies.add(new BookOfStabbing());
                type = EnemyEnum.STABBING;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(createEnemiesOfTypeActTwo(type));
        }
        return enemies;
    }


    /**
     * @author Keil, Vladislav
     * @return An Enemy
     */
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
