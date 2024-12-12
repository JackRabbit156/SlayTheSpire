package de.bundeswehr.auf.slaythespire.model.battle;

public class Playable {

    private String errorMessage;
    private String logMessage;
    private boolean playable;

    public Playable() {
        this.playable = true;
    }

    public Playable(String logMessage, String errorMessage) {
        this.logMessage = logMessage;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public boolean isPlayable() {
        return playable;
    }

}
