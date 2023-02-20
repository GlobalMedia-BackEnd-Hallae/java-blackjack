package gmbs.dto;

import gmbs.model.vo.Card;
import gmbs.model.players.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDTO {

    private static final int FIRST_CARD_INDEX = 0;
    private final String cards;
    private final String name;

    private PlayerDTO(final List<Card> cards, final String name) {
        this.cards = cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining());
        this.name = name;
    }

    public static PlayerDTO oneCardOf(final Player player) {
        Card firstCard = player.getCardHand()
                .getCards()
                .get(FIRST_CARD_INDEX);
        return new PlayerDTO(List.of(firstCard), player.getName());
    }

    public static PlayerDTO allCardOf(final Player player) {
        List<Card> allCards = player.getCardHand()
                .getCards();
        return new PlayerDTO(allCards, player.getName());
    }

    public String getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}
