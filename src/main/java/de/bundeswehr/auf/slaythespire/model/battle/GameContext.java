package de.bundeswehr.auf.slaythespire.model.battle;

import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.ArrayList;
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

    private AttackContext attackContext;
    private final BattleDeck battleDeck;
    private final List<Enemy> enemies;
    private final Player player;
    private Enemy selectedEnemy;

    public GameContext(Player player, AttackContext attackContext) {
        this.player = player;
        this.attackContext = attackContext;
        enemies = new ArrayList<>();
        battleDeck = null;
    }

    /**
     * Konstruktor für die GameContext-Klasse.
     *
     * @param player     Der Spieler im aktuellen Spielkontext.
     * @param enemies    Die Liste der Gegner.
     * @param battleDeck Der Kampfstapel des Spielers.
     */
    public GameContext(Player player, List<Enemy> enemies, BattleDeck battleDeck) {
        this.player = player;
        this.enemies = enemies;
        this.battleDeck = battleDeck;
    }

    public AttackContext getAttackContext() {
        return attackContext;
    }

    public void setAttackContext(AttackContext attackContext) {
        this.attackContext = attackContext;
    }

    public BattleDeck getBattleDeck() {
        return battleDeck;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getSelectedEnemy() {
        if (selectedEnemy == null) {
            setRandomEnemy();
        }
        return selectedEnemy;
    }

    public void setSelectedEnemy(Enemy selectedEnemy) {
        this.selectedEnemy = selectedEnemy;
    }

    public boolean isRestricted() {
        return battleDeck == null;
    }

    public void setRandomEnemy() {
        do {
            selectedEnemy = enemies.get(rnd.nextInt(enemies.size()));
        } while (!selectedEnemy.isAlive());
    }

}