package helper;

import controller.gui.*;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.enemy.Enemy;
import models.map_elements.field_types.EnemyField;
import models.map_elements.field_types.FieldEnum;
import models.player.player_structure.Player;
import view.cli.StatisticsView;
import view.gui.StatisticView;

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
        public static void startBattleScene(Player player, List<Enemy> enemies, FieldEnum enemyField) {
            BattleController battle = new BattleController(player, enemies, enemyField);
            Stage primaryStage = player.getPrimaryStage();

            Scene scene = new Scene(battle.getBattleView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Loot-Szene, in der der Spieler eine Liste von Items erhalten kann, wenn er will.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         * @param fieldType Welchen fieldType man vorher besucht hat.
         */
        public static void startLootScene(Player player, FieldEnum fieldType) {
            LootController loot = new LootController(player, fieldType);
            Stage primaryStage = player.getPrimaryStage();

            Scene scene = new Scene(loot.getLootView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Treasure-Szene, in der der Spieler eine Liste von Items erhalten kann, wenn er will.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startTreasureScene(Player player) {
            TreasureController treasureController = new TreasureController(player);
            Stage primaryStage = player.getPrimaryStage();

            Scene scene = new Scene(treasureController.getTreasureView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Rest-Szene, in der der Spieler sich ausruhen kann.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startRestScene(Player player) {
            RestController rest = new RestController(player);
            Stage primaryStage = player.getPrimaryStage();

            Scene scene = new Scene(rest.getRestView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Shop-Szene, in der der Spieler eine Liste von Items kaufen kann, wenn er will.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startShopScene(Player player) {
            ShopController shop = new ShopController(player);
            Stage primaryStage = player.getPrimaryStage();

            Scene scene = new Scene(shop.getShopView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Statistik-Szene, in der der Spieler eine Liste von Daten aus den letzten Akten erhält.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startStatisticScene(Player player) {
            StatisticView view = new StatisticView(player);
            Stage primaryStage = player.getPrimaryStage();

            Scene scene = new Scene(view, 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/battleStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Szene zum Laden eines gespeicherten Spielstands (Load Save State Scene).
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         */
        public static void startLoadGameFromMenuScene(Stage primaryStage) {
            LoadController loadController = new LoadController(primaryStage);

            Scene scene = new Scene(loadController.getLoadView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/loadViewStyle.css")).toExternalForm());
            fadeTransition(primaryStage, scene);
        }

        /**
         * Startet die Szene zum Laden eines gespeicherten Spielstands (Load Save State Scene).
         *
         * @param player der Player, im aktuellen Spiel
         */
        public static void startLoadGameFromMapScene(Player player) {
            LoadController loadController = new LoadController(player);

            Scene scene = new Scene(loadController.getLoadView(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(Scenes.class.getResource("/css/loadViewStyle.css")).toExternalForm());
            fadeTransition(player.getPrimaryStage(), scene);
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
            setCursor(scene);
            // Direkt die neue Szene setzen, falls die aktuelle Szene oder deren Root-Node ungültig ist
            if (currentScene == null || currentScene.getRoot() == null) {
                System.out.println("NO Fade");
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
                primaryStage.show(); // Zeigt die neue Szene sofort an
                return;
            }

            // Fade-Out der aktuellen Szene
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), currentScene.getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                // Setzen der neuen Szene nach dem Fade-Out
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
                scene.getRoot().setOpacity(0.0); // Stellen Sie sicher, dass die neue Szene unsichtbar ist, bevor sie einblendet
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), scene.getRoot());
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
        }

        private static void setCursor(Scene scene){
            Image cursorImage = new Image(Objects.requireNonNull(GuiHelper.class.getResource("/images/scene/cursor.png")).toExternalForm());
            ImageCursor customCursor = new ImageCursor(cursorImage);
            scene.setCursor(customCursor);
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

    /**
     * Lässt ein ImageView beim Hovern aufleuchten.
     * @param imageView ImageView
     */
    public static void setHoverEffect(ImageView imageView) {
        double downScaleX = imageView.getScaleX();;
        double downScaleY = imageView.getScaleY();;
        double upScaleX = downScaleX * 1.1;
        double upScaleY = downScaleY * 1.1;

        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            imageView.setEffect(glow);
            imageView.setScaleX(upScaleX); // Slightly increase the width
            imageView.setScaleY(upScaleY); // Slightly increase the height
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            imageView.setEffect(null);
            imageView.setScaleX(downScaleX); // Reset the width to original
            imageView.setScaleY(downScaleY); // Reset the height to original
        });
    }

    /**
     * Lässt ein ImageView beim Hovern über das Bild oder den Text aufleuchten.
     * @param imageView ImageView
     * @param label Schrift, die über dem Image gelegt wird.
     */
    public static void setButtonHoverEffect(ImageView imageView, Label label) {
        double downScaleX = imageView.getScaleX();;
        double downScaleY = imageView.getScaleY();;
        double upScaleX = downScaleX * 1.1;
        double upScaleY = downScaleY * 1.1;

        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);

        label.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            imageView.setEffect(glow);
            imageView.setScaleX(upScaleX); // Slightly increase the width
            imageView.setScaleY(upScaleY); // Slightly increase the height
        });

        label.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            imageView.setEffect(null);
            imageView.setScaleX(downScaleX); // Reset the width to original
            imageView.setScaleY(downScaleY); // Reset the height to original
        });

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            imageView.setEffect(glow);
            imageView.setScaleX(upScaleX); // Slightly increase the width
            imageView.setScaleY(upScaleY); // Slightly increase the height
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            imageView.setEffect(null);
            imageView.setScaleX(downScaleX); // Reset the width to original
            imageView.setScaleY(downScaleY); // Reset the height to original
        });
    }

    public static StackPane addButtonStackPane(ImageView imgView, Label label, double scale) {
        return addButtonStackPane(imgView, label, scale, scale);
    }

    public static StackPane addButtonStackPane(ImageView imgView, Label label, double scaleX, double scaleY) {
        StackPane btnStackPane = new StackPane(imgView);

        imgView.setFitHeight(Math.sqrt(imgView.getImage().getHeight()) * scaleY);
        imgView.setFitWidth(Math.sqrt(imgView.getImage().getWidth()) * scaleX);

        btnStackPane.setMaxSize(imgView.getFitWidth(), imgView.getFitHeight());

        label.setStyle("-fx-font-size: 24; -fx-font-family: Kreon;");
        //DEBUGGER
//        btnStackPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("Purple"), null, null)));
        btnStackPane.getChildren().add(label);
        btnStackPane.setMaxHeight(100);

        setButtonHoverEffect(imgView, label);
        return btnStackPane;
    }
}