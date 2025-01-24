package de.bundeswehr.auf.slaythespire.model.relic;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Die RelicFactory-Klasse ist verantwortlich für die Erstellung der Relics eines Spielers.
 *
 * @author L Frank Rieger
 */
public class RelicFactory {

    private static class SpecialRelic implements Predicate<Class<? extends Relic>> {

        @Override
        public boolean test(Class<? extends Relic> relic) {
            return SpecialTypeRelic.class.isAssignableFrom(relic);
        }

    }

    private static class StarterRelic implements Predicate<Class<? extends Relic>> {

        @Override
        public boolean test(Class<? extends Relic> relic) {
            return StarterTypeRelic.class.isAssignableFrom(relic);
        }

    }

    private static class ValidBossRelic implements Predicate<Class<? extends Relic>> {

        @Override
        public boolean test(Class<? extends Relic> relic) {
            return BossTypeRelic.class.isAssignableFrom(relic);
        }

    }

    private static class ValidEventRelic implements Predicate<Class<? extends Relic>> {

        @Override
        public boolean test(Class<? extends Relic> relic) {
            return EventTypeRelic.class.isAssignableFrom(relic);
        }

    }

    private class ValidLootRelic implements Predicate<Class<? extends Relic>> {

        private final ValidBossRelic validBossRelic = new ValidBossRelic();
        private final ValidEventRelic validEventRelic = new ValidEventRelic();
        private final ValidPlayer validPlayer = new ValidPlayer();
        private final ValidShopRelic validShopRelic = new ValidShopRelic();

        @Override
        public boolean test(Class<? extends Relic> relic) {
            return validPlayer.test(relic) && !validShopRelic.test(relic) &&
                    !validBossRelic.test(relic) && !validEventRelic.test(relic);
        }

    }

    private class ValidPlayer implements Predicate<Class<? extends Relic>> {

        @Override
        public boolean test(Class<? extends Relic> relic) {
            return !PlayerTypeRelic.class.isAssignableFrom(relic) ||
                    (player.getPlayerType() == ((PlayerTypeRelic) relicFor(relic.getSimpleName())).getPlayerType() &&
                            RelicRarity.SHOP != relicFor(relic.getSimpleName()).getRarity());
        }

    }

    private class ValidShopRelic implements Predicate<Class<? extends Relic>> {

        @Override
        public boolean test(Class<? extends Relic> relic) {
            return ShopTypeRelic.class.isAssignableFrom(relic) ||
                    (PlayerTypeRelic.class.isAssignableFrom(relic) &&
                            player.getPlayerType() == ((PlayerTypeRelic) relicFor(relic.getSimpleName())).getPlayerType() &&
                            RelicRarity.SHOP == relicFor(relic.getSimpleName()).getRarity());
        }

    }
    private static final List<Class<? extends Relic>> availableRelics = Model.relics();
    private static final Random rnd = new Random();
    private static final SpecialRelic special = new SpecialRelic();
    private static final StarterRelic starter = new StarterRelic();

    private final Player player;

    public static void add(Class<? extends Relic> relicClass) {
        availableRelics.add(relicClass);
    }

    public static Relic copy(Relic relic) {
        return relicFor(relic.getClass().getSimpleName());
    }

    public static Relic relicFor(String relicName) {
        Relic relic = null;
        try {
            relic = Model.ofRelics(relicName);
        } catch (IllegalArgumentException e) {
            LoggingAssistant.log("Error in DeckFactory creating Relic: " + relicName, Color.RED);
        }
        return relic;
    }

    public static boolean remove(Class<? extends Relic> relicClass) {
        return availableRelics.remove(relicClass);
    }

    /**
     * Relics können für den gegebenen Spieler gefiltert werden.
     *
     * @param player Der Spieler, dessen Relics erstellt werden soll.
     */
    public RelicFactory(Player player) {
        this.player = player;
    }

    public Relic generateRelicForBoss() {
        return generateRelic(new ValidBossRelic());
    }

    public Relic generateRelicForEvent() {
        return generateRelic(new ValidEventRelic());
    }

    public Relic generateRelicForLoot() {
        return generateRelic(new ValidLootRelic());
    }

    public Relic generateRelicForShop() {
        return generateRelic(new ValidShopRelic());
    }

    private Relic generateRelic(Predicate<Class<? extends Relic>> predicate) {
        List<Class<? extends Relic>> availableRelics = new ArrayList<>(RelicFactory.availableRelics);
        availableRelics.removeIf(special);
        availableRelics.removeIf(starter);
        availableRelics.removeIf(predicate.negate());
        if (availableRelics.isEmpty()) {
            return new CircletRelic();
        }
        int randomNumber = rnd.nextInt(availableRelics.size());
        try {
            Class<? extends Relic> relicClass = availableRelics.get(randomNumber);
            Relic relic = relicClass.getDeclaredConstructor().newInstance();
            RelicFactory.availableRelics.remove(relicClass);
            return relic;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LoggingAssistant.log(e, Color.RED);
            return null;
        }
    }

}
