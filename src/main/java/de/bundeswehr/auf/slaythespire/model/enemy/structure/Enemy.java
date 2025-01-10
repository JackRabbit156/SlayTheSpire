package de.bundeswehr.auf.slaythespire.model.enemy.structure;

import de.bundeswehr.auf.slaythespire.controller.listener.EnemyEventListener;
import de.bundeswehr.auf.slaythespire.events.EffectEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyBanterEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyBlockEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.enemy_card.InsultEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Diese abstrakte Klasse repräsentiert einen allgemeinen Gegner im Spiel.
 * Sie enthält gemeinsame Eigenschaften und Methoden, die für alle spezifischen Gegner gelten.
 *
 * @author Warawa Alexander
 */
public abstract class Enemy extends Entity {

    private static final Random rnd = new Random();

    private int enemyCardToBePlayed;
    private List<EnemyCard> enemyDeck;
    private final List<EnemyEventListener> enemyEventListeners = new ArrayList<>();
    private final EnemyCard insult = new InsultEnemyCard();
    private EnemyCard intent;
    private final List<String> wittyBanterList = new ArrayList<>();

    /**
     * Generiert einen maximalen Gesundheitswert für den Gegner
     * innerhalb des angegebenen Bereichs.
     *
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     * @return Der generierte maximale Gesundheitswert.
     */
    private static int generateMaxHealth(int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        int difference = highestMaxHealthPossible - lowestMaxHealthPossible;
        int hp = rnd.nextInt(difference + 1);
        return lowestMaxHealthPossible + hp;
    }

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     *
     * @param name                     Der Name des Gegners.
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     */
    public Enemy(String name, int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        super(name, GameSettings.getDifficultyLevel().modifyHealth(generateMaxHealth(lowestMaxHealthPossible, highestMaxHealthPossible)));
        try (Scanner fileScanner = new Scanner(Enemy.class.getResourceAsStream("/wittybanter.txt"))) {
            while (fileScanner.hasNext()) {
                wittyBanterList.add(fileScanner.nextLine());
            }
        }
    }

    /**
     * Eine Action ist entweder ein ganz normaler angriff oder eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     *
     * @param gameContext der gameContext für den angriff
     * @author OF Daniel Willig
     */
    public void action(GameContext gameContext) {
        if (getIntent().equals(insult)) {
            String banter = doNothing();
            notifyBanter(banter);
            LoggingAssistant.log(getName() + ": \"" + banter + "\"", Color.ITALIC, Color.CYAN);
        }
        else {
            LoggingAssistant.log(getName() + " attacking", Color.ITALIC, Color.BLUE);
            attack(gameContext);
        }
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
//        triggerEffect(EffectTrigger.BEFORE_ATTACK, gameContext);
        getEnemyDeck().get(enemyCardToBePlayed).playEnemy(gameContext, this);
//        triggerEffect(EffectTrigger.AFTER_ATTACK, gameContext);
    }

    /**
     * berechnet die Aktion bevor sie ausgeführt wird. Wie bei diesem Film indem die Polizei Bösewichte fangen bevor sie Straftaten begehen.
     *
     * @author OF Daniel Willig
     */
    public void calculateIntent() {
        DifficultyLevel difficulty = GameSettings.getDifficultyLevel();
        int randomNumber = (rnd.nextInt(100) + 1);
        if (difficulty.getAttackPercentage() >= randomNumber) {
            setEnemyCardToBePlayed(rnd.nextInt(getEnemyDeck().size()));
            setIntent(getEnemyDeck().get(enemyCardToBePlayed));
        }
        else {
            setIntent(insult);
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

    public void resetListeners() {
        enemyEventListeners.clear();
    }

    public void setEnemyCardToBePlayed(int enemyCardToBePlayed) {
        this.enemyCardToBePlayed = enemyCardToBePlayed;
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
    protected void notifyMaxHealthChanged(int hpAmount) {}

    /**
     * eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     *
     * @return call of the enemy
     * @author OF Daniel Willig
     */
    private String doNothing() {
        return wittyBanterList.get(rnd.nextInt(wittyBanterList.size()));
    }

    private void notifyBanter(String banter) {
        EnemyBanterEvent event = new EnemyBanterEvent(this, banter);
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onBanter(event);
        }
    }

}
