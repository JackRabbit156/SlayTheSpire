package de.bundeswehr.auf.slaythespire.model.battle;

import de.bundeswehr.auf.slaythespire.model.Entity;

public class AttackContext {

    private double damage;
    private Object action;
    private final Entity source;
    private final Entity target;

    public AttackContext(Entity source, Entity target, int damage, Object action) {
        this.source = source;
        this.target = target;
        this.damage = damage;
        this.action = action;
    }

    public void addDamage(int modifier) {
        damage += modifier;
    }

    public Object getAction() {
        return action;
    }

    public int getDamage() {
        return (int) Math.max(damage, 0);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Entity getSource() {
        return source;
    }

    public Entity getTarget() {
        return target;
    }

    public void multiplyDamage(double factor) {
        damage *= factor;
    }

}
