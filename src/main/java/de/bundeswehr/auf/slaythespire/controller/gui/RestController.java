package de.bundeswehr.auf.slaythespire.controller.gui;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.ConsoleAssistent;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.gui.RestView;
import de.bundeswehr.auf.slaythespire.gui.layouts.layout_events.RestViewEvents;

/**
 * Die Klasse RestController steuert den Rastvorgang im Spiel und verwaltet die Anzeige der Rast-Ansicht.
 * Sie ermöglicht dem Spieler, sich zu heilen und kehrt zur Kartenansicht zurück.
 *
 * @author Vladislav Keil
 */
public class RestController implements RestViewEvents {
    private Player player;
    private RestView restView;
    private boolean healed = false;

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
     * Startet die Rast und initialisiert die Rast-Ansicht.
     */
    private void startRest() {
        this.healed = false;
        this.restView = new RestView(this);
        this.restView.initRestViewEvents(this);
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
            ConsoleAssistent.println(Color.YELLOW, "Healed!!");
            this.healed = true;
        }
        onBackClicked();
    }

    /**
     * Event-Handler für den Zurück-Klick.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        ConsoleAssistent.println(Color.YELLOW, "Back wurde im RestViewController angeklickt");
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Gibt die Rest-Ansicht zurück.
     *
     * @return Die RestView-Instanz.
     */
    public RestView getRestView() {
        return restView;
    }
}
