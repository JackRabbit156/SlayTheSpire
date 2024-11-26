package controller.listener;

/**
 * Das Interface 'DifficultyMenuListener' definiert eine Sammlung von
 * Rückruffunktionen, die für Aktionen im Difficultymenu verwendet werden.
 *
 * <p>
 * Klassen, die dieses Interface implementieren, müssen die
 * entsprechenden Methoden bereitstellen, um auf Benutzereingaben
 * im Spielmenü zu reagieren.
 * </p>
 *
 * @author Warawa Alexander
 */
public interface DifficultyMenuListener {
    void onNormalClick();
    void onHardcoreClick();
    void onDifficultyBackClick();

}
