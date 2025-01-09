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
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;
import de.bundeswehr.auf.slaythespire.model.enemy_card.InsultEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;

import java.util.*;

/**
 * Diese abstrakte Klasse repräsentiert einen allgemeinen Gegner im Spiel.
 * Sie enthält gemeinsame Eigenschaften und Methoden, die für alle spezifischen Gegner gelten.
 *
 * @author Warawa Alexander
 */
public abstract class Enemy implements Entity {

    private static final Random rnd = new Random();

    private int block;
    private int currentHealth;
    private double damageFactor;
    private int damageModifier;
    private final Map<Effect, Integer> effects = new HashMap<>();
    private int enemyCardToBePlayed;
    private List<EnemyCard> enemyDeck;
    private final List<EnemyEventListener> enemyEventListeners = new ArrayList<>();
    private String imagePath;
    private final EnemyCard insult = new InsultEnemyCard();
    private EnemyCard intent;
    private final int maxHealth;
    private final String name;
    private final List<String> wittyBanterList = new ArrayList<>();

    /**
     * Konstruktor für die Enemy-Klasse.
     * Initialisiert einen Gegner mit einem Namen und einem maximalen Gesundheitsbereich.
     *
     * @param name                     Der Name des Gegners.
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     */
    public Enemy(String name, int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        this.name = name;
        maxHealth = GameSettings.getDifficultyLevel().getHealth(generateMaxHealth(lowestMaxHealthPossible, highestMaxHealthPossible));
        currentHealth = maxHealth;
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

    public void addBlock(int block) {
        this.block += block;
        notifyBlockReceived(block);
    }

    @Override
    public void addDamageFactor(double factor) {
        this.damageFactor *= factor;
    }

    @Override
    public void addDamageModifier(int modifier) {
        this.damageModifier += modifier;
    }

    public void addEffect(Effect effect, int value) {
        notifyEffect(effect, value);
        effects.put(effect, effects.getOrDefault(effect, 0) + value);
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

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    @Override
    public int getEffectCounter(Effect effect) {
        return effects.getOrDefault(effect, 0);
    }

    @Override
    public Map<Effect, Integer> getEffects() {
        return effects;
    }

    public List<EnemyCard> getEnemyDeck() {
        return enemyDeck;
    }

    public void setEnemyDeck(List<EnemyCard> enemyDeck) {
        this.enemyDeck = enemyDeck;
    }

    public int getHealth() {
        return currentHealth;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public EnemyCard getIntent() {
        return intent;
    }

    public void setIntent(EnemyCard intent) {
        this.intent = intent;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void reduceDurationEffects() {
        for (Map.Entry<Effect, Integer> entry : effects.entrySet()) {
            if (entry.getKey().getStackingBehaviour() == StackingBehaviour.DURATION) {
                entry.setValue(entry.getValue() - 1);
            }
        }
        effects.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

    public void resetListeners() {
        enemyEventListeners.clear();
    }

    public void setDamageFactor(double damageFactor) {
        this.damageFactor = damageFactor;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public void setEnemyCardToBePlayed(int enemyCardToBePlayed) {
        this.enemyCardToBePlayed = enemyCardToBePlayed;
    }

    /**
     * Fügt dem Gegner Schaden zu. Der Schaden wird abhängig von
     * dem Blockwert des Gegners berücksichtigt.
     *
     * @param damage Der zuzu fügende Schaden.
     */
    public void takeDamage(int damage, GameContext gameContext) {
        triggerEffect(EffectTrigger.BEFORE_ATTACK_SOURCE, gameContext, gameContext.getPlayer());
        triggerEffect(EffectTrigger.BEFORE_ATTACK_TARGET, gameContext, this);
        System.out.println("Enemy.takeDamage damageModifier=" + damageModifier + ", factor=" + damageFactor);
        int damageAfterEffects = (int) ((damage + damageModifier) * damageFactor);
        int oldHealth = currentHealth;
        int damageAfterBlock;
        if (block - damageAfterEffects >= 0) {
            block -= damageAfterEffects;
            damageAfterBlock = 0;
        }
        else {
            damageAfterBlock = Math.abs(block - damageAfterEffects);
            block = 0;
        }
        currentHealth -= damageAfterBlock;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        GameSettings.increaseDistributedDamageStats(oldHealth - currentHealth);
        notifyDamageReceived(oldHealth - currentHealth);
        triggerEffect(EffectTrigger.AFTER_ATTACK, gameContext, this);
    }

    @Override
    public String toString() {
        return "Enemy{" +
                ", name='" + name + '\'' +
                ", health=" + currentHealth + "/" + maxHealth +
                ", block=" + block +
                ", enemyCardToBePlayed=" + enemyCardToBePlayed +
                ", enemyDeck=" + enemyDeck +
                ", insult=" + insult +
                ", intent=" + intent +
                '}';
    }

    protected void triggerEffect(EffectTrigger trigger, GameContext gameContext, Entity target) {
        for (Effect effect : effects.keySet()) {
            if (effect.getEffectTrigger() == trigger) {
                effect.apply(gameContext, target);
            }
        }
    }

    /**
     * eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     *
     * @return call of the enemy
     * @author OF Daniel Willig
     */
    private String doNothing() {
        return wittyBanterList.get(rnd.nextInt(wittyBanterList.size()));
    }

    /**
     * Generiert einen maximalen Gesundheitswert für den Gegner
     * innerhalb des angegebenen Bereichs.
     *
     * @param lowestMaxHealthPossible  Der niedrigste mögliche Maximalwert für die Gesundheit.
     * @param highestMaxHealthPossible Der höchste mögliche Maximalwert für die Gesundheit.
     * @return Der generierte maximale Gesundheitswert.
     */
    private int generateMaxHealth(int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        int difference = highestMaxHealthPossible - lowestMaxHealthPossible;

        Random randi = new Random();

        int hp = randi.nextInt(difference + 1);

        return lowestMaxHealthPossible + hp;
    }

    private void notifyBanter(String banter) {
        EnemyBanterEvent event = new EnemyBanterEvent(this, banter);
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onBanter(event);
        }
    }

    private void notifyBlockReceived(int blockAmount) {
        EnemyBlockEvent event = new EnemyBlockEvent(this, blockAmount);
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onBlockReceived(event);
        }
    }

    private void notifyDamageReceived(int damageAmount) {
        EnemyDamageEvent event = new EnemyDamageEvent(this, damageAmount);
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

    private void notifyEffect(Effect effect, int value) {
        EffectEvent event = new EffectEvent(this, effect, value);
        for (EnemyEventListener enemyEventListener : enemyEventListeners) {
            enemyEventListener.onEffect(event);
        }
    }

}
