package de.bundeswehr.auf.slaythespire.helper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The type Music boy.
 *
 * @author OF Daniel Willig
 */
public class MusicBoy {
    /**
     * The constant mediaPlayer.
     */
    private static MediaPlayer mediaPlayer;

    /**
     * Play.
     *
     * @param titel the titel
     */
    public static void play(String titel) {
        Media act1 = new Media(MusicBoy.class.getResource("/music/Act1.mp3").toExternalForm());
        Media act2 = new Media(MusicBoy.class.getResource("/music/Act2.mp3").toExternalForm());
        Media act4 = new Media(MusicBoy.class.getResource("/music/Act4.mp3").toExternalForm());
        Media boss = new Media(MusicBoy.class.getResource("/music/Boss.mp3").toExternalForm());
        Media elite = new Media(MusicBoy.class.getResource("/music/Elite.mp3").toExternalForm());
        Media mainMenu = new Media(MusicBoy.class.getResource("/music/MainMenu.mp3").toExternalForm());
        Media map = new Media(MusicBoy.class.getResource("/music/Map.mp3").toExternalForm());
        Media shop = new Media(MusicBoy.class.getResource("/music/Shop.mp3").toExternalForm());

        stopMusic();
        switch (titel) {
            case "act1":
                mediaPlayer = new MediaPlayer(act1);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
            case "act2":
                mediaPlayer = new MediaPlayer(act2);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
            case "act4":
                mediaPlayer = new MediaPlayer(act4);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
            case "boss":
                mediaPlayer = new MediaPlayer(boss);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
            case "elite":
                mediaPlayer = new MediaPlayer(elite);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
            case "mainMenu":
                mediaPlayer = new MediaPlayer(mainMenu);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
            case "map":
                mediaPlayer = new MediaPlayer(map);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
            default:
                mediaPlayer = new MediaPlayer(shop);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                break;
        }
    }

    /**
     * Stop music.
     */
    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }
}
