package de.bundeswehr.auf.slaythespire.models.battle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

public class BattlePlayer {
    private Player player;
    private int maxHealth;
    private IntegerProperty currentHealth;

    public BattlePlayer(Player player) {
        this.player = player;

        this.maxHealth = player.getMaxHealth();
        this.currentHealth = new SimpleIntegerProperty(player.getCurrentHealth());


    }
}
