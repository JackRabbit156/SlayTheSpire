package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class InventoryEvent {

    public enum Direction {
        GAIN, LOSE
    }

    public enum Type {
        CARD, GOLD, LEVEL, POTION, RELIC
    }

    private Direction direction;
    private final Player player;
    private final Type type;
    private final Object value;

    public InventoryEvent(Player player, Direction direction, Type type, Object value) {
        this.player = player;
        this.direction = direction;
        this.type = type;
        this.value = value;
    }

    public Direction getDirection() {
        return direction;
    }

    public Player getPlayer() {
        return player;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

}
