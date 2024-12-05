package de.bundeswehr.auf.slaythespire.models.map_elements.acts;

import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy.EnemyEnum;
import de.bundeswehr.auf.slaythespire.models.enemy.act_one.AcidSlimeEnemy;
import de.bundeswehr.auf.slaythespire.models.enemy.act_one.CultistEnemy;
import de.bundeswehr.auf.slaythespire.models.enemy.act_one.MadGremlinEnemy;
import de.bundeswehr.auf.slaythespire.models.enemy.act_one.boss.SlimeBoss;
import de.bundeswehr.auf.slaythespire.models.enemy.act_one.boss.TheGuardianBoss;
import de.bundeswehr.auf.slaythespire.models.enemy.act_one.elite.GremlinNobElite;
import de.bundeswehr.auf.slaythespire.models.enemy.act_one.elite.LagavulinElite;
import de.bundeswehr.auf.slaythespire.models.game_settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.map_elements.Coordinates;
import de.bundeswehr.auf.slaythespire.models.map_elements.Node;
import de.bundeswehr.auf.slaythespire.models.map_elements.field_types.*;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse ActOne ist eine konkrete Implementierung des ersten Aktes im Spiel.
 * Sie legt die Struktur des Aktes fest, indem sie die Positionen und Typen der Felder (z. B. Kampf, Shop, Elite) definiert.
 *
 * <p>Diese Klasse initialisiert die Knotenpunkte und deren Verbindungen und erlaubt es dem Spieler,
 * sich innerhalb des Aktes zu bewegen und spezifische Feldaktionen auszuführen.</p>
 *
 * @author Warawa Alexander
 */
public class ActOne extends Act {

    private static final int MAP_HEIGHT = 16;
    private static final int MAP_WIDTH = 7;
    private static final Class<?>[] possibleEnemies = { AcidSlimeEnemy.class, CultistEnemy.class, MadGremlinEnemy.class};

    private Player player;

    /**
     * Konstruktor für die Klasse ActOne.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     */
    public ActOne(Player player) {
        super(1, MAP_WIDTH, MAP_HEIGHT);

        MusicBoy.play("act1");

        this.player = player;

        initNodes();

        Node playerNode = getNoteByName(player.getCurrentField());
        if (playerNode != null) {
            playerNode.setPlayer(player);
        }
    }

