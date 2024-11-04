package models.map_elements.acts;

import models.map_elements.Node;
import models.player.player_structure.Player;

public class ActTwo extends Act {
    public ActTwo(Player player, boolean loadingFromFile){
        super(2);
        initNodes();

        Node playerNode = getNoteByName(player.getCurrentField());
        if(playerNode == null){
            System.out.println("ERROR");
            return;
        }

        playerNode.setPlayer(player);
        if(loadingFromFile)
            playerNode.setFieldBeaten();
    }

    private void initNodes(){

    }

    @Override
    public void doFieldThing() {

    }

    @Override
    public String[][] getRawMap() {
        return new String[0][];
    }
}
