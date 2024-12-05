package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.AcidSlimeEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.CultistEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.MadGremlinEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.boss.SlimeBoss;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.elite.GremlinNobElite;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.elite.LagavulinElite;
import de.bundeswehr.auf.slaythespire.model.map.field.EliteField;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author Keil, Vladislav
 */
public class EliteFieldTester {
    Random randi = new Random();

    public static void main(String[] args) {
        EliteFieldTester tester = new EliteFieldTester();

        EliteField eliteField = new EliteField(tester.createElitesEnemies());

        Player player = new TestPlayer(null);

        // Es muss gekämpft werden, um die ausgabe zu erhalten.
        // FALL 1: ERWARTE : Hier kommt die StatisticsView mit Loot View
        eliteField.doFieldThing(player);
        // Ausgabe alles 0
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
