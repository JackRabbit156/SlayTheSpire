package controller.listener;

/**
 * Das Interface 'LoadEventListener' definiert Rückruffunktionen,
 * die auf Ereignisse im Ladebildschirm reagieren.
 *
 * <p>
 * Klassen, die dieses Interface implementieren, müssen die
 * entsprechenden Methoden bereitstellen, um auf Benutzerinteraktionen
 * zu reagieren, insbesondere wenn ein Element aus einer
 * Liste von speicherbaren Inhalten ausgewählt wird,
 * oder wenn der Benutzer die Option hat, zum vorherigen Menü zurückzukehren.
 * </p>
 *
 * @author Warawa Alexander
 */
public interface LoadEventListener {
    void onSelectedItem(int id);
    void onBackButtonClick();
}
