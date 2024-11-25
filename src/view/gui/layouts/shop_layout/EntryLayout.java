package view.gui.layouts.shop_layout;

import helper.Color;
import helper.ConsoleAssistent;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.gui.ShopView;
import view.gui.TreasureView;


public class EntryLayout extends HBox {
    private String playerImagePath;
    private ShopView parentView;
    private VBox vMerchant;
    private Image merchantImg;
    private ImageView merchantImgView;


    public EntryLayout(ShopView parentView, String playerImagePath) {
        this.parentView = parentView;
        this.playerImagePath = playerImagePath;
        init();
    }

    public void init() {
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
        this.vMerchant = new VBox();
        HBox.setHgrow(this.vMerchant, Priority.ALWAYS);
        this.vMerchant.setAlignment(Pos.CENTER);
        this.vMerchant.setTranslateY(50);
        this.vMerchant.setTranslateX(-150);

        this.merchantImgView.setOnMouseClicked(event -> {
            ConsoleAssistent.println(Color.YELLOW, "Clicked on Merchant");
            setTreasureImageView(true);
//            onMerchantClick();
            this.merchantImgView.setDisable(true);
        });
        this.vMerchant.getChildren().add(this.merchantImgView);
        getChildren().add(this.vMerchant);
    }

    private ImageView getPlayerImageView() {
        Image image = new Image(getClass().getResource(this.playerImagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    private void setTreasureImageView(boolean open) {
        if (!open) {
            this.merchantImg = new Image(getClass().getResource("/images/treasure/treasure.png").toExternalForm());
        } else {
            ConsoleAssistent.println(Color.YELLOW, "Treasure Open");
            this.merchantImg = new Image(getClass().getResource("/images/treasure/treasureOpen.png").toExternalForm());
        }
        this.merchantImgView = new ImageView(this.merchantImg);
    }


    private void onMerchantClick() {
        init();
    }
}
