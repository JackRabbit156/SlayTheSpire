package models.map_elements.field_types;

import controller.BattleViewController;
import controller.LootViewController;
import models.enemy.act_one.Cultist;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert ein spezielles Feld im Spiel, in dem der Spieler gegen Gegner kämpfen kann.
 * Das Feld enthält eine Liste von Feinden und ermöglicht Kämpfe sowie Loot-Optionen nach dem Sieg.
 *
 * @author Warawa Alexander
 */
public class EnemyField extends Field{

    private List<Enemy> enemies = new ArrayList<>();
    private LootViewController lootViewController;
    public EnemyField(){
        super("👹");
    }

    /**
     * Konstruktor für die EnemyField-Klasse. Setzt das Feld-Symbol auf "👹"
     * und initialisiert die Liste der Feinde mit den angegebenen Feinden.
     *
     * @param enemies Eine Liste von Feinden, die diesem Feld hinzugefügt werden.
     */
    public EnemyField(List<Enemy> enemies){
        super("👹");
        this.enemies = enemies;
    }

    /**
     * Führt die spezifische Aktion aus, die für das Feld vorgesehen ist, wenn ein Spieler es betritt.
     * Der Spieler kämpft gegen die Feinde auf dem Feld.
     *
     * @param player Der Spieler, der das Feld betritt und gegen die Feinde kämpft.
     */
    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        BattleViewController battle = new BattleViewController(player, enemies);
        battle.startBattle();

        if(!player.isAlive())
            return;

        setFieldBeaten();

        lootViewController = new LootViewController(player, "EnemyField");
        lootViewController.openLootView(player);

    }

    /**
     * Fügt einen neuen Feind zur Liste der Feinde auf diesem Feld hinzu.
     *
     * @param enemy Der zu hinzuzufügende Feind.
     */
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }
}
