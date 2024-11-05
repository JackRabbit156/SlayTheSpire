package tests;

import models.cards.card_structure.Card;
import models.cards.general_cards.DefendCard;
import models.cards.general_cards.StrikeCard;
import models.cards.ironclad_cards.attack.common.BashCard;
import models.enemy.Enemy;
import models.enemy.act_one.AcidSlime;
import models.enemy.act_one.Cultist;
import models.enemy.act_one.MadGremlin;
import models.enemy.act_one.bosses.SlimeBoss;
import models.enemy.act_one.elites.GremlinNob;
import models.enemy.act_one.elites.Lagavulin;
import models.map_elements.field_types.BossField;
import models.player.Ironclad;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relics.BurningBloodRelic;
import models.relics.relic_structure.Relic;
import view.StatisticsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossFieldTester {
    Random randi = new Random();

    public static void main(String[] args) {
        BossFieldTester tester = new BossFieldTester();

        BossField bossField = new BossField(tester.createBossEnemies());

        StatisticsView statisticsView = new StatisticsView();
        Player player = new TestPlayer();

        // Es muss gekämpft werden, um die ausgabe zu erhalten.
        // FALL 1: ERWARTE : Hier kommt die StatisticsView mit Loot View
        bossField.doFieldThing(player);
        // Ausgabe alles 0
        statisticsView.display(player);
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
                enemies.add(new GremlinNob());
                type = "Goblin";
                break;
            default:
                // 2 - Lagavulin
                enemies.add(new Lagavulin());
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
                return new MadGremlin();
            case "Guardian":
                return new Cultist();
            case "Lagavulin":
                return new Cultist();
            case "Goblin":
                return new MadGremlin();
            default: // "Slime"
                return new AcidSlime();
        }
    }
}
