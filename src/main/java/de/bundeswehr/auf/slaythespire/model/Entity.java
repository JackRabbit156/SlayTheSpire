package de.bundeswehr.auf.slaythespire.model;

import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {

    private int block;
    private int currentHealth;
    private final Map<Effect, Integer> effects = new HashMap<>();
    private String imagePath;
    private int maxHealth;
    private final String name;

    public Entity(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
    }

    public void addEffect(Effect effect, int value) {
        if (effect.getStackingBehaviour() == StackingBehaviour.NON_STACKING) {
            effects.put(effect, 1);
        }
        else {
            effects.put(effect, effects.getOrDefault(effect, 0) + value);
        }
        notifyEffect(effect, value);
    }

    public void dealDamage(GameContext gameContext, int damage, Entity target, Object action) {
        gameContext.setAttackContext(new AttackContext(this, target, damage, action));
        triggerEffect(EffectTrigger.BEFORE_ATTACK_SOURCE, gameContext, this);
        target.takeDamage(gameContext);
    }

    /**
     * Erhöht den Blockwert des Spielers um einen bestimmten Betrag.
     *
     * @param block Der Betrag, um den der Blockwert erhöht werden soll.
     */
    public void gainBlock(int block) {
        this.block += block;
        notifyBlockReceived(block);
    }

    public int getBlock() {
        return block;
    }

    public void looseMaxHp(int hp) {
        maxHealth -= hp;
        notifyMaxHealthChanged(-hp);
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getEffectCounter(Effect effect) {
        return effects.getOrDefault(effect, 0);
    }

    public Map<Effect, Integer> getEffects() {
        return effects;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return name;
    }

    /**
     * Erhöht die aktuelle Gesundheit des Spielers um einen bestimmten Punktwert.
     *
     * @param hp Der Punktwert, um den die Gesundheit erhöht werden soll.
     */
    public void heal(int hp) {
        int oldHealth = currentHealth;
        currentHealth += hp;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        notifyHealthReceived(currentHealth - oldHealth);
    }

    /**
     * Erhöht die maximale Gesundheit des Spielers um einen bestimmten Punktwert.
     *
     * @param hp Der Punktwert, um den die maximale Gesundheit erhöht werden soll.
     */
    public void gainMaxHp(int hp) {
        maxHealth += hp;
        notifyMaxHealthChanged(hp);
    }

    /**
     * Überprüft, ob der Spieler lebt.
     *
     * @return true, wenn die aktuelle Gesundheit größer als 0 ist, andernfalls false.
     */
    public boolean isAlive() {
        return currentHealth > 0;
    }

    /**
     * Entity loses health. Ignores Block.
     */
    public void looseHp(GameContext gameContext) {
        takeDamage(gameContext, true);
    }

    public void reduceDurationEffects() {
        for (Map.Entry<Effect, Integer> entry : effects.entrySet()) {
            if (entry.getKey().getStackingBehaviour() == StackingBehaviour.DURATION) {
                entry.setValue(entry.getValue() - 1);
            }
        }
        effects.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

    public void reduceEffectCounter(Effect effect, int value) {
        effects.put(effect, effects.get(effect) - value);
        notifyEffect(effect, -value);
    }

    /**
     * Setzt den Blockwert des Spielers auf 0 zurück.
     */
    public void resetBlock() {
        block = 0;
    }

    /**
     * Verringert die aktuelle Gesundheit der Entität um den angegebenen Schaden.
     *
     * @param gameContext im {@link de.bundeswehr.auf.slaythespire.model.battle.AttackContext} des {@link GameContext} findet sich der zugefügte Schaden.
     */
    public void takeDamage(GameContext gameContext) {
        takeDamage(gameContext, false);
    }

    /**
     * Benachrichtigt den Listener über den empfangenen Blockwert.
     *
     * @param blockAmount Der Betrag des Blocks, der empfangen wurde.
     */
    protected abstract void notifyBlockReceived(int blockAmount);

    /**
     * Benachrichtigt den Listener über den ausgeteilten Schaden.
     *
     * @param gameContext im {@link de.bundeswehr.auf.slaythespire.model.battle.AttackContext} des {@link GameContext} findet sich der zugefügte Schaden.
     */
    protected abstract void notifyDamageDealt(GameContext gameContext);

    /**
     * Benachrichtigt den Listener über den erlittenen Schaden.
     *
     * @param gameContext im {@link de.bundeswehr.auf.slaythespire.model.battle.AttackContext} des {@link GameContext} findet sich der zugefügte Schaden.
     */
    protected abstract void notifyDamageReceived(GameContext gameContext);

    protected abstract void notifyEffect(Effect effect, int value);

    /**
     * Benachrichtigt den Listener über die empfangene Lebenskraft.
     *
     * @param hpAmount Der Betrag der Lebenskraft, die empfangen wurde.
     */
    protected abstract void notifyHealthReceived(int hpAmount);

    protected abstract void notifyMaxHealthChanged(int hpAmount);

    private void takeDamage(GameContext gameContext, boolean ignoreBlock) {
        triggerEffect(EffectTrigger.BEFORE_ATTACK_TARGET, gameContext, this);
        int oldHealth = currentHealth;
        int damage = gameContext.getAttackContext().getDamage();
        if (!ignoreBlock) {
            if (block - damage >= 0) {
                block -= damage;
                damage = 0;
            }
            else {
                damage = Math.abs(block - damage);
                block = 0;
            }
        }
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        gameContext.getAttackContext().setDamage(oldHealth - currentHealth);
        notifyDamageReceived(gameContext);
        triggerEffect(EffectTrigger.AFTER_ATTACK, gameContext, this);
    }

    private void triggerEffect(EffectTrigger trigger, GameContext gameContext, Entity target) {
        if (!gameContext.isRestricted()) {
            for (Effect effect : effects.keySet()) {
                if (effect.getEffectTrigger() == trigger) {
                    effect.apply(gameContext, target);
                }
            }
        }
    }

}
