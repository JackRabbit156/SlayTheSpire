package models.player;

import models.player.player_structure.Player;

public class Silent extends Player {
    public Silent() {
        super("Silent", 70, 3);
    }

    @Override
    protected void initDeck() {

    }
}
