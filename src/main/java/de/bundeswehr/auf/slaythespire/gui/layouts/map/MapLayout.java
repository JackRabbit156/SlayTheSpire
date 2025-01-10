package de.bundeswehr.auf.slaythespire.gui.layouts.map;

import de.bundeswehr.auf.slaythespire.gui.MapView;
import de.bundeswehr.auf.slaythespire.gui.View;
import de.bundeswehr.auf.slaythespire.gui.components.animation.MovingAnimation;
import de.bundeswehr.auf.slaythespire.model.map.Node;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse 'MapLayout' ist eine benutzerdefinierte Layout-Klasse für die Anzeige der Karte.
 * Sie erweitert die 'GridPane'-Klasse und stellt eine Rasterdarstellung von Knoten auf der Karte dar,
 * einschließlich Linien zwischen den Knoten und grafischen Darstellungen für Spieler und verfügbare Felder.
 *
 * <p>
 * Die Klasse kümmert sich um das Zeichnen der Knoten und deren Verbindungen, das Verarbeiten von
 * Mausklickereignissen auf den Knoten und das Setzen visueller Effekte auf die Knoten.
 * </p>
 *
 * @author Warawa Alexander
 */
public class MapLayout extends StackPane implements View {

    private static final int CENTER_OFFSET = 39;
    private static final int TILE_SIZE = 58;

    private MovingAnimation animation;
    private final Pane lineLayer;
    private final GridPane gridLayer;
    private final int mapHeight;
    private final MapView mapView;
    private final int mapWidth;
    private final int playerField;

    /**
     * Konstruktor für die Klasse 'MapLayout'.
     *
     * @param mapView     Die MapView, die zur Anzeige der Karte verwendet wird
     * @param nodes       Eine Liste von Knoten, die auf der Karte angezeigt werden
     * @param mapWidth    Die Breite der Karte in Kacheln
     * @param mapHeight   Die Höhe der Karte in Kacheln
     * @param playerField Das Feld des Spielers
     */

    public MapLayout(MapView mapView, List<Node> nodes, int mapWidth, int mapHeight, int playerField) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.mapView = mapView;
        this.playerField = playerField;
        lineLayer = new Pane();
        gridLayer = new GridPane();
        // Raster der GridPane setzen
        initMapGridPane();
        // Setze die Knoten und Linien
        setNodesOnMap(nodes);
        drawLines(nodes);

        getChildren().addAll(lineLayer, gridLayer);
    }

    @Override
    public void discard() {
        if (animation != null) {
            animation.stop();
        }
    }

    /**
     * Erstellt eine Linie zwischen zwei Knoten und fügt sie der Linien-Ebene hinzu.
     *
     * @param fromNode Der Ausgangsknoten
     * @param toNode   Der Zielknoten
     */
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

    /**
     * Zeichnet die Linien zwischen den Knoten auf der Karte.
     *
     * @param nodes Die Liste der Knoten, zwischen denen die Linien gezeichnet werden
     */
    private void drawLines(List<Node> nodes) {
        for (Node currentNode : nodes) {
            if (currentNode.getLeftNode() != null) {
                connectNodes(currentNode, currentNode.getLeftNode());
            }
            if (currentNode.getMiddleNode() != null) {
                connectNodes(currentNode, currentNode.getMiddleNode());
            }
            if (currentNode.getRightNode() != null) {
                connectNodes(currentNode, currentNode.getRightNode());
            }
        }
    }

    private void handleFieldClick(Node node) {
        mapView.clickedOnValidField(node);
    }

    private ImageView image(String imagePath) {
        Image figureImage = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);
        imageViewFigure.setFitWidth(TILE_SIZE);
        imageViewFigure.setFitHeight(TILE_SIZE);
        imageViewFigure.setPreserveRatio(true);
        return imageViewFigure;
    }

    private void initMapGridPane() {
        gridLayer.setPadding(new Insets(10));
        for (int i = 0; i < mapWidth; i++) {
            ColumnConstraints colConst = new ColumnConstraints(TILE_SIZE);
            gridLayer.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < mapHeight; i++) {
            RowConstraints rowConst = new RowConstraints(TILE_SIZE);
            gridLayer.getRowConstraints().add(rowConst);
        }
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

    /**
     * Fügt die Knoten zur Karte hinzu und behandelt deren Status.
     *
     * @param nodes Die Liste von Knoten, die auf der Karte angezeigt werden sollen
     */
    private void setNodesOnMap(List<Node> nodes) {
        List<Node> availablePosFromPlayer = new ArrayList<>();

        ImageView image;
        for (int i = 0; i < nodes.size(); i++) {
            image = image(nodes.get(i).getImagePath());

            if (nodes.get(i).getPlayer() != null) {
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

                ImageView playerImage = image("/images/map/player/ironclad.png");
                animation = new MovingAnimation(playerImage);
                animation.start();
                gridLayer.add(playerImage, nodes.get(i).getX(), nodes.get(i).getY());
                continue;
            }
            else if (Integer.parseInt(nodes.get(0).getFieldName()) > playerField && availablePosFromPlayer.size() < 1) {
                availablePosFromPlayer.add(nodes.get(0));
            }
            for (Node node : availablePosFromPlayer) {
                if (node.getX() == nodes.get(i).getX() && node.getY() == nodes.get(i).getY()) {
                    setColorEffekt(image);
                    int finalI = i;
                    image.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleFieldClick(nodes.get(finalI)));
                }
            }
            gridLayer.add(image, nodes.get(i).getX(), nodes.get(i).getY());
        }
    }

}
