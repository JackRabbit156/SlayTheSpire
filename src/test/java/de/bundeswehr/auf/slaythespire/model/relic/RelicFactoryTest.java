package de.bundeswehr.auf.slaythespire.model.relic;

import de.bundeswehr.auf.slaythespire.helper.TestHelper;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.ironclad.BurningBloodRelic;
import de.bundeswehr.auf.slaythespire.model.relic.silent.RingOfTheSnakeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.*;

class RelicFactoryTest {

    private RelicFactory cut;

    @Mock
    private Player player;

    @Test
    void copy() {
        Class<? extends Relic> expected = CircletRelic.class;
        Relic relic = new CircletRelic();

        Class<? extends Relic> actual = RelicFactory.copy(relic).getClass();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void copy_NotEqual() {
        Relic unexpected = new CircletRelic();

        Relic actual = RelicFactory.copy(unexpected);

        Assertions.assertNotEquals(unexpected, actual);
    }

    @Test
    void generateRelicForBoss() {
        Set<Class<? extends Relic>> expected = Model.loadRelicClasses(".boss");

        Class<? extends Relic> actual = cut.generateRelicForBoss().getClass();

        MatcherAssert.assertThat("Has no valid boss relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForBoss_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        int size = Model.loadRelicClasses(".boss").size();
        for (int i = 0; i < size; i++) {
            cut.generateRelicForBoss();
        }

        Class<? extends Relic> actual = cut.generateRelicForBoss().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Test
    void generateRelicForBoss_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".boss.silent"));
        int size = Model.loadRelicClasses(".boss").size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForBoss().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void generateRelicForBoss_NotSpecial() {
        Class<? extends Relic> unexpected = CircletRelic.class;
        int size = Model.loadRelicClasses(".boss").size() - Model.loadRelicClasses(".boss.silent").size();
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForBoss().getClass());
        }

