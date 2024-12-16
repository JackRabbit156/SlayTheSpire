package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.gui.BattleView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

/**
 * Die Klasse 'BottomSideLayout' repräsentiert das untere Layout im
 * Schlachtansicht (Battle View), das die Energieleiste des Spielers,
 * die Handkarten und die Schaltfläche zum Beenden des Zuges anzeigt.
 *
 * <p>
 * Dieses Layout besteht aus drei Hauptkomponenten: einer
 * {@link EnergyLayout} für die Energieanzeige des Spielers,
 * einem {@link CardLayout} zur Darstellung der Handkarten und
 * einer Schaltfläche zum Beenden des Zuges. Es ermöglicht
 * den Spielern, ihre Züge zu planen und auszuführen.
 * </p>
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class BottomSideLayout extends HBox {

    private final BattleView battleView;
    private CardLayout cardLayout;
    private EnergyLayout energyLayout;
    private final List<Card> hand;
    private final Player player;
    private HBox right;

    public BottomSideLayout(BattleView battleView, Player player, List<Card> hand) {
        this.player = player;
        this.battleView = battleView;
        this.hand = hand;

        initBottomSide();
    }

    public void setDisableEndTurn(boolean value) {
        right.setDisable(value);
    }

    public void update() {
        energyLayout.setEnergyText(player.getCurrentEnergy(), player.getMaxEnergy());
        cardLayout.refreshHand();
    }

    private void initBottomSide() {
        Button endTurnButton = new Button("End Turn");
        endTurnButton.setTextFill(Color.WHITE);
        endTurnButton.setFont(Font.loadFont(getClass().getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 24));
        endTurnButton.setBackground(new Background(GuiHelper.backgroundEndTurn("/images/buttons/endTurnButton.png")));
        endTurnButton.setMinSize(256, 100);
        endTurnButton.setOnMouseEntered(event -> endTurnButton.setBackground(new Background(GuiHelper.backgroundEndTurn("/images/buttons/endTurnButtonGlow.png"))));
        endTurnButton.setOnMouseExited(event -> endTurnButton.setBackground(new Background(GuiHelper.backgroundEndTurn("/images/buttons/endTurnButton.png"))));
        endTurnButton.setOnAction(event -> battleView.clickedOnEndTurn());

        energyLayout = new EnergyLayout();
        HBox left = new HBox(energyLayout);
        left.setAlignment(Pos.CENTER);
        left.setPrefWidth(400);

        cardLayout = new CardLayout(hand, battleView);
        right = new HBox(endTurnButton);
        right.setAlignment(Pos.CENTER);
        right.setPrefWidth(400);

        getChildren().addAll(left, cardLayout, right);
        setAlignment(Pos.CENTER);

//        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(cardLayout, Priority.ALWAYS);
//        HBox.setHgrow(right, Priority.ALWAYS);
    }

}