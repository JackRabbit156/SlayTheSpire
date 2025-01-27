package de.bundeswehr.auf.slaythespire.model.enemy.structure;

import de.bundeswehr.auf.slaythespire.controller.EnemyController;
import de.bundeswehr.auf.slaythespire.controller.listener.EnemyEventListener;
import de.bundeswehr.auf.slaythespire.events.EffectEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyBanterEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyBlockEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Diese abstrakte Klasse repräsentiert einen allgemeinen Gegner im Spiel.
 * Sie enthält gemeinsame Eigenschaften und Methoden, die für alle spezifischen Gegner gelten.
 *
 * @author Warawa Alexander
 */
public abstract class Enemy extends Entity {

    private static final Random rnd = new Random();

    private List<EnemyCard> enemyDeck;
    private final List<EnemyEventListener> enemyEventListeners = new ArrayList<>();
    private EnemyCard intent;

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     *
     * @param name             Der Name des Gegners.
     * @param lowestMaxHealth  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealth Der höchste mögliche Maximalwert für die Gesundheit.
     */
    public Enemy(String name, int lowestMaxHealth, int highestMaxHealth) {
        super(name, GameSettings.getDifficultyLevel().modifyHealth(EnemyController.generateBetween(lowestMaxHealth, highestMaxHealth)));
    }

    public void addEnemyEventListener(EnemyEventListener enemyEventListener) {
        enemyEventListeners.add(enemyEventListener);
    }

    /**
     * Führt den Angriff des Gegners aus.
     * Diese Methode muss in den spezifischen Unterklassen implementiert werden.
     *
     * @param gameContext Der aktuelle Spielkontext, der weitere Informationen enthält.
     */
    public void attack(GameContext gameContext) {
        EnemyCard card = getIntent();
        card.playEnemy(gameContext, this);
        if (card instanceof AttackEnemyCard) {
            notifyDamageDealt(gameContext);
        }
    }

    public List<EnemyCard> getEnemyDeck() {
        return enemyDeck;
    }

    public void setEnemyDeck(List<EnemyCard> enemyDeck) {
        this.enemyDeck = enemyDeck;
    }

    public EnemyCard getIntent() {
        return intent;
    }

    public void setIntent(EnemyCard intent) {
        this.intent = intent;
    }

    public void notifyBanter(String banter) {
        EnemyBanterEvent event = new EnemyBanterEvent(this, banter);
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onBanter(event);
        }
    }

    public void resetListeners() {
        enemyEventListeners.clear();
    }

    /**
     * Fügt dem Gegner Schaden zu. Der Schaden wird abhängig von
     * dem Blockwert des Gegners berücksichtigt.
     *
     * @param gameContext im {@link de.bundeswehr.auf.slaythespire.model.battle.AttackContext} des {@link GameContext} findet sich der zugefügte Schaden.
     */
    @Override
    public void takeDamage(GameContext gameContext) {
        super.takeDamage(gameContext);
        GameSettings.increaseDistributedDamageStats(gameContext.getAttackContext().getDamage());
    }

    @Override
    protected void notifyBlockReceived(int blockAmount) {
        EnemyBlockEvent event = new EnemyBlockEvent(this, blockAmount);
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onBlockReceived(event);
        }
    }

    @Override
    protected void notifyDamageDealt(GameContext gameContext) {
        EnemyDamageEvent event = new EnemyDamageEvent(this, gameContext.getAttackContext().getDamage());
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onDamageDealt(event);
        }
    }

    @Override
    protected void notifyDamageReceived(GameContext gameContext) {
        EnemyDamageEvent event = new EnemyDamageEvent(this, gameContext.getAttackContext().getDamage());
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onDamageReceived(event);
        }
        if (!isAlive()) {
            for (int i = enemyEventListeners.size() - 1; i >= 0; i--) {
                if (!enemyEventListeners.isEmpty()) {
                    enemyEventListeners.get(i).onEnemyDeath(this);
                }
            }
        }
    }

    @Override
    protected void notifyEffect(Effect effect, int value) {
        EffectEvent event = new EffectEvent(this, effect, value);
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onEffect(event);
        }
    }

    @Override
    protected void notifyHealthReceived(int hpAmount) {
        // TODO gibt es heal bei Enemies?
    }

    @Override
    protected void notifyMaxHealthChanged(int hpAmount) {
    }

}
