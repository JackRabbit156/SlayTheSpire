package models.battle;

import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Diese Klasse repräsentiert den Kontext eines Spiels,
 * der die Spielerdaten, die Feinde und das Kampfstapel verwaltet.
 * Sie ermöglicht den Zugriff auf alle relevanten Informationen
 * für den aktuellen Spielzustand.
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class GameContext {
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

    public Enemy getSelectedEnemy() {
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