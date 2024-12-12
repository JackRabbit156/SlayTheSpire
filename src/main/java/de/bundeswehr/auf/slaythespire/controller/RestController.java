package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.RestView;
import de.bundeswehr.auf.slaythespire.gui.events.RestViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.ConsoleAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Klasse RestController steuert den Rastvorgang im Spiel und verwaltet die Anzeige der Rast-Ansicht.
 * Sie ermöglicht dem Spieler, sich zu heilen und kehrt zur Kartenansicht zurück.
 *
 * @author Vladislav Keil
 */
public class RestController implements Controller, RestViewEvents {

    private boolean healed = false;
    private final Player player;
    private RestView restView;

    /**
     * Konstruktor für die Klasse RestController.
     * Initialisiert den Spieler und startet die Rast.
     *
     * @param player Der Spieler, der die Rast durchführt.
     */
    public RestController(Player player) {
        this.player = player;
        this.startRest();
    }

    /**
     * Gibt die Rest-Ansicht zurück.
     *
     * @return Die RestView-Instanz.
     */
    public RestView getRestView() {
        return restView;
    }

    /**
     * Event-Handler für den Zurück-Klick.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        ConsoleAssistant.log("RestViewController closed");
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Event-Handler für den Heilungs-Klick.
     * Erhöht die aktuelle Gesundheit des Spielers um 30% seiner maximalen Gesundheit.
     */
    @Override
    public void onHealClicked() {
        if (!this.healed) {
            int increasedHp = (int) (player.getMaxHealth() * 0.30);
            player.increaseCurrentHealth(increasedHp);
            ConsoleAssistant.log("Healed by " + increasedHp);
            this.healed = true;
        }
        onBackClicked();
    }

    /**
     * Startet die Rast und initialisiert die Rast-Ansicht.
     */
    private void startRest() {
        this.healed = false;
        this.restView = new RestView(this);
        this.restView.initRestViewEvents(this);
    }
}
