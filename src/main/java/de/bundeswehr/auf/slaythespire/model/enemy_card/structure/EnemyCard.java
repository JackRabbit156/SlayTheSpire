package de.bundeswehr.auf.slaythespire.model.enemy_card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

/**
 * Die Enemy card.
 * Im Prinzip so aufgebaut wie die Card Klasse mit sehr viel raus gelassen, da Enemies nicht so viel Zeug brauchen wie der Player.
 *
 * @author OF Daniel Willig
 */
public abstract class EnemyCard {

    private final String name;
    private final String description;
    private final String iconText;
    private String imagePath;

    /**
     * Constructor Enemy card.
     *
     * @param name        the name
     * @param description the description
     * @param iconText    the icon text
     */
    public EnemyCard(String name, String description, String iconText) {
        this.name = name;
        this.description = description;
        this.iconText = iconText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * die play Methode aus der Card Klasse.
     * dient dazu eine Karte eines gegners zu spielen.
     *
     * @param gameContext der context
     * @param enemy       der enemy
     */
    public abstract void playEnemy(GameContext gameContext, Enemy enemy);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconText() {
        return iconText;
    }

}