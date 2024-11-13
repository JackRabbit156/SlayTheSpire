package tests;

import models.enemy.Enemy;
import models.enemy.act_one.AcidSlimeEnemy;
import models.enemy.act_one.CultistEnemy;
import models.enemy.act_one.MadGremlinEnemy;
import models.enemy.act_one.bosses.SlimeBoss;
import models.enemy.act_one.elites.GremlinNobElite;
import models.enemy.act_one.elites.LagavulinElite;
import models.map_elements.field_types.*;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnknownFieldTester {
    Random randi = new Random();

    public static void main(String[] args) {
        Player player = new TestPlayer();

        EventViewControllerTester eventTester = new EventViewControllerTester();
        UnknownFieldTester tester = new UnknownFieldTester();
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            enemies.add(tester.createEnemiesOfType("Slime"));
        }
//        EliteField eliteField = new EliteField(tester.createElitesEnemies());
        UnknownField unknownField = new UnknownField(
                new EventField(eventTester.randomEvent(player)),
                new EnemyField(enemies),
                new EliteField(tester.createElitesEnemies()),
                new ShopField());

    UnknownField unknownField1 = new UnknownField(
            new EventField(eventTester.randomEvent(player)),
            new EnemyField(enemies),
            new EliteField(tester.createElitesEnemies()),
            new ShopField());
    UnknownField unknownField2 = new UnknownField(
            new EventField(eventTester.randomEvent(player)),
            new EnemyField(enemies),
            new EliteField(tester.createElitesEnemies()),
            new ShopField());
        UnknownField unknownField3 = new UnknownField(
                new EventField(eventTester.randomEvent(player)),
                new EnemyField(enemies),
                new EliteField(tester.createElitesEnemies()),
                new ShopField());
        // FALL 1:
        // eliteField.doFieldThing(player);
        unknownField.doFieldThing(player);
        unknownField1.doFieldThing(player);
        unknownField2.doFieldThing(player);
        unknownField3.doFieldThing(player);

    }

    public List<Enemy> createBossEnemies() {
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
