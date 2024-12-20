package de.bundeswehr.auf.slaythespire.model.load_save;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;

import java.time.LocalDateTime;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;

/**
 * Diese Klasse verwaltet die Speicherung und das Laden von Spielständen.
 * Sie bietet Methoden zum Speichern, Löschen und Auflisten von Spielständen.
 *
 * @author Warawa Alexander
 */
public class GameSaveManager {

    private static final String SAVE_FOLDER = "saves";

    public GameSaveManager() {
        createSaveFolder();
    }

    /**
     * Speichert den aktuellen Spielstand für den angegebenen Spieler.
     *
     * @param player Der Spieler, dessen Spielstand gespeichert werden soll.
     */
    public void saveGame(Player player) {
        if(!GameSettings.lastSession.isEmpty()) {
            deleteSelectedSaveFile(GameSettings.lastSession);
        }

        Map<String, String> gameData = collectGameData(player);
        //String fileName = getTimestampedFileName();
        String fileName = "save_" + gameData.get("lastSession") + ".txt";
        saveDataToFile(gameData, new File(SAVE_FOLDER, fileName));
    }

    /**
     * Listet alle vorhandenen Speicherdateien auf und gibt eine Vorschau zurück.
     *
     * @return Eine Liste von Speicherdatei-Vorschauen.
     */
    public List<SaveFilePreview> listSaveFiles() {
        List<SaveFilePreview> savePreview = new ArrayList<>();
        File[] saveFiles = getSaveFiles();

        if (saveFiles != null) {
            for (File file : saveFiles) {
                savePreview.add(createFilePreview(file.getName()));
            }
        }

        return savePreview;
    }

    /**
     * Löscht die angegebene Speicherdatei basierend auf dem Speicher-Sitzungsnamen.
     *
     * @param session Der Name der Sitzung, deren Speicherdatei gelöscht werden soll.
     */
    public void deleteSelectedSaveFile(String session){
        File folder = new File(SAVE_FOLDER);
        File[] saveFiles = folder.listFiles((dir, name) -> name.startsWith("save_") && name.endsWith(".txt"));

        for (File saveFile : saveFiles) {
            if (saveFile.getName().equals("save_" + session + ".txt")) {
                saveFile.delete();
                return;
            }
        }
    }

    /**
     * Löscht die Speicherdatei, die der angegebenen ID entspricht.
     *
     * @param id Die ID der Speicherdatei, die gelöscht werden soll.
     */
    public void deleteSelectedSaveFile(int id){
        File folder = new File(SAVE_FOLDER);
        File[] saveFiles = folder.listFiles((dir, name) -> name.startsWith("save_") && name.endsWith(".txt"));

        // Überprüfen, ob die Datei existiert und die ID gültig ist
        if (saveFiles == null || id < 0 || id >= saveFiles.length) {
            LoggingAssistant.log("ID does not exist.", Color.RED);
            return;
        }

        // Die Datei mit der entsprechenden ID auswählen
        File fileToDelete = saveFiles[id];

        // Datei löschen und Ergebnis ausgeben
        if (fileToDelete.delete()) {
            LoggingAssistant.log("Save file " + fileToDelete.getName() + " successfully deleted!.");
        } else {
            LoggingAssistant.log("Error, could not delete file: " + fileToDelete.getName() + ".", Color.RED);
        }
    }

    /**
     * Lädt den Spielstand, der der angegebenen ID entspricht.
     *
     * @param id Die ID des Spielstandes, der geladen werden soll.
     * @return Eine Map mit den geladenen Spieldaten.
     */
    public Map<String, String> loadGame(int id) {
        File[] saveFiles = getSaveFiles();
        if (saveFiles == null || id >= saveFiles.length) {
            LoggingAssistant.log("Could not find the File.", Color.RED);
            return null;
        }
        return loadDataFromFile(saveFiles[id]);
    }

