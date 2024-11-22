package models.map_elements.acts;

import models.enemy.Enemy;
import models.enemy.EnemyEnum;
import models.enemy.act_one.CultistEnemy;
import models.enemy.act_one.MadGremlinEnemy;
import models.enemy.act_two.ByrdEnemy;
import models.enemy.act_two.SphericGuardianEnemy;
import models.enemy.act_two.boss.BronzeAutomaton;
import models.enemy.act_two.boss.TheChamp;
import models.enemy.act_two.elites.BookOfStabbing;
import models.enemy.act_two.elites.GremlinLeader;
import models.map_elements.Coordinates;
import models.map_elements.Node;
import models.map_elements.field_types.*;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static final int MAP_WIDTH = 4;
    private static final int MAP_HEIGHT= 14;

    private Random randi = new Random();

    private Player player;
    /**
     * Konstruktor für die Klasse ActTwo.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld, mit der Option einen Spieler auf einem bestimmten Floor zu spawnen.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     * @param playerAlreadyOnAct ob der Spieler von einer Datei geladen wurde.
     */
    public ActTwo(Player player, boolean playerAlreadyOnAct){
        super(2, MAP_WIDTH, MAP_HEIGHT);
        this.player = player;
        initNodes();

        Node playerNode = null;
        if(playerAlreadyOnAct)
            playerNode = getNoteByName(player.getCurrentField());
        else
            playerNode = getNoteByName(getFirstField());

        if(playerNode == null){
            System.out.println("ActTwo: ERROR - No Player on node");
            return;
        }

        playerNode.setPlayer(player);
        if(playerAlreadyOnAct)
            playerNode.setFieldBeaten();
        else {
//            playerNode.doFieldThing(player);
            player.setCurrentField(this.getCurrentFieldName());
        }
    }

    private void initNodes(){
        Node start17 = new Node("17", new EnemyField(generateEnemies()), new Coordinates(2, 14));
        Node unknown18 = new Node("18", new UnknownField(null, new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(0, 12));
        Node fight19 = new Node("19", new EnemyField(generateEnemies()), new Coordinates(2, 12));
        Node fight20 = new Node("20", new EnemyField(generateEnemies()), new Coordinates(4, 12));
        Node unknown21 = new Node("21", new UnknownField(null, null, null, null), new Coordinates(0, 10));
        Node fight22 = new Node("22", new EnemyField(generateEnemies()), new Coordinates(2, 10));
        Node fight23 = new Node("23", new EnemyField(generateEnemies()), new Coordinates(4, 10));
        Node shop24 = new Node("24", new ShopField(), new Coordinates(0, 8));
        Node elite25 = new Node("25", new EliteField(createElitesEnemies()), new Coordinates(2, 8));
        Node unknown26 = new Node("26", new UnknownField(null, new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(0, 6));
        Node fight27 = new Node("27", new EnemyField(generateEnemies()), new Coordinates(2, 6));
        Node unknown28 = new Node("28", new UnknownField(null, new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(4, 6));
        Node unknown29 = new Node("29", new UnknownField(null, new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(0, 4));
        Node fight30 = new Node("30", new EnemyField(generateEnemies()), new Coordinates(2, 4));
        Node fight31 = new Node("31", new EnemyField(generateEnemies()), new Coordinates(4, 4));
        Node rest32 = new Node("32", new RestField(), new Coordinates(2, 2));
        Node boss33 = new Node("33", new BossField(createBossEnemies()), new Coordinates(2,0));

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

    private List<Enemy> generateEnemies(){
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
    private List<Enemy> createBossEnemies() {
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
            enemies.add(createEnemiesOfType(type));
        }
        return enemies;
    }


    /**
     * @author Keil, Vladislav
     * @return List of Enemies
     */
    public List<Enemy> createElitesEnemies() {
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
            enemies.add(createEnemiesOfType(type));
        }
        return enemies;
    }


    /**
     * @author Keil, Vladislav
     * @return An Enemy
     */
    private Enemy createEnemiesOfType(EnemyEnum type) {
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


    @Override
    public void doFieldThing() {
        Node currentNode = getPlayerNode();
        currentNode.doFieldThing(currentNode.getPlayer());
    }

    @Override
    public String[][] getRawMap() {
        return new String[0][];
    }
}
