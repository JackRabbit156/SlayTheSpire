package models.map_elements.acts;


import models.enemy.Enemy;
import models.enemy.act_four.Shapes;
import models.enemy.act_four.boss.CorruptHeart;
import models.enemy.act_four.elites.SpireShield;
import models.enemy.act_four.elites.SpireSpear;
import models.enemy.act_one.AcidSlime;
import models.enemy.act_one.Cultist;
import models.enemy.act_one.MadGremlin;
import models.enemy.act_one.bosses.SlimeBoss;
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
    Random randi = new Random();

    /**
     * Konstruktor für die Klasse ActFour.
     * Initialisiert den Akt und platziert den Spieler auf dem Startfeld.
     *
     * @param player der Spieler, der sich im Akt bewegen soll
     */
    public ActFour(Player player){
        initNodes(player);
    }
    private void initNodes(Player player){
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

        // TODO: Marked for Save Func.
        rest51.setPlayer(player);
    }

    private List<Enemy> createBossEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        int randAmountEnemies = randi.nextInt(4);
        enemies.add(new CorruptHeart());

        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(new Shapes());
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
                enemies.add(new SpireShield());
                break;
            default:
                // 2 - SpireSpear
                enemies.add(new SpireSpear());
                break;
        }
        for (int i = 0; i < randAmountEnemies; i++) {
            enemies.add(new Shapes());
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
        currentNode.doFieldThing();
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