    private void createSaveFolder() {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Sammelt alle relevanten Spieldaten in einer Map für den angegebenen Spieler.
     *
     * @param player Der Spieler, dessen Daten gesammelt werden sollen.
     * @return Eine Map mit den gesammelten Spieldaten.
     */
    private Map<String, String> collectGameData(Player player) {
        Map<String, String> gameData = new HashMap<>();
        writePlayer(player, gameData);
        writeDeck(player, gameData);
        writePotions(player, gameData);
        writeRelic(player, gameData);
        writeGameSettings(gameData);
        writeStatistics(gameData);
        writeTimePlayed(gameData);
        return gameData;
    }

    private void writePlayer(Player player, Map<String, String> gameData) {
        gameData.put("character", player.getPlayerType().name());
        gameData.put("field", player.getCurrentField());
        gameData.put("currentAct", String.valueOf(player.getCurrentAct()));
        gameData.put("currentHealth", String.valueOf(player.getCurrentHealth()));
        gameData.put("maxHealth", String.valueOf(player.getMaxHealth()));
        gameData.put("gold", String.valueOf(player.getGold()));
    }

    private void writeTimePlayed(Map<String, String> gameData) {
        int seconds = GameSettings.getTimerSeconds();
        int minutes = GameSettings.getTimerMinutes();
        int hours = GameSettings.getTimerHours();
        gameData.put("timePlayed", hours +"h "+ minutes +"m "+ seconds +"s");
        gameData.put("seconds", String.valueOf(seconds));
        gameData.put("minutes", String.valueOf(minutes));
        gameData.put("hours", String.valueOf(hours));
    }

    private void writeGameSettings(Map<String, String> gameData) {
        String currentTimeStamp = getCurrentTimestamp();
        gameData.put("lastSession", currentTimeStamp);
        GameSettings.lastSession = currentTimeStamp;
        gameData.put("difficulty", GameSettings.getDifficultyLevel().name());
    }

    private void writeDeck(Player player, Map<String, String> gameData) {
        List<Card> deck = player.getDeck();
        for (int i = 0; i < deck.size(); i++) {
            Card card = deck.get(i);
            gameData.put("card" + i, card.getClass().getSimpleName());
        }
    }

    private void writeRelic(Player player, Map<String, String> gameData) {
        List<Relic> relics = player.getRelics();
        for (int i = 0; i < relics.size(); i++) {
            Relic relic = relics.get(i);
            gameData.put("relic" + i, relic.getClass().getSimpleName());
        }
    }

    private void writePotions(Player player, Map<String, String> gameData) {
        List<Potion> potions = player.getPotions();
        for (int i = 0; i < potions.size(); i++) {
            Potion potion = potions.get(i);
            gameData.put("potion" + i, potion.getClass().getSimpleName());
        }
    }

    private void writeStatistics(Map<String, String> gameData) {
        gameData.put("receivedGoldStats", GameSettings.getReceivedGoldStats()+"");
        gameData.put("receivedDamageStats", GameSettings.getReceivedDamageStats()+"");
        gameData.put("distributedDamageStats", GameSettings.getDistributedDamageStats()+"");
        gameData.put("energySpentStats", GameSettings.getEnergySpentStats()+"");
    }

    private void saveDataToFile(Map<String, String> data, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            LoggingAssistant.log("Fehler beim Speichern des Spiels: " + e.getMessage(), Color.RED);
        }
    }

    private Map<String, String> loadDataFromFile(File file) {
        Map<String, String> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2)
                    data.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            LoggingAssistant.log("Error while loading the game: " + e.getMessage(), Color.RED);
        }
        return data;
    }

    private File[] getSaveFiles() {
        File folder = new File(SAVE_FOLDER);
        return folder.listFiles((dir, name) -> name.startsWith("save_") && name.endsWith(".txt"));
    }

    private String getTimestampedFileName() {
        return "save_" + getCurrentTimestamp() + ".txt";
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
    }

    /**
     * Erstellt eine Vorschau für eine Speicherdatei basierend auf dem Dateinamen.
     *
     * @param fileName Der Name der Speicherdatei.
     * @return Eine Vorschau des Speicherdateiformats.
     */
    private SaveFilePreview createFilePreview(String fileName) {
        SaveFilePreview preview = new SaveFilePreview();
        Map<String, String> data = loadDataFromFile(new File(SAVE_FOLDER, fileName));

        preview.setPlayerType(data.get("character"));
        preview.setField(Integer.parseInt(data.get("field")));
        preview.setCurrentAct(Integer.parseInt(data.get("currentAct")));
        preview.setTimePlayed(data.get("timePlayed"));
        preview.setLastSession(data.get("lastSession"));

        return preview;
    }
}