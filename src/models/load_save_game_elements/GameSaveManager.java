package models.load_save_game_elements;

import controller.MapViewController;
import models.cards.DeckFactory;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;

import java.time.LocalDateTime;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class GameSaveManager {
    private static final String SAVE_FOLDER = "saves";

    public GameSaveManager() {
        createSaveFolder();
    }

    public void saveGame(Player player) {
        Map<String, String> gameData = collectGameData(player);
        String fileName = getTimestampedFileName();
        saveDataToFile(gameData, new File(SAVE_FOLDER, fileName));
        System.out.println("Successfully saved the game.");
    }

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

    public void deleteSelcetedSaveFile(String session){
        File folder = new File(SAVE_FOLDER);
        File[] saveFiles = folder.listFiles((dir, name) -> name.startsWith("save_") && name.endsWith(".txt"));

        for(int i = 0; i< saveFiles.length; i++){
            if(saveFiles[i].getName().equals("save_"+session+".txt")){
                saveFiles[i].delete();
                System.out.println("Save file " + session + " successfully deleted!.");
                return;
            }
        }

        System.out.println("Error, could not delete file: " + session + ".");
    }

    public void deleteSelcetedSaveFile(int id){
        File folder = new File(SAVE_FOLDER);
        File[] saveFiles = folder.listFiles((dir, name) -> name.startsWith("save_") && name.endsWith(".txt"));

        // Überprüfen, ob die Datei existiert und die ID gültig ist
        if (saveFiles == null || id < 0 || id >= saveFiles.length) {
            System.out.println("ID does not exist..");
            return;
        }

        // Die Datei mit der entsprechenden ID auswählen
        File fileToDelete = saveFiles[id];

        // Datei löschen und Ergebnis ausgeben
        if (fileToDelete.delete()) {
            System.out.println("Save file " + fileToDelete.getName() + " successfully deleted!.");
        } else {
            System.out.println("Error, could not delete file: " + fileToDelete.getName() + ".");
        }
    }

    public Map<String, String> loadGame(int id) {
        File[] saveFiles = getSaveFiles();
        if (saveFiles == null || id >= saveFiles.length) {
            System.out.println("Could not find the File.");
            return null;
        }
        return loadDataFromFile(saveFiles[id]);
    }

    private void createSaveFolder() {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists()) folder.mkdir();
    }

    private Map<String, String> collectGameData(Player player) {
        int seconds = GameSettings.getTimerSeconds();
        int minutes = GameSettings.getTimerMinutes();
        int hours = GameSettings.getTimerHours();

        String currentTimeStamp = getCurrentTimestamp();

        Map<String, String> gameData = new HashMap<>();

        gameData.put("character", player.getPlayerType().toString());
        gameData.put("field", player.getCurrentField());
        gameData.put("currentAct", String.valueOf(player.getCurrentAct()));
        gameData.put("currentHealth", String.valueOf(player.getCurrentHealth()));
        gameData.put("gold", String.valueOf(player.getGold()));

        gameData.put("lastSession", currentTimeStamp);
        GameSettings.lastSession = currentTimeStamp;

        gameData.put("timePlayed", hours+"h "+minutes+"m "+seconds+"s");
        gameData.put("seconds", String.valueOf(seconds));
        gameData.put("minutes", String.valueOf(minutes));
        gameData.put("hours", String.valueOf(hours));

        for (int i = 0; i < player.getDeck().size(); i++) {
            gameData.put("card" + i, player.getDeck().get(i).getName() + "Card");
        }

        return gameData;
    }

    private void saveDataToFile(Map<String, String> data, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern des Spiels: " + e.getMessage());
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
            System.out.println("Error while loading the game: " + e.getMessage());
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