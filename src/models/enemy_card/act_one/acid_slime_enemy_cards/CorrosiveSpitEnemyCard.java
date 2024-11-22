package models.enemy_card.act_one.acid_slime_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class CorrosiveSpitEnemyCard extends EnemyCard {
    public CorrosiveSpitEnemyCard() {
        super("Corrosive Spit", "Deals 11 damage, shuffles 2 Slimed into the discard pile.");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(11, false);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(11, false);
    }
}
