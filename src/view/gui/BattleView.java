package view.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import models.cards.card_structure.Card;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import view.gui.layouts.battle_view_layouts.CardLayout;
import view.gui.layouts.battle_view_layouts.EnemyLayout;
import view.gui.layouts.battle_view_layouts.PlayerLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.gui.layouts.layout_events.BattleViewEvents;

import java.util.ArrayList;
import java.util.List;

public class BattleView extends BorderPane {
    private VBox leftVBox; // Die linke VBox
    private HBox rightVBox; // Die rechte VBox; // Die linke VBox

    private HBox bottomHBox;
    private HBox topHBox; // Die obere HBox;

    private TextField text;
    private Button okayButton;


    private Player player;
    private List<Enemy> enemies;
    private List<Card> hand;

    private BattleViewEvents battleViewEvents;

    public BattleView(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        hand = new ArrayList<>();
        setBackground(new Background(background()));

        initNodes();
    }

    public void initBattleViewEvents(BattleViewEvents battleViewEvents){
        this.battleViewEvents = battleViewEvents;
    }

    private BackgroundImage background(){
        Image backgroundImage = new Image(getClass().getResource("/images/act1.png").toExternalForm()); // Lokaler Pfad oder URL
        // Erstelle das Hintergrundbild mit den gewünschten Eigenschaften
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
        return background;
    }

    // Controller kann diesen Handler hinzufügen
    public void setOkayButtonHandler(EventHandler<ActionEvent> handler) {
        okayButton.setOnAction(handler);
    }

    // Beispiel für Methode, um den Status zu aktualisieren
    public void updateEnemyStatus(/*List<Enemy> updatedEnemies*/) {
        // Hier können Änderungen im Enemy-Status angezeigt werden
        initRight();
    }

    private void initNodes() {
        initTop();
        initLeft();
        initRight();
        initBottom();
    }

    /**
     * Top side for the Player Information
     */
    private void initTop() {
        topHBox = new HBox();
        topHBox.setStyle("-fx-background-color: #926099;");

        okayButton = new Button();
        okayButton.setText("Okay");

        topHBox.getChildren().add(okayButton);

        this.setTop(topHBox);

    }

    /**
     * Right side for the enemies
     */
    private void initRight(){
        rightVBox = new HBox(-150);

        for(int i = 0; i< enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            enemy.setImagePath("/images/Cultist.png");
            rightVBox.getChildren().add(new EnemyLayout(enemy, this));
        }

        this.setRight(rightVBox);
    }

    /**
     * Left side for the player
     */
    private void initLeft(){
        leftVBox = new VBox();
        PlayerLayout playerLayout = new PlayerLayout(player);
        leftVBox.getChildren().add(playerLayout);

        this.setLeft(leftVBox);
    }

    /**
     * Bottom side for the Hand cards
     */
    private void initBottom(){
        bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER);
        if(hand.isEmpty()){
            Region bottomPlaceholder = new Region();
            bottomPlaceholder.setMinHeight(350); // Festlegen der konstanten Höhe
            bottomHBox.getChildren().add(bottomPlaceholder);
            setBottom(bottomPlaceholder);
            return;
        }

        CardLayout cardLayout = new CardLayout(hand, this);
        bottomHBox.getChildren().add(cardLayout);

        setBottom(bottomHBox);
    }

    public void setHand(List<Card> hand){
        this.hand = hand;
        initBottom();
    }

    public void clickedOnCard(Card card, int index){
        battleViewEvents.onCardClick(card, index);
    }

    public void clickedOnEnemy(Enemy enemy){
        battleViewEvents.onEnemyClick(enemy);
    }

    public void selectEnemyView(){
        topHBox.setDisable(true);
        leftVBox.setDisable(true);
        bottomHBox.setDisable(true);
    }

    public void enableBattleView(){
        topHBox.setDisable(false);
        leftVBox.setDisable(false);
        bottomHBox.setDisable(false);
    }
}