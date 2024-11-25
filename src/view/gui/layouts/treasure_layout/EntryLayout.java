package view.gui.layouts.treasure_layout;

import helper.Color;
import helper.ConsoleAssistent;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.gui.TreasureView;

public class EntryLayout extends HBox {
    private String playerImagePath;
    private TreasureView treasureView;
    private Image treasureImg;
    private ImageView treasureImgView;

    public EntryLayout(TreasureView treasureView, String playerImagePath) {
        this.treasureView = treasureView;
        this.playerImagePath = playerImagePath;
        setPickOnBounds(false);
        init();
    }

    public void init() {
        setTreasureImageView(false);
        initLeft();
        initRight();
    }

    private void initLeft() {
        VBox vPlayer = new VBox();
        HBox.setHgrow(vPlayer, Priority.ALWAYS);
        vPlayer.setAlignment(Pos.CENTER);
        vPlayer.setTranslateY(150);
        vPlayer.setTranslateX(150);
        vPlayer.getChildren().add(getPlayerImageView());
        getChildren().add(vPlayer);
    }

    private void initRight() {
        VBox vTreasure = new VBox();
        HBox.setHgrow(vTreasure, Priority.ALWAYS);
        vTreasure.setAlignment(Pos.CENTER);
        vTreasure.setTranslateY(50);
        vTreasure.setTranslateX(-150);

        this.treasureImgView.setOnMouseClicked(event -> {
            ConsoleAssistent.println(Color.YELLOW, "Clicked on Treasure");
            setTreasureImageView(true);
            onTreasureClick();
            this.treasureImgView.setDisable(true);
        });

        vTreasure.getChildren().add(this.treasureImgView);
        getChildren().add(vTreasure);
    }

    private ImageView getPlayerImageView() {
        Image image = new Image(getClass().getResource(this.playerImagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    private void setTreasureImageView(boolean open) {
        if (!open) {
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasure.png").toExternalForm());
        } else {
            ConsoleAssistent.println(Color.YELLOW, "Treasure Open");
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasureOpen.png").toExternalForm());
        }
        this.treasureImgView = new ImageView(this.treasureImg);
    }

    private void onTreasureClick(){
        this.treasureView.onTreasureClick();
        initRight();
    }
}
