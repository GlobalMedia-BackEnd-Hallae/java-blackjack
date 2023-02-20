package gmbs.dto;

import gmbs.model.Card;
import gmbs.model.players.Player;

import java.util.List;
import java.util.stream.Collectors;

public class DealerDTO implements PlayerDTO {

    private static final int FIRST_CARD_INDEX = 0;
    private final String cards;
    private final String name;

    private DealerDTO(final List<Card> cards, final String name) {
        this.cards = cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining());
        this.name = name;
    }

    public static DealerDTO oneCardOf(final Player player) {
        Card firstCard = player.getCardHand()
                .getCards()
                .get(FIRST_CARD_INDEX);
        return new DealerDTO(List.of(firstCard), player.getName());
    }

    public static DealerDTO allCardOf(final Player player) {
        List<Card> allCards = player.getCardHand()
                .getCards();
        return new DealerDTO(allCards, player.getName());
    }

    @Override
    public String getCards() {
        return cards;
    }

    @Override
    public String getName() {
        return name;
    }
}
