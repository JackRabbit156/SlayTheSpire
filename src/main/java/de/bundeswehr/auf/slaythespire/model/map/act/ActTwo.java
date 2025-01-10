package de.bundeswehr.auf.slaythespire.model.map.act;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.CultistEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.MadGremlinEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_two.ByrdEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_two.SphericGuardianEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_two.boss.BronzeAutomatonBoss;
import de.bundeswehr.auf.slaythespire.model.enemy.act_two.boss.TheChampBoss;
import de.bundeswehr.auf.slaythespire.model.enemy.act_two.elite.BookOfStabbingElite;
import de.bundeswehr.auf.slaythespire.model.enemy.act_two.elite.GremlinLeaderElite;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.EnemyEnum;
import de.bundeswehr.auf.slaythespire.model.map.Coordinates;
import de.bundeswehr.auf.slaythespire.model.map.Node;
import de.bundeswehr.auf.slaythespire.model.map.field.*;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse ActTwo ist eine konkrete Implementierung des zweiten Aktes im Spiel.
 * Sie legt die Struktur des Aktes fest, indem sie die Positionen und Typen der Felder (z. B. Kampf, Shop, Elite) definiert.
 *
 * <p>Diese Klasse initialisiert die Knotenpunkte und deren Verbindungen und erlaubt es dem Spieler,
 * sich innerhalb des Aktes zu bewegen und spezifische Feldaktionen auszuführen.</p>
 *
 * @author Warawa Alexander
 */
public class ActTwo extends Act {

    public static final String IMAGE = "/images/gui/act2.png";

    private static final int MAP_HEIGHT = 14;
    private static final int MAP_WIDTH = 4;
    private static final Class<?>[] possibleEnemies = {ByrdEnemy.class, CultistEnemy.class, SphericGuardianEnemy.class};

    private Player player;

    /**
     * Konstruktor für die Klasse ActTwo.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld, mit der Option einen Spieler auf einem bestimmten Floor zu spawnen.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     */
    public ActTwo(Player player) {
        super(2, MAP_WIDTH, MAP_HEIGHT);
        MusicBoy.play("act2");
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
        int randAmountEnemies = GameSettings.getDifficultyLevel().getNumberOfMinionsElite(2);
        EnemyEnum type;
        Elite elite;
        switch (randElite) {
            case 0:
                elite = new GremlinLeaderElite();
                type = EnemyEnum.GREMLIN;
                break;
            default:
                elite = new BookOfStabbingElite();
                type = EnemyEnum.STABBING;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(createEnemiesOfType(type));
        }
        enemies.add(elite);
        return enemies;
    }

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

        int randBoss = rnd.nextInt(2);
        int randAmountEnemies = GameSettings.getDifficultyLevel().getNumberOfMinionsBoss(3);
        EnemyEnum type;
        Boss boss;
        switch (randBoss) {
            case 0:
                boss = new BronzeAutomatonBoss();
                type = EnemyEnum.BRONZE_AUTOMATON;
                break;
            default:
                boss = new TheChampBoss();
                type = EnemyEnum.THE_CHAMP;
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(createEnemiesOfType(type));
        }
        enemies.add(boss);
        return enemies;
    }

    private List<Enemy> createEnemies() {
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
                    LoggingAssistant.log("Enemy type not configured: " + randomNumber, Color.RED);
                    break;
            }
        }
        return enemies;
    }

    /**
     * @return An Enemy
     * @author Keil, Vladislav
     */
    private Enemy createEnemiesOfType(EnemyEnum type) {
        switch (type) {
            case STABBING:
                return new ByrdEnemy();
            case BRONZE_AUTOMATON:
                return new SphericGuardianEnemy();
            case THE_CHAMP:
                return new CultistEnemy();
            default: // GREMLIN
                return new MadGremlinEnemy();
        }
    }

    private void initNodes() {
        Node start17 = new Node("17", new EnemyField(createEnemies()), new Coordinates(2, 14));
        Node unknown18 = new Node("18", new UnknownField(new EventField(), new EnemyField(createEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(0, 12));
        Node fight19 = new Node("19", new EnemyField(createEnemies()), new Coordinates(2, 12));
        Node fight20 = new Node("20", new EnemyField(createEnemies()), new Coordinates(4, 12));
        Node unknown21 = new Node("21", new UnknownField(new EventField(), new EnemyField(createEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(0, 10));
        Node fight22 = new Node("22", new EnemyField(createEnemies()), new Coordinates(2, 10));
        Node fight23 = new Node("23", new EnemyField(createEnemies()), new Coordinates(4, 10));
        Node shop24 = new Node("24", new ShopField(), new Coordinates(0, 8));
        Node elite25 = new Node("25", new EliteField(createElitesEnemies()), new Coordinates(2, 8));
        Node unknown26 = new Node("26", new TreasureField(), new Coordinates(0, 6));
        Node fight27 = new Node("27", new EnemyField(createEnemies()), new Coordinates(2, 6));
        Node unknown28 = new Node("28", new UnknownField(new EventField(), new EnemyField(createEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(4, 6));
        Node unknown29 = new Node("29", new UnknownField(new EventField(), new EnemyField(createEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(0, 4));
        Node fight30 = new Node("30", new EnemyField(createEnemies()), new Coordinates(2, 4));
        Node fight31 = new Node("31", new EnemyField(createEnemies()), new Coordinates(4, 4));
        Node rest32 = new Node("32", new RestField(), new Coordinates(2, 2));
        Node boss33 = new Node("33", new BossField(createBossEnemies()), new Coordinates(2, 0));

        nodes.add(start17);
        nodes.add(unknown18);
        nodes.add(fight19);
        nodes.add(fight20);
        nodes.add(unknown21);
        nodes.add(fight22);
        nodes.add(fight23);

        nodes.add(shop24);
        nodes.add(elite25);
        nodes.add(unknown26);
        nodes.add(fight27);
        nodes.add(unknown28);
        nodes.add(unknown29);
        nodes.add(fight30);
        nodes.add(fight31);
        nodes.add(rest32);
        nodes.add(boss33);


        start17.setLeftNode(unknown18);
        start17.setMiddleNode(fight19);
        start17.setRightNode(fight20);

        unknown18.setMiddleNode(unknown21);

        fight19.setLeftNode(unknown21);
        fight19.setMiddleNode(fight22);

        fight20.setMiddleNode(fight23);

        unknown21.setMiddleNode(shop24);

        fight22.setMiddleNode(elite25);

        fight23.setLeftNode(elite25);
        fight23.setMiddleNode(unknown28);

        shop24.setMiddleNode(unknown26);
        elite25.setLeftNode(unknown26);
        elite25.setMiddleNode(fight27);

        unknown26.setMiddleNode(unknown29);

        fight27.setMiddleNode(fight30);
        unknown28.setMiddleNode(fight31);

        unknown29.setRightNode(rest32);

        fight30.setMiddleNode(rest32);

        fight31.setLeftNode(rest32);

        rest32.setMiddleNode(boss33);
    }
}
