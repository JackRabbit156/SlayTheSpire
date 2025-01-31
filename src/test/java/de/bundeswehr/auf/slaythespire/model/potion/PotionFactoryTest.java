package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.helper.TestHelper;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.potion.uncommon.DistilledChaosPotion;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PotionFactoryTest {

    @Test
    void copy() {
        Class<? extends Potion> expected = DistilledChaosPotion.class;
        Potion potion = new DistilledChaosPotion();

        Class<? extends Potion> actual = PotionFactory.copy(potion).getClass();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void copy_NotEqual() {
        Potion unexpected = new DistilledChaosPotion();

        Potion actual = PotionFactory.copy(unexpected);

        Assertions.assertNotEquals(unexpected, actual);
    }

    @Test
    void generatePotion() {
        Class<? extends Potion>[] expected = TestHelper.toArray(Model.loadPotionClasses(""));

        Class<? extends Potion> actual = PotionFactory.generatePotion().getClass();

        MatcherAssert.assertThat("Not obtainable potion", actual, Matchers.oneOf(expected));
    }

    @Test
    void potionFor() {
        Class<? extends Potion> expected = DistilledChaosPotion.class;

        Class<? extends Potion> actual = PotionFactory.potionFor("DistilledChaosPotion").getClass();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void potionFor_Empty() {
        Potion actual = PotionFactory.potionFor("");

        MatcherAssert.assertThat("Empty String should yield in no class", actual, Matchers.nullValue());
    }

}