package helper;

import controller.gui.BattleController;
import controller.gui.LoadController;
import controller.gui.MapController;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    private static final String DEFAULT_TITLE = "Slay the Spire - JavaFX";

    /**
     * Verschachtelte Klasse mit Hilfsmethoden zur Verwaltung von Szenen.
     */
    public static class Scenes {
        /**
         * Startet die Karten-Szene (Map Scene), die es dem Spieler ermöglicht, zwischen Knoten auf der Karte zu navigieren.
         * Wenn das Spiel zum ersten Mal gestartet wird, lädt der 'MapController' automatisch den ersten Kampf.
         *
         * @param player die {@code Player}-Instanz des aktuellen Spiels
         * @param fromFile ein Flag, das angibt, ob das Spiel aus einer Speicherdatei geladen wurde
         */
        public static void startMapScene(Player player, boolean fromFile) {
            MapController mapController = new MapController(player, fromFile);
            Stage primaryStage = player.getPrimaryStage();

            // Früher Rücksprung, wenn das Spiel zum ersten Mal gestartet wird,
            // damit der MapController den ersten Kampf laden kann.
            if (!fromFile)
                return;

            Scene scene = new Scene(mapController.getMapView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(GuiHelper.class.getResource("/css/mapStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Kampf-Szene (Battle Scene), in der der Spieler gegen eine Liste von Gegnern kämpfen kann.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         * @param enemies eine Liste von 'Enemy'-Instanzen, die die Gegner im Kampf darstellen
         */
        public static void startBattleScene(Player player, List<Enemy> enemies) {
            BattleController battle = new BattleController(player, enemies);
            Stage primaryStage = player.getPrimaryStage();

            Scene scene = new Scene(battle.getBattleView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Szene zum Laden eines gespeicherten Spielstands (Load Save State Scene).
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         */
        public static void startLoadSaveStateScene(Stage primaryStage) {
            LoadController loadController = new LoadController(primaryStage);

            Scene scene = new Scene(loadController.getLoadView(), 1920, 1080);
            fadeTransition(primaryStage, scene);
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
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet eine Szene mit einem vordefinierten 'Scene'-Objekt und einem Titel.
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         * @param scene das 'Scene'-Objekt, das angezeigt werden soll
         * @param title der Titel der Szene, der im Stage angezeigt wird
         */
        public static void startScene(Stage primaryStage, Scene scene, String title) {
            fadeTransition(primaryStage, scene);
        }

        /**
         * Führt eine Fade-Transition für die Szene durch.
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         * @param scene das 'Scene'-Objekt, das angezeigt werden soll
         */
        private static void fadeTransition(Stage primaryStage, Scene scene) {
            Scene currentScene = primaryStage.getScene();

            // Direkt die neue Szene setzen, falls die aktuelle Szene oder deren Root-Node ungültig ist
            if (currentScene == null || currentScene.getRoot() == null) {
                System.out.println("NO Fade");
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
                primaryStage.show(); // Zeigt die neue Szene sofort an
                return;
            }

            // Fade-Out der aktuellen Szene
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), currentScene.getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                // Setzen der neuen Szene nach dem Fade-Out
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
                scene.getRoot().setOpacity(0.0); // Stellen Sie sicher, dass die neue Szene unsichtbar ist, bevor sie einblendet
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), scene.getRoot());
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
        }
    }

    /**
     * Erstellt ein 'BackgroundImage' mit dem angegebenen Pfad zu einem Bild.
     *
     * @param backgroundPath der relative Pfad zur Bilddatei
     * @return ein 'BackgroundImage', das mit den angegebenen Eigenschaften konfiguriert ist
     * @throws NullPointerException wenn der angegebene Pfad nicht existiert oder ungültig ist
     */
    public static BackgroundImage background(String backgroundPath) {
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