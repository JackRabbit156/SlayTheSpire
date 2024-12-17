package de.bundeswehr.auf.slaythespire.controller.listener;

/**
 * Das Interface 'GameMenuListener' definiert eine Sammlung von
 * Rückruffunktionen, die für Aktionen im Spielmenü verwendet werden.
 *
 * <p>
 * Klassen, die dieses Interface implementieren, müssen die
 * entsprechenden Methoden bereitstellen, um auf Benutzereingaben
 * im Spielmenü zu reagieren, wie z.B. das Speichern des Spiels,
 * das Laden eines Spiels, das Zurückkehren zum vorherigen Menü,
 * das Öffnen des Hauptmenüs und das Beenden des Spiels.
 * </p>
 *
 * @author Warawa Alexander
 */
public interface GameMenuListener {

    void onBackClick();

    void onChangeDifficultyClick();

    void onExitClick();

    void onLoadClick();

    void onMainMenuClick();

    void onSaveClick();

}
