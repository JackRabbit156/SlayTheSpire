package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import models.card.card_structure.Card;
import models.player.player_structure.Player;
import view.gui.BattleView;

import java.util.List;

public class BottomSideLayout extends HBox {
    private BattleView battleView;
    private List<Card> hand;
    private Player player;

    private CardLayout cardLayout;
    private EnergyLayout energyLayout;

    private HBox leftHBox;
    private HBox rightHBox;

    private Button endTurnButton;

    public BottomSideLayout(BattleView battleView, Player player, List<Card> hand) {
        this.player = player;
        this.battleView = battleView;
        this.hand = hand;

        initBottomSide();
    }

    public void updateBottom() {
        energyLayout.setEnergyText(player.getCurrentEnergy(), player.getMaxEnergy());
        cardLayout.refreshHand();
    }

    private void initBottomSide() {
        endTurnButton = new Button("End Turn");
        endTurnButton.getStyleClass().add("endround-button");
        endTurnButton.setOnAction(event -> battleView.clickedOnEndTurn());

        energyLayout = new EnergyLayout();
        leftHBox = new HBox(energyLayout);
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