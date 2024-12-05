package de.bundeswehr.auf.slaythespire.model.load_save;

/**
 * Die Klasse 'SaveFilePreview' repräsentiert eine Vorschau auf eine
 * gespeicherte Spielesitzung. Sie enthält Informationen über den
 * Charakter des Spielers, den aktuellen Status des Spiels und
 * die Spielzeit.
 *
 * <p>
 * Diese Klasse bietet Methoden zum Festlegen und Abrufen von Informationen
 * über den Charakter und den Fortschritt im Spiel, die für das Laden
 * von gespeicherten Spielen benötigt werden.
 * </p>
 *
 * @author Warawa Alexander
 */
public class SaveFilePreview {
    private String characterName;
    private String playerType;
    private int field;
    private int currentAct;
    private String lastSession;
    private String timePlayed;

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public void setField(int field) {
        this.field = field;
    }

    public void setCurrentAct(int currentAct) {
        this.currentAct = currentAct;
    }


    public void setLastSession(String lastSession) {
        this.lastSession = lastSession;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getPlayerType() {
        return playerType;
    }

    public int getField() {
        return field;
    }

    public int getCurrentAct() {
        return currentAct;
    }

    public String getLastSession() {
        return lastSession;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    @Override
    public String toString() {
        String returnString = "";

        returnString += playerType + "\n";
        returnString += String.format("\t%-25s %s\n", "Last Session:", lastSession);
        returnString += String.format("\t%-23s %s\n", "Time wasted:", timePlayed);
        returnString += String.format("\t%-25s %s\n", "Current Act:", currentAct);
        returnString += String.format("\t%-25s %s", "Current Field:", field);

        return returnString;
    }
}
