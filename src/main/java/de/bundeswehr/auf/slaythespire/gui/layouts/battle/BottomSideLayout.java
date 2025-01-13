package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import com.sun.javafx.scene.traversal.Direction;
import de.bundeswehr.auf.slaythespire.controller.listener.EmptyPlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.PlayerEnergyEvent;
import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.gui.components.animation.EnergyText;
import de.bundeswehr.auf.slaythespire.helper.Animate;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    private final BattleDeck battleDeck;
    private final BattleView battleView;
    private CardLayout cardLayout;
    private DeckLayout deckLayout;
    private DiscardPileLayout discardPileLayout;
    private EnergyLayout energyLayout;
    private final Player player;
    private HBox right;

    public BottomSideLayout(BattleView battleView, Player player, GameContext gameContext) {
        this.player = player;
        this.battleView = battleView;
        this.battleDeck = gameContext.getBattleDeck();

        initBottomSide(gameContext);
    }

    public void setDisableEndTurn(boolean value) {
        right.setDisable(value);
    }

    public void update() {
        deckLayout.setDeckText(battleDeck.getDeck().size());
        energyLayout.setEnergyText(player.getCurrentEnergy(), player.getMaxEnergy());
        discardPileLayout.setDiscardPileText(battleDeck.getDiscardPile().size());
        cardLayout.refreshHand();
    }

    private Button createEndTurn() {
        Button endTurnButton = new Button("End Turn");
        endTurnButton.setTextFill(Color.WHITE);
        endTurnButton.setFont(Font.loadFont(getClass().getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 24));
        endTurnButton.setBackground(new Background(GuiHelper.backgroundEndTurn("/images/buttons/end_turn.png")));
        endTurnButton.setMinSize(256, 100);
        endTurnButton.setOnMouseEntered(event -> endTurnButton.setBackground(new Background(GuiHelper.backgroundEndTurn("/images/buttons/end_turn_glow.png"))));
        endTurnButton.setOnMouseExited(event -> endTurnButton.setBackground(new Background(GuiHelper.backgroundEndTurn("/images/buttons/end_turn.png"))));
        endTurnButton.setOnMouseReleased(event -> endTurnButton.setBackground(new Background(GuiHelper.backgroundEndTurn("/images/buttons/end_turn.png"))));
        endTurnButton.setOnAction(event -> battleView.clickedOnEndTurn());
        return endTurnButton;
    }

    private void initBottomSide(GameContext gameContext) {
        deckLayout = new DeckLayout();
        energyLayout = new EnergyLayout(gameContext.getPlayer());
        energyLayout.setEnergyText(player.getCurrentEnergy(), player.getMaxEnergy());
        player.addPlayerEventListener(new EmptyPlayerEventListener() {

            @Override
            public void onEnergyReceived(PlayerEnergyEvent event) {
                Animate.pathAnimationBelowTarget(new EnergyText(event.getEnergyAmount()),
                        energyLayout,
                        Direction.DOWN,
                        e -> energyLayout.setEnergyText(player.getCurrentEnergy(), player.getMaxEnergy()));
            }
            
        });
        HBox left = new HBox(deckLayout, energyLayout);
        left.setAlignment(Pos.CENTER_LEFT);
        left.setPrefWidth(400);

        cardLayout = new CardLayout(battleDeck.getHand(), gameContext, battleView);

        Button endTurnButton = createEndTurn();
        discardPileLayout = new DiscardPileLayout();
        right = new HBox(endTurnButton, discardPileLayout);
        right.setAlignment(Pos.CENTER_RIGHT);
        right.setPrefWidth(400);

        getChildren().addAll(left, cardLayout, right);
        setAlignment(Pos.CENTER);

        HBox.setHgrow(cardLayout, Priority.ALWAYS);
    }

}