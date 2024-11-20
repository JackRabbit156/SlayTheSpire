package view.gui.layouts.map_view_layouts;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import models.map_elements.Node;
import view.gui.MapView;
import view.gui.layouts.battle_view_layouts.MovingAnimation;

import java.util.ArrayList;
import java.util.List;

public class MapLayout extends GridPane {
    private Pane lineLayer;
    private MapView mapView;
    private int mapWidth;
    private int mapHeight;

    private static final int TILE_SIZE = 60;
    private static final int CENTER_OFFSET = 40;
    public MapLayout(MapView mapView, List<Node> nodes, int mapWidth, int mapHeight) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.mapView = mapView;
        lineLayer = new Pane();

        // Raster der GridPane setzen
        initMapGridPane();

        // Setze die Knoten und Linien
        setNodesOnMap(nodes);
        drawLines(nodes);

        // StackPane, um GridPane und Linien-Ebene übereinander zu legen
        StackPane stackPane = new StackPane();

        // Füge GridPane und Linie-Ebene zum StackPane hinzu
        stackPane.getChildren().addAll(lineLayer, this);
        mapView.getMainMap().setCenter(stackPane);
    }

    private void drawLines(List<Node> nodes) {
        for (Node currentNode : nodes) {
            if (currentNode.getRightNode() != null) {
                connectNodes(currentNode, currentNode.getRightNode());
            }
            if (currentNode.getLeftNode() != null) {
                connectNodes(currentNode, currentNode.getLeftNode());
            }
            if (currentNode.getMiddleNode() != null) {
                connectNodes(currentNode, currentNode.getMiddleNode());
            }
        }
    }

    private void connectNodes(Node fromNode, Node toNode) {
        // Berechne die Start- und Endposition im Grid für die Linien
        double startX = fromNode.getX() * TILE_SIZE + CENTER_OFFSET; // Beispiel: Zellenbreite 60, Offset 30 für Zentrierung
        double startY = fromNode.getY() * TILE_SIZE + CENTER_OFFSET;
        double endX = toNode.getX() * TILE_SIZE + CENTER_OFFSET;
        double endY = toNode.getY() * TILE_SIZE + CENTER_OFFSET;

        // Linie erstellen
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2.0);

        // Gestricheltes Muster für die Linie
        line.getStrokeDashArray().addAll(10.0, 5.0);

        // Füge die Linie zum lineLayer hinzu
        lineLayer.getChildren().add(line);
    }

    private void setNodesOnMap(List<Node> nodes) {
        List<Node> availablePosFromPlayer = new ArrayList<>();

        ImageView image = null;
        for(int i = 0; i< nodes.size(); i++){
            image = image(nodes.get(i).getSymbol());

            if(nodes.get(i).getPlayer() != null){
                // saving the next possible nodes of the player
                if (nodes.get(i).getRightNode() != null) {
                    availablePosFromPlayer.add(nodes.get(i).getRightNode());
                }
                if (nodes.get(i).getLeftNode() != null) {
                    availablePosFromPlayer.add(nodes.get(i).getLeftNode());
                }
                if (nodes.get(i).getMiddleNode() != null) {
                    availablePosFromPlayer.add(nodes.get(i).getMiddleNode());
                }

                ImageView playerImage =image("/images/map/player/ironclad.png");
                MovingAnimation movingAnimation = new MovingAnimation(playerImage);
                movingAnimation.start();
                this.add(playerImage, nodes.get(i).getX(), nodes.get(i).getY());
                continue;
            }

            for(int j = 0; j< availablePosFromPlayer.size(); j++){
                if(availablePosFromPlayer.get(j).getX() == nodes.get(i).getX() && availablePosFromPlayer.get(j).getY() == nodes.get(i).getY()) {
                    //setColorEffekt(image(availablePosFromPlayer.get(i).getSymbol()));
                    setColorEffekt(image);

                    int finalI = i;
                    image.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                        handleFieldClick(nodes.get(finalI)); //  das Klick-Event
                    });
                }
            }

            this.add(image, nodes.get(i).getX(), nodes.get(i).getY());
        }
    }

    private void handleFieldClick(Node node){
        mapView.clickedOnValidField(node);
    }

    private void setColorEffekt(ImageView imageView) {
        DropShadow glowPossibleField = new DropShadow();
        glowPossibleField.setColor(Color.CYAN);
        glowPossibleField.setHeight(30);
        glowPossibleField.setWidth(30);

        DropShadow glowHoveredField = new DropShadow();
        glowHoveredField.setColor(Color.RED);
        glowHoveredField.setHeight(30);
        glowHoveredField.setWidth(30);

        // Muss ein mal initialisiert sein, damit es ohne Interaktion schon leuchtet.
        imageView.setEffect(glowPossibleField);
        imageView.setScaleX(1.0); // Reset the width to original
        imageView.setScaleY(1.0); // Reset the height to original

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            imageView.setEffect(glowHoveredField);
            imageView.setScaleX(1.1); // Slightly increase the width
            imageView.setScaleY(1.1); // Slightly increase the height
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            imageView.setEffect(glowPossibleField);
            imageView.setScaleX(1.0); // Reset the width to original
            imageView.setScaleY(1.0); // Reset the height to original
        });
    }

    private void initMapGridPane() {
        setPadding(new Insets(10));

        for (int i = 0; i < mapWidth; i++) {
            ColumnConstraints colConst = new ColumnConstraints(TILE_SIZE);
            getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < mapHeight; i++) {
            RowConstraints rowConst = new RowConstraints(TILE_SIZE);
            getRowConstraints().add(rowConst);
        }
    }

    private ImageView image(String imagePath) {
        Image figureImage = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);
        imageViewFigure.setFitWidth(TILE_SIZE);
        imageViewFigure.setFitHeight(TILE_SIZE);
        imageViewFigure.setPreserveRatio(true);
        return imageViewFigure;
    }
}