        MatcherAssert.assertThat("Has special relics", actual, Matchers.not(Matchers.hasItem(unexpected)));
    }

    @Test
    void generateRelicForBoss_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);
        int size = Model.loadRelicClasses(".boss").size() - Model.loadRelicClasses(".boss.silent").size();
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForBoss().getClass());
        }

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForBoss_Silent_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".boss.ironclad"));
        Player player = Mockito.mock(Player.class);
        cut = new RelicFactory(player);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.SILENT);
        int size = Model.loadRelicClasses(".boss").size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForBoss().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void generateRelicForEvent() {
        Set<Class<? extends Relic>> expected = Model.loadRelicClasses(".event");

        Class<? extends Relic> actual = cut.generateRelicForEvent().getClass();

        MatcherAssert.assertThat("Has no valid event relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForEvent_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        int size = Model.loadRelicClasses(".event").size();
        for (int i = 0; i < size; i++) {
            cut.generateRelicForEvent();
        }

        Class<? extends Relic> actual = cut.generateRelicForEvent().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Test
    void generateRelicForEvent_NotSpecial() {
        Class<? extends Relic> unexpected = CircletRelic.class;
        int size = Model.loadRelicClasses(".event").size();
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForEvent().getClass());
        }

        MatcherAssert.assertThat("Has special relics", actual, Matchers.not(Matchers.hasItem(unexpected)));
    }

    @Test
    void generateRelicForEvent_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);
        int size = Model.loadRelicClasses(".event").size();
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForEvent().getClass());
        }

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForLoot() {
        Set<Class<? extends Relic>> expected = lootRelics();

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has no valid loot relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForLoot_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        double size = lootRelics().size();
        for (int i = 0; i < size; i++) {
            cut.generateRelicForLoot();
        }

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Test
    void generateRelicForLoot_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".boss.silent"));
        int size = lootRelics().size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForLoot().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void generateRelicForLoot_NotSpecial() {
        Class<? extends Relic> unexpected = CircletRelic.class;
        int size = lootRelics().size();
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForLoot().getClass());
        }

        MatcherAssert.assertThat("Has special relics", actual, Matchers.not(Matchers.hasItem(unexpected)));
    }

    @Test
    void generateRelicForLoot_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForLoot_Silent_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".boss.ironclad"));
        Player player = Mockito.mock(Player.class);
        cut = new RelicFactory(player);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.SILENT);
        Set<Class<? extends Relic>> lootRelics = new HashSet<>();
        lootRelics.addAll(Model.loadRelicClasses(".common"));
        lootRelics.addAll(Model.loadRelicClasses(".uncommon"));
        lootRelics.addAll(Model.loadRelicClasses(".rare"));
        lootRelics.addAll(Model.loadRelicClasses(".boss"));
        lootRelics.removeAll(Model.loadRelicClasses(".boss.ironclad"));
        lootRelics.addAll(Model.loadRelicClasses(".silent"));
        lootRelics.remove(RingOfTheSnakeRelic.class);
        int size = lootRelics.size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForLoot().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void generateRelicForShop() {
        Set<Class<? extends Relic>> expected = Model.loadRelicClasses(".shop");

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has no valid shop relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForShop_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        int size = Model.loadRelicClasses(".shop").size();
        for (int i = 0; i < size; i++) {
            cut.generateRelicForShop();
        }

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Test
    void generateRelicForShop_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".shop.silent"));
        int size = Model.loadRelicClasses(".shop").size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForShop().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void generateRelicForShop_NotSpecial() {
        Class<? extends Relic> unexpected = CircletRelic.class;
        int size = Model.loadRelicClasses(".shop").size() - Model.loadRelicClasses(".shop.silent").size();
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForShop().getClass());
        }

        MatcherAssert.assertThat("Has special relics", actual, Matchers.not(Matchers.hasItem(unexpected)));
    }

    @Test
    void generateRelicForShop_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForShop_Silent_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".shop.ironclad"));
        Player player = Mockito.mock(Player.class);
        cut = new RelicFactory(player);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.SILENT);
        int size = Model.loadRelicClasses(".shop").size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForShop().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void generateRelicForTreasure() {
        Set<Class<? extends Relic>> expected = treasureRelics();

        Class<? extends Relic> actual = cut.generateRelicForTreasure().getClass();

        MatcherAssert.assertThat("Has no valid treasure relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForTreasure_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        double size = treasureRelics().size();
        for (int i = 0; i < size; i++) {
            cut.generateRelicForTreasure();
        }

        Class<? extends Relic> actual = cut.generateRelicForTreasure().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Test
    void generateRelicForTreasure_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".silent"));
        int size = treasureRelics().size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForTreasure().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void generateRelicForTreasure_NotSpecial() {
        Class<? extends Relic> unexpected = CircletRelic.class;
        int size = treasureRelics().size();
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForTreasure().getClass());
        }

        MatcherAssert.assertThat("Has special relics", actual, Matchers.not(Matchers.hasItem(unexpected)));
    }

    @Test
    void generateRelicForTreasure_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForTreasure().getClass();

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForTreasure_Silent_NotOtherCharacter() {
        Class<? extends Relic>[] unexpected = TestHelper.toArray(Model.loadRelicClasses(".ironclad"));
        Player player = Mockito.mock(Player.class);
        cut = new RelicFactory(player);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.SILENT);
        Set<Class<? extends Relic>> treasureRelics = new HashSet<>();
        treasureRelics.addAll(Model.loadRelicClasses(".common"));
        treasureRelics.addAll(Model.loadRelicClasses(".uncommon"));
        treasureRelics.addAll(Model.loadRelicClasses(".rare"));
        treasureRelics.addAll(Model.loadRelicClasses(".silent"));
        treasureRelics.remove(RingOfTheSnakeRelic.class);
        int size = treasureRelics.size() - 1;
        Set<Class<? extends Relic>> actual = new HashSet<>();

        for (int i = 0; i < size; i++) {
            actual.add(cut.generateRelicForTreasure().getClass());
        }

        MatcherAssert.assertThat("Has relics of other characters", actual, Matchers.not(Matchers.hasItems(unexpected)));
    }

    @Test
    void relicFor() {
        Class<? extends Relic> expected = CircletRelic.class;

        Class<? extends Relic> actual = RelicFactory.relicFor("CircletRelic").getClass();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void relicFor_Empty() {
        Relic actual = RelicFactory.relicFor("");

        MatcherAssert.assertThat("Empty String should yield in no class", actual, Matchers.nullValue());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reinitialize();

        cut = new RelicFactory(player);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.IRONCLAD);
    }

    private Set<Class<? extends Relic>> lootRelics() {
        Set<Class<? extends Relic>> lootRelics = new HashSet<>();
        lootRelics.addAll(Model.loadRelicClasses(".common"));
        lootRelics.addAll(Model.loadRelicClasses(".uncommon"));
        lootRelics.addAll(Model.loadRelicClasses(".rare"));
        lootRelics.addAll(Model.loadRelicClasses(".boss"));
        lootRelics.removeAll(Model.loadRelicClasses(".boss.silent"));
        lootRelics.addAll(Model.loadRelicClasses(".ironclad"));
        lootRelics.remove(BurningBloodRelic.class);
        return lootRelics;
    }

    private void reinitialize() {
        try {
            Field field = RelicFactory.class.getDeclaredField("availableRelics");
            field.setAccessible(true);
            List<Class<? extends Relic>> availableRelics = (List<Class<? extends Relic>>) field.get(RelicFactory.class);
            availableRelics.clear();
            availableRelics.addAll(Model.relics());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Set<Class<? extends Relic>> treasureRelics() {
        Set<Class<? extends Relic>> treasureRelics = new HashSet<>();
        treasureRelics.addAll(Model.loadRelicClasses(".common"));
        treasureRelics.addAll(Model.loadRelicClasses(".uncommon"));
        treasureRelics.addAll(Model.loadRelicClasses(".rare"));
        treasureRelics.addAll(Model.loadRelicClasses(".ironclad"));
        treasureRelics.remove(BurningBloodRelic.class);
        return treasureRelics;
    }

}