package helper;

import controller.gui.BattleController;
import controller.gui.LoadController;
import controller.gui.MapController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Objects;

/**
 * Die Klasse 'GuiHelper' bietet Hilfsmethoden für die Verwaltung von Szenen und UI-Elementen
 * in der JavaFX-Anwendung "Slay the Spire - JavaFX".
 *
 * <p>Sie enthält Methoden zum Wechseln zwischen verschiedenen Szenen und zur Erstellung von Hintergrundbildern
 * für Ansichten. Diese Klasse erleichtert den Szenenwechsel und sorgt für einheitliches Styling und Verhalten
 * in der grafischen Benutzeroberfläche (GUI) der Anwendung.</p>
 *
 * @author Warawa Alexander, ...
 */
public class GuiHelper {
    /**
     * Verschachtelte Klasse mit Hilfsmethoden zur Verwaltung von Szenen.
     */
    public static class Scenes {
        private static final String DEFAULT_TITLE = "Slay the Spire - JavaFX";
        /**
         * Startet die Karten-Szene (Map Scene), die es dem Spieler ermöglicht, zwischen Knoten auf der Karte zu navigieren.
         * Wenn das Spiel zum ersten Mal gestartet wird, lädt der 'MapController' automatisch den ersten Kampf.
         *
         * @param primaryStage das primäre {@code Stage}-Objekt der Anwendung
         * @param player die {@code Player}-Instanz des aktuellen Spiels
         * @param fromFile ein Flag, das angibt, ob das Spiel aus einer Speicherdatei geladen wurde
         */
        public static void startMapScene(Stage primaryStage, Player player, boolean fromFile) {
            MapController mapController = new MapController(player, fromFile);

            // Früher Rücksprung, wenn das Spiel zum ersten Mal gestartet wird,
            // damit der MapController den ersten Kampf laden kann.
            if(!fromFile)
                return;

            Scene scene = new Scene(mapController.getMapView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(GuiHelper.class.getResource("/css/mapStyle.css")).toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle(DEFAULT_TITLE);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }
        /**
         * Startet die Kampf-Szene (Battle Scene), in der der Spieler gegen eine Liste von Gegnern kämpfen kann.
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         * @param enemies eine Liste von 'Enemy'-Instanzen, die die Gegner im Kampf darstellen
         */
        public static void startBattleScene(Stage primaryStage, Player player, List<Enemy> enemies) {
            BattleController battle = new BattleController(player, enemies);

            Scene scene = new Scene(battle.getBattleView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle(DEFAULT_TITLE);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }
        /**
         * Startet die Szene zum Laden eines gespeicherten Spielstands (Load Save State Scene).
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         */
        public static void startLoadSaveStateScene(Stage primaryStage) {
            LoadController loadController = new LoadController(primaryStage);

            Scene scene = new Scene(loadController.getLoadView(), 1920, 1080);

            primaryStage.setScene(scene);
            primaryStage.setTitle(DEFAULT_TITLE);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }

        /**
         * Startet eine generische Szene mit einem angegebenen Parent-Node und einem Titel.
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         * @param parentToShow der Root-Node 'Parent' der Szene, die angezeigt werden soll
         * @param title der Titel der Szene, der im Stage angezeigt wird
         */
        public static void startScene(Stage primaryStage, Parent parentToShow, String title) {
            Scene scene = new Scene(parentToShow, 1920, 1080);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }

        /**
         * Startet eine Szene mit einem vordefinierten 'Scene'-Objekt und einem Titel.
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         * @param scene das 'Scene'-Objekt, das angezeigt werden soll
         * @param title der Titel der Szene, der im Stage angezeigt wird
         */
        public static void startScene(Stage primaryStage, Scene scene, String title) {
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }
    }
    /**
     * Erstellt ein 'BackgroundImage' mit dem angegebenen Pfad zu einem Bild.
     *
     * @param backgroundPath der relative Pfad zur Bilddatei
     * @return ein 'BackgroundImage', das mit den angegebenen Eigenschaften konfiguriert ist
     * @throws NullPointerException wenn der angegebene Pfad nicht existiert oder ungültig ist
     */
    public static BackgroundImage background(String backgroundPath){
        Image backgroundImage = new Image(Objects.requireNonNull(GuiHelper.class.getResource(backgroundPath)).toExternalForm());
        // Erstelle das Hintergrundbild mit den gewünschten Eigenschaften
        return new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, true)
        );
    }
}