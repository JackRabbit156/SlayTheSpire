package de.bundeswehr.auf.slaythespire.gui.events;

import de.bundeswehr.auf.slaythespire.model.map.Node;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Das Interface 'MapViewEvents' definiert die Ereignisse, die von
 * der MapView-Implementierung behandelt werden. Diese Ereignisse
 * beschreiben die Interaktionen, die der Spieler mit der Spielkarte
 * hat, einschließlich Klicks auf gültige Felder sowie Aktionen
 * für die Einstellungen und den Vollbildmodus.
 *
 * <p>
 * Implementierende Klassen sollten die Methoden bereitstellen, um
 * auf die jeweiligen Ereignisse zu reagieren und entsprechende
 * Aktionen auszuführen.
 * </p>
 *
 * @author Warawa Alexander
 */
public interface MapViewEvents {

    void onFullScreenClick();
    void onValidFieldClick(Player player, Node node);
    void onSettingsClick();

}
