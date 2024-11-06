package models.load_save_game_elements;

import models.player.player_structure.PlayerType;

public class SaveFilePreview {
    private String characterName;
    private String playerType;
    private int field;
    private int currentAct;
    private String lastSession;
    private String timePlayed;
    //private int currentHealth;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

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
}
