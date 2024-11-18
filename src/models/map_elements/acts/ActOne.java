package models.map_elements.acts;

import models.enemy.Enemy;
import models.enemy.act_one.AcidSlimeEnemy;
import models.enemy.act_one.CultistEnemy;
import models.enemy.act_one.MadGremlinEnemy;
import models.enemy.act_one.bosses.SlimeBoss;
import models.enemy.act_one.elites.GremlinNobElite;
import models.enemy.act_one.elites.LagavulinElite;
import models.event.Event;
import models.event.act_one.*;
import models.event.generelevents.BonfireSpiritsEvent;
import models.event.generelevents.DuplicatorEvent;
import models.event.generelevents.GoldenShrineEvent;
import models.event.generelevents.NoteForYourselfEvent;
import models.map_elements.Coordinates;
import models.map_elements.Node;
import models.map_elements.field_types.*;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private static final int MAP_WIDTH = 7;
    private static final int MAP_HEIGHT= 16;

    private Player player;
    private Random randi = new Random();

    /**
     * Konstruktor für die Klasse ActOne.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     * @param loadingFromFile ob der Spieler von einer Datei geladen wurde.
     */
    public ActOne(Player player, boolean loadingFromFile){
        super(1, MAP_WIDTH, MAP_HEIGHT);
        this.player = player;
        initNodes();

        Node playerNode = null;
        if(loadingFromFile)
            playerNode = getNoteByName(player.getCurrentField());
        else
            playerNode = getNoteByName(getFirstField());

        if(playerNode == null){
            System.out.println("ERROR");
            return;
        }

        playerNode.setPlayer(player);

        if(loadingFromFile)
            playerNode.setFieldBeaten();
        else
            playerNode.doFieldThing(player);
    }

    private void initNodes(){
        Node start1 = new Node("1", new EnemyField(generateEnemies()), new Coordinates(3, 16));
        Node fight2 = new Node("2", new EnemyField(generateEnemies()), new Coordinates(3, 14));
        Node unknown3 = new Node("3", new UnknownField(new EventField(randomEvent()), new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(1, 12));
        Node fight4 = new Node("4", new EnemyField(generateEnemies()), new Coordinates(5, 12));
        Node fight5 = new Node("5", new EnemyField(generateEnemies()), new Coordinates(0, 10));
        Node elite6 = new Node("6", new EliteField(createElitesEnemies()), new Coordinates(2, 10));
        Node fight7 = new Node("7", new EnemyField(generateEnemies()), new Coordinates(5, 10));
        Node shop8 = new Node("8", new ShopField(), new Coordinates(2, 8));
        //Node unknown9 = new Node("9", new UnknownField(new EventField(randomEvent()), new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(5, 8));
        Node treasure9 = new Node("9", new TreasureField(), new Coordinates(5, 8));
        Node event10 = new Node("10", new EventField(randomEvent()), new Coordinates(1, 6));
        Node fight11 = new Node("11", new EnemyField(generateEnemies()), new Coordinates(4, 6));
        Node fight12 = new Node("12", new EnemyField(generateEnemies()), new Coordinates(6, 6));
        Node unknown13 = new Node("13", new UnknownField(new EventField(randomEvent()), new EnemyField(generateEnemies()), new EliteField(createElitesEnemies()), new ShopField()), new Coordinates(4, 4));
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
                // 1 - SlimeBoss
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


    public List<Enemy> createElitesEnemies() {
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

    /**
     * Führt die spezifizierte Aktion auf dem aktuellen Feld des Spielers aus.
     * Ruft die Methode `doFieldThing()` für das Feld auf, auf dem sich der Spieler befindet.
     */
    @Override
    public void doFieldThing(){
        Node currentNode = getPlayerNode();
        currentNode.doFieldThing(currentNode.getPlayer());
    }

    private Event randomEvent() {
        Random randi = new Random();
        ActOneEventEnum[] events = ActOneEventEnum.values();
        ActOneEventEnum event = events[randi.nextInt(events.length)];
        switch (event) {
            case BigFish:
                return new BigFishEvent(this.player);
            case BonfireSpirits:
                return new BonfireSpiritsEvent(this.player);
            case DeadAdventurer:
                return new DeadAdventurerEvent(this.player);
            case Duplicator:
                return new DuplicatorEvent(this.player);
            case NoteForYourself:
                return new NoteForYourselfEvent(this.player);
            case ScrapOoze:
                return new ScrapOozeEvent(this.player);
            case TheCleric:
                return new TheClericEvent(this.player);
            case TheSsssserpent:
                return new TheSssssserpentEvent(this.player);
            case WorldofGoo:
                return new WorldOfGooEvent(this.player);
            default:
                return new GoldenShrineEvent(this.player);
        }
    }

    @Override
    public String[][] getRawMap(){
        String[][] rawMap= {
                {"  ", "  ", "  ", "  ", "", "  ", "  "},
                {"  ", "  ", "  ", "  ", "| ", "  ", "  "},
                {"  ", "┌ ", "──", "──", "", "  ", "  "},
                {"  ", "| ", "  ", "  ", "| ", "──", "┐ "},
                {"  ", "| ", "  ", "  ", "", "  ", ""},
                {"  ", "| ", "  ", "  ", "| ", "  ", "| "},
                {"┌ ", "", " ┐", "  ", "", "  ", ""},
                {"| ", "  ", "| ", "  ", "└─", "┬─", "┘ "},
                {"| ", "  ", "", "  ", "  ", "", "  "},
                {"| ", "  ", "| ", "  ", "  ", "| ", "  "},
                {"", "  ", "", "  ", "  ", "", "  "},
                {"└─", "┬─", "┘ ", "  ", "  ", "| ", "  "},
                {"  ", "", "  ", "  ", "  ", "", "  "},
                {"  ", "└─", "──", "┬─", "──", "┘ ", " "},
                {"  ", "  ", "  ", "", "  ", "  ", "  "},
                {"  ", "  ", "  ", "| ", "  ", "  ", "  "},
                {"  ", "  ", "  ", "", "  ", "  ", "  "}, // 16/3
        };

        return rawMap;
    }
}
