package controller.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import models.map_elements.Node;
import models.map_elements.acts.Act;
import models.map_elements.acts.ActFour;
import models.map_elements.acts.ActOne;
import models.map_elements.acts.ActTwo;
import models.player.player_structure.Player;
import view.gui.MapView;
import view.gui.layouts.layout_events.MapViewEvents;

public class MapController implements MapViewEvents {
    private MapView mapView;
    private Player player;

    private Act act;

    public MapController (Player player, boolean loadingFromFile) {
        this.player = player;

        switch (player.getCurrentAct()){
            case 1: act = new ActOne(player, loadingFromFile); break;
           // case 2: act = new ActTwo(player, loadingFromFile); break; // TODO: Für GUI
            case 3:  break; // TODO: Act 3, Für GUI
            case 4: act = new ActFour(player, loadingFromFile); break;
            default:
                System.out.println("Weird"); return;
        }

        this.mapView = new MapView(player, act.getNodes(), act.getMapWidth(), act.getMapHeight(), this);

    }

    public MapView getMapView(){
        return mapView;
    }

    @Override
    public void onValidFieldClick(Player player, Node node) {
        //Stage primaryStage = player.getPrimaryStage();
        System.out.println("clicked on valid Field! " + node.getFieldName());
        node.doFieldThing(player);

        act.setBeatenNode(player, node);
        player.setCurrentField(getCurrentFieldFromAct());

      /*  this.mapView = new MapView(player, act.getNodes(), act.getMapWidth(), act.getMapHeight(), this);

        Scene scene = new Scene(mapView, 1920, 1080);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - Map");
        primaryStage.show();*/

    }

    private String getCurrentFieldFromAct(){
        return act.getCurrentField();
    }
}
