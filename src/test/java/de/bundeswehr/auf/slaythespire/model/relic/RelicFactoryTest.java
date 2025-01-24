package de.bundeswehr.auf.slaythespire.model.relic;

import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.common.*;
import de.bundeswehr.auf.slaythespire.model.relic.event.*;
import de.bundeswehr.auf.slaythespire.model.relic.ironclad.BurningBloodRelic;
import de.bundeswehr.auf.slaythespire.model.relic.rare.*;
import de.bundeswehr.auf.slaythespire.model.relic.shop.BrimstoneRelic;
import de.bundeswehr.auf.slaythespire.model.relic.shop.TwistedFunnelRelic;
import de.bundeswehr.auf.slaythespire.model.relic.silent.RingOfTheSnakeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.relic.uncommon.*;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

class RelicFactoryTest {

    private RelicFactory cut;

    @Mock
    private Player player;

    private final List<Class<? extends Relic>> bossRelics = Arrays.asList();
    private final List<Class<? extends Relic>> eventRelics = Arrays.asList(BloodyIdolRelic.class,
            CultistHeadpieceRelic.class, FaceOfClericRelic.class, GoldenIdolRelic.class, GremlinVisageRelic.class,
            MutagenicStrengthRelic.class, NeowsLamentRelic.class, RedMaskRelic.class);
    private final List<Class<? extends Relic>> lootRelics = Arrays.asList(AnchorRelic.class, BagOfMarblesRelic.class,
            BagOfPreparationRelic.class, BloodVialRelic.class, BronzeScalesRelic.class, CentennialPuzzleRelic.class,
            HappyFlowerRelic.class, LanternRelic.class, OrichalcumRelic.class, StrawberryRelic.class,
            ToyOrnithopterRelic.class, VajraRelic.class,
            BottledFlameRelic.class, HornCleatRelic.class, MeatOnTheBoneRelic.class, MercuryHourglassRelic.class,
            PearRelic.class,
            BirdFacedUrnRelic.class, CaptainsWheelRelic.class, DeadBranchRelic.class, MangoRelic.class,
            OldCoinRelic.class, StoneCalendarRelic.class, UnceasingTopRelic.class);
    private final List<Class<? extends Relic>> shopRelics = Arrays.asList(BrimstoneRelic.class);

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

    @Disabled
    @Test
    void generateRelicForBoss() {
        List<Class<? extends Relic>> expected = bossRelics;

        Class<? extends Relic> actual = cut.generateRelicForBoss().getClass();

        MatcherAssert.assertThat("Has no valid boss relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForBoss_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        for (int i = 0; i < bossRelics.size(); i++) {
            cut.generateRelicForBoss();
        }

        Class<? extends Relic> actual = cut.generateRelicForBoss().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Disabled
    @Test
    void generateRelicForBoss_NotOtherCharacter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList();

        Class<? extends Relic> actual = cut.generateRelicForBoss().getClass();

        MatcherAssert.assertThat("Has relics of other characters", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Disabled
    @Test
    void generateRelicForBoss_NotSpecial() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(CircletRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForBoss().getClass();

        MatcherAssert.assertThat("Has special relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForBoss_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForBoss().getClass();

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForEvent() {
        List<Class<? extends Relic>> expected = eventRelics;

        Class<? extends Relic> actual = cut.generateRelicForEvent().getClass();

        MatcherAssert.assertThat("Has no valid event relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForEvent_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        for (int i = 0; i < eventRelics.size(); i++) {
            cut.generateRelicForEvent();
        }

        Class<? extends Relic> actual = cut.generateRelicForEvent().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Disabled
    @Test
    void generateRelicForEvent_NotOtherCharacter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList();

        Class<? extends Relic> actual = cut.generateRelicForEvent().getClass();

        MatcherAssert.assertThat("Has relics of other characters", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForEvent_NotSpecial() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(CircletRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForEvent().getClass();

        MatcherAssert.assertThat("Has special relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForEvent_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForEvent().getClass();

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForLoot() {
        List<Class<? extends Relic>> expected = lootRelics;

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has no valid loot relic", expected, Matchers.hasItem(actual));
    }

    @Disabled
    @Test
    void generateRelicForLoot_NotOtherCharacter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList();

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has relics of other characters", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForLoot_NotSpecial() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(CircletRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has special relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForLoot_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        for (int i = 0; i < lootRelics.size(); i++) {
            cut.generateRelicForLoot();
        }

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Test
    void generateRelicForLoot_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForLoot().getClass();

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForShop() {
        List<Class<? extends Relic>> expected = shopRelics;

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has no valid shop relic", expected, Matchers.hasItem(actual));
    }

    @Test
    void generateRelicForShop_Empty() {
        Class<? extends Relic> expected = CircletRelic.class;
        for (int i = 0; i < shopRelics.size(); i++) {
            cut.generateRelicForShop();
        }

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has not the unending relic", actual, Matchers.equalTo(expected));
    }

    @Test
    void generateRelicForShop_NotOtherCharacter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(TwistedFunnelRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has relics of other characters", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForShop_Silent_NotOtherCharacter() {
        Player player = Mockito.mock(Player.class);
        cut = new RelicFactory(player);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.SILENT);
        List<Class<? extends Relic>> unexpected = Arrays.asList(BrimstoneRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has relics of other characters", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForShop_NotSpecial() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(CircletRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has special relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
    }

    @Test
    void generateRelicForShop_NotStarter() {
        List<Class<? extends Relic>> unexpected = Arrays.asList(BurningBloodRelic.class, RingOfTheSnakeRelic.class);

        Class<? extends Relic> actual = cut.generateRelicForShop().getClass();

        MatcherAssert.assertThat("Has starter relics", unexpected, Matchers.hasItem(Matchers.not(actual)));
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

        initialize();

        cut = new RelicFactory(player);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.IRONCLAD);
    }

    private void initialize() {
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

}