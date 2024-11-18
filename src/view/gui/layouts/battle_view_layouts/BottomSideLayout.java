package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;
import view.gui.BattleView;

import java.util.List;

public class BottomSideLayout extends HBox {
    private Label energyLabel;
    private HBox leftHBox;

    private BattleView battleView;
    private List<Card> hand;
    private Player player;

    private CardLayout cardLayout;
    private HBox rightHBox;

    private Button endTurnButton;

    public BottomSideLayout(BattleView battleView, Player player, List<Card> hand) {
        this.player = player;
        this.battleView = battleView;
        this.hand = hand;

        initBottomSide();

        MovingAnimation movingEnergyLabel = new MovingAnimation(energyLabel);
        movingEnergyLabel.start();
    }

    public void updateBottom() {
        energyLabel.setText(player.getCurrentEnergy()+"/"+player.getMaxEnergy());
        cardLayout.refreshHand();
    }

    private void initBottomSide() {
        energyLabel = new Label("3/3");
        energyLabel.getStyleClass().add("energy-label");

        endTurnButton = new Button("End Turn");
        endTurnButton.getStyleClass().add("endround-button");
        endTurnButton.setOnAction(event -> battleView.clickedOnEndTurn());

        leftHBox = new HBox(energyLabel);
        leftHBox.setAlignment(Pos.CENTER);

        cardLayout = new CardLayout(hand, battleView);
        rightHBox = new HBox(endTurnButton);
        rightHBox.setAlignment(Pos.CENTER);

        getChildren().addAll(leftHBox, cardLayout ,rightHBox);
        setAlignment(Pos.CENTER);

        HBox.setHgrow(leftHBox, Priority.ALWAYS);
        HBox.setHgrow(cardLayout, Priority.ALWAYS);
        HBox.setHgrow(rightHBox, Priority.ALWAYS);
    }
}