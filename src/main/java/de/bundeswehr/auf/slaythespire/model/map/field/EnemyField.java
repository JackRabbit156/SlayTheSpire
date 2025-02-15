package de.bundeswehr.auf.slaythespire.model.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert ein spezielles Feld im Spiel, in dem der Spieler gegen Gegner kämpfen kann.
 * Das Feld enthält eine Liste von Feinden und ermöglicht Kämpfe sowie Loot-Optionen nach dem Sieg.
 *
 * @author Warawa Alexander
 */
public class EnemyField extends Field{
    private static final String imagePath = "/images/map/field_types/EnemyField.png";

    private List<Enemy> enemies = new ArrayList<>();
    public EnemyField(){
        super(imagePath);
    }

    /**
     * Konstruktor für die EnemyField-Klasse. Setzt das Feld-Symbol auf "👹"
     * und initialisiert die Liste der Feinde mit den angegebenen Feinden.
     *
     * @param enemies Eine Liste von Feinden, die diesem Feld hinzugefügt werden.
     */
    public EnemyField(List<Enemy> enemies){
        super(imagePath);
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
        GuiHelper.Scenes.startBattleScene(player, enemies, FieldEnum.ENEMY_FIELD);
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
