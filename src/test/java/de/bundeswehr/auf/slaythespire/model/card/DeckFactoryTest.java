package de.bundeswehr.auf.slaythespire.model.card;

import de.bundeswehr.auf.slaythespire.helper.AnyOf;
import de.bundeswehr.auf.slaythespire.helper.TestHelper;
import de.bundeswehr.auf.slaythespire.model.Model;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.starter.IroncladDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.starter.IroncladStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

class DeckFactoryTest {

    private DeckFactory cut;

    @Mock
    private Player player;

    @Test
    void cardFor() {
        Class<? extends Card> expected = IroncladStrikeCard.class;

        Class<? extends Card> actual = DeckFactory.cardFor("IroncladStrikeCard").getClass();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void cardFor_Empty() {
        Card actual = DeckFactory.cardFor("");

        MatcherAssert.assertThat("Empty String should yield in no class", actual, Matchers.nullValue());
    }

    @Test
    void copyCard() {
        Class<? extends Card> expected = IroncladStrikeCard.class;
        Card card = new IroncladStrikeCard();

        Class<? extends Card> actual = DeckFactory.copy(card).getClass();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void copyCard_NotEqual() {
        Card unexpected = new IroncladStrikeCard();

        Card actual = DeckFactory.copy(unexpected);

        Assertions.assertNotEquals(unexpected, actual);
    }

    @Test
    void copyListOfCard() {
        List<Class<? extends Card>> expected = Collections.singletonList(IroncladStrikeCard.class);
        List<Card> cards = Collections.singletonList(new IroncladStrikeCard());

        List<Card> actualCards = DeckFactory.copy(cards);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : actualCards) {
            actual.add(card.getClass());
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void copyListOfCard_Empty() {
        List<Class<? extends Card>> expected = new ArrayList<>();

        List<Card> actualCards = DeckFactory.copy(new ArrayList<>());

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : actualCards) {
            actual.add(card.getClass());
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void copyListOfCard_Multiple() {
        Class<? extends Card>[] expected = new Class[]{IroncladStrikeCard.class, IroncladDefendCard.class};
        List<Card> cards = Arrays.asList(new IroncladDefendCard(), new IroncladStrikeCard());

        List<Card> actualCards = DeckFactory.copy(cards);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : actualCards) {
            actual.add(card.getClass());
        }
        MatcherAssert.assertThat("ES sind nicht alle Karten kopiert worden", actual, Matchers.containsInAnyOrder(expected));
    }

    @Test
    void copyListOfCard_MultipleOfTheSameType() {
        Class<? extends Card>[] expected = new Class[]{IroncladStrikeCard.class, IroncladStrikeCard.class};
        List<Card> cards = Arrays.asList(new IroncladStrikeCard(), new IroncladStrikeCard());

        List<Card> actualCards = DeckFactory.copy(cards);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : actualCards) {
            actual.add(card.getClass());
        }
        MatcherAssert.assertThat("ES sind nicht alle Karten kopiert worden", actual, Matchers.containsInAnyOrder(expected));
    }

    @Test
    void copyListOfCard_NotEqual() {
        List<Card> unexpected = new ArrayList<>();
        unexpected.add(new IroncladStrikeCard());

        List<Card> actual = DeckFactory.copy(unexpected);

        Assertions.assertNotEquals(unexpected, actual);
    }

    @Test
    void copyListOfCard_NotEqualMultiple() {
        List<Card> unexpected = Arrays.asList(new IroncladStrikeCard(), new IroncladDefendCard());

        List<Card> actual = DeckFactory.copy(unexpected);

        Assertions.assertNotEquals(unexpected, actual);
    }

    @Test
    void init() {
        int expected = 40;

        List<Card> cards = cut.init(false);

        int actual = cards.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void init_NoDuplicates() {
        boolean expected = false;
        cut = new DeckFactory(player, 400);

        List<Card> cards = cut.init(false);

        List<Class<? extends Card>> actualClasses = new ArrayList<>();
        boolean actual = false;
        for (Card card : cards) {
            if (actualClasses.contains(card.getClass())) {
                actual = true;
            }
            else {
                actualClasses.add(card.getClass());
            }
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void init_NotOtherCharacter() {
        Class<? extends Card>[] unexpected = TestHelper.toArray(Model.loadCardClasses(".silent"));

        List<Card> cards = cut.init(false);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : cards) {
            actual.add(card.getClass());
        }
        MatcherAssert.assertThat("Cards of wrong character were created.", actual, Matchers.not(AnyOf.anyOf(unexpected)));
    }

    @Test
    void init_TrueNotOtherCharacter() {
        Class<? extends Card>[] unexpected = TestHelper.toArray(Model.loadCardClasses(".silent"));

        List<Card> cards = cut.init(true);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : cards) {
            actual.add(card.getClass());
        }
        MatcherAssert.assertThat("Cards of wrong character were created.", actual, Matchers.not(AnyOf.anyOf(unexpected)));
    }

    @Test
    void init_Silent_NotOtherCharacter() {
        Class<? extends Card>[] unexpected = TestHelper.toArray(Model.loadCardClasses(".ironclad"));
        Player player = Mockito.mock(Player.class);
        cut = new DeckFactory(player, 40);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.SILENT);

        List<Card> cards = cut.init(false);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : cards) {
            actual.add(card.getClass());
        }
        MatcherAssert.assertThat("Cards of wrong character were created.", actual, Matchers.not(AnyOf.anyOf(unexpected)));
    }

    @Test
    void init_Silent_SizeBiggerThanIndividualCards() {
        Collection<Class<? extends Card>> expected = possibleCardsSilent();
        Player player = Mockito.mock(Player.class);
        int expectedSize = expected.size();
        cut = new DeckFactory(player, expectedSize + 1);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.SILENT);

        List<Card> cards = cut.init(false);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : cards) {
            actual.add(card.getClass());
        }
        int actualSize = cards.size();
        System.out.println(actualSize + "/" + expectedSize);
        MatcherAssert.assertThat("Duplicates were generated.", actual, Matchers.containsInAnyOrder(TestHelper.toArray(expected)));
        MatcherAssert.assertThat("Duplicates were generated.", actualSize, Matchers.equalTo(expectedSize));
    }

    @Test
    void init_SizeBiggerThanIndividualCards() {
        Class<? extends Card>[] expected = TestHelper.toArray(possibleCardsIronclad());
        int expectedSize = expected.length;
        cut = new DeckFactory(player, expectedSize + 1);

        List<Card> cards = cut.init(false);

        List<Class<? extends Card>> actual = new ArrayList<>();
        for (Card card : cards) {
            actual.add(card.getClass());
        }
        int actualSize = cards.size();
        System.out.println(actualSize + "/" + expectedSize);
        MatcherAssert.assertThat("Duplicates were generated.", actual, Matchers.containsInAnyOrder(expected));
        MatcherAssert.assertThat("Duplicates were generated.", actualSize, Matchers.equalTo(expectedSize));
    }

    @Test
    void init_True() {
        int expected = 40;
        cut = new DeckFactory(player, expected);

        List<Card> cards = cut.init(true);

        int actual = cards.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void init_TrueDuplicates() {
        boolean expected = true;
        cut = new DeckFactory(player, 400);

        List<Card> cards = cut.init(true);

        List<Class<? extends Card>> actualClasses = new ArrayList<>();
        boolean actual = false;
        for (Card card : cards) {
            if (actualClasses.contains(card.getClass())) {
                actual = true;
            }
            else {
                actualClasses.add(card.getClass());
            }
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void init_TrueSizeBiggerThanIndividualCards() {
        int expected = Model.loadCardClasses(".ironclad").size() + 1;
        cut = new DeckFactory(player, expected);

        List<Card> cards = cut.init(true);

        int actual = cards.size();
        MatcherAssert.assertThat("Generated not enough cards.", actual, Matchers.equalTo(expected));
    }

    @Test
    void removeRandomCard() {
        List<Card> deck = Collections.singletonList(new IroncladStrikeCard());
        Mockito.when(player.getDeck()).thenReturn(deck);

        DeckFactory.removeRandomCard(player);

        Mockito.verify(player).removeCardFromDeck(Mockito.any(Card.class));
    }

    @Test
    void removeRandomCard_Empty() {
        List<Card> deck = new ArrayList<>();
        Mockito.when(player.getDeck()).thenReturn(deck);

        DeckFactory.removeRandomCard(player);

        Mockito.verify(player, Mockito.never()).removeCardFromDeck(Mockito.any(Card.class));
    }

    @Test
    void removeRandomCard_Multiple() {
        List<Card> deck = Arrays.asList(new IroncladStrikeCard(), new IroncladDefendCard());
        Mockito.when(player.getDeck()).thenReturn(deck);

        DeckFactory.removeRandomCard(player);

        Mockito.verify(player).removeCardFromDeck(Mockito.any(Card.class));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cut = new DeckFactory(player, 40);
        Mockito.when(player.getPlayerType()).thenReturn(PlayerType.IRONCLAD);
    }

    private Collection<Class<? extends Card>> possibleCards() {
        Set<Class<? extends Card>> possibleCards = new HashSet<>();
        possibleCards.addAll(Model.loadCardClasses(".attack"));
        possibleCards.addAll(Model.loadCardClasses(".skill"));
        possibleCards.add(CheaterCard.class);
        possibleCards.add(CheaterDefendCard.class);
        possibleCards.add(CheaterEnergyCard.class);
        possibleCards.add(CheaterHealCard.class);
        return possibleCards;
    }

    private Collection<Class<? extends Card>> possibleCardsIronclad() {
        Collection<Class<? extends Card>> possibleCards = possibleCards();
        possibleCards.addAll(Model.loadCardClasses(".ironclad"));
        return possibleCards;
    }

    private Collection<Class<? extends Card>> possibleCardsSilent() {
        Collection<Class<? extends Card>> possibleCards = possibleCards();
        possibleCards.addAll(Model.loadCardClasses(".silent"));
        return possibleCards;
    }

}