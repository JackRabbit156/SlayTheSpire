package models.map_elements.acts;


import models.enemy.Enemy;
import models.enemy.act_four.SpikerEnemy;
import models.enemy.act_four.boss.CorruptHeartBoss;
import models.enemy.act_four.elites.SpireShieldElite;
import models.enemy.act_four.elites.SpireSpearElite;
import models.map_elements.Coordinates;
import models.map_elements.Node;
import models.map_elements.field_types.*;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Klasse ActFour ist eine konkrete Implementierung des vierten Aktes im Spiel.
 * Sie legt die Struktur des Aktes fest, indem sie die Positionen und Typen der Felder (z. B. Kampf, Shop, Elite) definiert.
 *
 * <p>Diese Klasse initialisiert die Knotenpunkte und deren Verbindungen und erlaubt es dem Spieler,
 * sich innerhalb des Aktes zu bewegen und spezifische Feldaktionen auszuführen.</p>
 *
 * @author Warawa Alexander
 */
public class ActFour extends Act{
    private static final int MAP_WIDTH = 7;
    private static final int MAP_HEIGHT= 16;

    Random randi = new Random();

    /**
     * Konstruktor für die Klasse ActFour.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld, mit der Option einen Spieler auf einem bestimmten Floor zu spawnen.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     * @param playerAlreadyOnAct ob der Spieler von einer Datei geladen wurde.
     */
    public ActFour(Player player, boolean playerAlreadyOnAct){
        super(4, MAP_WIDTH, MAP_HEIGHT);
        initNodes();

        Node playerNode = null;
        if(playerAlreadyOnAct)
            playerNode = getNoteByName(player.getCurrentField());
        else
            playerNode = getNoteByName(getFirstField());

        if(playerNode == null){
            System.out.println("ERROR");
            return;
        }

        playerNode.setPlayer(player);
        if(playerAlreadyOnAct)
            playerNode.setFieldBeaten();
        else {
            playerNode.doFieldThing(player);
            player.setCurrentField(this.getCurrentFieldName());
        }
    }

    private void initNodes(){
        Node rest51 = new Node("51", new RestField(), new Coordinates(0, 6));
        Node shop52 = new Node("52", new ShopField(), new Coordinates(0, 4));
        Node elite53 = new Node("53", new EliteField(createElitesEnemies()), new Coordinates(0, 2));
        Node boss54 = new Node("54", new BossField(createBossEnemies()), new Coordinates(0, 0));

        nodes.add(rest51);
        nodes.add(shop52);
        nodes.add(elite53);
        nodes.add(boss54);

        rest51.setMiddleNode(shop52);
        shop52.setMiddleNode(elite53);
        elite53.setMiddleNode(boss54);
    }

    private List<Enemy> createBossEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        int randAmountEnemies = randi.nextInt(4);
        enemies.add(new CorruptHeartBoss());

        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(new SpikerEnemy());
        }
        return enemies;
    }

    private List<Enemy> createElitesEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        int randElite = randi.nextInt(2);
        int randAmountEnemies = randi.nextInt(2);

        switch (randElite) {
            case 0:
                // 1 - SpireShield
                enemies.add(new SpireShieldElite());
                break;
            default:
                // 2 - SpireSpear
                enemies.add(new SpireSpearElite());
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(new SpikerEnemy());
        }

        return enemies;
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

    @Override
    public String[][] getRawMap(){
        String[][] rawMap= {
                {"  "},
                {"|  "},
                {"  "},
                {"|  "},
                {"  "},
                {"|  "},
                {"  "},

        };

        return rawMap;
    }
}