    /**
     * @return List of Enemies
     * @author Keil, Vladislav
     */
    public List<Enemy> createElitesEnemies() {
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
            enemies.add(createEnemiesOfType(type));
        }
        return enemies;
    }

    /**
     * Führt die spezifizierte Aktion auf dem aktuellen Feld des Spielers aus.
     * Ruft die Methode `doFieldThing()` für das Feld auf, auf dem sich der Spieler befindet.
     */
    @Override
    public void doFieldThing() {
        Node currentNode = getPlayerNode();
        currentNode.doFieldThing(currentNode.getPlayer());
    }

    /**
     * @return List of Enemies
     * @author Keil, Vladislav
     */
    private List<Enemy> createBossEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        int randBoss = rnd.nextInt(3);
        int randAmountEnemies = GameSettings.getDifficultyLevel().getNumberOfEnemies() - 1;
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
            enemies.add(createEnemiesOfType(type));
        }
        return enemies;
    }

    /**
     * @return An Enemy
     * @author Keil, Vladislav
     */
    private Enemy createEnemiesOfType(EnemyEnum type) {
        switch (type) {
            case HEXA:
                return new MadGremlinEnemy();
            case GUARDIAN:
                return new CultistEnemy();
            case LAGAVULIN:
                return new CultistEnemy();
            case GOBLIN:
                return new MadGremlinEnemy();
            default: // SLIME
                return new AcidSlimeEnemy();
        }
    }

    private List<Enemy> generateEnemies() {
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

    private void initNodes() {
        Node start1 = new Node("1", new EnemyField(generateEnemies()), new Coordinates(3, 16));
        Node fight2 = new Node("2", new EnemyField(generateEnemies()), new Coordinates(3, 14));
        Node unknown3 = new Node("3", new UnknownField(new EventField(), new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(1, 12));
        Node fight4 = new Node("4", new EnemyField(generateEnemies()), new Coordinates(5, 12));
        Node fight5 = new Node("5", new EnemyField(generateEnemies()), new Coordinates(0, 10));
        Node elite6 = new Node("6", new EliteField(createElitesEnemies()), new Coordinates(2, 10));
        Node fight7 = new Node("7", new EnemyField(generateEnemies()), new Coordinates(5, 10));
        Node shop8 = new Node("8", new ShopField(), new Coordinates(2, 8));
        //Node unknown9 = new Node("9", new UnknownField(new EventField(randomEvent()), new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(5, 8));
        Node treasure9 = new Node("9", new TreasureField(), new Coordinates(5, 8));
        Node event10 = new Node("10", new EventField(), new Coordinates(1, 6));
        Node fight11 = new Node("11", new EnemyField(generateEnemies()), new Coordinates(4, 6));
        Node fight12 = new Node("12", new EnemyField(generateEnemies()), new Coordinates(6, 6));
        Node unknown13 = new Node("13", new UnknownField(new EventField(), new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(4, 4));
        Node elite14 = new Node("14", new EliteField(createElitesEnemies()), new Coordinates(6, 4));
        Node rest15 = new Node("15", new RestField(), new Coordinates(4, 2));
        Node boss16 = new Node("16", new BossField(createBossEnemies()), new Coordinates(4, 0));

        nodes.add(start1);
        nodes.add(fight2);
        nodes.add(unknown3);
        nodes.add(fight4);
        nodes.add(fight5);
        nodes.add(elite6);
        nodes.add(fight7);
        nodes.add(shop8);
        nodes.add(treasure9);
        nodes.add(event10);
        nodes.add(fight11);
        nodes.add(fight12);
        nodes.add(unknown13);
        nodes.add(elite14);
        nodes.add(rest15);
        nodes.add(boss16);

        start1.setMiddleNode(fight2);
        fight2.setLeftNode(unknown3);
        fight2.setRightNode(fight4);
        unknown3.setLeftNode(fight5);
        unknown3.setRightNode(elite6);
        fight4.setMiddleNode(fight7);
        fight5.setRightNode(event10);
        elite6.setMiddleNode(shop8);
        fight7.setMiddleNode(treasure9);
        shop8.setLeftNode(event10);
        treasure9.setLeftNode(fight11);
        treasure9.setRightNode(fight12);
        event10.setRightNode(rest15);
        fight11.setMiddleNode(unknown13);
        fight12.setMiddleNode(elite14);
        unknown13.setMiddleNode(rest15);
        elite14.setLeftNode(rest15);
        rest15.setMiddleNode(boss16);
    }
//
//    private Event randomEvent() {
//        Random randi = new Random();
//        ActOneEventEnum[] events = ActOneEventEnum.values();
//        ActOneEventEnum event = events[randi.nextInt(events.length)];
//        switch (event) {
//            case BIG_FISH:
//                return new BigFishEvent(this.player);
//            case BONFIRE_SPIRITS:
//                return new BonfireSpiritsEvent(this.player);
//            case DEAD_ADVENTURER:
//                return new DeadAdventurerEvent(this.player);
//            case DUPLICATOR:
//                return new DuplicatorEvent(this.player);
//            case NOTE_FOR_YOURSELF:
//                return new NoteForYourselfEvent(this.player);
//            case SCRAP_OOZE:
//                return new ScrapOozeEvent(this.player);
//            case THE_CLERIC:
//                return new TheClericEvent(this.player);
//            case THE_SSSSSERPENT:
//                return new TheSssssserpentEvent(this.player);
//            case WORLDOF_GOO:
//                return new WorldOfGooEvent(this.player);
//            default:
//                return new GoldenShrineEvent(this.player);
//        return null;
//    }
}
