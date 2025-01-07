package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.controller.*;
import de.bundeswehr.auf.slaythespire.gui.GameOverView;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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

        private static Controller lastController;

        public static void close() {
            GameSettings.stop();
            discardLastController();
            Platform.exit();
        }

        public static void requestClose(Stage stage) {
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        }

        /**
         * Startet die Kampf-Szene (Battle Scene), in der der Spieler gegen eine Liste von Gegnern kämpfen kann.
         *
         * @param player     die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         * @param enemies    eine Liste von 'Enemy'-Instanzen, die die Gegner im Kampf darstellen
         * @param enemyField aktueller Feldtyp
         */
        public static void startBattleScene(Player player, List<Enemy> enemies, FieldEnum enemyField) {
            discardLastController(player);
            BattleController battleController = new BattleController(player, enemies, enemyField);
            registerController(battleController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/battleStyle.css";
            fadeTransition(primaryStage, battleController.getBattleView(), cssPath);
        }

        /**
         * Startet die Kampf-Szene (Battle Scene), in der der Spieler gegen eine Liste von Gegnern kämpfen kann.
         *
         * @param primaryStage die Stage die Übergeben wird
         */
        public static void startCharSelection(Stage primaryStage) {
            discardLastController();
            CharacterController characterController = new CharacterController();
            registerController(characterController);
            String cssPath = "";
            fadeTransition(primaryStage, characterController.startSelection(primaryStage), cssPath);
        }

        /**
         * Startet die Szene zum Laden eines gespeicherten Spielstands (Load Save State Scene).
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         */
        public static void startDeleteMenuScene(Stage primaryStage) {
            discardLastController();
            DeleteController deleteController = new DeleteController(primaryStage);
            registerController(deleteController);
            String cssPath = "/css/loadViewStyle.css";
            fadeTransition(primaryStage, deleteController.getDeleteMenuView(), cssPath);
        }

        /**
         * Startet die Kampf-Szene (Battle Scene), in der der Spieler gegen eine Liste von Gegnern kämpfen kann.
         *
         * @param player die Stage die Übergeben wird
         */
        public static void startEventScene(Player player) {
            discardLastController(player);
            EventController eventController = new EventController(player);
            registerController(eventController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "";
            fadeTransition(primaryStage, eventController.getEventView(), cssPath);
        }

        public static void startGameOverScene(Player player) {
            discardLastController(player);
            GameOverView view = new GameOverView(player);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/battleStyle.css";
            fadeTransition(primaryStage, view, cssPath);
        }

        /**
         * Startet die Szene zum Laden eines gespeicherten Spielstands (Load Save State Scene).
         *
         * @param player der Player, im aktuellen Spiel
         */
        public static void startLoadGameFromMapScene(Player player) {
            discardLastController(player);
            LoadController loadController = new LoadController(player);
            registerController(loadController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/loadViewStyle.css";
            fadeTransition(primaryStage, loadController.getLoadView(), cssPath);
        }

        /**
         * Startet die Szene zum Laden eines gespeicherten Spielstands (Load Save State Scene).
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         */
        public static void startLoadGameFromMenuScene(Stage primaryStage) {
            discardLastController();
            LoadController loadController = new LoadController(primaryStage);
            registerController(loadController);
            String cssPath = "/css/loadViewStyle.css";
            fadeTransition(primaryStage, loadController.getLoadView(), cssPath);
        }

        /**
         * Startet die Loot-Szene, in der der Spieler eine Liste von Items erhalten kann, wenn er will.
         *
         * @param player    die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         * @param fieldType Welchen fieldType man vorher besucht hat.
         */
        public static void startLootScene(Player player, FieldEnum fieldType) {
            discardLastController(player);
            LootController lootController = new LootController(player, fieldType);
            registerController(lootController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/battleStyle.css";
            fadeTransition(primaryStage, lootController.getLootView(), cssPath);
        }

        /**
         * Startet die Kampf-Szene (Battle Scene), in der der Spieler gegen eine Liste von Gegnern kämpfen kann.
         *
         * @param primaryStage die Stage die Übergeben wird
         */
        public static void startMainMenuScene(Stage primaryStage) {
            discardLastController();
            MainMenuController mainMenuController = new MainMenuController();
            registerController(mainMenuController);
            String cssPath = "";
            fadeTransition(primaryStage, mainMenuController.startMenu(primaryStage), cssPath);
        }

        /**
         * Startet die Karten-Szene (Map Scene), die es dem Spieler ermöglicht, zwischen Knoten auf der Karte zu navigieren.
         * Wenn das Spiel zum ersten Mal gestartet wird, lädt der 'MapController' automatisch den ersten Kampf.
         *
         * @param player die {@code Player}-Instanz des aktuellen Spiels
         */
        public static void startMapScene(Player player) {
            discardLastController(player);
            MapController mapController = new MapController(player);
            registerController(mapController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/mapStyle.css";
            fadeTransition(primaryStage, mapController.getMapView(), cssPath);
        }

        /**
         * Startet die Rest-Szene, in der der Spieler sich ausruhen kann.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startRestScene(Player player) {
            discardLastController(player);
            RestController restController = new RestController(player);
            registerController(restController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/battleStyle.css";
            fadeTransition(primaryStage, restController.getRestView(), cssPath);
        }

        /**
         * Startet eine Szene mit einem vordefinierten 'Scene'-Objekt und einem Titel.
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         * @param scene        das 'Scene'-Objekt, das angezeigt werden soll
         * @param title        der Titel der Szene, der im Stage angezeigt wird
         */
        public static void startScene(Stage primaryStage, Scene scene, String title) {
            setCursor(scene);
            primaryStage.getIcons().add(new Image(Scenes.class.getResource("/images/icon.png").toExternalForm()));
            Popup quitPopup = createPopup();
            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                quitPopup.show(primaryStage);
            });
            primaryStage.setX(1920); // TODO remove
            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();
        }

        /**
         * Startet die Shop-Szene, in der der Spieler eine Liste von Items kaufen kann, wenn er will.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startShopScene(Player player) {
            discardLastController(player);
            ShopController shopController = new ShopController(player);
            registerController(shopController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/battleStyle.css";
            fadeTransition(primaryStage, shopController.getShopView(), cssPath);
        }

        /**
         * Startet die Statistik-Szene, in der der Spieler eine Liste von Daten aus den letzten Akten erhält.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startStatisticScene(Player player) {
            discardLastController(player);
            StatisticsController statisticsController = new StatisticsController(player);
            registerController(statisticsController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/battleStyle.css";
            fadeTransition(primaryStage, statisticsController.getView(), cssPath);
        }

        /**
         * Startet die Treasure-Szene, in der der Spieler eine Liste von Items erhalten kann, wenn er will.
         *
         * @param player die 'Player'-Instanz, die den Spieler im Spiel repräsentiert
         */
        public static void startTreasureScene(Player player) {
            discardLastController(player);
            TreasureController treasureController = new TreasureController(player);
            registerController(treasureController);
            Stage primaryStage = player.getPrimaryStage();
            String cssPath = "/css/battleStyle.css";
            fadeTransition(primaryStage, treasureController.getTreasureView(), cssPath);
        }

        private static Popup createPopup() {
            Popup quitPopup = new Popup();
            quitPopup.setAutoHide(true);

            Text text = new Text("Are you sure that you\n" +
                    "want to Quit the Game?");
            text.setFill(Color.WHITE);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setFont(Font.loadFont(GuiHelper.class.getResourceAsStream(DEFAULT_FONT_BOLD), 30));

            Button no = new Button("No");
            initialize(no);
            no.setOnMouseClicked(e -> quitPopup.hide());
            Button yes = new Button("Yes");
            initialize(yes);
            yes.setOnMouseClicked(e -> Scenes.close());

            HBox buttons = new HBox();
            buttons.getChildren().addAll(yes, no);
            buttons.setAlignment(Pos.BOTTOM_CENTER);

            VBox content = new VBox();
            content.setBackground(new Background(GuiHelper.backgroundInHD("/images/popup/popupBg.png")));
            content.setPadding(new Insets(25, 0, 0, 0));
            content.setPrefSize(900, 500);
            content.setAlignment(Pos.CENTER);
            content.getChildren().addAll(text, buttons);

            quitPopup.getContent().add(content);
            return quitPopup;
        }

        private static void discardLastController() {
            discardLastController(null);
        }

        private static void discardLastController(Player player) {
            if (lastController != null) {
                lastController.discard();
            }
            if (player != null) {
                player.resetListeners();
            }
        }

        /**
         * Führt eine Fade-Transition für die Szene durch.
         *
         * @param primaryStage das primäre 'Stage'-Objekt der Anwendung
         * @param parent       das 'Parent'-Objekt, das angezeigt werden soll
         * @param cssPath      falls keien Css-Datei geladen werden soll, leer lassen
         */
        private static void fadeTransition(Stage primaryStage, Parent parent, String cssPath) {
            Scene currentScene = primaryStage.getScene();

            // Direkt die neue Szene setzen, falls die aktuelle Szene oder deren Root-Node ungültig ist
            if (currentScene == null || currentScene.getRoot() == null) {
                Scene scene = new Scene(parent, 1920, 1080);
                startScene(primaryStage, scene, DEFAULT_TITLE);
                return;
            }

            // Fade-Out der aktuellen Szene
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), currentScene.getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                primaryStage.getScene().setRoot(parent);
                if (!cssPath.equals("")) {
                    primaryStage.getScene().getStylesheets().addAll(cssPath);
                }
                parent.setOpacity(0.0); // Stellen Sie sicher, dass die neue Szene unsichtbar ist, bevor sie einblendet
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), parent);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
        }

        private static void initialize(Button button) {
            button.setTextFill(Color.WHITE);
            button.setFont(Font.loadFont(GuiHelper.class.getResourceAsStream(DEFAULT_FONT_BOLD), 24));
            button.setAlignment(Pos.CENTER);
            button.setBackground(new Background(GuiHelper.backgroundInHD("/images/buttons/endTurnButton.png")));
            button.setPrefSize(120, 120);
            button.setOnMouseEntered(event -> button.setBackground(new Background(GuiHelper.backgroundInHD("/images/buttons/endTurnButtonGlow.png"))));
            button.setOnMouseExited(event -> button.setBackground(new Background(GuiHelper.backgroundInHD("/images/buttons/endTurnButton.png"))));
        }

        private static void registerController(Controller controller) {
            lastController = controller;
        }

        private static void setCursor(Scene scene) {
            Image cursorImage = new Image(Objects.requireNonNull(GuiHelper.class.getResource("/images/scene/cursor.png")).toExternalForm());
            ImageCursor customCursor = new ImageCursor(cursorImage);
            scene.setCursor(customCursor);
        }

    }

    public static final String DEFAULT_FONT_BOLD = "/font/kreon/static/Kreon-Bold.ttf";

    private static final String DEFAULT_TITLE = "Slay the Spire - JavaFX";

    /**
     * Erstellt ein StackPane mit einem ImageView und einem Label.
     *
     * @param imgView Das ImageView, das im StackPane angezeigt werden soll.
     * @param label   Das Label, das im StackPane angezeigt werden soll.
     * @param scale   Der Skalierungsfaktor für die Breite und Höhe des ImageView.
     * @return Ein StackPane, das das ImageView und das Label enthält.
     */
    public static StackPane addButtonStackPane(ImageView imgView, Label label, double scale) {
        return addButtonStackPane(imgView, label, scale, scale);
    }

    /**
     * Erstellt ein StackPane mit einem ImageView und einem Label.
     *
     * @param imgView Das ImageView, das im StackPane angezeigt werden soll.
     * @param label   Das Label, das im StackPane angezeigt werden soll.
     * @param scaleX  Der Skalierungsfaktor für die Breite des ImageView.
     * @param scaleY  Der Skalierungsfaktor für die Höhe des ImageView.
     * @return Ein StackPane, das das ImageView und das Label enthält.
     */
    public static StackPane addButtonStackPane(ImageView imgView, Label label, double scaleX, double scaleY) {
        StackPane btnStackPane = new StackPane(imgView);

        imgView.setFitHeight(Math.sqrt(imgView.getImage().getHeight()) * scaleY);
        imgView.setFitWidth(Math.sqrt(imgView.getImage().getWidth()) * scaleX);

        btnStackPane.setMaxSize(imgView.getFitWidth(), imgView.getFitHeight());

        label.setStyle("-fx-font-size: 24; -fx-font-family: Kreon;");
        //DEBUGGER
//    btnStackPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("Purple"), null, null)));
        btnStackPane.getChildren().add(label);
        btnStackPane.setMaxHeight(100);

        setButtonHoverEffect(imgView, label);
        return btnStackPane;
    }

    /**
     * Erstellt ein 'BackgroundImage' mit dem angegebenen Pfad zu einem Bild.
     *
     * @param backgroundPath der relative Pfad zur Bilddatei
     * @return ein 'BackgroundImage', das mit den angegebenen Eigenschaften konfiguriert ist
     * @throws NullPointerException wenn der angegebene Pfad nicht existiert oder ungültig ist
     */
    public static BackgroundImage background(String backgroundPath) {
        Image backgroundImage = new Image(backgroundPath);
        return new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, false,
                        Side.TOP, 0, false),
                BackgroundSize.DEFAULT);
    }

    /**
     * Erstellt ein 'BackgroundImage' mit dem angegebenen Pfad zu einem Bild.
     *
     * @param backgroundPath der relative Pfad zur Bilddatei
     * @return ein 'BackgroundImage', das mit den angegebenen Eigenschaften konfiguriert ist
     * @throws NullPointerException wenn der angegebene Pfad nicht existiert oder ungültig ist
     */
    public static BackgroundImage backgroundEndTurn(String backgroundPath) {
        Image backgroundImage = new Image(backgroundPath);
        return new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, false,
                        Side.TOP, -80, false),
                BackgroundSize.DEFAULT);
    }

    /**
     * Erstellt ein 'BackgroundImage' mit dem angegebenen Pfad zu einem Bild.
     *
     * @param backgroundPath der relative Pfad zur Bilddatei
     * @return ein 'BackgroundImage', das mit den angegebenen Eigenschaften konfiguriert ist
     * @throws NullPointerException wenn der angegebene Pfad nicht existiert oder ungültig ist
     */
    public static BackgroundImage backgroundInHD(String backgroundPath) {
        Image backgroundImage = new Image(Objects.requireNonNull(GuiHelper.class.getResource(backgroundPath)).toExternalForm());
        return new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1920, 1080, false, false, false, true));
    }

    /**
     * Erstellt ein Bild in der gewünschten Größe.
     */
    public static ImageView image(String imagePath, double width, double height) {
        Image figureImage = new Image(imagePath);
        ImageView imageViewFigure = new ImageView(figureImage);
        imageViewFigure.setFitWidth(width);
        imageViewFigure.setFitHeight(height);
        imageViewFigure.setPreserveRatio(true);
        return imageViewFigure;
    }

    /**
     * Lässt ein ImageView beim Hovern über das Bild oder den Text aufleuchten.
     *
     * @param imageView ImageView
     * @param label     Schrift, die über dem Image gelegt wird.
     */
    public static void setButtonHoverEffect(ImageView imageView, Label label) {
        double downScaleX = imageView.getScaleX();
        double downScaleY = imageView.getScaleY();
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
    }

    /**
     * Lässt ein ImageView beim Hovern aufleuchten.
     *
     * @param imageView ImageView
     */
    public static void setHoverEffect(Node imageView) {
        double downScaleX = imageView.getScaleX();
        double downScaleY = imageView.getScaleY();
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

}