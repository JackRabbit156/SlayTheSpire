package tests;

import controller.gui.BattleController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.enemy.Enemy;
import models.enemy.act_one.AcidSlimeEnemy;
import models.enemy.act_one.CultistEnemy;
import models.enemy.act_one.MadGremlinEnemy;
import models.enemy.act_one.bosses.SlimeBoss;
import models.enemy.act_one.elites.GremlinNobElite;
import models.enemy.act_one.elites.LagavulinElite;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleViewTester extends Application {
    Random randi = new Random();
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) {
//        BattleViewTester tester = new BattleViewTester();
//        BossField bossField = new BossField(tester.createBossEnemies());

        TestPlayer player = new TestPlayer();

        // Es muss gekämpft werden, um die ausgabe zu erhalten.
        // FALL 1: ERWARTE : Hier kommt die StatisticsView mit Loot View
//        bossField.doFieldThing(player);
        // Ausgabe alles 0

        BattleController battleController = new BattleController(player, generateEnemies());

        Scene scene = new Scene(battleController.getBattleView(), 1920, 1080);

//        scene.getStylesheets().add(getClass().getResource("/debug.css").toExternalForm());
        player.setPrimaryStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - JavaFX");
        primaryStage.show();
    }

    private List<Enemy> generateEnemies(){
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

    private List<Enemy> createBossEnemies() {
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
            enemies.add(createEnemiesOfType(type));
        }
        return enemies;
    }


    private List<Enemy> createElitesEnemies() {
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
            enemies.add(createEnemiesOfType(type));
        }
        return enemies;
    }

    private Enemy createEnemiesOfType(String type) {
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
