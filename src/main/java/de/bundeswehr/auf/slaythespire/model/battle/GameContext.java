package de.bundeswehr.auf.slaythespire.model.battle;

import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Diese Klasse repräsentiert den Kontext eines Spiels,
 * der die Spielerdaten, die Feinde und das Kampfstapel verwaltet.
 * Sie ermöglicht den Zugriff auf alle relevanten Informationen
 * für den aktuellen Spielzustand.
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class GameContext {

    private static final Random rnd = new Random();

    private final Player player;
    private final List<Enemy> enemies;
    private final BattleDeck battleDeck;
    private Enemy selectedEnemy;

    /**
     * Konstruktor für die GameContext-Klasse.
     *
     * @param player Der Spieler im aktuellen Spielkontext.
     * @param enemies Die Liste der Gegner.
     * @param battleDeck Der Kampfstapel des Spielers.
     */
    public GameContext(Player player, List<Enemy> enemies, BattleDeck battleDeck){
        this.player = player;
        this.enemies = enemies;
        this.battleDeck = battleDeck;
    }

    public void setSelectedEnemy(Enemy selectedEnemy){
        this.selectedEnemy = selectedEnemy;
    }

    public void setRandomEnemy(){
        this.selectedEnemy = enemies.get(rnd.nextInt(enemies.size()));
    }

    public Enemy getSelectedEnemy() {
        if (selectedEnemy == null) {
            setRandomEnemy();
        }
        return selectedEnemy;
    }

    public Player getPlayer(){
        return player;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public BattleDeck getBattleDeck() {
        return battleDeck;
    }
}